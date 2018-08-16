package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;

public class AttributeInstanceToDefineDto extends AttributeInstanceDto {
	private boolean toDefine = true;

	public AttributeInstanceToDefineDto(EntityInstanceToDefineDto entityInstance, Attribute attribute) {
		super(null, new AttributeDto(attribute), null, new ArrayList<>());

		entityInstance.addAttributeInstance(this);
	}

	public boolean isToDefine() {
		return this.toDefine;
	}

	public void setToDefine(boolean toDefine) {
		this.toDefine = toDefine;
	}

}
