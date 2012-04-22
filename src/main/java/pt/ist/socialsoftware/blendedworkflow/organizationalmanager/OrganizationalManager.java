package pt.ist.socialsoftware.blendedworkflow.organizationalmanager;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;

public class OrganizationalManager {
	
	private Logger log = Logger.getLogger("OrganizationalManager");
	protected BWPresentation bwPresentation = null;

	public BWPresentation getBwPresentation() {
		return bwPresentation;
	}

	public void setBwPresentation(BWPresentation bwPresentation) {
		this.bwPresentation = bwPresentation;
	}
	
	/**
	 * TODO: OrganizationalManager:checkPermissions
	 */
	void checkPermissions() {
		log.info("Check permissions for user: ");
	}

}
