package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;

public class InnerRelationDto {
	private EntityDto sourceEntity;
	private EntityDto targetEntity;
	private MulConditionDto mulCondition;
	private Set<ProductInstanceDto> productInstanceSet;

	public static InnerRelationDto createInnerRelationDTO(Entity entity, MulCondition mulCondition) {
		InnerRelationDto innerRelationDTO = new InnerRelationDto();
		innerRelationDTO.setSourceEntity(entity.getDTO());
		innerRelationDTO.setTargetEntity(mulCondition.getTargetEntity().getDTO());
		innerRelationDTO.setMulCondition(mulCondition.getDTO());
		innerRelationDTO.setProductInstanceSet(new HashSet<ProductInstanceDto>());
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

	public Set<ProductInstanceDto> getProductInstanceSet() {
		return this.productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDto> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

}
