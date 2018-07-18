package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;

public class EntityInstanceContextDTO {
	private ProductInstanceDTO entityInstance;
	private MulConditionDTO mulConditionDTO;
	private Set<ProductInstanceDTO> pathValueSet;
	private String pathValues;

	public static EntityInstanceContextDTO createEntityInstanceContextDTO(EntityContextDto entityContextDTO,
			EntityInstance entityInstance) {
		EntityInstanceContextDTO entityContextInstanceDTO = new EntityInstanceContextDTO();

		entityContextInstanceDTO.setEntityInstance(entityInstance.getDTO());
		entityContextInstanceDTO.setPathValueSet(new HashSet<ProductInstanceDTO>());
		entityContextInstanceDTO.setMulConditionDTO(entityContextDTO.getMulCondition());
		entityContextInstanceDTO.setPathValues("");
		for (DefPathConditionDTO defPathConditionDTO : entityContextDTO.getDefPathConditionSet()) {
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

	public EntityInstanceContextDTO() {
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

	public MulConditionDTO getMulConditionDTO() {
		return mulConditionDTO;
	}

	public void setMulConditionDTO(MulConditionDTO mulConditionDTO) {
		this.mulConditionDTO = mulConditionDTO;
	}

	public String getPathValues() {
		return pathValues;
	}

	public void setPathValues(String pathValues) {
		this.pathValues = pathValues;
	}

}
