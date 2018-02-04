package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;

public class Capability extends Capability_Base {
    private static Logger logger = LoggerFactory.getLogger(Capability.class);
    
    public Capability(ResourceModel resourceModel, String name, String description) throws RMException {
        setResourceModel(resourceModel);

        if (name == null) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Missing capability name");
        }

        if (checkUniqueName(name)) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Capability name is not unique");
        }

        logger.debug("Creating a new Capability object");

        setName(name);
        setDescription(description);
    }

    public void delete() {
        setResourceModel(null);
        deleteDomainObject();
    }

    private boolean checkUniqueName(String name) {
        return getResourceModel()
                .getCapabilitySet()
                .stream()
                .anyMatch(capability -> name.equals(capability.getName()));
    }
}
