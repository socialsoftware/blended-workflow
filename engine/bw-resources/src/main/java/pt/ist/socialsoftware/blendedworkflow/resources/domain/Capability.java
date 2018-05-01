package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.CapabilityDTO;

public class Capability extends Capability_Base {
    private static Logger logger = LoggerFactory.getLogger(Capability.class);

    public Capability(ResourceModel resourceModel, String name, String description) throws RMException {
        setResourceModel(resourceModel);
        setName(name);
        setDescription(description);
    }

    @Override
    public void setName(String name) throws RMException {
        if (name == null) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Missing capability name");
        }

        if (checkUniqueName(name)) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Capability name is not unique");
        }

        super.setName(name);
    }

    public void delete() {
        getRalExprHasCapabilitySet().stream().forEach(expr -> removeRalExprHasCapability(expr));
        getPersonSet().stream().forEach(p -> removePerson(p));
        setResourceModel(null);
        deleteDomainObject();
    }


    private boolean checkUniqueName(String name) {
        return getResourceModel()
                .getCapabilitySet()
                .stream()
                .anyMatch(capability -> name.equals(capability.getName()));
    }

    public CapabilityDTO getDTO() {
        return new CapabilityDTO(getResourceModel().getSpec().getSpecId(),
                getName(),
                getDescription());
    }
}
