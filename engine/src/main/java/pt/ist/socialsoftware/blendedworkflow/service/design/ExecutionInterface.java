package pt.ist.socialsoftware.blendedworkflow.service.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;

public class ExecutionInterface {
	private static Logger log = LoggerFactory.getLogger(ExecutionInterface.class);

	private static ExecutionInterface instance;

	public static ExecutionInterface getInstance() {
		if (instance == null) {
			instance = new ExecutionInterface();
		}
		return instance;
	}

	private ExecutionInterface() {
	}

	private BlendedWorkflow getBlendedWorkflow() {
		return BlendedWorkflow.getInstance();
	}

}
