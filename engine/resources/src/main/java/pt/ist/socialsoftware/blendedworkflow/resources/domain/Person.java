package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PersonDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Person extends Person_Base {
    private static Logger logger = LoggerFactory.getLogger(Person.class);

    public Person(ResourceModel resourceModel, String name, String description) throws RMException {
        logger.debug("Creating a new Position object");
        setResourceModel(resourceModel);
        setName(name);
        setDescription(description);
    }
    
    public Person(ResourceModel resourceModel, String name, String description,
                  List<Position> positions, List<Capability> capabilities) {
        this(resourceModel, name, description);
        positions.stream().forEach(p -> addPosition(p));
        capabilities.stream().forEach(c -> addCapability(c));
    }

    public void delete() {
        getEntityInstanceSet().stream().forEach(e -> removeEntityInstance(e));
        getPositionSet().stream().forEach(p -> removePosition(p));
        getCapabilitySet().stream().forEach(c -> removeCapability(c));
        setResourceModel(null);
        deleteDomainObject();
    }

    @Override
    public void setName(String name) throws RMException {
        if (name == null) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Missing person name");
        }

        if (checkUniqueName(name)) {
            throw new RMException(RMErrorType.INVALID_RESOURCE_NAME, "Person name is not unique");
        }

        super.setName(name);
    }

    private boolean checkUniqueName(String name) {
        return getResourceModel()
                .getPersonSet()
                .stream()
                .anyMatch(position -> name.equals(position.getName()));
    }

    public PersonDTO getDTO() {
        PersonDTO personDTO = new PersonDTO(
                getResourceModel().getSpec().getSpecId(),
                getName(),
                getDescription()
            );

        personDTO.setPositions(getPositionSet().stream().map(p -> p.getName()).collect(Collectors.toList()));
        personDTO.setCapabilities(getCapabilitySet().stream().map(c -> c.getName()).collect(Collectors.toList()));

        return personDTO;
    }
}
