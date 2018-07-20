package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;

public class EntityInstanceContextDto {
	private ProductInstanceDto entityInstance;
	private MulConditionDto mulConditionDTO;
	private Set<ProductInstanceDto> pathValueSet;
	private String pathValues;

	public static EntityInstanceContextDto createEntityInstanceContextDTO(EntityContextDto entityContextDTO,
			EntityInstance entityInstance) {
		EntityInstanceContextDto entityContextInstanceDTO = new EntityInstanceContextDto();

		entityContextInstanceDTO.setEntityInstance(entityInstance.getDTO());
		entityContextInstanceDTO.setPathValueSet(new HashSet<ProductInstanceDto>());
		entityContextInstanceDTO.setMulConditionDTO(entityContextDTO.getMulCondition());
		entityContextInstanceDTO.setPathValues("");
		for (DefPathConditionDto defPathConditionDTO : entityContextDTO.getDefPathConditionSet()) {
			for (ProductInstance productInstance : entityInstance
					.getProductInstancesByPath(defPathConditionDTO.getPath())) {
				ProductInstanceDto productInstanceDto = productInstance.getDTO();
				productInstanceDto.setPath(defPathConditionDTO.getPath());
				entityContextInstanceDTO.getPathValueSet().add(productInstanceDto);
				entityContextInstanceDTO
						.setPathValues(productInstanceDto.getValue() + ";" + entityContextInstanceDTO.getPathValues());
			}
		}

		return entityContextInstanceDTO;
	}

	public EntityInstanceContextDto() {
	}

	public ProductInstanceDto getEntityInstance() {
		return entityInstance;
	}

	public void setEntityInstance(ProductInstanceDto entityInstance) {
		this.entityInstance = entityInstance;
	}

	public Set<ProductInstanceDto> getPathValueSet() {
		return pathValueSet;
	}

	public void setPathValueSet(Set<ProductInstanceDto> pathValueSet) {
		this.pathValueSet = pathValueSet;
	}

	public MulConditionDto getMulConditionDTO() {
		return mulConditionDTO;
	}

	public void setMulConditionDTO(MulConditionDto mulConditionDTO) {
		this.mulConditionDTO = mulConditionDTO;
	}

	public String getPathValues() {
		return pathValues;
	}

	public void setPathValues(String pathValues) {
		this.pathValues = pathValues;
	}

}
