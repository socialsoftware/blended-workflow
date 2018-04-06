package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.AttributeDTO;

public class Attribute extends Attribute_Base {
	private static Logger logger = LoggerFactory.getLogger(Attribute.class);

	final static String ATTRIBUTE_TYPE = "(" + AttributeType.STRING + "|" + AttributeType.NUMBER + "|"
			+ AttributeType.BOOLEAN + "|" + AttributeType.DATE + ")";

	public enum AttributeType {
		BOOLEAN("Boolean"), NUMBER("Number"), STRING("String"), DATE("Date");
		private String name;

		AttributeType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static Attribute.AttributeType parseAttributeType(String type) {
			if (!Pattern.matches(ATTRIBUTE_TYPE, type)) {
				throw new BWException(BWErrorType.INVALID_ATTRIBUTE_TYPE);
			}

			Attribute.AttributeType res = null;

			if (type.equals(AttributeType.STRING.toString())) {
				return AttributeType.STRING;
			}

			if (type.equals(AttributeType.NUMBER.toString())) {
				return AttributeType.NUMBER;
			}

			if (type.equals(AttributeType.BOOLEAN.toString())) {
				return AttributeType.BOOLEAN;
			}

			if (type.equals(AttributeType.DATE.toString())) {
				return AttributeType.DATE;
			}

			assert false;

			return res;
		}

	};

	@Override
	public void setName(String name) {
		checkName(name);
		super.setName(name);
	}

	private void checkName(String name) {
		if (name == null || name.equals("")) {
			throw new BWException(BWErrorType.INVALID_ATTRIBUTE_NAME, name);
		}

		getEntity().checkUniqueElementName(name);
	}

	public Attribute(DataModel dataModel, Entity entity, String name, AttributeType type, boolean isMandatory) {
		setDataModel(dataModel);
		setEntity(entity);
		setName(name);
		setType(type);
		setIsMandatory(isMandatory);
	}

	@Override
	public ProductType getProductType() {
		return ProductType.ATTRIBUTE;
	}

	/**
	 * FIXME: Double/Boolean
	 */
	public String getYAWLAttributeType() {
		return "string";
	}

	public String getYAWLAttributeValue() {
		if (this.getType().equals(AttributeType.NUMBER)) {
			return "0";
		} else {
			return "string";
		}
	}

	@Override
	public void delete() {
		setDataModel(null);

		getAttributeInstanceSet().stream().forEach(ai -> ai.delete());

		setEntity(null);
		if (getDefAttributeCondition() != null) {
			getDefAttributeCondition().delete();
		}
		getAttValueExpressionSet().stream().forEach(exp -> exp.delete());
		getAttBoolConditionSet().stream().forEach(cond -> cond.delete());

		super.delete();
	}

	@Override
	public Product getNext(List<String> pathLeft, String path) {
		// log.debug("getNext {}:{}", path, pathLeft);

		if (pathLeft.size() == 0) {
			return this;
		}

		throw new BWException(BWErrorType.INVALID_PATH, path + ":" + pathLeft);
	}

	@Override
	public AttributeDTO getDTO() {
		AttributeDTO attDTO = new AttributeDTO();
		attDTO.setSpecId(getDataModel().getSpecification().getSpecId());
		attDTO.setExtId(getExternalId());
		attDTO.setProductType(ProductType.ATTRIBUTE.name());
		attDTO.setEntityExtId(getEntity().getExternalId());
		attDTO.setEntityName(getEntity().getName());
		attDTO.setName(getName());
		attDTO.setType(getType().name);
		attDTO.setMandatory(getIsMandatory());

		return attDTO;
	}

	@Override
	public DefProductCondition getDefCondition() {
		return DefAttributeCondition.getDefAttributeCondition(this);
	}

	@Override
	public String getFullPath() {
		return getEntity().getFullPath() + "." + getName();
	}

}