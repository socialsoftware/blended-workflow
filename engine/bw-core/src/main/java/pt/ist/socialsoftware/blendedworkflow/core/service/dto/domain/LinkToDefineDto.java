package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

public class LinkToDefineDto extends LinkDto {
	public LinkToDefineDto(EntityInstanceDto entityInstance, MulCondition mulCondition) {
		super(new HashSet<>(), mulCondition);

		setToDefine(true);

		entityInstance.getLinks().add(this);
	}

	public LinkToDefineDto(EntityInstanceToDefineDto entityInstanceToDefineDto, MulCondition mulCondition,
			boolean innerCreate) {
		this(entityInstanceToDefineDto, mulCondition);

		setInnerCreate(true);
	}

	public LinkToDefineDto(EntityInstanceDto entityInstance, Set<EntityInstance> entityInstanceContext,
			MulCondition mulCondition) {
		this(entityInstance, mulCondition);
		setCandidateEntityInstances(entityInstanceContext.stream().map(ei -> new EntityInstanceDto(ei, Depth.SHALLOW))
				.sorted((ei1, ei2) -> Integer.parseInt(ei1.getId()) - Integer.parseInt(ei2.getId()))
				.collect(Collectors.toList()));

	}

	@Override
	public String print() {
		return super.print() + "["
				+ getCandidateEntityInstances().stream().map(ei -> ei.shortPrint()).collect(Collectors.joining(","))
				+ "]";
	}

}
