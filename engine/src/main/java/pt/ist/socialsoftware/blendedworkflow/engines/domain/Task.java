package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Task extends Task_Base {

	public Task(TaskModel taskModel, String name, String description,
			Condition preConstrain, Condition postConstrain, String previous,
			String joinCode, String splitCode) throws BlendedWorkflowException {
		checkUniqueTaskName(taskModel, name);
		setTaskModel(taskModel);
		setName(name);
		setPreConstraint(preConstrain);
		setPostConstraint(postConstrain);
		setDescription(description);
		setPrevious(previous);
		setJoinCode(joinCode);
		setSplitCode(splitCode);
	}

	private void checkUniqueTaskName(TaskModel taskModel, String name)
			throws BlendedWorkflowException {
		for (Task task : taskModel.getTasksSet()) {
			if (task.getName().equals(name)) {
				throw new BlendedWorkflowException(
						BlendedWorkflowError.INVALID_TASK_NAME, name);
			}
		}
	}

	public void cloneTask(TaskModelInstance taskModelInstance)
			throws BlendedWorkflowException {
		Condition newPreCondition = null;
		Condition preCondition = getPreConstraint();
		Condition newPostCondition = null;
		Condition postCondition = getPostConstraint();
		if (preCondition != null && postCondition != null) {
			newPreCondition = preCondition.cloneCondition(taskModelInstance);
			newPostCondition = postCondition.cloneCondition(taskModelInstance);
		}
		Task newTask = new Task(taskModelInstance, getName(), getDescription(),
				newPreCondition, newPostCondition, getPrevious(),
				getJoinCode(), getSplitCode());
		newTask.setUser(getUser());
		newTask.setRole(getRole());
	}

	/**
	 * Get the Task condition data to use in the use interface.
	 * 
	 * @return a string with the condition data entities.
	 */
	public String getConstraintData(Boolean isPreConstraint) {
		Set<Entity> entities;
		Set<Attribute> attributes;
		String dataString = "";

		// Get Condition Data
		if (isPreConstraint) {
			entities = getPreConstraint().getEntities();
			attributes = getPreConstraint().getAttributes();
		} else {
			entities = getPostConstraint().getEntities();
			attributes = getPostConstraint().getAttributes();
		}

		// Add Attribute entities
		for (Attribute attribute : attributes) {
			entities.add(attribute.getEntity());
		}

		// Create String
		int count = 0;
		for (Entity entity : entities) {
			if (entities.size() == 1) {
				dataString += entity.getName();
			} else if (count < entities.size() - 1) {
				dataString += entity.getName() + ", ";
				;
			} else {
				dataString += entity.getName();
			}
			count++;
		}
		return dataString;
	}

}
