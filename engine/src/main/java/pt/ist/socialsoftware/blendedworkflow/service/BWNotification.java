package pt.ist.socialsoftware.blendedworkflow.service;

import java.util.ArrayList;
import java.util.List;

public class BWNotification {
    private final List<BWError> errors = new ArrayList<>();

    public void addError(BWError error) {
        errors.add(error);

    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<BWError> getError() {
        return errors;
    }

}
