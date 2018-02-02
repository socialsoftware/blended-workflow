package pt.ist.socialsoftware.blendedworkflow.core.service;

public class BWException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private BWErrorType error;
    private String message;

    public BWException(BWErrorType error) {
        this.error = error;
    }

    public BWException(BWErrorType error, String message) {
        this.error = error;
        this.message = message;
    }

    public BWErrorType getError() {
        return error;
    }

    public void setError(BWErrorType error) {
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