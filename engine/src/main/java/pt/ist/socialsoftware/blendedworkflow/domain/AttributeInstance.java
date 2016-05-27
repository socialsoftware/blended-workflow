package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;
import java.util.regex.Pattern;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductInstanceDTO;

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

					if (!Pattern.matches(BOOLEAN_EXP, value))
						throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
								getAttribute().getType() + ":" + value);
					break;
				case NUMBER:
					final String NEGATIVE = "-(?!0)\\d+";
					final String ZERO = "0";
					final String POSITIVE = "(?!0)\\d+";
					final String LITERAL_EXP = "(" + NEGATIVE + "|" + ZERO + "|" + POSITIVE + ")";

					if (!Pattern.matches(LITERAL_EXP, value))
						throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
								getAttribute().getType() + ":" + value);
					break;
				case DATE:
					final String DATE_EXP = "[01]\\d/[01]\\d/\\d{4}";

					if (!Pattern.matches(DATE_EXP, value))
						throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
								getAttribute().getType() + ":" + value);
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
	public boolean isDefined() {
		if (getValue() == null || getValue().length() == 0)
			throw new BWException(BWErrorType.NOT_DEFINED, getAttribute().getFullPath());

		return true;
	}

	@Override
	public boolean holdsPost(DefProductCondition defProductCondition, Set<MulCondition> mulConditionSet) {
		if (getAttribute() != defProductCondition.getTargetOfPath() || !isDefined())
			throw new BWException(BWErrorType.WORK_ITEM_ARGUMENT_CONSISTENCY, "post work item argument "
					+ getAttribute().getFullPath() + ":" + defProductCondition.getTargetOfPath().getFullPath());

		return true;
	}

	@Override
	public boolean holdsPre(DefPathCondition defPathCondition) {
		return getAttribute() == defPathCondition.getTargetOfPath() && isDefined();
	}

	@Override
	public ProductInstanceDTO getDTO() {
		ProductInstanceDTO productInstanceDTO = new ProductInstanceDTO();
		productInstanceDTO.setProduct(getAttribute().getDTO());
		productInstanceDTO.setExternalId(getExternalId());
		productInstanceDTO.setPath(getAttribute().getFullPath());
		productInstanceDTO.setValue(getAttribute().getFullPath() + "[" + getValue() + "]");

		return productInstanceDTO;
	}

}
