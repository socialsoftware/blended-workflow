package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

public class EntityInstanceContextDto {
	private int index;
	private EntityInstanceDto entityInstance;
	private MulConditionDto mulConditionDTO;

	public static EntityInstanceContextDto createEntityInstanceContextDto(int index, EntityContextDto entityContextDTO,
			EntityInstance entityInstance) {
		EntityInstanceContextDto entityContextInstanceDto = new EntityInstanceContextDto();

		entityContextInstanceDto.setIndex(index);
		entityContextInstanceDto.setEntityInstance(new EntityInstanceDto(entityInstance, Depth.DEEP));
		entityContextInstanceDto.setMulConditionDTO(entityContextDTO.getMulCondition());

		return entityContextInstanceDto;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public EntityInstanceDto getEntityInstance() {
		return this.entityInstance;
	}

	public void setEntityInstance(EntityInstanceDto entityInstance) {
		this.entityInstance = entityInstance;
	}

	public MulConditionDto getMulConditionDTO() {
		return this.mulConditionDTO;
	}

	public void setMulConditionDTO(MulConditionDto mulConditionDTO) {
		this.mulConditionDTO = mulConditionDTO;
	}

}
