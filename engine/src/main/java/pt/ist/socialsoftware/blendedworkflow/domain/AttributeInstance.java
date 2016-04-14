package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.regex.Pattern;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class AttributeInstance extends AttributeInstance_Base {

	@Override
	public void setEntityInstance(EntityInstance entityInstance) {
		checkConsistency(entityInstance, getAttribute());
		super.setEntityInstance(entityInstance);
	}

	@Override
	public void setAttribute(Attribute attribute) {
		checkConsistency(getEntityInstance(), attribute);
		super.setAttribute(attribute);
	}

	@Override
	public void setValue(String value) {
		checkValue(value);
		super.setValue(value);
	}

	public AttributeInstance(EntityInstance entityInstance, Attribute attribute, String value) {
		setEntityInstance(entityInstance);
		setAttribute(attribute);
		setValue(value);
	}

	private void checkConsistency(EntityInstance entityInstance, Attribute attribute) {
		if (entityInstance != null && attribute != null) {
			if (!entityInstance.getEntity().getAttributeSet().contains(attribute)) {
				throw new BWException(BWErrorType.CREATE_ATTRIBUTE_INSTANCE,
						entityInstance.getEntity().getName() + ":" + attribute.getName());
			}
		}
	}

	private void checkValue(String value) {
		if (value != null) {
			if (getAttribute() != null) {
				switch (getAttribute().getType()) {
				case STRING:
					break;
				case BOOLEAN:
					final String BOOLEAN_EXP = "(true|TRUE|false|FALSE)";

					if (!Pattern.matches(BOOLEAN_EXP, value))
						throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_VALUE,
								getAttribute().getType() + ":" + value);
					break;
				case NUMBER:
					final String NEGATIVE = "-(?!0)\\d+";
					final String ZERO = "0";
					final String POSITIVE = "(?!0)\\d+";
					final String LITERAL_EXP = "(" + NEGATIVE + "|" + ZERO + "|" + POSITIVE + ")";

					if (!Pattern.matches(LITERAL_EXP, value))
						throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_VALUE,
								getAttribute().getType() + ":" + value);
					break;
				case DATE:
					final String DATE_EXP = "[01]\\d-[01]\\d-\\d{4}";

					if (!Pattern.matches(DATE_EXP, value))
						throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_VALUE,
								getAttribute().getType() + ":" + value);
					break;
				default:
					assert false;
				}
			}
		}
	}

}
