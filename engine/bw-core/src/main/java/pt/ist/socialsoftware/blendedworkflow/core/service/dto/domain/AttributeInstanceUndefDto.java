package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;

public class AttributeInstanceUndefDto extends AttributeInstanceDto {

	public AttributeInstanceUndefDto(Attribute attribute) {
		super(null, attribute.getDto(), null, new ArrayList<>(), ProductInstanceState.UNDEFINED);
	}

	public AttributeInstanceUndefDto(Attribute attribute, EntityInstance entityInstance) {
		super(null, attribute.getDto(), null, getDependenceInstances(attribute, entityInstance), ProductInstanceState.UNDEFINED);
	}

	@Override
	public String getValue() {
		return "undef";
	}

}
