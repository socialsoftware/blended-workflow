package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;

public class AttributeInstanceToDefineDto extends AttributeInstanceDto {
	public AttributeInstanceToDefineDto(EntityInstanceToDefineDto entityInstance, Attribute attribute) {
		super(null, new AttributeDto(attribute), "", new ArrayList<>(), ProductInstanceState.UNDEFINED);

		setToDefine(true);

		entityInstance.addAttributeInstance(this);
	}

}
