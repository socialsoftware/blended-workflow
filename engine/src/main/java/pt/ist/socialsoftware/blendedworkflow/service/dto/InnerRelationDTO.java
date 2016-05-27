package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;

public class InnerRelationDTO {
	private EntityDTO sourceEntity;
	private EntityDTO targetEntity;
	private MulConditionDTO mulCondition;
	private Set<ProductInstanceDTO> productInstanceSet;

	public static InnerRelationDTO createInnerRelationDTO(Entity entity, MulCondition mulCondition) {
		InnerRelationDTO innerRelationDTO = new InnerRelationDTO();
		innerRelationDTO.setSourceEntity(entity.getDTO());
		innerRelationDTO.setTargetEntity(mulCondition.getTargetEntity().getDTO());
		innerRelationDTO.setMulCondition(mulCondition.getDTO());
		innerRelationDTO.setProductInstanceSet(new HashSet<ProductInstanceDTO>());
		return innerRelationDTO;
	}

	public InnerRelationDTO() {
	}

	public EntityDTO getSourceEntity() {
		return sourceEntity;
	}

	public void setSourceEntity(EntityDTO sourceEntity) {
		this.sourceEntity = sourceEntity;
	}

	public EntityDTO getTargetEntity() {
		return targetEntity;
	}

	public void setTargetEntity(EntityDTO targetEntity) {
		this.targetEntity = targetEntity;
	}

	public MulConditionDTO getMulCondition() {
		return mulCondition;
	}

	public void setMulCondition(MulConditionDTO mulCondition) {
		this.mulCondition = mulCondition;
	}

	public Set<ProductInstanceDTO> getProductInstanceSet() {
		return productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDTO> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

}
