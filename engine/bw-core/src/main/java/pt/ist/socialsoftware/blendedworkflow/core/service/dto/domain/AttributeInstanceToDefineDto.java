package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;

public class AttributeInstanceToDefineDto extends AttributeInstanceDto {

	public AttributeInstanceToDefineDto(EntityInstanceToDefineDto entityInstance, Attribute attribute) {
		setExternalId(null);
		setAttribute(new AttributeDto(attribute));
		setValue(null);

		entityInstance.addAttributeInstance(this);
	}

}
