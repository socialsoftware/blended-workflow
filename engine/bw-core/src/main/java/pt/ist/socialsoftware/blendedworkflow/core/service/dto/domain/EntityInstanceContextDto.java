package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;

public class EntityInstanceContextDto {
	private ProductInstanceDTO entityInstance;
	private MulConditionDto mulConditionDTO;
	private Set<ProductInstanceDTO> pathValueSet;
	private String pathValues;

	public static EntityInstanceContextDto createEntityInstanceContextDTO(EntityContextDto entityContextDTO,
			EntityInstance entityInstance) {
		EntityInstanceContextDto entityContextInstanceDTO = new EntityInstanceContextDto();

		entityContextInstanceDTO.setEntityInstance(entityInstance.getDTO());
		entityContextInstanceDTO.setPathValueSet(new HashSet<ProductInstanceDTO>());
		entityContextInstanceDTO.setMulConditionDTO(entityContextDTO.getMulCondition());
		entityContextInstanceDTO.setPathValues("");
		for (DefPathConditionDto defPathConditionDTO : entityContextDTO.getDefPathConditionSet()) {
			for (ProductInstance productInstance : entityInstance
					.getProductInstancesByPath(defPathConditionDTO.getPath())) {
				ProductInstanceDTO productInstanceDTO = productInstance.getDTO();
				productInstanceDTO.setPath(defPathConditionDTO.getPath());
				entityContextInstanceDTO.getPathValueSet().add(productInstanceDTO);
				entityContextInstanceDTO
						.setPathValues(productInstanceDTO.getValue() + ";" + entityContextInstanceDTO.getPathValues());
			}
		}

		return entityContextInstanceDTO;
	}

	public EntityInstanceContextDto() {
	}

	public ProductInstanceDTO getEntityInstance() {
		return entityInstance;
	}

	public void setEntityInstance(ProductInstanceDTO entityInstance) {
		this.entityInstance = entityInstance;
	}

	public Set<ProductInstanceDTO> getPathValueSet() {
		return pathValueSet;
	}

	public void setPathValueSet(Set<ProductInstanceDTO> pathValueSet) {
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