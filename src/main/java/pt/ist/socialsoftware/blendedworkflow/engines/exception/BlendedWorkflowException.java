package pt.ist.socialsoftware.blendedworkflow.engines.exception;

public class BlendedWorkflowException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final String msg;
	
	public BlendedWorkflowException(String message) {
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}
	
}