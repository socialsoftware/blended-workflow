package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;

public class InnerRelationDto {
	private EntityDto sourceEntity;
	private EntityDto targetEntity;
	private MulConditionDto mulCondition;
	private Set<EntityInstanceDto> entityInstanceSet;

	public static InnerRelationDto createInnerRelationDTO(Entity entity, MulCondition mulCondition) {
		InnerRelationDto innerRelationDTO = new InnerRelationDto();
		innerRelationDTO.setSourceEntity(entity.getDTO());
		innerRelationDTO.setTargetEntity(mulCondition.getTargetEntity().getDTO());
		innerRelationDTO.setMulCondition(mulCondition.getDTO());
		innerRelationDTO.setEntitytInstanceSet(new HashSet<EntityInstanceDto>());
		return innerRelationDTO;
	}

	public EntityDto getSourceEntity() {
		return this.sourceEntity;
	}

	public void setSourceEntity(EntityDto sourceEntity) {
		this.sourceEntity = sourceEntity;
	}

	public EntityDto getTargetEntity() {
		return this.targetEntity;
	}

	public void setTargetEntity(EntityDto targetEntity) {
		this.targetEntity = targetEntity;
	}

	public MulConditionDto getMulCondition() {
		return this.mulCondition;
	}

	public void setMulCondition(MulConditionDto mulCondition) {
		this.mulCondition = mulCondition;
	}

	public Set<EntityInstanceDto> getEntityInstanceSet() {
		return this.entityInstanceSet;
	}

	public void setEntitytInstanceSet(Set<EntityInstanceDto> entityInstanceSet) {
		this.entityInstanceSet = entityInstanceSet;
	}

}
