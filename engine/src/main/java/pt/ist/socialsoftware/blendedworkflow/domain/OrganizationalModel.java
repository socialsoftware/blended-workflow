package pt.ist.socialsoftware.blendedworkflow.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class OrganizationalModel extends OrganizationalModel_Base {

    private final Logger log = LoggerFactory.getLogger("OrganizationalModel");

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
        throw new BWException(BWErrorType.INVALID_USER);
    }

    public Role getRole(String roleName) throws BWException {
        for (Role role : getRolesSet()) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }
        throw new BWException(BWErrorType.INVALID_ROLE);
    }

}
