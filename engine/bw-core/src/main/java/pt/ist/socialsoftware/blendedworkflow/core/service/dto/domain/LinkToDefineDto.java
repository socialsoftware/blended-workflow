package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;

public class LinkToDefineDto extends LinkDto {
	private List<EntityInstance> candidateEntityInstances = new ArrayList<>();

	public LinkToDefineDto(EntityInstanceDto entityInstance, MulCondition mulCondition) {
		super(new HashSet<>(), mulCondition);

		entityInstance.getLinks().add(this);
	}

	public LinkToDefineDto(EntityInstanceDto entityInstance, Set<EntityInstance> entityInstanceContext,
			MulCondition mulCondition) {
		this(entityInstance, mulCondition);
		setCandidateEntityInstances(this.candidateEntityInstances);

	}

	public List<EntityInstance> getCandidateEntityInstances() {
		return this.candidateEntityInstances;
	}

	public void setCandidateEntityInstances(List<EntityInstance> candidateEntityInstances) {
		this.candidateEntityInstances = candidateEntityInstances;
	}

}
