package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

public class DefAttributeCondition extends DefAttributeCondition_Base {

	public static DefAttributeCondition getDefAttributeCondition(Specification spec, String path) {
		Attribute attribute = (Attribute) spec.getDataModel().getTargetOfPath(path);
		return DefAttributeCondition.getDefAttributeCondition(attribute);
	}

	public static DefAttributeCondition getDefAttributeCondition(Attribute attribute) {
		DefAttributeCondition defAttributeCondition = attribute.getDefAttributeCondition();
		if (defAttributeCondition == null) {
			defAttributeCondition = new DefAttributeCondition(attribute);
		}
		return defAttributeCondition;
	}

	private DefAttributeCondition(Attribute attribute) {
		setConditionModel(attribute.getEntity().getDataModel().getSpecification().getConditionModel());
		Path path = new Path(attribute.getEntity().getDataModel(),
				attribute.getEntity().getName() + "." + attribute.getName());
		setPath(path);

		setAttributeOfDef(attribute);
	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<>();
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
		String attributeName = getAttributeOfDef().getName().replaceAll(" ", "");
		String entityName = getAttributeOfDef().getEntity().getName().replaceAll(" ", "");

		condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + ")";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String attributeName = getAttributeOfDef().getName().replaceAll(" ", "");
		String entityName = getAttributeOfDef().getEntity().getName().replaceAll(" ", "");

		condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + ")";
		return condition;
	}

	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String attributeName = getAttributeOfDef().getName().replaceAll(" ", "");
		String entityName = getAttributeOfDef().getEntity().getName().replaceAll(" ", "");

		condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + ")";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		return "(FALSE_NODE = FALSE)";
	}

	@Override
	public String toString() {
		if (getAttributeOfDef() != null && getAttributeOfDef().getEntity() != null
				&& getAttributeOfDef().getEntity().getName() != null) {
			return "existsAttribute(" + getAttributeOfDef().getEntity().getName() + "." + getAttributeOfDef().getName()
					+ ")";
		}
		return "DEFAttributeCondition: attribute or entity with empty value";
	}

	@Override
	public Boolean existExistEntity() {
		return false;
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
		setAttributeOfDef(null);
		super.delete();
	}

	public AttributeType getType() {
		return AttributeType.BOOLEAN;
	}

	@Override
	public String getSubPath() {
		return "DEF(" + getAttributeOfDef().getFullPath() + ")";
	}

	public DefAttributeConditionDTO getDTO() {
		DefAttributeConditionDTO defConditionDTO = new DefAttributeConditionDTO();
		defConditionDTO.setSpecId(getConditionModel().getSpecification().getSpecId());
		defConditionDTO.setPath(getPath().getValue());
		defConditionDTO.setAttributeExtId(getAttributeOfDef().getExternalId());
		defConditionDTO.setMandatory(getAttributeOfDef().getIsMandatory());

		return defConditionDTO;
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		assert false : "expressions cannot have a def condition";
		return null;
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		Set<Attribute> attributes = new HashSet<>();
		attributes.add(getAttributeOfDef());
		return attributes;
	}

	@Override
	public Entity getSourceOfPath() {
		return getAttributeOfDef().getEntity();
	}

}
