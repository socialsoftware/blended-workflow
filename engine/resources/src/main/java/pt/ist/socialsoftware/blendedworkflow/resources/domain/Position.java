package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PositionDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Position extends Position_Base {
    private static Logger logger = LoggerFactory.getLogger(Role.class);

    public Position(ResourceModel resourceModel, String name, Unit unit) throws RMException {
        logger.debug("Creating a new Position object");
        setName(name);
        setUnit(unit);
        setResourceModel(resourceModel);
    }

    public Position(ResourceModel resourceModel, String name, Unit unit, List<Role> roles, List<Position> delegates, Position reports) {
        this(resourceModel, name, unit);
        roles.stream().forEach(role -> addRole(role));
        delegates.stream().forEach(position -> addCanDelegateWorkTo(position));
        setReportsTo(reports);
    }

    @Override
    public void setName(String name) throws RMException {
        if (name == null) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Missing position name");
        }

        if (checkUniqueName(name)) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Position name is not unique");
        }

        super.setName(name);
    }

    @Override
    public void setUnit(Unit unit) throws RMException {
        if (unit == null) {
            throw new RMException(RMErrorType.MISSING_POSITION_UNIT, "Position must belong to an unit");
        }

        super.setUnit(unit);
    }

    public void delete() {
        setUnit(null);
        getRoleSet().stream().forEach(r -> r.removePosition(this));
        getIsReportedBySet().stream().forEach(p -> p.setReportsTo(null));
        getCanDelegateWorkToSet().stream().forEach(p -> p.removeCanDelegateWorkTo(this));
        getWorkDelegatedBySet().stream().forEach(p -> p.removeWorkDelegatedBy(this));
        setReportsTo(null);
        setResourceModel(null);
        deleteDomainObject();
    }

    private boolean checkUniqueName(String name) {
        return getResourceModel()
                .getPositionSet()
                .stream()
                .anyMatch(position -> name.equals(position.getName()));
    }

    public PositionDTO getDTO() {
        PositionDTO positionDTO = new PositionDTO(
                getResourceModel().getSpec().getSpecId(),
                getName(),
                getUnit().getName());
        if (!getRoleSet().isEmpty()) {
            positionDTO.setRoles(getRoleSet().stream().map(r -> r.getName()).collect(Collectors.toList()));
        }

        if (!getCanDelegateWorkToSet().isEmpty()) {
            positionDTO.setDelegateToRelations(getCanDelegateWorkToSet().stream().map(p -> p.getName()).collect(Collectors.toList()));
        }
        if (getReportsTo() != null) {
            positionDTO.setReportsTo(getReportsTo().getName());
        }

        return positionDTO;
    }
}
