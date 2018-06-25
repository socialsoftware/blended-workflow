package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

public class InnerRelationInstanceDTO {
	private MulConditionDTO mulConditionDTO;
	private Set<ProductInstanceDTO> productInstanceSet;

	public InnerRelationInstanceDTO() {
	}

	public Set<ProductInstanceDTO> getProductInstanceSet() {
		return productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDTO> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

	public MulConditionDTO getMulConditionDTO() {
		return mulConditionDTO;
	}

	public void setMulConditionDTO(MulConditionDTO mulConditionDTO) {
		this.mulConditionDTO = mulConditionDTO;
	}

}
