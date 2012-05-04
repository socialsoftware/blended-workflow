package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class OrganizationalModel extends OrganizationalModel_Base {
    
	private Logger log = Logger.getLogger("OrganizationalModel");
	
    public OrganizationalModel() {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		blendedWorkflow.setOrganizationalModel(this);
		log.info("OrganizationalModel created.");
    }

	public User getUser(String userID) throws BlendedWorkflowException {
    	for (User user : getUsers()) {
    		if (user.getID().equals(userID)) {
    			return user;
    		}
    	}
    	throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_USER);
	}
	
	public Role getRole(String roleName) throws BlendedWorkflowException {
    	for (Role role : getRoles()) {
    		if (role.getName().equals(roleName)) {
    			return role;
    		}
    	}
    	throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_ROLE);
	}
	
}
