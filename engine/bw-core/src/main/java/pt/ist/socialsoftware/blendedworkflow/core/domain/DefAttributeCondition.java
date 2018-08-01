package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefAttributeConditionDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

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

	public DefAttributeConditionDto getDto() {
		DefAttributeConditionDto defConditionDto = new DefAttributeConditionDto();
		defConditionDto.setSpecId(getConditionModel().getSpecification().getSpecId());
		defConditionDto.setName(getAttributeOfDef().getName());
		defConditionDto.setPath(getPath().getValue());
		defConditionDto.setAttributeExtId(getAttributeOfDef().getExternalId());
		defConditionDto.setMandatory(getAttributeOfDef().getIsMandatory());

		return defConditionDto;
	}

	@Override
	public ExpressionDto getExpressionDTO(String specId) {
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
