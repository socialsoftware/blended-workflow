package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

public class InnerRelationInstanceDto {
	private MulConditionDto mulConditionDto;
	private Set<EntityInstanceDto> entityInstanceSet;

	public InnerRelationInstanceDto() {
	}

	public Set<EntityInstanceDto> getEntityInstanceSet() {
		return this.entityInstanceSet;
	}

	public void setEntityInstanceSet(Set<EntityInstanceDto> entityInstanceSet) {
		this.entityInstanceSet = entityInstanceSet;
	}

	public MulConditionDto getMulConditionDto() {
		return this.mulConditionDto;
	}

	public void setMulConditionDto(MulConditionDto mulConditionDTO) {
		this.mulConditionDto = mulConditionDTO;
	}

}
