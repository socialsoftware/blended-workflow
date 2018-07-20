package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.UnitDto;

public class Unit extends Unit_Base {
    private static Logger logger = LoggerFactory.getLogger(Unit.class);

    public Unit(ResourceModel resourceModel, String name, String description) throws RMException {
        setResourceModel(resourceModel);
        setName(name);
        setDescription(description);
    }

    @Override
    public void setName(String name) throws RMException {
        if (name == null) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Missing unit name");
        }

        if (checkUniqueName(name)) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Unit name is not unique");
        }

        super.setName(name);
    }

    public void delete() {
        getRalExprHasRoleSet().stream().forEach(e -> removeRalExprHasRole(e));
        getRalExprHasUnitSet().stream().forEach(e -> removeRalExprHasUnit(e));
        getRalExprSharesRoleSet().stream().forEach(e -> removeRalExprSharesRole(e));
        getPositionSet().stream().forEach(p -> removePosition(p));
        setResourceModel(null);
        deleteDomainObject();
    }

    private boolean checkUniqueName(String name) {
        return getResourceModel()
                .getUnitSet()
                .stream()
                .anyMatch(unit -> name.equals(unit.getName()));
    }

    public UnitDto getDTO() {
        return new UnitDto(getResourceModel().getSpec().getSpecId(),
                getName(),
                getDescription());
    }
}
