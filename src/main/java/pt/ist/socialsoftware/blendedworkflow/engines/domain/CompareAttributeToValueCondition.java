package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class CompareAttributeToValueCondition extends CompareAttributeToValueCondition_Base {

	public CompareAttributeToValueCondition() {
		super();
	}

	public CompareAttributeToValueCondition(Attribute attribute) {
		setAttribute(attribute);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getAttribute().getEntity().getName());
		Attribute attribute = entity.getAttribute(getAttribute().getName());
		return new CompareAttributeToValueCondition(attribute) ;
	}

	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		Entity entity = getAttribute().getEntity();
		
		DataModelInstance dataModelInstance = goalWorkItem.getBwInstance().getDataModelInstance();
		
		// Should evolve when a entity allows more than one instance
		EntityInstance entityInstance = entity.getFirstEntityInstance();
		if (entityInstance != null) {
			for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
				if (attributeInstance.getAttribute().equals(getAttribute())) {
					goalWorkItem.addAttributeInstances(attributeInstance);
				}
			}
		} else {
			entityInstance = new EntityInstance(dataModelInstance, entity);
			AttributeInstance attributeInstance = new AttributeInstance(getAttribute(), entityInstance);
			goalWorkItem.addAttributeInstances(attributeInstance);
		}				

	}

}
