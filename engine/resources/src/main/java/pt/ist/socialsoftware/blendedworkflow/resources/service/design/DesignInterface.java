package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;

public class DesignInterface {
	private static Logger log = LoggerFactory.getLogger(DesignInterface.class);

	private static DesignInterface instance;

	public static DesignInterface getInstance() {
		if (instance == null) {
			instance = new DesignInterface();
		}
		return instance;
	}

	private pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface workflowDesigner;

	private DesignInterface() {
		workflowDesigner = pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance();
	}

	public ResourceModel createResourceModel(String specId) {
		Specification spec = workflowDesigner.getSpecBySpecId(specId);
		ResourceModel resourceModel = new ResourceModel();
		spec.setResourceModel(resourceModel);

		return resourceModel;
	}

}
