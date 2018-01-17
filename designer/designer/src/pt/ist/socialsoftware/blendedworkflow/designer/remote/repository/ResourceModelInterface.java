package pt.ist.socialsoftware.blendedworkflow.designer.remote.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceModelInterface {
	private static Logger logger = LoggerFactory.getLogger(ResourceModelInterface.class);

	final static String BASE_URL = "http://localhost:8080";

	private static ResourceModelInterface instance = null;

	public static ResourceModelInterface getInstance() {
		if (instance == null) {
			instance = new ResourceModelInterface();
		}
		return instance;
	}
}
