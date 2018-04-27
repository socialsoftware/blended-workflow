package pt.ist.socialsoftware.blendedworkflow.core.utils;

import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;

public class ModulesFactory {

	public DesignInterface createDesignInterface() {
		return DesignInterface.getInstance();
	}

}
