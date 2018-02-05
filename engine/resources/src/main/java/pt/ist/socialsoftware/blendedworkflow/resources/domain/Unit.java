package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.UnitDTO;

public class Unit extends Unit_Base {
    private static Logger logger = LoggerFactory.getLogger(Unit.class);

    public Unit(ResourceModel resourceModel, String name, String description) throws RMException {
        setResourceModel(resourceModel);

        if (name == null) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Missing unit name");
        }

        if (checkUniqueName(name)) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Unit name is not unique");
        }

        logger.debug("Creating a new Unit object");

        setName(name);
        setDescription(description);
    }

    public void delete() {
        setResourceModel(null);
        deleteDomainObject();
    }

    private boolean checkUniqueName(String name) {
        return getResourceModel()
                .getUnitSet()
                .stream()
                .anyMatch(unit -> name.equals(unit.getName()));
    }

    public UnitDTO getDTO() {
        return new UnitDTO(getResourceModel().getSpec().getSpecId(),
                getName(),
                getDescription());
    }
}
