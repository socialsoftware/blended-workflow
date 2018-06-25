package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

public class DefinitionGroupDTO {
	private DefProductConditionSetDTO defProductConditionSet;
	private Set<EntityContextDTO> entityContextSet;
	private Set<DefinitionGroupInstanceDTO> definitionGroupInstanceSet;
	private Set<InnerRelationDTO> innerRelationSet;

	public DefinitionGroupDTO() {
	}

	public DefProductConditionSetDTO getDefProductConditionSet() {
		return defProductConditionSet;
	}

	public void setDefProductConditionSet(DefProductConditionSetDTO defProductConditionSet) {
		this.defProductConditionSet = defProductConditionSet;
	}

	public Set<EntityContextDTO> getEntityContextSet() {
		return entityContextSet;
	}

	public void setEntityContextSet(Set<EntityContextDTO> entityContextSet) {
		this.entityContextSet = entityContextSet;
	}

	public Set<DefinitionGroupInstanceDTO> getDefinitionGroupInstanceSet() {
		return definitionGroupInstanceSet;
	}

	public void setDefinitionGroupInstanceSet(Set<DefinitionGroupInstanceDTO> definitionGroupInstanceSet) {
		this.definitionGroupInstanceSet = definitionGroupInstanceSet;
	}

	public Set<InnerRelationDTO> getInnerRelationSet() {
		return innerRelationSet;
	}

	public void setInnerRelationSet(Set<InnerRelationDTO> innerRelationSet) {
		this.innerRelationSet = innerRelationSet;
	}

}
