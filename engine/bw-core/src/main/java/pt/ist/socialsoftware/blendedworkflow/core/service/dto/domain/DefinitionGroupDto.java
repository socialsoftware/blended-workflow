package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

public class DefinitionGroupDto {
	private Set<EntityContextDto> entityContextSet = new HashSet<>();
	private DefEntityConditionDto defEnt = null;
	private Set<DefAttributeConditionDto> defAtts = new HashSet<>();
	private Set<DefinitionGroupInstanceDto> definitionGroupInstanceSet = new HashSet<>();
	private Set<InnerRelationDto> innerRelationSet = new HashSet<>();

	public DefinitionGroupDto() {
	}

	public Set<EntityContextDto> getEntityContextSet() {
		return this.entityContextSet;
	}

	public void setEntityContextSet(Set<EntityContextDto> entityContextSet) {
		this.entityContextSet = entityContextSet;
	}

	public DefEntityConditionDto getDefEnt() {
		return this.defEnt;
	}

	public void setDefEnt(DefEntityConditionDto defEnt) {
		this.defEnt = defEnt;
	}

	public Set<DefAttributeConditionDto> getDefAtts() {
		return this.defAtts;
	}

	public void setDefAtts(Set<DefAttributeConditionDto> defAtts) {
		this.defAtts = defAtts;
	}

	public Set<DefinitionGroupInstanceDto> getDefinitionGroupInstanceSet() {
		return this.definitionGroupInstanceSet;
	}

	public void setDefinitionGroupInstanceSet(Set<DefinitionGroupInstanceDto> definitionGroupInstanceSet) {
		this.definitionGroupInstanceSet = definitionGroupInstanceSet;
	}

	public Set<InnerRelationDto> getInnerRelationSet() {
		return this.innerRelationSet;
	}

	public void setInnerRelationSet(Set<InnerRelationDto> innerRelationSet) {
		this.innerRelationSet = innerRelationSet;
	}

}
