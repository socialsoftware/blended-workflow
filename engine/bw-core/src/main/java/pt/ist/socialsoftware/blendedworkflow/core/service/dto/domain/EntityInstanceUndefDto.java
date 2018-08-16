package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;

public class EntityInstanceUndefDto extends EntityInstanceDto {

	public EntityInstanceUndefDto(Entity entity) {
		setEntity(entity.getDto());
	}

	@Override
	public String getId() {
		return "undef";
	}
}
