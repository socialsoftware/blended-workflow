package pt.ist.socialsoftware.blendedworkflow.core.domain.attributeinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.AttributeInstanceDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class DefineSkippedAttributeInstanceMethodTest extends TeardownRollbackTest {
	private static final String ENT_NAME = "EntOne";
	private static final String ATT_NAME = "AttOne";
	
	Specification spec = null;
	Entity entity;
	EntityInstance entityInstance;
	AttributeInstanceDto attributeInstanceDto;
	AttributeInstance attributeInstance;
	Attribute attribute;
	WorkflowInstance workflowInstance = null;
	
	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec");
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");
		
		entity = new Entity(spec.getDataModel(), ENT_NAME, false);	
		entityInstance = new EntityInstance(workflowInstance, entity, ProductInstanceState.SKIPPED);
		
		attribute = new Attribute(spec.getDataModel(), entity, ATT_NAME, AttributeType.NUMBER, true);
		attributeInstance = new AttributeInstance(entityInstance, attribute, "",  ProductInstanceState.SKIPPED);
		attributeInstanceDto = new AttributeInstanceDto();
		
		attributeInstanceDto.setExternalId(attributeInstance.getExternalId());
	}
	
	@Test
	public void successfullAttributeDefinition() {
		attributeInstanceDto.setValue("123");
		
		attributeInstanceDto.getAttributeInstance(workflowInstance, attributeInstanceDto.getExternalId()).get()
			.defineSkippedAttributeInstance(attributeInstanceDto);
		
		assertEquals(attributeInstance.getState(), ProductInstanceState.DEFINED);
		assertEquals(attributeInstance.getValue(), attributeInstanceDto.getValue());
		assertEquals(entityInstance.getState(), ProductInstanceState.DEFINED);
	}
	
	@Test
	public void emptyValueAttributeDefinition() {
		attributeInstanceDto.setValue("");
		
		try {
			attributeInstanceDto.getAttributeInstance(workflowInstance, attributeInstanceDto.getExternalId()).get()
				.defineSkippedAttributeInstance(attributeInstanceDto);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.EMPTY_INPUT_VALUE, bwe.getError());
			assertEquals("Empty input value on entity instance " + entityInstance.getEntity().getName() + 
					"[" + entityInstance.getId() + "]", bwe.getMessage());
		}
	}
	
	@Test
	public void incorrectValueTypeAttributeDefinition() {
		attributeInstanceDto.setValue("abc");
		
		try {
			attributeInstanceDto.getAttributeInstance(workflowInstance, attributeInstanceDto.getExternalId()).get()
				.defineSkippedAttributeInstance(attributeInstanceDto);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(entityInstance.getEntity().getName() + "[" + entityInstance.getId() + "] " + 
					attributeInstance.getAttribute().getType() + ":" + attributeInstanceDto.getValue(), bwe.getMessage());
		}
	}
}
