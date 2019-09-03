package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeInstanceDto;
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
		if (super.getState() == ProductInstanceState.DEFINED)
			checkValue(value);
		
		super.setValue(value);
	}

	public AttributeInstance(EntityInstance entityInstance, Attribute attribute, String value,  ProductInstanceState state) {
		setEntityInstance(entityInstance);
		setAttribute(attribute);
		setState(state);
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
	public ProductInstanceDto getDto() {
		ProductInstanceDto productInstanceDto = new ProductInstanceDto();
		productInstanceDto.setProduct(getAttribute().getDto());
		productInstanceDto.setExternalId(getExternalId());
		productInstanceDto.setPath(getAttribute().getFullPath());
		productInstanceDto.setValue(getAttribute().getEntity().getName() + "[" + getEntityInstance().getId() + ","
				+ getAttribute().getName() + "[" + getValue() + "]]");

		return productInstanceDto;
	}
	
	public void defineAttributeInstance(AttributeInstanceDto attributeInstanceDto) {		
		setState(ProductInstanceState.DEFINED);
		setValue(attributeInstanceDto.getValue());
	}
	
	public void defineEntityInstance(EntityInstance entityInstance) {
		if (entityInstance.getState().equals(ProductInstanceState.SKIPPED))
			entityInstance.setState(ProductInstanceState.DEFINED);
	}

	public void defineSkippedAttributeInstance(AttributeInstanceDto attributeInstanceDto) {
		if (getState().equals(ProductInstanceState.SKIPPED)) {
			defineAttributeInstance(attributeInstanceDto);
			defineEntityInstance(getEntityInstance());
		}
	}
	
	public void checkValueInDependencyTreeNode(AttributeInstanceDto attributeInstanceDto, EntityInstance entityInstance) {
		if (attributeInstanceDto.getValue().equals("")) 
			throw new BWException(BWErrorType.EMPTY_INPUT_VALUE,
					"Empty input value on entity instance " + entityInstance.getEntity().getName() + "[" + entityInstance.getId() + "]");
		
		try {
			checkValue(attributeInstanceDto.getValue());
		} catch (BWException attributeinstance_consistency) {
			throw new BWException(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY,
					entityInstance.getEntity().getName() + "[" + entityInstance.getId() + "] " + 
					getAttribute().getType() + ":" + attributeInstanceDto.getValue());
		}
	}
	
	public void checkSkippedAttributeInstance(AttributeInstanceDto attributeInstanceDto) {
		if (getState().equals(ProductInstanceState.SKIPPED))
			checkValueInDependencyTreeNode(attributeInstanceDto, getEntityInstance());
	}
	
	// TODO: Test this method when attributeInstance has more than one dependence
	public List<AttributeInstance> getDependentAttributeInstances(WorkflowInstance workflowInstance) {
		Set<Dependence> dependences = getAttribute().getDependenceSet();
		List<List<AttributeInstance>> targetAttributeInstances = new ArrayList<List<AttributeInstance>>();
			
		dependences.stream()
			.forEach(dependence -> 
				targetAttributeInstances.add(dependence.getTargetAttributeInstances(this.getDependentProductInstances(dependence), workflowInstance))
			);

		return targetAttributeInstances.stream().flatMap(List::stream).collect(Collectors.toList());
	}
	
	public List<AttributeInstance> getNextDependentAttributeInstances(WorkflowInstance workflowInstance, 
			List<AttributeInstance> attributeInstances) {
		return attributeInstances.stream()
				.flatMap(ai -> ai.getDependentAttributeInstances(workflowInstance).stream()).collect(Collectors.toList());
	}
}
