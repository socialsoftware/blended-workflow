package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;

import java.util.HashSet;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;

public class ResourceEntityInstanceDto extends EntityInstanceDto {
    public ResourceEntityInstanceDto(EntityInstance entityInstance, Depth depth, Person person) {
        setExternalId(entityInstance.getExternalId());
        setId(entityInstance.getId());
        setEntity(entityInstance.getEntity().getDto());

        if (depth.equals(Depth.DEEP)) {
            this.setAttributeInstances(entityInstance.getEntity().getAttributeSet().stream()
                    .filter(attribute -> attribute.getInforms() == null ||
                            attribute.getInforms().hasEligiblePerson(person, entityInstance.getWorkflowInstance(), new HashSet<>()))
                    .sorted((a1, a2) -> a1.getName().compareTo(a2.getName()))
                    .map(a -> getAttributeInstanceDto(entityInstance, a)).collect(Collectors.toList()));
            this.setLinks(entityInstance.getEntity().getMulConditions().stream()
                    .sorted((m1, m2) -> m1.getRolename().compareTo(m2.getRolename()))
                    .map(m -> getLinkDto(entityInstance, m)).collect(Collectors.toList()));
        }
    }
}
