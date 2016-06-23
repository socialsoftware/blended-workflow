package pt.ist.socialsoftware.blendedworkflow.service.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;

public class GoalWorkItemDTO extends WorkItemDTO {
	private static Logger logger = LoggerFactory.getLogger(GoalWorkItemDTO.class);

	String goalName;

	public GoalWorkItemDTO() {
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public static GoalWorkItemDTO createGoalWorkItemDTO(WorkflowInstance workflowInstance, Goal goal) {
		// TODO Auto-generated method stub
		return null;
	}

}
