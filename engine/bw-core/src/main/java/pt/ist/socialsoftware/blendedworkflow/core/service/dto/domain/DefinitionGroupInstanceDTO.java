package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

public class DefinitionGroupInstanceDTO {
	private Set<EntityInstanceContextDTO> entityInstanceContextSet;
	private Set<ProductInstanceDTO> productInstanceSet;
	private Set<InnerRelationInstanceDTO> innerRelationInstanceSet;

	public DefinitionGroupInstanceDTO() {
	}

	public Set<EntityInstanceContextDTO> getEntityInstanceContextSet() {
		return entityInstanceContextSet;
	}

	public void setEntityInstanceContextSet(Set<EntityInstanceContextDTO> entityInstanceContextSet) {
		this.entityInstanceContextSet = entityInstanceContextSet;
	}

	public Set<ProductInstanceDTO> getProductInstanceSet() {
		return productInstanceSet;
	}

	public void setProductInstanceSet(Set<ProductInstanceDTO> productInstanceSet) {
		this.productInstanceSet = productInstanceSet;
	}

	public Set<InnerRelationInstanceDTO> getInnerRelationInstanceSet() {
		return innerRelationInstanceSet;
	}

	public void setInnerRelationInstanceSet(Set<InnerRelationInstanceDTO> innerRelationInstanceSet) {
		this.innerRelationInstanceSet = innerRelationInstanceSet;
	}

}
