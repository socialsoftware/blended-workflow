package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

public class EntityContextDto {
	private static Logger logger = LoggerFactory.getLogger(EntityContextDto.class);

	private int index;
	private DefEntityConditionDto defEntityCondition;
	private MulConditionDto mulCondition;
	private Set<EntityInstanceContextDto> entityInstanceContextSet;

	public static EntityContextDto createEntityContextDTO(int counter, Activity activity, Entity entityContext,
			MulCondition mulCondition, WorkflowInstance workflowInstance) {
		Map<Entity, Set<EntityInstance>> instanceContext = activity.getInstanceContext(workflowInstance);

		EntityContextDto entityContextDto = new EntityContextDto();
		entityContextDto.setIndex(counter);
		entityContextDto.setDefEntityCondition(entityContext.getDefEntityCondition().getDTO());

		if (mulCondition == null) {
			MulConditionDto mulConditionDTO = new MulConditionDto();
			mulConditionDTO.setCardinality("1");
			mulConditionDTO.setMin(1);
			mulConditionDTO.setMax(1);
			mulConditionDTO.setRolePath(entityContext.getName());
			entityContextDto.setMulCondition(mulConditionDTO);
		} else {
			entityContextDto.setMulCondition(mulCondition.getDTO());
		}

		Set<EntityInstanceContextDto> entityInstanceContextDTOs = new HashSet<EntityInstanceContextDto>();
		entityContextDto.setEntityInstanceContextSet(entityInstanceContextDTOs);
		int index = 0;
		for (EntityInstance entityInstance : instanceContext.get(entityContext)) {
			entityInstanceContextDTOs.add(
					EntityInstanceContextDto.createEntityInstanceContextDto(index++, entityContextDto, entityInstance));
		}

		return entityContextDto;
	}

	public static EntityContextDto createEntityContextDTO(int counter, Goal goal, Entity entityContext,
			MulCondition mulCondition, WorkflowInstance workflowInstance) {
		EntityContextDto entityContextDto = new EntityContextDto();
		entityContextDto.setIndex(counter);
		entityContextDto.setDefEntityCondition(entityContext.getDefEntityCondition().getDTO());

		if (mulCondition == null) {
			MulConditionDto mulConditionDto = new MulConditionDto();
			mulConditionDto.setCardinality("1");
			mulConditionDto.setMin(1);
			mulConditionDto.setMax(1);
			mulConditionDto.setRolePath(entityContext.getName());
			entityContextDto.setMulCondition(mulConditionDto);
		} else {
			entityContextDto.setMulCondition(mulCondition.getDTO());
		}

		Set<EntityInstanceContextDto> entityInstanceContextDTOs = new HashSet<EntityInstanceContextDto>();
		entityContextDto.setEntityInstanceContextSet(entityInstanceContextDTOs);
		int index = 0;
		for (EntityInstance entityInstance : goal.getInstanceContext(workflowInstance, entityContext)) {
			entityInstanceContextDTOs.add(
					EntityInstanceContextDto.createEntityInstanceContextDto(index++, entityContextDto, entityInstance));
		}

		return entityContextDto;
	}

	public EntityContextDto() {
	}

	public DefEntityConditionDto getDefEntityCondition() {
		return this.defEntityCondition;
	}

	public void setDefEntityCondition(DefEntityConditionDto entity) {
		this.defEntityCondition = entity;
	}

	public MulConditionDto getMulCondition() {
		return this.mulCondition;
	}

	public void setMulCondition(MulConditionDto mulCondition) {
		this.mulCondition = mulCondition;
	}

	public Set<EntityInstanceContextDto> getEntityInstanceContextSet() {
		return this.entityInstanceContextSet;
	}

	public void setEntityInstanceContextSet(Set<EntityInstanceContextDto> entityInstanceContextSet) {
		this.entityInstanceContextSet = entityInstanceContextSet;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}