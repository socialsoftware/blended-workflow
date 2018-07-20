package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

public class InnerRelationInstanceDto {
	private MulConditionDto mulConditionDTO;
	private Set<ProductInstanceDTO> productInstanceSet;

	public InnerRelationInstanceDto() {
	}

	public Set<ProductInstanceDTO> getProductInstanceSet() {
		return productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDTO> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

	public MulConditionDto getMulConditionDTO() {
		return mulConditionDTO;
	}

	public void setMulConditionDTO(MulConditionDto mulConditionDTO) {
		this.mulConditionDTO = mulConditionDTO;
	}

}
