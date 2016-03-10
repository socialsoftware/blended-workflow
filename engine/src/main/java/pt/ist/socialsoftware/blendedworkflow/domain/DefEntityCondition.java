package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class DefEntityCondition extends DefEntityCondition_Base {

	public static DefEntityCondition getDefEntity(Entity entity) {
		if (entity.getDefEntityCondition() != null)
			return entity.getDefEntityCondition();
		else
			return new DefEntityCondition(entity);
	}

	private DefEntityCondition(Entity entity) {
		setConditionModel(entity.getDataModel().getSpecification().getConditionModel());
		Path path = new Path(entity.getDataModel(), entity.getName());
		setPath(path);
		setEntity(entity);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getEntity().getName()).get();
		return new DefEntityCondition(entity);
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		DataModelInstance dataModelInstance = taskModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getEntity().getName()).get();
		return new DefEntityCondition(entity);
	}

	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		getEntity().assignAllAttributeInstances(goalWorkItem, getEntity(), conditionType);
	}

	@Override
	public void assignAttributeInstances(TaskWorkItem taskWorkItem, ConditionType conditionType) {
		getEntity().assignAllAttributeInstances(taskWorkItem, getEntity(), conditionType);
	}

	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entity = new HashSet<Entity>();
		entity.add(getEntity());
		return entity;
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> paths = new HashSet<Path>();
		paths.add(getPath());

		return paths;
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<Attribute, String>();
	}

	@Override
	public String getRdrUndefinedCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributeBasicSet()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " | " + entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED;
				}

			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributeBasicSet()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " | " + entityName + "_" + attributeName + "_State = " + DataState.SKIPPED;
				}
			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");

		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributeBasicSet()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " & " + entityName + "_" + attributeName + "_State = " + DataState.DEFINED;
				}

			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		return "(FALSE_NODE = FALSE)";
	}

	@Override
	public String toString() {
		return "existsEntity(" + getEntity().getName() + ")";
	}

	@Override
	public Boolean existExistEntity() {
		return true;
	}

	@Override
	public Boolean existTrue() {
		return false;
	}

	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO:Refactor
		return TripleStateBool.FALSE;
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		EntityInstance workItemEntityInstance = null;
		Set<WorkItemArgument> arguments = null;
		TripleStateBool finalResult = TripleStateBool.TRUE;
		if (conditionType.equals(ConditionType.ACTIVATE_CONDITION)) {
			arguments = goalWorkItem.getInputWorkItemArgumentsSet();
		} else if (conditionType.equals(ConditionType.SUCESS_CONDITION)) {
			arguments = goalWorkItem.getOutputWorkItemArgumentsSet();
		}

		// Exists Entity
		if (arguments != null) {
			for (WorkItemArgument workItemArgument : arguments) {
				Attribute workItemAttribute = workItemArgument.getAttributeInstance().getAttribute();
				Attribute conditionAttribute = getEntity().getAttribute(workItemAttribute.getName()).orElse(null);
				if (conditionAttribute != null && conditionAttribute.getIsKeyAttribute()) {
					workItemEntityInstance = workItemArgument.getAttributeInstance().getEntityInstance();

					if (workItemArgument.getState().equals(DataState.SKIPPED)) {
						finalResult = finalResult.AND(TripleStateBool.SKIPPED);
					} else if (workItemArgument.getState().equals(DataState.UNDEFINED)) {
						finalResult = finalResult.AND(TripleStateBool.FALSE);
					} else {
						finalResult = finalResult.AND(TripleStateBool.TRUE);
					}
				}
			}
		}

		// Exists Entity Key Relations
		if (workItemEntityInstance != null) {
			for (RelationInstance relationInstance : workItemEntityInstance
					.getEntityInstanceOneRelationInstancesSet()) {
				if (relationInstance.getRelationType().getIsTwoKeyEntity()) {
					EntityInstance two = relationInstance.getEntityInstanceTwo();
					finalResult = finalResult.AND(evaluateWithDataModel(two, goalWorkItem, conditionType));
				}
			}

			for (RelationInstance relationInstance : workItemEntityInstance
					.getEntityInstanceTwoRelationInstancesSet()) {
				if (relationInstance.getRelationType().getIsOneKeyEntity()) {
					EntityInstance one = relationInstance.getEntityInstanceOne();
					finalResult = finalResult.AND(evaluateWithDataModel(one, goalWorkItem, conditionType));
				}
			}
		}
		return finalResult;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		TripleStateBool finalResult = TripleStateBool.TRUE;

		// Exists Entity
		for (AttributeInstance attributeInstance : entityInstance.getAttributeInstancesSet()) {
			if (attributeInstance.getAttribute().getIsKeyAttribute()) {
				TripleStateBool attributeResult;

				DataState state = getWorkItemState(attributeInstance, goalWorkItem, conditionType);
				if (state == null) {
					state = attributeInstance.getState();
				}

				if (state.equals(DataState.DEFINED)) {
					attributeResult = TripleStateBool.TRUE;
				} else if (state.equals(DataState.SKIPPED)) {
					attributeResult = TripleStateBool.SKIPPED;
				} else {
					attributeResult = TripleStateBool.FALSE;
				}

				finalResult = finalResult.AND(attributeResult);
			}
		}

		for (RelationInstance relationInstance : entityInstance.getEntityInstanceOneRelationInstancesSet()) {
			if (relationInstance.getRelationType().getIsTwoKeyEntity()) {
				EntityInstance two = relationInstance.getEntityInstanceTwo();
				finalResult = finalResult.AND(evaluateWithDataModel(two, goalWorkItem, conditionType));
			}
		}

		for (RelationInstance relationInstance : entityInstance.getEntityInstanceTwoRelationInstancesSet()) {
			if (relationInstance.getRelationType().getIsOneKeyEntity()) {
				EntityInstance one = relationInstance.getEntityInstanceOne();
				finalResult = finalResult.AND(evaluateWithDataModel(one, goalWorkItem, conditionType));
			}
		}

		return finalResult;
	}

	private DataState getWorkItemState(AttributeInstance attributeInstance, GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		// List<WorkItemArgument> arguments = null;
		// if (conditionType.equals(ConditionType.ACTIVATE)) {
		// arguments = goalWorkItem.getInputWorkItemArguments();
		// } else if (conditionType.equals(ConditionType.SUCESS)) {
		// arguments = goalWorkItem.getOutputWorkItemArguments();
		// }
		// for (WorkItemArgument workItemArgument : arguments) {
		if (goalWorkItem != null) {
			for (WorkItemArgument workItemArgument : goalWorkItem.getOutputWorkItemArgumentsSet()) {
				if (workItemArgument.getAttributeInstance().equals(attributeInstance)) {
					return workItemArgument.getState();
				}
			}
		}
		return null;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return false;
	}

	@Override
	public void delete() {
		setConditionModel(null);
		setEntity(null);
		super.delete();
	}

	public AttributeType getType() {
		return AttributeType.BOOLEAN;
	}

	@Override
	public String getSubPath() {
		return "DEF(" + getEntity().getFullPath() + ")";
	}

	public DefEntityConditionDTO getDTO() {
		DefEntityConditionDTO eacDTO = new DefEntityConditionDTO();
		eacDTO.setSpecId(getEntity().getDataModel().getSpecification().getSpecId());
		eacDTO.setExtId(getExternalId());
		eacDTO.setEntityName(getEntity().getName());
		eacDTO.setExists(getEntity().getExists());

		return eacDTO;
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		assert false : "expressions cannot have a def condition";
		return null;
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return new HashSet<Attribute>();
	}

}