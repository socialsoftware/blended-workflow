package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.regex.Pattern;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ProductInstanceDto;

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
				throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
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

					if (!Pattern.matches(BOOLEAN_EXP, value)) {
						throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
								getAttribute().getType() + ":" + value);
					}
					break;
				case NUMBER:
					final String NEGATIVE = "-(?!0)\\d+";
					final String ZERO = "0";
					final String POSITIVE = "(?!0)\\d+";
					final String LITERAL_EXP = "(" + NEGATIVE + "|" + ZERO + "|" + POSITIVE + ")";

					if (!Pattern.matches(LITERAL_EXP, value)) {
						throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
								getAttribute().getType() + ":" + value);
					}
					break;
				case DATE:
					final String DATE_EXP = "[01]\\d/[01]\\d/\\d{4}";

					if (!Pattern.matches(DATE_EXP, value)) {
						throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
								getAttribute().getType() + ":" + value);
					}
					break;
				default:
					assert false;
				}
			}
		}
	}

	@Override
	public void delete() {
		setEntityInstance(null);
		setAttribute(null);

		super.delete();
	}

	@Override
	public WorkflowInstance getWorkflowInstance() {
		return getEntityInstance().getWorkflowInstance();
	}

	@Override
	public Product getProduct() {
		return getAttribute();
	}

	@Override
	public Entity getEntity() {
		return getAttribute().getEntity();
	}

	@Override
	public boolean isDefined() {
		if (getValue() == null || getValue().length() == 0) {
			throw new BWException(BWErrorType.NOT_DEFINED, getAttribute().getFullPath());
		}

		return true;
	}

	@Override
	public boolean holdsPre(DefPathCondition defPathCondition) {
		return getAttribute() == defPathCondition.getTargetOfPath() && isDefined();
	}

	@Override
	public ProductInstanceDto getDTO() {
		ProductInstanceDto productInstanceDto = new ProductInstanceDto();
		productInstanceDto.setProduct(getAttribute().getDto());
		productInstanceDto.setExternalId(getExternalId());
		productInstanceDto.setPath(getAttribute().getFullPath());
		productInstanceDto.setValue(getAttribute().getEntity().getName() + "[" + getEntityInstance().getId() + ","
				+ getAttribute().getName() + "[" + getValue() + "]]");

		return productInstanceDto;
	}

}
