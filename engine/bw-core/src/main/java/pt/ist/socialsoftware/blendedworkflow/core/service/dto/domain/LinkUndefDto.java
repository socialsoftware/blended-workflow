package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;
import java.util.List;

import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;

public class LinkUndefDto extends LinkDto {

	public LinkUndefDto(MulCondition mulCondition) {
		setMulCondition(mulCondition.getDto());

		List<EntityInstanceDto> entityInstances = new ArrayList<EntityInstanceDto>();
		entityInstances.add(new EntityInstanceUndefDto(mulCondition.getTargetEntity()));
		setEntityInstances(entityInstances);
	}

}
