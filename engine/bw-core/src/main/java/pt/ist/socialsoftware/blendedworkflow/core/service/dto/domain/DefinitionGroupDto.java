package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

public class DefinitionGroupDto {
	private DefProductConditionSetDto defProductConditionSet = new DefProductConditionSetDto();
	private Set<EntityContextDto> entityContextSet = new HashSet<>();
	private Set<DefinitionGroupInstanceDTO> definitionGroupInstanceSet = new HashSet<>();
	private Set<InnerRelationDto> innerRelationSet = new HashSet<>();

	public DefinitionGroupDto() {
	}

	public DefProductConditionSetDto getDefProductConditionSet() {
		return this.defProductConditionSet;
	}

	public void setDefProductConditionSet(DefProductConditionSetDto defProductConditionSet) {
		this.defProductConditionSet = defProductConditionSet;
	}

	public Set<EntityContextDto> getEntityContextSet() {
		return this.entityContextSet;
	}

	public void setEntityContextSet(Set<EntityContextDto> entityContextSet) {
		this.entityContextSet = entityContextSet;
	}

	public Set<DefinitionGroupInstanceDTO> getDefinitionGroupInstanceSet() {
		return this.definitionGroupInstanceSet;
	}

	public void setDefinitionGroupInstanceSet(Set<DefinitionGroupInstanceDTO> definitionGroupInstanceSet) {
		this.definitionGroupInstanceSet = definitionGroupInstanceSet;
	}

	public Set<InnerRelationDto> getInnerRelationSet() {
		return this.innerRelationSet;
	}

	public void setInnerRelationSet(Set<InnerRelationDto> innerRelationSet) {
		this.innerRelationSet = innerRelationSet;
	}

}
