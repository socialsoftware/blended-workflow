package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

public class DefinitionGroupInstanceDTO {
	private Set<EntityInstanceContextDto> entityInstanceContextSet;
	private Set<ProductInstanceDTO> productInstanceSet;
	private Set<InnerRelationInstanceDto> innerRelationInstanceSet;

	public DefinitionGroupInstanceDTO() {
	}

	public Set<EntityInstanceContextDto> getEntityInstanceContextSet() {
		return entityInstanceContextSet;
	}

	public void setEntityInstanceContextSet(Set<EntityInstanceContextDto> entityInstanceContextSet) {
		this.entityInstanceContextSet = entityInstanceContextSet;
	}

	public Set<ProductInstanceDTO> getProductInstanceSet() {
		return productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDTO> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

	public Set<InnerRelationInstanceDto> getInnerRelationInstanceSet() {
		return innerRelationInstanceSet;
	}

	public void setInnerRelationInstanceSet(Set<InnerRelationInstanceDto> innerRelationInstanceSet) {
		this.innerRelationInstanceSet = innerRelationInstanceSet;
	}

}
