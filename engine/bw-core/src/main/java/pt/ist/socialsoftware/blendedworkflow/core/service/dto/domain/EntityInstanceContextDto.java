package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EntityInstanceDto.Depth;

public class EntityInstanceContextDto {
	private int index;
	private EntityInstanceDto entityInstance;
	private MulConditionDto mulConditionDTO;
	private Set<ProductInstanceDto> pathValueSet;
	private String pathValues;

	public static EntityInstanceContextDto createEntityInstanceContextDTO(int index, EntityContextDto entityContextDTO,
			EntityInstance entityInstance) {
		EntityInstanceContextDto entityContextInstanceDto = new EntityInstanceContextDto();

		entityContextInstanceDto.setIndex(index);
		entityContextInstanceDto.setEntityInstance(new EntityInstanceDto(entityInstance, Depth.DEEP));
		entityContextInstanceDto.setPathValueSet(new HashSet<ProductInstanceDto>());
		entityContextInstanceDto.setMulConditionDTO(entityContextDTO.getMulCondition());
		entityContextInstanceDto.setPathValues("");
		for (DefPathConditionDto defPathConditionDTO : entityContextDTO.getDefPathConditionSet()) {
			for (ProductInstance productInstance : entityInstance
					.getProductInstancesByPath(defPathConditionDTO.getPath())) {
				ProductInstanceDto productInstanceDto = productInstance.getDTO();
				productInstanceDto.setPath(defPathConditionDTO.getPath());
				entityContextInstanceDto.getPathValueSet().add(productInstanceDto);
				entityContextInstanceDto
						.setPathValues(productInstanceDto.getValue() + ";" + entityContextInstanceDto.getPathValues());
			}
		}

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

	public Set<ProductInstanceDto> getPathValueSet() {
		return this.pathValueSet;
	}

	public void setPathValueSet(Set<ProductInstanceDto> pathValueSet) {
		this.pathValueSet = pathValueSet;
	}

	public MulConditionDto getMulConditionDTO() {
		return this.mulConditionDTO;
	}

	public void setMulConditionDTO(MulConditionDto mulConditionDTO) {
		this.mulConditionDTO = mulConditionDTO;
	}

	public String getPathValues() {
		return this.pathValues;
	}

	public void setPathValues(String pathValues) {
		this.pathValues = pathValues;
	}

}
