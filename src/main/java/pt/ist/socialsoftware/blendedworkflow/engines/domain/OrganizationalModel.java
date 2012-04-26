package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import org.apache.log4j.Logger;

public class OrganizationalModel extends OrganizationalModel_Base {
    
	private Logger log = Logger.getLogger("OrganizationalModel");
	
    public OrganizationalModel() {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		blendedWorkflow.setOrganizationalModel(this);
		log.info("OrganizationalModel created.");
    }
    
    public void setDefault(Task task) {
    	for (Role role : getRoles()) {
    		if (role.getName().equals("Admin")) {
    			task.setRole(role);
    		}
    	}
    	
    	for (User user : getUsers()) {
    		if (user.getID().equals("BlendedWorkflow")) {
    			task.setUser(user);
    		}
    	}
    }
    
    public void setDefault(Goal goal) {
    	for (Role role : getRoles()) {
    		if (role.getName().equals("Admin")) {
    			goal.setRole(role);
    		}
    	}
    	
    	for (User user : getUsers()) {
    		if (user.getID().equals("BlendedWorkflow")) {
    			goal.setUser(user);
    		}
    	}
    }

	public User getUser(String userID) {
    	for (User user : getUsers()) {
    		if (user.getID().equals(userID)) {
    			return user;
    		}
    	}
    	return null;
	}
}
