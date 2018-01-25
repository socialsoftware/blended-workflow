package pt.ist.socialsoftware.blendedworkflow.designer.remote;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Association;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Attribute;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Constraint;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Entity;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Person;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Resource;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.DataModelInterface;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ProductDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.ResourceModelInterface;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWError;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.RepositoryException;

public class WriteBlendedWorkflowService {
	private static Logger logger = LoggerFactory.getLogger(WriteBlendedWorkflowService.class);

	private static WriteBlendedWorkflowService instance = null;

	public static WriteBlendedWorkflowService getInstance() {
		logger.debug("getInstance");
		if (instance == null) {
			instance = new WriteBlendedWorkflowService();
		}
		return instance;
	}

	// to be invoked by tests only
	public void deleteSpecification(String specId) {
		// logger.debug("deleteSpecification {}", specId);
		this.repository.deleteSpecification(specId);
	}

	private DataModelInterface repository = null;
	private ResourceModelInterface rmRepository = null;

	private WriteBlendedWorkflowService() {
		this.repository = DataModelInterface.getInstance();
		this.rmRepository = ResourceModelInterface.getInstance();
	}

	public BWNotification write(BWSpecification eBWSpecification) {
		String specId = eBWSpecification.getSpecification().getName().replaceAll("\\s+", "");
		// logger.debug("eBWSpecification: {}", specId);

		BWNotification notification = new BWNotification();

		try {
			this.repository.getSpecBySpecId(specId);
			this.repository.cleanBlendedWorkflowModel(specId);
		} catch (RepositoryException re) {
			// logger.debug("getSpec: {}", re.getMessage());
			try {
				this.repository.createSpec(new SpecDTO(specId, eBWSpecification.getSpecification().getName()));
			} catch (RepositoryException ree) {
				notification.addError(ree.getError());
				// logger.debug("createSpec: {}", re.getMessage());
				return notification;
			}
		}

		writeDataModel(eBWSpecification, specId, notification);
		
		// write resource model
		writeResourceModel(specId, eBWSpecification.getResourceSpecification(), notification);

		for (BWError error : notification.getError()) {
			System.out.println(error.getMessage());
		}

		try {
			this.repository.checkBlendedWorkflowModel(specId);
		} catch (RepositoryException re) {
			notification.addError(re.getError());
		}

		this.repository.printSpecificationModels(specId);

		if (!notification.hasErrors()) {
			this.repository.generateConditionModel(specId);

			this.repository.generateGoalModel(specId);

			this.repository.generateActivityModel(specId);
		}

		return notification;
	}

	private void writeDataModel(BWSpecification eBWSpecification, String specId, BWNotification notification) {
		
		Set<RuleDTO> rulesToCreate = new HashSet<>();

		createEntities(eBWSpecification, specId, notification, rulesToCreate);

		createAssociations(eBWSpecification, specId, notification);

		createRules(notification, rulesToCreate);

		checkDependencies(specId, notification);
	}

	private void checkDependencies(String specId, BWNotification notification) {
		Set<DependenceDTO> deps = this.repository.getDependencies(specId);
		for (DependenceDTO dep : deps) {
			// logger.debug("dependence extId:{}, path:{}, product:{}",
			// dep.getExtId(), dep.getPath(), dep.getProduct());
			try {
				this.repository.checkDependence(specId, dep.getExtId());
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				// logger.debug("Error: {}", re.getMessage());

				this.repository.deleteDependence(specId, dep.getExtId());
			}
		}
	}

	private void createEntities(BWSpecification eBWSpecification, String specId, BWNotification notification,
			Set<RuleDTO> rulesToCreate) {
		for (Entity eEnt : eBWSpecification.getDataSpecification().getEntities()) {
			String entityExtId = null;
			try {
				EntityDTO entityDTO = this.repository
						.createEntity(new EntityDTO(specId, eEnt.getName(), eEnt.isExists()));
				entityExtId = entityDTO.getExtId();
				// logger.debug("createdEntity: {}, {}, {}",
				// entityDTO.getExtId(), entityDTO.getName(),
				// entityDTO.getExists());
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				// logger.debug("createEntity: {}", re.getMessage());
				continue;
			}
			
			createEntityDependencies(specId, notification, eEnt);

			createAttributes(specId, notification, eEnt, entityExtId);

			createConstraints(specId, rulesToCreate, eEnt);

		}
	}

