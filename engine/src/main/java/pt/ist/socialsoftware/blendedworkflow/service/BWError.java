package pt.ist.socialsoftware.blendedworkflow.service;

public class BWError {
    public BWErrorType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    private final BWErrorType type;
    private final String value;
    private final String message;

    public BWError(BWErrorType type, String value, String message) {
        this.type = type;
        this.value = value;
        this.message = message;
    }

}
