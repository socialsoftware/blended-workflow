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

	private EntityDTO entity;
	private MulConditionDTO mulCondition;
	private Set<DefPathConditionDTO> defPathConditionSet;
	private Set<EntityInstanceContextDTO> entityInstanceContextSet;

	public static EntityContextDto createEntityContextDTO(Activity activity, Entity entityContext,
			MulCondition mulCondition, WorkflowInstance workflowInstance) {
		Map<Entity, Set<EntityInstance>> instanceContext = activity.getInstanceContext(workflowInstance);

		EntityContextDto entityContextDTO = new EntityContextDto();
		entityContextDTO.setEntity(entityContext.getDTO());

		if (mulCondition == null) {
			MulConditionDTO mulConditionDTO = new MulConditionDTO();
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

		Set<EntityInstanceContextDTO> entityInstanceContextDTOs = new HashSet<EntityInstanceContextDTO>();
		entityContextDTO.setEntityInstanceContextSet(entityInstanceContextDTOs);
		for (EntityInstance entityInstance : instanceContext.get(entityContext)) {
			entityInstanceContextDTOs
					.add(EntityInstanceContextDTO.createEntityInstanceContextDTO(entityContextDTO, entityInstance));
		}

		return entityContextDTO;
	}

	public static EntityContextDto createEntityContextDTO(Goal goal, Entity entityContext, MulCondition mulCondition,
			WorkflowInstance workflowInstance) {
		EntityContextDto entityContextDTO = new EntityContextDto();
		entityContextDTO.setEntity(entityContext.getDTO());

		if (mulCondition == null) {
			MulConditionDTO mulConditionDTO = new MulConditionDTO();
			mulConditionDTO.setCardinality("1");
			mulConditionDTO.setMin(1);
			mulConditionDTO.setMax(1);
			mulConditionDTO.setRolePath(entityContext.getName());
			entityContextDTO.setMulCondition(mulConditionDTO);
			// parent defines the entity
			DefPathConditionDTO defPathConditionDTO = DefPathCondition
					.getDefPathCondition(entityContext.getDataModel().getSpecification(), entityContext.getName())
					.getDTO(entityContext.getDataModel().getSpecification().getSpecId());
			// others defpaths due to activation condition
			Set<DefPathConditionDTO> defPathConditions = goal.getActivationConditionSet().stream()
					.filter(d -> d.getSourceOfPath() == entityContext)
					.map(d -> d.getDTO(entityContext.getDataModel().getSpecification().getSpecId()))
					.collect(Collectors.toSet());
			defPathConditions.add(defPathConditionDTO);
			entityContextDTO.setDefPathConditionSet(defPathConditions);
		} else {
			entityContextDTO.setMulCondition(mulCondition.getDTO());
			// defpath due to the mulcondition
			DefPathConditionDTO defPathConditionDTO = DefPathCondition
					.getDefPathCondition(entityContext.getDataModel().getSpecification(), mulCondition.getPath())
					.getDTO(entityContext.getDataModel().getSpecification().getSpecId());
			// others defpaths due to activation condition
			Set<DefPathConditionDTO> defPathConditions = goal.getActivationConditionSet().stream()
					.filter(d -> d.getSourceOfPath() == mulCondition.getSourceEntity()
							&& d.getSourceOfPath() != d.getTargetOfPath() && d.getPath().getAdjacent() == entityContext)
					.map(d -> d.getDTO(entityContext.getDataModel().getSpecification().getSpecId()))
					.collect(Collectors.toSet());
			defPathConditions.add(defPathConditionDTO);
			entityContextDTO.setDefPathConditionSet(defPathConditions);
		}

		Set<EntityInstanceContextDTO> entityInstanceContextDTOs = new HashSet<EntityInstanceContextDTO>();
		entityContextDTO.setEntityInstanceContextSet(entityInstanceContextDTOs);
		for (EntityInstance entityInstance : goal.getInstanceContext(workflowInstance, entityContext)) {
			entityInstanceContextDTOs
					.add(EntityInstanceContextDTO.createEntityInstanceContextDTO(entityContextDTO, entityInstance));
		}

		return entityContextDTO;
	}

	public EntityContextDto() {
	}

	public EntityDTO getEntity() {
		return entity;
	}

	public void setEntity(EntityDTO entity) {
		this.entity = entity;
	}

	public MulConditionDTO getMulCondition() {
		return mulCondition;
	}

	public void setMulCondition(MulConditionDTO mulCondition) {
		this.mulCondition = mulCondition;
	}

	public Set<DefPathConditionDTO> getDefPathConditionSet() {
		return defPathConditionSet;
	}

	public void setDefPathConditionSet(Set<DefPathConditionDTO> defPathConditionSet) {
		this.defPathConditionSet = defPathConditionSet;
	}

	public Set<EntityInstanceContextDTO> getEntityInstanceContextSet() {
		return entityInstanceContextSet;
	}

	public void setEntityInstanceContextSet(Set<EntityInstanceContextDTO> entityInstanceContextSet) {
		this.entityInstanceContextSet = entityInstanceContextSet;
	}

}