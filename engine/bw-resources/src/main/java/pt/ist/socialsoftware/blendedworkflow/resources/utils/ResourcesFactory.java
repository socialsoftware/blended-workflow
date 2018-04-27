package pt.ist.socialsoftware.blendedworkflow.resources.utils;

import pt.ist.socialsoftware.blendedworkflow.core.service.execution.ExecutionInterface;
import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.execution.ExecutionResourcesInterface;

public class ResourcesFactory extends ModulesFactory {

	@Override
	public DesignResourcesInterface createDesignInterface() {
		return DesignResourcesInterface.getInstance();
	}

	@Override
	public ExecutionResourcesInterface createExecutionInterface() {
		return ExecutionResourcesInterface.getInstance();
	}
}
