package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesignInterface {
	private static Logger log = LoggerFactory.getLogger(DesignInterface.class);

	private static DesignInterface instance;

	public static DesignInterface getInstance() {
		if (instance == null) {
			instance = new DesignInterface();
		}
		return instance;
	}

	private DesignInterface() {
	}

}
