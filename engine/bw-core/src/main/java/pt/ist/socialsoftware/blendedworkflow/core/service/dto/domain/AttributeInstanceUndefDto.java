package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;

public class AttributeInstanceUndefDto extends AttributeInstanceDto {

	public AttributeInstanceUndefDto(Attribute attribute) {
		setAttribute(attribute.getDto());
	}

	@Override
	public String getValue() {
		return "undef";
	}

}
