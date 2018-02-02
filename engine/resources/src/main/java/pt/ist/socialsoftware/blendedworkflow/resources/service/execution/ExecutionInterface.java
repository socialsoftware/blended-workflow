package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutionInterface {
	private static Logger logger = LoggerFactory.getLogger(ExecutionInterface.class);

	private static ExecutionInterface instance;

	public static ExecutionInterface getInstance() {
		if (instance == null) {
			instance = new ExecutionInterface();
		}
		return instance;
	}

	private ExecutionInterface() {
	}

}
