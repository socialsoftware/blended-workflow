package pt.ist.socialsoftware.blendedworkflow.organizationalmanager;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.OrganizationalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;

public class OrganizationalManager {

	private Logger log = Logger.getLogger("OrganizationalManager");
	protected BWPresentation bwPresentation = null;
	User activeUser = null;

	public BWPresentation getBwPresentation() {
		return bwPresentation;
	}

	public void setBwPresentation(BWPresentation bwPresentation) {
		this.bwPresentation = bwPresentation;
	}

	/**
	 * Notify the BWPresentation of created Roles.
	 * @param role The created Role.
	 */
	public void notifyCreatedRole(Role role) {
		log.info("Role " + role.getName() + " created.");
		getBwPresentation().addRole(role.getOID(), role.getName());
	}

	/**
	 * Notify the BWPresentation of created Users.
	 * @param user The created User.
	 */
	public void notifyCreatedUser(User user) {
		log.info("User " + user.getName() + " created.");
		getBwPresentation().addUser(user.getOID(), user.getName());
	}

	/**
	 * Update the BWPresentation with all Roles and Users created.
	 */
	public void updateBWPresentation() {
		OrganizationalModel organizationalModel = BlendedWorkflow.getInstance().getOrganizationalModel();
		for (Role role : organizationalModel.getRoles()) {
			notifyCreatedRole(role);
		}
		for (User user : organizationalModel.getUsers()) {
			notifyCreatedUser(user);
		}
	}

	/**
	 * TODO: OrganizationalManager:checkPermissions
	 * @throws BlendedWorkflowException 
	 */
	public Boolean loginUser(String userID, String userPassword) {
		OrganizationalModel organizationalModel = BlendedWorkflow.getInstance().getOrganizationalModel();
		Boolean valid = false;
		for (User user : organizationalModel.getUsers()) {
			if (user.getID().equals(userID) && user.getPassword().equals(userPassword)) {
				this.activeUser = user;
				valid = true;
			}
		}

		if (!valid) {
			return false;
		} else {
			return true;
		}
	}

	public User getActiveUser() {
		return this.activeUser;
	}

}
