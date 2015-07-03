package pt.ist.socialsoftware.blendedworkflow.domain;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class OrganizationalModel extends OrganizationalModel_Base {

    private final Logger log = Logger.getLogger("OrganizationalModel");

    public OrganizationalModel() {
        BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
        blendedWorkflow.setOrganizationalModel(this);
        log.info("OrganizationalModel created.");
    }

    public User getUser(String userID) throws BWException {
        for (User user : getUsersSet()) {
            if (user.getID().equals(userID)) {
                return user;
            }
        }
        throw new BWException(BlendedWorkflowError.INVALID_USER);
    }

    public Role getRole(String roleName) throws BWException {
        for (Role role : getRolesSet()) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }
        throw new BWException(BlendedWorkflowError.INVALID_ROLE);
    }

}
