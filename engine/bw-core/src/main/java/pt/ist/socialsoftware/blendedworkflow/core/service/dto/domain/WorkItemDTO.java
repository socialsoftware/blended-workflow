package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class WorkItemDTO {
	private static Logger logger = LoggerFactory.getLogger(WorkItemDTO.class);

	/* TODO: TO DEFINE A LOG */
	private String name;
	private int timestamp;
	private String preArguments;
	private String postArguments;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getPreArguments() {
		return this.preArguments;
	}

	public void setPreArguments(String preArguments) {
		this.preArguments = preArguments;
	}

	public String getPostArguments() {
		return this.postArguments;
	}

	public void setPostArguments(String postArguments) {
		this.postArguments = postArguments;
	}
	/* TODO: TO DEFINE A LOG */

	private String specId;
	private String specName;
	private String workflowInstanceName;
	private Set<DefinitionGroupDto> definitionGroupSet;

	public WorkItemDTO() {
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return this.specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getWorkflowInstanceName() {
		return this.workflowInstanceName;
	}

	public void setWorkflowInstanceName(String workflowInstanceName) {
		this.workflowInstanceName = workflowInstanceName;
	}

	public Set<DefinitionGroupDto> getDefinitionGroupSet() {
		return this.definitionGroupSet;
	}

	public void setDefinitionGroupSet(Set<DefinitionGroupDto> definitionGroupSet) {
		this.definitionGroupSet = definitionGroupSet;
	}

	public void executeWorkItem(WorkflowInstance workflowInstance, WorkItem workItem) {
		Map<String, EntityInstance> newEntityInstances = new HashMap<>();
		for (DefinitionGroupInstanceDTO definitionGroupInstanceDTO : getDefinitionGroupSet().stream()
				.flatMap(dgs -> dgs.getDefinitionGroupInstanceSet().stream()).collect(Collectors.toSet())) {

			// create post work item argument for created entity instance
			EntityInstance entityInstance = null;
			Optional<ProductInstanceDTO> oProductInstanceDTO = definitionGroupInstanceDTO.getProductInstanceSet()
					.stream().filter(pi -> pi.getProduct().getProductType().equals(ProductType.ENTITY.name()))
					.findFirst();
			if (oProductInstanceDTO.isPresent()) {
				entityInstance = createEntityInstance(workflowInstance, oProductInstanceDTO.get());
				newEntityInstances.put(oProductInstanceDTO.get().getExternalId(), entityInstance);

				workItem.addPostWorkItemArgument(entityInstance,
						DefEntityCondition.getDefEntityCondition(entityInstance.getEntity()));

				// create post work item argument for attribute instances of
				// created
				// entity instance
				for (ProductInstanceDTO productInstanceDTO : definitionGroupInstanceDTO.getProductInstanceSet().stream()
						.filter(pi -> pi.getProduct().getProductType().equals(ProductType.ATTRIBUTE.name()))
						.collect(Collectors.toSet())) {
					AttributeInstance attributeInstance = createAttributeInstance(workflowInstance, entityInstance,
							productInstanceDTO);

					workItem.addPostWorkItemArgument(attributeInstance,
							DefAttributeCondition.getDefAttributeCondition(attributeInstance.getAttribute()));
				}
			}

			// create pre and post work item arguments for already created
			// entity instances
			for (EntityInstanceContextDto entityInstanceContextDTO : definitionGroupInstanceDTO
					.getEntityInstanceContextSet()) {
				EntityInstance entityInstanceContext = FenixFramework
						.getDomainObject(entityInstanceContextDTO.getEntityInstance().getExternalId());

				// create attribute instances in context
				for (ProductInstanceDTO productInstanceDTO : definitionGroupInstanceDTO.getProductInstanceSet().stream()
						.filter(pi -> pi.getProduct().getProductType().equals(ProductType.ATTRIBUTE.name()))
						.collect(Collectors.toSet())) {

					Attribute attribute = (Attribute) workflowInstance.getSpecification().getDataModel()
							.getTargetOfPath(productInstanceDTO.getPath());
					if (entityInstanceContext.getEntity() == attribute.getEntity()) {
						if (definitionGroupInstanceDTO.getEntityInstanceContextSet().size() > 1) {
							throw new BWException(BWErrorType.PRE_WORK_ITEM_ARGUMENT,
									"Attribute instance defined for more than an entity instance "
											+ attribute.getName());
						}

						AttributeInstance attributeInstance = createAttributeInstance(workflowInstance,
								entityInstanceContext, productInstanceDTO);

						workItem.addPostWorkItemArgument(attributeInstance,
								DefAttributeCondition.getDefAttributeCondition(attributeInstance.getAttribute()));
					}
				}

				// associate new entity instance with context
				if (entityInstance != null && entityInstance != entityInstanceContext) {
					MulCondition mulCondition = FenixFramework
							.getDomainObject(entityInstanceContextDTO.getMulConditionDTO().getExternalId());
					new RelationInstance(entityInstance, mulCondition.getSymmetricMulCondition().getRolename(),
							entityInstanceContext, mulCondition.getRolename(), mulCondition.getRelationBW());
				}

				// create pre work item arguments
				for (ProductInstanceDTO productInstanceDTO : entityInstanceContextDTO.getPathValueSet()) {
					DefPathCondition defPathCondition = DefPathCondition
							.getDefPathCondition(workflowInstance.getSpecification(), productInstanceDTO.getPath());
					workItem.addPreWorkItemArgument(productInstanceDTO, defPathCondition);
				}
			}

			// inner relation instance
			for (InnerRelationInstanceDto innerRelationInstanceDTO : definitionGroupInstanceDTO
					.getInnerRelationInstanceSet()) {
				MulCondition mulCondition = FenixFramework
						.getDomainObject(innerRelationInstanceDTO.getMulConditionDTO().getExternalId());
				for (ProductInstanceDTO productInstanceDTO : innerRelationInstanceDTO.getProductInstanceSet()) {
					EntityInstance innerEntity = newEntityInstances.get(productInstanceDTO.getExternalId());
					if (innerEntity != null) {
						new RelationInstance(entityInstance, mulCondition.getSymmetricMulCondition().getRolename(),
								innerEntity, mulCondition.getRolename(), mulCondition.getRelationBW());
					} else {
						innerEntity = FenixFramework.getDomainObject(productInstanceDTO.getExternalId());
						for (EntityInstanceContextDto entityInstanceContextDTO : definitionGroupInstanceDTO
								.getEntityInstanceContextSet()) {
							EntityInstance entityInstanceContext = FenixFramework
									.getDomainObject(entityInstanceContextDTO.getEntityInstance().getExternalId());
							new RelationInstance(entityInstanceContext,
									mulCondition.getSymmetricMulCondition().getRolename(), innerEntity,
									mulCondition.getRolename(), mulCondition.getRelationBW());
						}
					}
				}
			}

		}
	}

	public EntityInstance createEntityInstance(WorkflowInstance workflowInstance,
			ProductInstanceDTO productInstanceDTO) {
		Entity entity;
		EntityInstance entityInstance;
		entity = workflowInstance.getSpecification().getDataModel().getEntity(productInstanceDTO.getPath()).get();
		entityInstance = new EntityInstance(workflowInstance, entity);
		return entityInstance;
	}

	public AttributeInstance createAttributeInstance(WorkflowInstance workflowInstance, EntityInstance entityInstance,
			ProductInstanceDTO productInstanceDTO) {
		Attribute attribute = (Attribute) workflowInstance.getSpecification().getDataModel()
				.getTargetOfPath(productInstanceDTO.getPath());
		AttributeInstance attributeInstance = new AttributeInstance(entityInstance, attribute,
				productInstanceDTO.getValue());
		return attributeInstance;
	}

	public String print() {
		String result = "\r\n";
		for (DefinitionGroupDto definitionGroupDTO : getDefinitionGroupSet()) {
			result = result + "DEF ENTITY: " + definitionGroupDTO.getDefProductConditionSet().getDefEnts().stream()
					.map(d -> d.getPath()).collect(Collectors.joining(",")) + "\r\n";
			result = result + "DEF ATTRIBUTE: " + definitionGroupDTO.getDefProductConditionSet().getDefAtts().stream()
					.map(d -> d.getPath()).collect(Collectors.joining(",")) + "\r\n";
			result = result + "ENTITY CONTEXT: "
					+ definitionGroupDTO.getEntityContextSet().stream()
							.map(ec -> ec.getEntity().getName() + ", " + ec.getMulCondition().getRolePath()
									+ " ENTITY INSTANCE CONTEXT: "
									+ printEntityInstanceContext(ec.getEntityInstanceContextSet()))
							.collect(Collectors.joining(";"))
					+ "\r\n";

			result = result + "INNER RELATION: " + definitionGroupDTO.getInnerRelationSet().stream()
					.map(ir -> " Source " + ir.getSourceEntity().getName() + ", Target "
							+ ir.getTargetEntity().getName() + ", Rolename " + ir.getMulCondition().getRolePath()
							+ ", Product " + printProductInstance(ir.getProductInstanceSet()))
					.collect(Collectors.joining(",")) + "\r\n";

			result = result + "ENTITY INSTANCE CONTEXT: "
					+ definitionGroupDTO.getDefinitionGroupInstanceSet().stream()
							.map(dgi -> printEntityInstanceContext(dgi.getEntityInstanceContextSet()))
							.collect(Collectors.joining(","))
					+ "\r\n";

			result = result + "PRODUCT INSTANCE: " + definitionGroupDTO.getDefinitionGroupInstanceSet().stream()
					.map(dgi -> printProductInstance(dgi.getProductInstanceSet())).collect(Collectors.joining(";"))
					+ "\r\n";

			result = result + "INNER RELATION INSTANCE: "
					+ definitionGroupDTO.getDefinitionGroupInstanceSet().stream()
							.map(dgi -> printInnerRelationInstance(dgi)).collect(Collectors.joining(";"))
					+ "\r\n" + "\r\n";
		}
		return result;
	}

	private String printInnerRelationInstance(DefinitionGroupInstanceDTO dgi) {
		return dgi
				.getInnerRelationInstanceSet().stream().map(iri -> iri.getMulConditionDTO().getRolePath()
						+ ", Products " + printProductInstance(iri.getProductInstanceSet()))
				.collect(Collectors.joining(";"));
	}

	private String printEntityInstanceContext(Set<EntityInstanceContextDto> eicSet) {
		return eicSet.stream().map(eic -> eic.getPathValues()).collect(Collectors.joining(";"));
	}

	private String printProductInstance(Set<ProductInstanceDTO> piSet) {
		return piSet.stream().map(pi -> pi.getPath() + ":" + pi.getExternalId() + "," + pi.getValue())
				.collect(Collectors.joining(";"));
	}

}
