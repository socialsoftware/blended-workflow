package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDTO;

public class DefEntityCondition extends DefEntityCondition_Base {

	public static DefEntityCondition getDefEntityCondition(Entity entity) {
		if (entity.getDefEntityCondition() != null) {
			return entity.getDefEntityCondition();
		} else {
			return new DefEntityCondition(entity);
		}
	}

	private DefEntityCondition(Entity entity) {
		setConditionModel(entity.getDataModel().getSpecification().getConditionModel());
		Path path = new Path(entity.getDataModel(), entity.getName());
		setPath(path);
		setEntity(entity);
	}

	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entity = new HashSet<>();
		entity.add(getEntity());
		return entity;
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> paths = new HashSet<>();
		paths.add(getPath());

		return paths;
	}

	@Override
	public void delete() {
		setConditionModel(null);
		setEntity(null);
		super.delete();
	}

	public AttributeType getType() {
		return AttributeType.BOOLEAN;
	}

	@Override
	public String getSubPath() {
		return "DEF(" + getEntity().getFullPath() + ")";
	}

	public DefEntityConditionDTO    getDTO() {
		DefEntityConditionDTO eacDTO = new DefEntityConditionDTO();
		eacDTO.setSpecId(getEntity().getDataModel().getSpecification().getSpecId());
		eacDTO.setExtId(getExternalId());
		eacDTO.setEntityName(getEntity().getName());
		eacDTO.setExists(getEntity().getExists());
		eacDTO.setMandatory(getEntity().getMandatory());
		eacDTO.setPath(getEntity().getName());

		return eacDTO;
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		assert false : "expressions cannot have a def condition";
		return null;
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return new HashSet<>();
	}

	@Override
	public Entity getSourceOfPath() {
		return getEntity();
	}

}