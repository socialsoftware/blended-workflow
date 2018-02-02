package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

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
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<>();
	}

	@Override
	public String getRdrUndefinedCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributeSet()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " | " + entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED;
				}

			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributeSet()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " | " + entityName + "_" + attributeName + "_State = " + DataState.SKIPPED;
				}
			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");

		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributeSet()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " & " + entityName + "_" + attributeName + "_State = " + DataState.DEFINED;
				}

			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		return "(FALSE_NODE = FALSE)";
	}

	@Override
	public String toString() {
		return "existsEntity(" + getEntity().getName() + ")";
	}

	@Override
	public Boolean existExistEntity() {
		return true;
	}

	@Override
	public Boolean existTrue() {
		return false;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return false;
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

	public DefEntityConditionDTO getDTO() {
		DefEntityConditionDTO eacDTO = new DefEntityConditionDTO();
		eacDTO.setSpecId(getEntity().getDataModel().getSpecification().getSpecId());
		eacDTO.setExtId(getExternalId());
		eacDTO.setEntityName(getEntity().getName());
		eacDTO.setExists(getEntity().getExists());
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