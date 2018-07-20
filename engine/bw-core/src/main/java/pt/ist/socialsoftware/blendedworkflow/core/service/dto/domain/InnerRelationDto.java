package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;

public class InnerRelationDto {
	private EntityDto sourceEntity;
	private EntityDto targetEntity;
	private MulConditionDto mulCondition;
	private Set<ProductInstanceDTO> productInstanceSet;

	public static InnerRelationDto createInnerRelationDTO(Entity entity, MulCondition mulCondition) {
		InnerRelationDto innerRelationDTO = new InnerRelationDto();
		innerRelationDTO.setSourceEntity(entity.getDTO());
		innerRelationDTO.setTargetEntity(mulCondition.getTargetEntity().getDTO());
		innerRelationDTO.setMulCondition(mulCondition.getDTO());
		innerRelationDTO.setProductInstanceSet(new HashSet<ProductInstanceDTO>());
		return innerRelationDTO;
	}

	public InnerRelationDto() {
	}

	public EntityDto getSourceEntity() {
		return sourceEntity;
	}

	public void setSourceEntity(EntityDto sourceEntity) {
		this.sourceEntity = sourceEntity;
	}

	public EntityDto getTargetEntity() {
		return targetEntity;
	}

	public void setTargetEntity(EntityDto targetEntity) {
		this.targetEntity = targetEntity;
	}

	public MulConditionDto getMulCondition() {
		return mulCondition;
	}

	public void setMulCondition(MulConditionDto mulCondition) {
		this.mulCondition = mulCondition;
	}

	public Set<ProductInstanceDTO> getProductInstanceSet() {
		return productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDTO> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

}
