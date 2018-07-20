package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

public class EntityContextDto {
	private static Logger logger = LoggerFactory.getLogger(EntityContextDto.class);

	private EntityDto entity;
	private MulConditionDto mulCondition;
	private Set<DefPathConditionDto> defPathConditionSet;
	private Set<EntityInstanceContextDto> entityInstanceContextSet;

	public static EntityContextDto createEntityContextDTO(Activity activity, Entity entityContext,
			MulCondition mulCondition, WorkflowInstance workflowInstance) {
		Map<Entity, Set<EntityInstance>> instanceContext = activity.getInstanceContext(workflowInstance);

		EntityContextDto entityContextDTO = new EntityContextDto();
		entityContextDTO.setEntity(entityContext.getDTO());

		if (mulCondition == null) {
			MulConditionDto mulConditionDTO = new MulConditionDto();
			mulConditionDTO.setCardinality("1");
			mulConditionDTO.setMin(1);
			mulConditionDTO.setMax(1);
			mulConditionDTO.setRolePath(entityContext.getName());
			entityContextDTO.setMulCondition(mulConditionDTO);
			entityContextDTO.setDefPathConditionSet(
					activity.getPreConditionSet().stream().filter(d -> d.getSourceOfPath() == entityContext)
							.map(d -> d.getDTO(entityContext.getDataModel().getSpecification().getSpecId()))
							.collect(Collectors.toSet()));
		} else {
			entityContextDTO.setMulCondition(mulCondition.getDTO());
			entityContextDTO.setDefPathConditionSet(activity.getPreConditionSet().stream()
					.filter(d -> d.getSourceOfPath() == mulCondition.getSourceEntity()
							&& d.getSourceOfPath() != d.getTargetOfPath() && d.getPath().getAdjacent() == entityContext)
					.map(d -> d.getDTO(entityContext.getDataModel().getSpecification().getSpecId()))
					.collect(Collectors.toSet()));
		}

		Set<EntityInstanceContextDto> entityInstanceContextDTOs = new HashSet<EntityInstanceContextDto>();
		entityContextDTO.setEntityInstanceContextSet(entityInstanceContextDTOs);
		for (EntityInstance entityInstance : instanceContext.get(entityContext)) {
			entityInstanceContextDTOs
					.add(EntityInstanceContextDto.createEntityInstanceContextDTO(entityContextDTO, entityInstance));
		}

		return entityContextDTO;
	}

	public static EntityContextDto createEntityContextDTO(Goal goal, Entity entityContext, MulCondition mulCondition,
			WorkflowInstance workflowInstance) {
		EntityContextDto entityContextDTO = new EntityContextDto();
		entityContextDTO.setEntity(entityContext.getDTO());

		if (mulCondition == null) {
			MulConditionDto mulConditionDTO = new MulConditionDto();
			mulConditionDTO.setCardinality("1");
			mulConditionDTO.setMin(1);
			mulConditionDTO.setMax(1);
			mulConditionDTO.setRolePath(entityContext.getName());
			entityContextDTO.setMulCondition(mulConditionDTO);
			// // parent defines the entity
			// DefPathConditionDTO defPathConditionDTO = DefPathCondition
			// .getDefPathCondition(entityContext.getDataModel().getSpecification(),
			// entityContext.getName())
			// .getDTO(entityContext.getDataModel().getSpecification().getSpecId());
			// others defpaths due to activation condition
			Set<DefPathConditionDto> defPathConditions = goal.getActivationConditionSet().stream()
					.filter(d -> d.getSourceOfPath() == entityContext)
					.map(d -> d.getDTO(entityContext.getDataModel().getSpecification().getSpecId()))
					.collect(Collectors.toSet());
			// defPathConditions.add(defPathConditionDTO);
			entityContextDTO.setDefPathConditionSet(defPathConditions);
		} else {
			entityContextDTO.setMulCondition(mulCondition.getDTO());
			// defpath due to the mulcondition
			DefPathConditionDto defPathConditionDTO = DefPathCondition
					.getDefPathCondition(entityContext.getDataModel().getSpecification(), mulCondition.getPath())
					.getDTO(entityContext.getDataModel().getSpecification().getSpecId());
			// others defpaths due to activation condition
			Set<DefPathConditionDto> defPathConditions = goal.getActivationConditionSet().stream()
					.filter(d -> d.getSourceOfPath() == mulCondition.getSourceEntity()
							&& d.getSourceOfPath() != d.getTargetOfPath() && d.getPath().getAdjacent() == entityContext)
					.map(d -> d.getDTO(entityContext.getDataModel().getSpecification().getSpecId()))
					.collect(Collectors.toSet());
			defPathConditions.add(defPathConditionDTO);
			entityContextDTO.setDefPathConditionSet(defPathConditions);
		}

		Set<EntityInstanceContextDto> entityInstanceContextDTOs = new HashSet<EntityInstanceContextDto>();
		entityContextDTO.setEntityInstanceContextSet(entityInstanceContextDTOs);
		for (EntityInstance entityInstance : goal.getInstanceContext(workflowInstance, entityContext)) {
			entityInstanceContextDTOs
					.add(EntityInstanceContextDto.createEntityInstanceContextDTO(entityContextDTO, entityInstance));
		}

		return entityContextDTO;
	}

	public EntityContextDto() {
	}

	public EntityDto getEntity() {
		return this.entity;
	}

	public void setEntity(EntityDto entity) {
		this.entity = entity;
	}

	public MulConditionDto getMulCondition() {
		return this.mulCondition;
	}

	public void setMulCondition(MulConditionDto mulCondition) {
		this.mulCondition = mulCondition;
	}

	public Set<DefPathConditionDto> getDefPathConditionSet() {
		return this.defPathConditionSet;
	}

	public void setDefPathConditionSet(Set<DefPathConditionDto> defPathConditionSet) {
		this.defPathConditionSet = defPathConditionSet;
	}

	public Set<EntityInstanceContextDto> getEntityInstanceContextSet() {
		return this.entityInstanceContextSet;
	}

	public void setEntityInstanceContextSet(Set<EntityInstanceContextDto> entityInstanceContextSet) {
		this.entityInstanceContextSet = entityInstanceContextSet;
	}

}