package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class CompareAttributeToValueCondition extends
		CompareAttributeToValueCondition_Base {

	private static Logger log = Logger
			.getLogger("CompareAttributeToValueCondition");

	public CompareAttributeToValueCondition(Attribute attribute,
			String operator, String value) {
		setAttribute(attribute);
		setOperator(operator);
		setValue(value);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance()
				.getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getAttribute().getEntity()
				.getName());
		Attribute attribute = entity.getAttribute(getAttribute().getName());
		return new CompareAttributeToValueCondition(attribute, getOperator(),
				getValue());
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		DataModelInstance dataModelInstance = taskModelInstance.getBwInstance()
				.getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getAttribute().getEntity()
				.getName());
		Attribute attribute = entity.getAttribute(getAttribute().getName());
		return new CompareAttributeToValueCondition(attribute, getOperator(),
				getValue());
	}

	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		getAttribute().getEntity().assignAttributeInstances(goalWorkItem,
				getAttribute(), conditionType);
	}

	@Override
	public void assignAttributeInstances(TaskWorkItem taskWorkItem,
			ConditionType conditionType) {
		getAttribute().getEntity().assignAttributeInstances(taskWorkItem,
				getAttribute(), conditionType);
	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<Entity>();
	}

	@Override
	public Set<Attribute> getAttributes() {
		Set<Attribute> attribute = new HashSet<Attribute>();
		attribute.add(getAttribute());
		return attribute;
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		HashMap<Attribute, String> result = new HashMap<Attribute, String>();
		result.put(getAttribute(), getValue());
		return result;
	}

	@Override
	public String getRdrUndefinedCondition() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName()
				.replaceAll(" ", "");

		condition += entityName + "_" + attributeName + "_State = "
				+ DataState.UNDEFINED + ")";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName()
				.replaceAll(" ", "");

		condition += entityName + "_" + attributeName + "_State = "
				+ DataState.SKIPPED + ")";
		return condition;
	}

	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName()
				.replaceAll(" ", "");
		String value = getValue();
		if (getValue().equals("$TODAY$")) {
			value = "" + "$TODAY$".hashCode();
		}

		condition += entityName + "_" + attributeName + " " + getOperator()
				+ " " + value + ")";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		return "(!" + getRdrTrueCondition() + ")";
	}

	@Override
	public String toString() {
		return "compareAttributeTo(" + getAttribute().getEntity().getName()
				+ "." + getAttribute().getName() + " " + getOperator() + " "
				+ getValue() + ")";
	}

	@Override
	public Boolean existExistEntity() {
		return false;
	}

	@Override
	public Boolean existTrue() {
		return false;
	}

	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		// TODO:In progress...

		// Input/Output Arguments
		Set<WorkItemArgument> arguments = null;
		if (conditionType.equals(ConditionType.ACTIVATE_CONDITION)) {
			arguments = goalWorkItem.getInputWorkItemArgumentsSet();
		} else if (conditionType.equals(ConditionType.SUCESS_CONDITION)) {
			arguments = goalWorkItem.getOutputWorkItemArgumentsSet();
		}
		if (arguments != null) {
			for (WorkItemArgument workItemArgument : arguments) {
				Attribute workItemAttribute = workItemArgument
						.getAttributeInstance().getAttribute();
				Attribute conditionAttribute = getAttribute();
				if (workItemAttribute == conditionAttribute) {
					if (workItemArgument.getState().equals(DataState.UNDEFINED)) {
						return TripleStateBool.FALSE;
					} else if (workItemArgument.getState().equals(
							DataState.SKIPPED)) {
						return TripleStateBool.SKIPPED;
					} else {
						if (evaluateComparation(workItemArgument.getValue())) {
							return TripleStateBool.TRUE;
						} else {
							return TripleStateBool.FALSE;
						}
					}
				}
			}
		}
		return TripleStateBool.FALSE;
	}

	// Legacy
	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		Set<WorkItemArgument> arguments = null;
		if (conditionType.equals(ConditionType.ACTIVATE_CONDITION)) {
			arguments = goalWorkItem.getInputWorkItemArgumentsSet();
		} else if (conditionType.equals(ConditionType.SUCESS_CONDITION)) {
			arguments = goalWorkItem.getOutputWorkItemArgumentsSet();
		}
		if (arguments != null) {
			for (WorkItemArgument workItemArgument : arguments) {
				Attribute workItemAttribute = workItemArgument
						.getAttributeInstance().getAttribute();
				Attribute conditionAttribute = getAttribute();
				if (workItemAttribute == conditionAttribute) {
					if (workItemArgument.getState().equals(DataState.UNDEFINED)) {
						return TripleStateBool.FALSE;
					} else if (workItemArgument.getState().equals(
							DataState.SKIPPED)) {
						return TripleStateBool.SKIPPED;
					} else {
						if (evaluateComparation(workItemArgument.getValue())) {
							return TripleStateBool.TRUE;
						} else {
							return TripleStateBool.FALSE;
						}
					}
				}
			}
		}
		return TripleStateBool.FALSE;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance,
			GoalWorkItem goalWorkItem, ConditionType conditionType) {
		if (entityInstance == null) {
			return evaluateWithWorkItem(goalWorkItem, conditionType);
		}

		else {
			for (AttributeInstance attributeInstance : entityInstance
					.getAttributeInstancesSet()) {
				if (attributeInstance.getAttribute().equals(getAttribute())) {
					DataState state = getWorkItemState(attributeInstance,
							goalWorkItem, conditionType);
					if (state == null) {
						state = attributeInstance.getState();
					}

					if (state.equals(DataState.UNDEFINED)) {
						return TripleStateBool.FALSE;
					} else if (state.equals(DataState.SKIPPED)) {
						return TripleStateBool.SKIPPED;
					} else {
						String value = getWorkItemValue(attributeInstance,
								goalWorkItem, conditionType);
						if (value == null) {
							value = attributeInstance.getValue();
						}

						if (evaluateComparation(value)) {
							return TripleStateBool.TRUE;
						} else {
							return TripleStateBool.FALSE;
						}
					}
				}
			}
			return TripleStateBool.FALSE;
		}
	}

	private String getWorkItemValue(AttributeInstance attributeInstance,
			GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// List<WorkItemArgument> arguments = null;
		// if (conditionType.equals(ConditionType.ACTIVATE)) {
		// arguments = goalWorkItem.getInputWorkItemArguments();
		// } else if (conditionType.equals(ConditionType.SUCESS)) {
		// arguments = goalWorkItem.getOutputWorkItemArguments();
		// }
		// for (WorkItemArgument workItemArgument : arguments) {
		if (goalWorkItem != null) {
			for (WorkItemArgument workItemArgument : goalWorkItem
					.getOutputWorkItemArgumentsSet()) {
				if (workItemArgument.getAttributeInstance().equals(
						attributeInstance)) {
					return workItemArgument.getValue();
				}
			}
		}
		return null;
	}

	private DataState getWorkItemState(AttributeInstance attributeInstance,
			GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// List<WorkItemArgument> arguments = null;
		// if (conditionType.equals(ConditionType.ACTIVATE)) {
		// arguments = goalWorkItem.getInputWorkItemArguments();
		// } else if (conditionType.equals(ConditionType.SUCESS)) {
		// arguments = goalWorkItem.getOutputWorkItemArguments();
		// }
		// for (WorkItemArgument workItemArgument : arguments) {
		if (goalWorkItem != null) {
			for (WorkItemArgument workItemArgument : goalWorkItem
					.getOutputWorkItemArgumentsSet()) {
				if (workItemArgument.getAttributeInstance().equals(
						attributeInstance)) {
					return workItemArgument.getState();
				}
			}
		}
		return null;
	}

	private boolean evaluateComparation(String evaluateValue) {
		String conditionValueString = getValue();

		if (conditionValueString.equals("$TODAY$")) {
			conditionValueString = BlendedWorkflow.getInstance().getToday();
		}

		if (((getAttribute().getType().equals(AttributeType.STRING) || (getAttribute()
				.getType().equals(AttributeType.BOOLEAN))))) {
			if (getOperator().equals("=")) {
				if (evaluateValue.equals(conditionValueString)) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals("!=")) {
				if (!evaluateValue.equals(conditionValueString)) {
					return true;
				} else {
					return false;
				}
			} else {
				log.error("Invalid operator for String or Boolean type.");
				return false;
			}
		} else if (getAttribute().getType().equals(AttributeType.NUMBER)) {
			Integer value = Integer.parseInt(evaluateValue);
			Integer conditionValue = Integer.parseInt(getValue());
			if (getOperator().equals("<")) {
				if (value < conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals("<=")) {
				if (value <= conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals("=")) {
				if (value.equals(conditionValue)) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals(">")) {
				if (value > conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals(">=")) {
				if (value >= conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals("!=")) {
				if (value.intValue() != conditionValue.intValue()) {
					return true;
				} else {
					return false;
				}
			} else {
				log.error("Invalid operator for Number type.");
				return false;
			}
		} else {
			log.error("Invalid operator for String or Boolean type.");
			return false;
		}
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return true;
	}

}