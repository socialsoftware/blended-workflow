package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

public class LinkDto {
	private MulConditionDto mulCondition;
	private List<EntityInstanceDto> entityInstances;
	private boolean toDefine = false;

	public LinkDto() {
	}

	public LinkDto(Set<EntityInstance> entityInstances, MulCondition mulCondition) {
		this.mulCondition = mulCondition.getDto();
		this.entityInstances = entityInstances.stream().sorted(Comparator.comparing(EntityInstance::getId))
				.map(ei -> new EntityInstanceDto(ei, Depth.SHALLOW)).collect(Collectors.toList());
	}

	public String print() {
		return getMulCondition().getRolename() + "["
				+ getEntityInstances().stream().map(ei -> ei.shortPrint()).collect(Collectors.joining(",")) + "]";
	}

	public MulConditionDto getMulCondition() {
		return this.mulCondition;
	}

	public void setMulCondition(MulConditionDto mulCondition) {
		this.mulCondition = mulCondition;
	}

	public List<EntityInstanceDto> getEntityInstances() {
		return this.entityInstances;
	}

	public void setEntityInstances(List<EntityInstanceDto> entityInstances) {
		this.entityInstances = entityInstances;
	}

	public boolean isToDefine() {
		return this.toDefine;
	}

	public void setToDefine(boolean toDefine) {
		this.toDefine = toDefine;
	}

}
