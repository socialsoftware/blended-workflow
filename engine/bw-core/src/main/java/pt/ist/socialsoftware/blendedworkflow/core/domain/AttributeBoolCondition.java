package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

public class AttributeBoolCondition extends AttributeBoolCondition_Base {

	public AttributeBoolCondition(Specification spec, String value) {
		Path path = new Path(spec.getDataModel(), value);
		setPath(path);
		setAttributeOfBool((Attribute) spec.getDataModel().getTargetOfPath(value));
	}

	@Override
	public void delete() {
		setAttributeOfBool(null);
		getPath().delete();

		super.delete();
	}

	@Override
	public String getSubPath() {
		return getPath().getValue();
	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<>();
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		Set<Attribute> attributes = new HashSet<>();
		attributes.add(getAttributeOfBool());

		return attributes;
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> paths = new HashSet<>();
		paths.add(getPath());

		return paths;
	}

	@Override
	public ExpressionDto getExpressionDTO(String specId) {
		return new ExpressionDto(specId, BooleanOperator.ATT_VALUE, getPath().getValue());
	}

}
