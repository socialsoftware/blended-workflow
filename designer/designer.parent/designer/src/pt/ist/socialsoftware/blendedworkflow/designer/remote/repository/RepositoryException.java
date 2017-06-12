package pt.ist.socialsoftware.blendedworkflow.designer.remote.repository;

public class RepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final BWError error;

	public BWError getError() {
		return this.error;
	}

	@Override
	public String getMessage() {
		return getError().getMessage();
	}

	public RepositoryException(BWError error) {
		this.error = error;
	}
}
