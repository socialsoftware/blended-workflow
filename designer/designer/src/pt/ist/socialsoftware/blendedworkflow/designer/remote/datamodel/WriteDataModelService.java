package pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Association;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Attribute;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Constraint;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.DataSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Entity;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ProductDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.RepositoryException;


public class WriteDataModelService {
	private Logger logger = LoggerFactory.getLogger(WriteDataModelService.class);
	
	private DataModelInterface repository = null;
	
	public WriteDataModelService(DataModelInterface repository) {
		this.repository = repository;
	}

	public void writeDataModel(DataSpecification dataSpecification, String specId, BWNotification notification) {
		Set<RuleDTO> rulesToCreate = new HashSet<>();

		createEntities(dataSpecification.getEntities(), specId, notification, rulesToCreate);

		createAssociations(dataSpecification.getAssociations(), specId, notification);

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

	private void createEntities(List<Entity> entities, String specId, BWNotification notification,
			Set<RuleDTO> rulesToCreate) {
		for (Entity eEnt : entities) {
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

	private void createAssociations(List<Association> associations, String specId, BWNotification notification) {
		for (Association assoc : associations) {
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
}
