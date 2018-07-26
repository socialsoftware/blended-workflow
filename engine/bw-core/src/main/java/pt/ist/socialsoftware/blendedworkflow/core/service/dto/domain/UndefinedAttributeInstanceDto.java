package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;

public class UndefinedAttributeInstanceDto extends AttributeInstanceDto {

	public UndefinedAttributeInstanceDto(Attribute attribute) {
		setAttribute(attribute.getDTO());
	}

}
