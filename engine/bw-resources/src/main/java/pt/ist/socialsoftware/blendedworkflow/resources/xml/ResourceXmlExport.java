package pt.ist.socialsoftware.blendedworkflow.resources.xml;

import org.jdom2.Element;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlExport;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;

import java.util.Set;

public class ResourceXmlExport extends SpecXmlExport {
    @Override
    public void exportModules(Specification spec, Element element) {
        exportResourceModel(element, spec.getResourceModel());
    }

    private void exportResourceModel(Element element, ResourceModel resourceModel) {
        Element parent = new Element("resource-model");

        exportCapabilities(parent, resourceModel.getCapabilitySet());
        exportPersons(parent, resourceModel.getPersonSet());
        exportPositions(parent, resourceModel.getPositionSet());
        exportRoles(parent, resourceModel.getRoleSet());
        exportUnits(parent, resourceModel.getUnitSet());
        exportIsPersons(parent, resourceModel.getEntityIsPersonSet());

        element.addContent(parent);
    }

    private void exportCapabilities(Element parent, Set<Capability> capabilitySet) {
        Element capabilitiesElement = new Element("capabilities");

        capabilitySet.stream().forEach(capability -> {
            Element element = new Element("capability");
            element.setAttribute("name", capability.getName());
            if (capability.getDescription() != null) {
                element.setAttribute("description", capability.getDescription());
            }
            capabilitiesElement.addContent(element);
        });

        parent.addContent(capabilitiesElement);
    }

    private void exportPersons(Element parent, Set<Person> personSet) {
        Element personsElement = new Element("persons");

        personSet.stream().forEach(person -> {
            Element element = new Element("person");
            element.setAttribute("name", person.getName());
            if (person.getDescription() != null) {
                element.setAttribute("description", person.getDescription());
            }
            Element positionsElement = new Element("positions");
            person.getPositionSet().stream().forEach(position -> {
                Element ele = new Element(position.getName());
                positionsElement.addContent(ele);
            });
            element.addContent(positionsElement);

            Element capabilitiesElement = new Element("capabilities");
            person.getCapabilitySet().stream().forEach(capability -> {
                Element ele = new Element(capability.getName());
                capabilitiesElement.addContent(ele);
            });
            element.addContent(capabilitiesElement);


            personsElement.addContent(element);
        });

        parent.addContent(personsElement);
    }

    private void exportPositions(Element parent, Set<Position> positionSet) {
        Element positionsElement = new Element("positions");

        positionSet.stream().forEach(position -> {
            Element element = new Element("position");

            element.setAttribute("name", position.getName());
            element.setAttribute("unit", position.getUnit().getName());
            if (position.getDescription() != null) {
                element.setAttribute("description", position.getDescription());
            }
            if (position.getReportsTo() != null) {
                element.setAttribute("reports-to", position.getReportsTo().getName());
            }

            Element rolesElement = new Element("roles");
            position.getRoleSet().stream().forEach(role -> {
                Element ele = new Element(role.getName());
                rolesElement.addContent(ele);
            });
            element.addContent(rolesElement);

            Element delegatesElement = new Element("delegate-to");
            position.getCanDelegateWorkToSet().stream().forEach(pos -> {
                Element ele = new Element(pos.getName());
                delegatesElement.addContent(ele);
            });
            element.addContent(delegatesElement);

            positionsElement.addContent(element);
        });

        parent.addContent(positionsElement);
    }

    private void exportRoles(Element parent, Set<Role> roleSet) {
        Element rolesElement = new Element("roles");

        roleSet.stream().forEach(role -> {
            Element element = new Element("role");
            element.setAttribute("name", role.getName());
            if (role.getDescription() != null) {
                element.setAttribute("description", role.getDescription());
            }
            rolesElement.addContent(element);
        });

        parent.addContent(rolesElement);
    }

    private void exportUnits(Element parent, Set<Unit> unitSet) {
        Element unitsElement = new Element("units");

        unitSet.stream().forEach(unit -> {
            Element element = new Element("unit");
            element.setAttribute("name", unit.getName());
            if (unit.getDescription() != null) {
                element.setAttribute("description", unit.getDescription());
            }
            unitsElement.addContent(element);
        });

        parent.addContent(unitsElement);
    }

    private void exportIsPersons(Element parent, Set<Entity> entityIsPersonSet) {
        Element eipElement = new Element("entity-is-person");

        entityIsPersonSet.stream().forEach(entity -> {
            Element element = new Element(entity.getName());
            eipElement.addContent(element);
        });

        parent.addContent(eipElement);
    }
}
