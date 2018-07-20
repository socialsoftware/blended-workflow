package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

public class InnerRelationInstanceDto {
	private MulConditionDto mulConditionDTO;
	private Set<ProductInstanceDto> productInstanceSet;

	public InnerRelationInstanceDto() {
	}

	public Set<ProductInstanceDto> getProductInstanceSet() {
		return productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDto> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

	public MulConditionDto getMulConditionDTO() {
		return mulConditionDTO;
	}

	public void setMulConditionDTO(MulConditionDto mulConditionDTO) {
		this.mulConditionDTO = mulConditionDTO;
	}

}
