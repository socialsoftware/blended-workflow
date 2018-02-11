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
    private static Logger logger = LoggerFactory.getLogger(Position.class);

    public Position(ResourceModel resourceModel, String name, String description, Unit unit) throws RMException {
        logger.debug("Creating a new Position object");
        setResourceModel(resourceModel);
        setName(name);
        setUnit(unit);
        setDescription(description);
    }

    public Position(ResourceModel resourceModel, String name, String description, Unit unit, List<Role> roles, List<Position> delegates, Position reports) {
        this(resourceModel, name, description, unit);
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
        super.setUnit(null);
        getPersonSet().stream().forEach(p -> removePerson(p));
        getRoleSet().stream().forEach(r -> removeRole(r));
        getCanDelegateWorkToSet().stream().forEach(p -> removeCanDelegateWorkTo(p));
        getWorkDelegatedBySet().stream().forEach(p -> removeWorkDelegatedBy(p));
        getIsReportedBySet().stream().forEach(p -> removeIsReportedBy(p));
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
                getUnit().getName(),
                getDescription());
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
