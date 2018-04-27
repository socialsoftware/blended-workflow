package pt.ist.socialsoftware.blendedworkflow.resources.utils;

import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;

public class ResourcesFactory extends ModulesFactory {

	@Override
	public DesignResourcesInterface createDesignInterface() {
		return DesignResourcesInterface.getInstance();
	}

}
