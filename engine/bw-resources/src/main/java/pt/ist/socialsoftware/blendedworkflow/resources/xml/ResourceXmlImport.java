package pt.ist.socialsoftware.blendedworkflow.resources.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlImport;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceXmlImport extends SpecXmlImport {
    private static Logger logger = LoggerFactory.getLogger(ResourceXmlImport.class);

    private DesignInterface resourceDesigner;

    public ResourceXmlImport() {
        resourceDesigner = DesignInterface.getInstance();
    }

    @Override
    public void importModules(Element specElement, Specification spec) {
        resourceDesigner.createResourceModel(spec.getSpecId());

        importResourceModel(specElement.getChild("resource-model"), spec);
        // importResourceRules(specElement, spec);
    }

    private void importResourceModel(Element specElement, Specification spec) {
        importCapabilities(specElement, spec);

        importUnits(specElement, spec);

        importRoles(specElement, spec);

        importPositions(specElement, spec);

        importPersons(specElement, spec);
    }

    private void importCapabilities(Element specElement, Specification spec) {
        specElement.getChild("capabilities").getChildren().stream().forEach(capability -> {
            resourceDesigner.createCapability(new CapabilityDTO(
                    spec.getSpecId(),
                    capability.getAttribute("name").getValue(),
                    (capability.getAttribute("description") != null) ? capability.getAttribute("description").getValue() : null
            ));
        });
    }

    private void importUnits(Element specElement, Specification spec) {
        specElement.getChild("units").getChildren().stream().forEach(unit -> {
            resourceDesigner.createUnit(new UnitDTO(
                    spec.getSpecId(),
                    unit.getAttribute("name").getValue(),
                    (unit.getAttribute("description") != null) ? unit.getAttribute("description").getValue() : null
            ));
        });
    }

    private void importRoles(Element specElement, Specification spec) {
        specElement.getChild("roles").getChildren().stream().forEach(role -> {
            resourceDesigner.createRole(new RoleDTO(
                    spec.getSpecId(),
                    role.getAttribute("name").getValue(),
                    (role.getAttribute("description") != null) ? role.getAttribute("description").getValue() : null
            ));
        });
    }

    private void importPositions(Element specElement, Specification spec) {
        specElement.getChild("positions").getChildren().stream().forEach(position -> {
            PositionDTO positionDTO = new PositionDTO(
                    spec.getSpecId(),
                    position.getAttribute("name").getValue(),
                    position.getAttribute("unit").getValue(),
                    (position.getAttribute("description") != null) ? position.getAttribute("description").getValue() : null
            );

            resourceDesigner.createPosition(positionDTO);
        });

        specElement.getChild("positions").getChildren().stream().forEach(position -> {
            PositionDTO positionDTO = new PositionDTO(
                    spec.getSpecId(),
                    position.getAttribute("name").getValue(),
                    position.getAttribute("unit").getValue(),
                    (position.getAttribute("description") != null) ? position.getAttribute("description").getValue() : null
            );
            List<String> roles = position.getChild("roles").getChildren().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList());
            positionDTO.setRoles(roles);

            List<String> delegatesTo = position.getChild("delegate-to").getChildren().stream()
                    .map(pos -> pos.getName())
                    .collect(Collectors.toList());
            positionDTO.setDelegateToRelations(delegatesTo);

            if (position.getChild("reports-to") != null) {
                positionDTO.setReportsTo(position.getChild("reports-to").getValue());
            }

            resourceDesigner.initPosition(positionDTO);
        });
    }

    private void importPersons(Element specElement, Specification spec) {
        specElement.getChild("persons").getChildren().stream().forEach(person -> {
            PersonDTO personDTO = new PersonDTO(
                    spec.getSpecId(),
                    person.getAttribute("name").getValue(),
                    (person.getAttribute("description") != null) ? person.getAttribute("description").getValue() : null
            );

            List<String> capabilities = person.getChild("capabilities").getChildren().stream()
                    .map(capability -> capability.getName())
                    .collect(Collectors.toList());
            personDTO.setCapabilities(capabilities);

            List<String> positions = person.getChild("positions").getChildren().stream()
                    .map(position -> position.getName())
                    .collect(Collectors.toList());
            personDTO.setPositions(positions);


            resourceDesigner.createPerson(personDTO);
        });
    }
}
