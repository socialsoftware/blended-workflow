package pt.ist.socialsoftware.blendedworkflow.engines.exception;

public class BlendedWorkflowException extends Exception {

	private static final long serialVersionUID = 1L;

	public enum BlendedWorkflowError {EMPTY_CONDITION_STRING, INVALID_CONDITION_STRING, INVALID_SPECIFICATION_NAME,
		INVALID_ENTITY_NAME, INVALID_ATTRIBUTE_NAME, INVALID_RELATION_NAME, INVALID_TASK_NAME, INVALID_GOAL_NAME, 
		NON_EXISTENT_CASE_ID, NON_EXISTENT_ENTITY_INSTANCE, NON_EXISTENT_TASK_NAME, NON_EXISTENT_GOAL_NAME,
		NON_EXISTENT_WORKITEM_ID, FALSE_PRE_CONSTRAIN, YAWL_ADAPTER, WORKLET_ADAPTER}

	private BlendedWorkflowError error;
	private String message;

	public BlendedWorkflowException(BlendedWorkflowError error) {
		this.error = error;
	}
	
	public BlendedWorkflowException(BlendedWorkflowError error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public BlendedWorkflowError getError() {
		return error;
	}

	public void setError(BlendedWorkflowError error) {
		this.error = error;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}