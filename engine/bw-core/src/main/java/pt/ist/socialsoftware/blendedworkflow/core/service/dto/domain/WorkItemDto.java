package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class WorkItemDto {
	private static Logger logger = LoggerFactory.getLogger(WorkItemDto.class);

	private String specId;
	private String specName;
	private String workflowInstanceName;
	private String name;
	private int timestamp;
	private String preArguments;
	private String postArguments;
	private List<EntityInstanceToDefineDto> entityInstancesToDefine = new ArrayList<>();
	private Set<EntityInstanceToDefineDto> unitOfWork;

	public WorkItemDto() {
	}

	public void executeWorkItem(WorkflowInstance workflowInstance, WorkItem workItem) {
		Set<EntityInstance> definedEntityInstances = new HashSet<EntityInstance>();
		Set<RelationBW> innerRelations = new HashSet<RelationBW>();
		for (EntityInstanceToDefineDto entityInstanceToDefine : getUnitOfWork()) {
			EntityInstance entityInstance = null;
			if (entityInstanceToDefine.isExists()) {
				entityInstance = workflowInstance.getEntityInstanceById(entityInstanceToDefine.getId());

				if (entityInstance == null) {
					throw new BWException(BWErrorType.PRE_WORK_ITEM_ARGUMENT,
							"Entity instance not defined: " + entityInstanceToDefine.getEntity().getName());
				}

				definedEntityInstances.add(entityInstance);

				workItem.addPreWorkItemArgument(entityInstance, DefPathCondition.getDefPathCondition(
						workflowInstance.getSpecification(), entityInstance.getEntity().getFullPath()));
			} else {
				Entity entity = workflowInstance.getEntityByName(entityInstanceToDefine.getEntity().getName());
				entityInstance = new EntityInstance(workflowInstance, entity);

				definedEntityInstances.add(entityInstance);

				if (workItem != null) {
					workItem.addPostWorkItemArgument(entityInstance,
							DefEntityCondition.getDefEntityCondition(entityInstance.getEntity()));
				}
			}

			for (AttributeInstanceDto attributeInstanceDto : entityInstanceToDefine.getAttributeInstances()) {
				if (attributeInstanceDto.isToDefine()) {
					Attribute attribute = workflowInstance
							.getAttributeByPath(attributeInstanceDto.getAttribute().getEntityName() + "."
									+ attributeInstanceDto.getAttribute().getName());
					AttributeInstance attributeInstance = new AttributeInstance(entityInstance, attribute,
							attributeInstanceDto.getValue());

					for (Dependence dependence : attribute.getDependenceSet()) {
						for (ProductInstance productInstance : entityInstance
								.getDependentProductInstances(dependence)) {
							workItem.addPreWorkItemArgument(productInstance,
									dependence.getPath().getDefPathCondition());
						}
					}

					if (workItem != null) {
						workItem.addPostWorkItemArgument(attributeInstance,
								DefAttributeCondition.getDefAttributeCondition(attributeInstance.getAttribute()));
					}
				}
			}

			for (LinkDto linkDto : entityInstanceToDefine.getLinks()) {
				if (linkDto.isToDefine()) {
					MulCondition mulCondition = FenixFramework
							.getDomainObject(linkDto.getMulCondition().getExternalId());
					if (!linkDto.getCandidateEntityInstances().isEmpty()) {
						for (EntityInstanceDto targetEntityInstanceDto : linkDto.getEntityInstances()) {
							EntityInstance targetEntityInstance = FenixFramework
									.getDomainObject(targetEntityInstanceDto.getExternalId());
							new RelationInstance(entityInstance, mulCondition.getSymmetricMulCondition().getRolename(),
									targetEntityInstance, mulCondition.getRolename(), mulCondition.getRelationBW());

							workItem.addPreWorkItemArgument(targetEntityInstance, DefPathCondition.getDefPathCondition(
									workflowInstance.getSpecification(), linkDto.getMulCondition().getRolePath()));
						}
					} else {
						// the link is associated to a inner relation
						innerRelations.add(mulCondition.getRelationBW());
					}
				}
			}
		}

		// define the inner relations instances
		for (RelationBW innerRelation : innerRelations) {
			for (EntityInstance definedEntityInstanceOne : definedEntityInstances) {
				if (definedEntityInstanceOne.getEntity() == innerRelation.getEntityOne()) {
					for (EntityInstance definedEntityInstanceTwo : definedEntityInstances) {
						if (definedEntityInstanceTwo.getEntity() == innerRelation.getEntityTwo()) {
							new RelationInstance(definedEntityInstanceOne, innerRelation.getRolenameOne(),
									definedEntityInstanceTwo, innerRelation.getRolenameTwo(), innerRelation);
						}
					}
				}
			}
		}
	}

	public String print() {
		String result = "";
		for (EntityInstanceToDefineDto entityInstanceToDefineDto : this.entityInstancesToDefine) {
			result = result + entityInstanceToDefineDto.print();
		}

		if (getUnitOfWork() != null) {
			for (EntityInstanceToDefineDto entityInstanceToDefineDto : getUnitOfWork()) {
				result = result + entityInstanceToDefineDto.print();
			}
		}

		return result;
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

	public List<EntityInstanceToDefineDto> getEntityInstancesToDefine() {
		return this.entityInstancesToDefine;
	}

	public void setEntityInstancesToDefine(List<EntityInstanceToDefineDto> entityInstancesToDefine) {
		this.entityInstancesToDefine = entityInstancesToDefine;
	}

	public Set<EntityInstanceToDefineDto> getUnitOfWork() {
		return this.unitOfWork;
	}

	public void setUnitOfWork(Set<EntityInstanceToDefineDto> unitOfWork) {
		this.unitOfWork = unitOfWork;
	}

}
