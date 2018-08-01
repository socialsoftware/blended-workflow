package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

public class LinkDto {
	MulConditionDto mulCondition;
	EntityInstanceDto entityInstance;

	public LinkDto() {
	}

	public LinkDto(EntityInstance sourceEntityInstance, MulCondition mulCondition) {
		this.mulCondition = mulCondition.getDTO();
		this.entityInstance = mulCondition.getRelationBW().getRelationInstanceSet().stream()
				.filter(ri -> ri.getEntityInstanceByRolename(sourceEntityInstance, mulCondition.getRolename()) != null)
				.map(ri -> ri.getEntityInstanceByRolename(sourceEntityInstance, mulCondition.getRolename()))
				.map(ei -> new EntityInstanceDto(ei, Depth.SHALLOW)).findFirst().orElse(null);

	}

	public MulConditionDto getMulCondition() {
		return this.mulCondition;
	}

	public void setMulCondition(MulConditionDto mulCondition) {
		this.mulCondition = mulCondition;
	}

	public EntityInstanceDto getEntityInstance() {
		return this.entityInstance;
	}

	public void setEntityInstance(EntityInstanceDto entityInstance) {
		this.entityInstance = entityInstance;
	}

}
