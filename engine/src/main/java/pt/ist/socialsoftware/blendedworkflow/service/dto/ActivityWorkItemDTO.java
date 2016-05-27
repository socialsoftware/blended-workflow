package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.PreWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.ProductInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;

public class ActivityWorkItemDTO extends WorkItemDTO {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemDTO.class);

	String activityName;

	public ActivityWorkItemDTO() {
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public static ActivityWorkItemDTO createActivityWorkItemDTO(WorkflowInstance workflowInstance, Activity activity) {
		ActivityWorkItemDTO activityWorkItemDTO = new ActivityWorkItemDTO();
		activityWorkItemDTO.setSpecId(workflowInstance.getSpecification().getSpecId());
		activityWorkItemDTO.setWorkflowInstanceName(workflowInstance.getName());
		activityWorkItemDTO.setDefinitionGroupSet(new HashSet<DefinitionGroupDTO>());
		activityWorkItemDTO.setActivityName(activity.getName());

		// get activity definition groups
		Map<Entity, List<DefProductCondition>> definitionGroupMap = activity.getPostConditionSet().stream()
				.collect(Collectors.groupingBy(d -> d.getSourceOfPath()));

		for (Entity entityDefinitionGroup : definitionGroupMap.keySet()) {
			DefinitionGroupDTO definitionGroup = new DefinitionGroupDTO();
			activityWorkItemDTO.getDefinitionGroupSet().add(definitionGroup);

			// create all def products conditions associated with the entity
			DefProductConditionSetDTO defProductConditionSet = new DefProductConditionSetDTO();
			definitionGroup.setDefProductConditionSet(defProductConditionSet);
			defProductConditionSet.setDefEnts(
					definitionGroupMap.get(entityDefinitionGroup).stream().filter(DefEntityCondition.class::isInstance)
							.map(DefEntityCondition.class::cast).map(d -> d.getDTO()).collect(Collectors.toSet()));
			defProductConditionSet.setDefAtts(definitionGroupMap.get(entityDefinitionGroup).stream()
					.filter(DefAttributeCondition.class::isInstance).map(DefAttributeCondition.class::cast)
					.map(d -> d.getDTO()).collect(Collectors.toSet()));

			// create entity contexts
			Set<EntityContextDTO> entityContextDTOs = new HashSet<EntityContextDTO>();
			definitionGroup.setEntityContextSet(entityContextDTOs);
			Set<Entity> entityContexts = activity.getEntityContext(entityDefinitionGroup);
			// for each entity context of the product groups to be created
			for (Entity entityContext : entityContexts) {
				for (MulCondition mulCondition : activity.getMulConditionFromEntityToEntity(entityDefinitionGroup,
						entityContext)) {
					entityContextDTOs.add(EntityContextDTO.createEntityContextDTO(activity, entityContext, mulCondition,
							workflowInstance));
				}

				// entity already exists, some of its attributes are being
				// defined)
				if (entityDefinitionGroup == entityContext) {
					entityContextDTOs.add(
							EntityContextDTO.createEntityContextDTO(activity, entityContext, null, workflowInstance));
				}
			}

			// create inner relations
			Set<InnerRelationDTO> innerRelationDTOs = new HashSet<InnerRelationDTO>();
			definitionGroup.setInnerRelationSet(innerRelationDTOs);
			for (MulCondition mulCondition : activity.getInnerMulConditions(entityDefinitionGroup)) {
				innerRelationDTOs.add(InnerRelationDTO.createInnerRelationDTO(entityDefinitionGroup, mulCondition));
			}

			// create definition group instances
			Set<DefinitionGroupInstanceDTO> definitionGroupInstances = new HashSet<DefinitionGroupInstanceDTO>();
			definitionGroup.setDefinitionGroupInstanceSet(definitionGroupInstances);
		}
		return activityWorkItemDTO;
	}

	public ActivityWorkItem createActivityWorkItem(WorkflowInstance workflowInstance, Activity activity) {
		ActivityWorkItem activityWorkItem = new ActivityWorkItem(workflowInstance, activity);

		Map<String, EntityInstance> newEntityInstances = new HashMap<String, EntityInstance>();
		for (DefinitionGroupInstanceDTO definitionGroupInstanceDTO : getDefinitionGroupSet().stream()
				.flatMap(dgs -> dgs.getDefinitionGroupInstanceSet().stream()).collect(Collectors.toSet())) {

			// create post work item argument for created entity instance
			EntityInstance entityInstance = null;
			for (ProductInstanceDTO productInstanceDTO : definitionGroupInstanceDTO.getProductInstanceSet().stream()
					.filter(pi -> pi.getProduct().getProductType().equals(ProductType.ENTITY.name()))
					.collect(Collectors.toSet())) {
				entityInstance = createEntityInstance(workflowInstance, productInstanceDTO);
				newEntityInstances.put(productInstanceDTO.getExternalId(), entityInstance);

				addPostWorkItemArgument(activityWorkItem, entityInstance,
						DefEntityCondition.getDefEntityCondition(entityInstance.getEntity()));
			}

			// create post work item argument for attribute instances of created
			// entity instance
			if (entityInstance != null) {
				for (ProductInstanceDTO productInstanceDTO : definitionGroupInstanceDTO.getProductInstanceSet().stream()
						.filter(pi -> pi.getProduct().getProductType().equals(ProductType.ATTRIBUTE.name()))
						.collect(Collectors.toSet())) {
					AttributeInstance attributeInstance = createAttributeInstance(workflowInstance, entityInstance,
							productInstanceDTO);

					addPostWorkItemArgument(activityWorkItem, attributeInstance,
							DefAttributeCondition.getDefAttributeCondition(attributeInstance.getAttribute()));
				}
			}

			// create pre and work item arguments for already created entity
			// instances
			for (EntityInstanceContextDTO entityInstanceContextDTO : definitionGroupInstanceDTO
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
						AttributeInstance attributeInstance = createAttributeInstance(workflowInstance,
								entityInstanceContext, productInstanceDTO);

						addPostWorkItemArgument(activityWorkItem, attributeInstance,
								DefAttributeCondition.getDefAttributeCondition(attributeInstance.getAttribute()));
					}
				}

				// associate new entity instance with context
				if (entityInstance != null && entityInstance != entityInstanceContext) {
					MulCondition mulCondition = FenixFramework
							.getDomainObject(entityInstanceContextDTO.getMulConditionDTO().getExternalId());
					createRelationInstance(entityInstance, entityInstanceContext, mulCondition);
				}

				// create pre work item arguments
				for (ProductInstanceDTO productInstanceDTO : entityInstanceContextDTO.getPathValueSet()) {
					DefPathCondition defPathCondition = DefPathCondition
							.getDefPathCondition(workflowInstance.getSpecification(), productInstanceDTO.getPath());
					addPreWorkItemArgument(activityWorkItem, productInstanceDTO, defPathCondition);
				}
			}

			// inner relation instance
			for (InnerRelationInstanceDTO innerRelationInstanceDTO : definitionGroupInstanceDTO
					.getInnerRelationInstanceSet()) {
				MulCondition mulCondition = FenixFramework
						.getDomainObject(innerRelationInstanceDTO.getMulConditionDTO().getExternalId());
				for (ProductInstanceDTO productInstanceDTO : innerRelationInstanceDTO.getProductInstanceSet()) {
					EntityInstance associatedEntity = newEntityInstances.get(productInstanceDTO.getExternalId());
					createRelationInstance(entityInstance, associatedEntity, mulCondition);
				}
			}

		}
		return activityWorkItem;
	}

	public void createRelationInstance(EntityInstance entityInstance, EntityInstance associatedEntity,
			MulCondition mulCondition) {
		if (!entityInstance.isInRelation(associatedEntity, mulCondition.getRelationBW())) {
			if (mulCondition.getRelationBW().getEntityOne() == entityInstance.getEntity()) {
				new RelationInstance(entityInstance, associatedEntity, mulCondition.getRelationBW());
			} else {
				new RelationInstance(associatedEntity, entityInstance, mulCondition.getRelationBW());
			}
		}
	}

	public void addPostWorkItemArgument(ActivityWorkItem activityWorkItem, ProductInstance productInstance,
			DefProductCondition defProductCondition) {
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(activityWorkItem, defProductCondition);
		postWorkItemArgument.addProductInstance(productInstance);
		activityWorkItem.addPostCondition(postWorkItemArgument);
	}

	public void addPreWorkItemArgument(ActivityWorkItem activityWorkItem, ProductInstanceDTO productInstanceDTO,
			DefPathCondition defPathCondition) {
		PreWorkItemArgument preWorkItemArgument = new PreWorkItemArgument(activityWorkItem, defPathCondition);
		preWorkItemArgument.addProductInstance(FenixFramework.getDomainObject(productInstanceDTO.getExternalId()));
		activityWorkItem.addPreCondition(preWorkItemArgument);
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

}
