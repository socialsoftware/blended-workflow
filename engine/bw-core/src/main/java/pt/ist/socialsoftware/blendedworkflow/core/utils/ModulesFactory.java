package pt.ist.socialsoftware.blendedworkflow.core.utils;

import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;

public class ModulesFactory {

	public DesignInterface createDesignInterface() {
		return DesignInterface.getInstance();
	}

	public ExecutionInterface createExecutionInterface() {
		return ExecutionInterface.getInstance();
	}

}
