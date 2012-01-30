package pt.ist.socialsoftware.blendedworkflow.exception;

public class BWSpecificationException extends BlendedWorkflowException {

	private static final long serialVersionUID = 1L;

	private String bwSpecification;

	public BWSpecificationException(String bwSpecification) {
		this.bwSpecification = bwSpecification;
	}

	public String getBWSpecification() {
		return this.bwSpecification;
	}

}