	private void createConstraints(String specId, Set<RuleDTO> rulesToCreate, Entity eEnt) {
		for (Constraint constraint : eEnt.getConstraint()) {
			ExpressionDTO expression = ExpressionDTO.buildExpressionDTO(specId, constraint.getConstraint());
			rulesToCreate.add(new RuleDTO(specId, eEnt.getName(), constraint.getName(), expression));
		}
	}

	private void createAttributes(String specId, BWNotification notification, Entity eEnt, String entityExtId) {
		for (EObject eObj : eEnt.getAttributes()) {
			if (eObj instanceof Attribute) {
				AttributeDTO attributeDTO;
				Attribute eAtt = (Attribute) eObj;
				try {
					attributeDTO = this.repository.createAttribute(
							new AttributeDTO(specId, ProductDTO.ProductType.ATTRIBUTE.name(), entityExtId,
									eEnt.getName(), eAtt.getName(), eAtt.getType(), eAtt.isMandatory()));
				} catch (RepositoryException re) {
					notification.addError(re.getError());
					// logger.debug("Error: {}", re.getMessage());
					continue;
				}

				createAttributeDependencies(specId, notification, attributeDTO, eAtt);
			}
		}
	}

	private void createRules(BWNotification notification, Set<RuleDTO> rulesToCreate) {
		for (RuleDTO ruleDTO : rulesToCreate) {
			try {
				this.repository.createRule(ruleDTO);
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				// logger.debug("Error: {}", re.getMessage());
			}
		}
	}

	private void createAssociations(BWSpecification eBWSpecification, String specId, BWNotification notification) {
		for (Association assoc : eBWSpecification.getDataSpecification().getAssociations()) {
			try {
				this.repository.createRelation(new RelationDTO(specId, assoc.getName(), assoc.getEntity1().getName(),
						assoc.getName1(), assoc.getCardinality1(), assoc.getEntity2().getName(), assoc.getName2(),
						assoc.getCardinality2()));
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				// logger.debug("Error: {}", re.getMessage());
			}
		}
	}

	private void createAttributeDependencies(String specId, BWNotification notification, AttributeDTO attributeDTO,
			Attribute eAtt) {
		for (String eDep : eAtt.getDependsOn()) {
			try {
				this.repository.createDependence(new DependenceDTO(specId,
						attributeDTO.getEntityName() + "." + attributeDTO.getName(), eDep));
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				// logger.debug("Error: {}", re.getMessage());
			}
		}
	}

	private void createEntityDependencies(String specId, BWNotification notification, Entity eEnt) {
		for (String eDep : eEnt.getDependsOn()) {
			try {
				this.repository.createDependence(new DependenceDTO(specId, eEnt.getName(), eDep));
			} catch (RepositoryException re) {
				notification.addError(re.getError());
				// logger.debug("Error: {}", re.getMessage());
			}
		}
	}
	
	private void writeResourceModel(String specId, ResourceSpecification spec, BWNotification notification) {
		System.out.println("[WriteRM] Begin writing resource model");
		
		List<Resource> resources = spec.getResources();
		
		for (Resource resource : resources) {
			String resourceType = resource.eClass().getName();
			
			switch (resourceType) {
				case "Person":
					System.out.println("[WriteRM] Writing a person");
					Person p = (Person) resource;
					PersonDTO person;
					if (p.getBody() != null) {
						person = new PersonDTO(specId, p.getName(), p.getBody().getText());
					} else {
						person = new PersonDTO(specId, p.getName());
					}
					rmRepository.createPerson(person, notification);
					break;
				default:
					notification.addError(new BWError("Invalid resource type", resourceType + " is not a valid resource type"));
					
			}
		}
		
		System.out.println("[WriteRM] Finish writing resource model");
	}
}
