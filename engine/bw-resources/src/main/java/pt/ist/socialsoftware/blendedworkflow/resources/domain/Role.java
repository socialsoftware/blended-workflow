package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.RoleDto;

public class Role extends Role_Base {
    private static Logger logger = LoggerFactory.getLogger(Role.class);

    public Role(ResourceModel resourceModel, String name, String description) throws RMException {
        setResourceModel(resourceModel);
        setName(name);
        setDescription(description);
    }

    @Override
    public void setName(String name) throws RMException {
        if (name == null) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Missing role name");
        }

        if (checkUniqueName(name)) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Role name is not unique");
        }

        super.setName(name);
    }

    public void delete() {
        getRalExprHasRoleSet().stream().forEach(e -> removeRalExprHasRole(e));
        getPositionSet().stream().forEach(p -> removePosition(p));
        setResourceModel(null);
        deleteDomainObject();
    }

    private boolean checkUniqueName(String name) {
        return getResourceModel()
                .getRoleSet()
                .stream()
                .anyMatch(role -> name.equals(role.getName()));
    }

    public RoleDto getDTO() {
        return new RoleDto(getResourceModel().getSpec().getSpecId(),
                getName(),
                getDescription());
    }
}
