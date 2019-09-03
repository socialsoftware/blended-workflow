package pt.ist.socialsoftware.blendedworkflow.core.domain.entityinstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class HasAttributeInstanceMethodTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ATT_ONE_NAME = "AttOne";
	
	Specification spec = null;
	Entity entity;
	Attribute attribute;	
	EntityInstance entityInstanceOne, entityInstanceTwo;
	AttributeInstance attributeInstanceOne, attributeInstanceTwo;
	WorkflowInstance workflowInstance = null;
	
	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec");
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");
		
		entity = new Entity(spec.getDataModel(), ENT_ONE_NAME, false);	
		attribute = new Attribute(spec.getDataModel(), entity, ATT_ONE_NAME, AttributeType.NUMBER, true);
		
		entityInstanceOne = new EntityInstance(workflowInstance, entity, ProductInstanceState.DEFINED);
		attributeInstanceOne = new AttributeInstance(entityInstanceOne, attribute, "1",  ProductInstanceState.DEFINED);
		
		entityInstanceTwo = new EntityInstance(workflowInstance, entity, ProductInstanceState.DEFINED);
		attributeInstanceTwo = new AttributeInstance(entityInstanceTwo, attribute, "2",  ProductInstanceState.DEFINED);
	}
	
	@Test
	public void entityInstanceHasAttributeInstance() {
		assertTrue(entityInstanceOne.hasAttributeInstance(attributeInstanceOne.getExternalId()));
		assertTrue(entityInstanceTwo.hasAttributeInstance(attributeInstanceTwo.getExternalId()));
	}
	
	@Test
	public void entityInstanceDoesntHaveAttributeInstance() {
		assertFalse(entityInstanceOne.hasAttributeInstance(attributeInstanceTwo.getExternalId()));
		assertFalse(entityInstanceTwo.hasAttributeInstance(attributeInstanceOne.getExternalId()));
	}
}
