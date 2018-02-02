package pt.ist.socialsoftware.blendedworkflow.core.service;

public class BWError {
    public BWErrorType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private final BWErrorType type;
    private final String value;

    public BWError(BWErrorType type, String value) {
        this.type = type;
        this.value = value;
    }

}
