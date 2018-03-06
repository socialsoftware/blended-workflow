package pt.ist.socialsoftware.blendedworkflow.resources.service;

public class RMException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private RMErrorType error;
    private String message;

    public RMException(RMErrorType error) {
        this.error = error;
    }

    public RMException(RMErrorType error, String message) {
        this.error = error;
        this.message = message;
    }

    public RMErrorType getError() {
        return error;
    }

    public void setError(RMErrorType error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
