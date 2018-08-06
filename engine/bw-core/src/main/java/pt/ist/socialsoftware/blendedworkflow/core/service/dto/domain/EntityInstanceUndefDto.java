package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;

public class EntityInstanceUndefDto extends EntityInstanceDto {

	public EntityInstanceUndefDto(Entity entity) {
		setEntity(entity.getDto());
		setAttributeInstances(entity.getAttributeSet().stream().map(a -> new AttributeInstanceUndefDto(a))
				.collect(Collectors.toList()));
	}

	@Override
	public String getId() {
		return "undef";
	}
}
