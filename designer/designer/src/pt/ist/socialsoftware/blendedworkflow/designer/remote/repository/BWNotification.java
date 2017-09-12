package pt.ist.socialsoftware.blendedworkflow.designer.remote.repository;

import java.util.ArrayList;
import java.util.List;

public class BWNotification {
	private final List<BWError> errors = new ArrayList<>();

	public void addError(BWError error) {
		this.errors.add(error);

	}

	public boolean hasErrors() {
		return !this.errors.isEmpty();
	}

	public List<BWError> getError() {
		return this.errors;
	}
}
