package pt.ist.socialsoftware.blendedworkflow.organizationalmanager;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.OrganizationalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class OrganizationalManager {

	private final Logger log = Logger.getLogger("OrganizationalManager");
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
	 * 
	 * @param role
	 *            The created Role.
	 */
	public void notifyCreatedRole(Role role) {
		log.info("Role " + role.getName() + " created.");
		getBwPresentation().addRole(role.getExternalId(), role.getName());
	}

	/**
	 * Notify the BWPresentation of created Users.
	 * 
	 * @param user
	 *            The created User.
	 */
	public void notifyCreatedUser(User user) {
		log.info("User " + user.getName() + " created.");
		getBwPresentation().addUser(user.getExternalId(), user.getName());
	}

	/**
	 * Update the BWPresentation with all Roles and Users created.
	 */
	public void updateBWPresentation() {
		OrganizationalModel organizationalModel = BlendedWorkflow.getInstance()
				.getOrganizationalModel();
		for (Role role : organizationalModel.getRolesSet()) {
			notifyCreatedRole(role);
		}
		for (User user : organizationalModel.getUsersSet()) {
			notifyCreatedUser(user);
		}
	}

	/**
	 * TODO: OrganizationalManager:checkPermissions
	 * 
	 * @throws BWException
	 */
	public Boolean loginUser(String userID, String userPassword) {
		OrganizationalModel organizationalModel = BlendedWorkflow.getInstance()
				.getOrganizationalModel();
		Boolean valid = false;
		for (User user : organizationalModel.getUsersSet()) {
			if (user.getID().equals(userID)
					&& user.getPassword().equals(userPassword)) {
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
