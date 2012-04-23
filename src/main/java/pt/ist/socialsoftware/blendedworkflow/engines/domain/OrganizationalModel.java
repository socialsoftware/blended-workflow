package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import org.apache.log4j.Logger;

public class OrganizationalModel extends OrganizationalModel_Base {
    
	private Logger log = Logger.getLogger("OrganizationalModel");
	
    public OrganizationalModel() {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		blendedWorkflow.setOrganizationalModel(this);
		log.info("OrganizationalModel created.");
    }
    
}
