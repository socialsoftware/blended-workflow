package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;

public class UndefinedEntityInstanceDto extends EntityInstanceDto {

	public UndefinedEntityInstanceDto(Entity entity) {
		setEntity(entity.getDTO());
		setAttributes(entity.getAttributeSet().stream().map(a -> new UndefinedAttributeInstanceDto(a))
				.collect(Collectors.toList()));
	}
}
