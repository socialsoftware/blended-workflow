package pt.ist.socialsoftware.blendedworkflow.domain.attributeinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class SetValueTest extends TeardownRollbackTest {
	private static final String STRING_VALUE = "value";

	Specification spec = null;
	Entity entity = null;
	AttributeInstance attributeInstanceString = null;
	AttributeInstance attributeInstanceBoolean = null;
	AttributeInstance attributeInstanceNumber = null;
	AttributeInstance attributeInstanceDate = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		entity = new Entity(spec.getDataModel(), "entityName", false);
		Attribute attributeString = new Attribute(spec.getDataModel(), entity, "name1", Attribute.AttributeType.STRING,
				false, false, false);
		Attribute attributeBoolean = new Attribute(spec.getDataModel(), entity, "name2",
				Attribute.AttributeType.BOOLEAN, false, false, false);
		Attribute attributeNumber = new Attribute(spec.getDataModel(), entity, "name3", Attribute.AttributeType.NUMBER,
				false, false, false);
		Attribute attributeDate = new Attribute(spec.getDataModel(), entity, "name4", Attribute.AttributeType.DATE,
				false, false, false);

		WorkflowInstance workflowInstance = new WorkflowInstance(spec, "name");
		EntityInstance entityInstance = new EntityInstance(workflowInstance, entity);
		attributeInstanceString = new AttributeInstance(entityInstance, attributeString, null);
		attributeInstanceBoolean = new AttributeInstance(entityInstance, attributeBoolean, null);
		attributeInstanceNumber = new AttributeInstance(entityInstance, attributeNumber, null);
		attributeInstanceDate = new AttributeInstance(entityInstance, attributeDate, null);
	}

	@Test
	public void successString() {
		attributeInstanceString.setValue(STRING_VALUE);

		assertEquals(STRING_VALUE, attributeInstanceString.getValue());
	}

	@Test
	public void successNumber() {
		attributeInstanceNumber.setValue("123");

		assertEquals("123", attributeInstanceNumber.getValue());
	}

	@Test
	public void successBoolean() {
		attributeInstanceBoolean.setValue("true");

		assertEquals("true", attributeInstanceBoolean.getValue());
	}

	@Test
	public void successDate() {
		attributeInstanceDate.setValue("12-12-2012");

		assertEquals("12-12-2012", attributeInstanceDate.getValue());
	}

	@Test
	public void attributeValueBoolFail() {
		try {
			attributeInstanceBoolean.setValue(STRING_VALUE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(AttributeType.BOOLEAN + ":" + STRING_VALUE, bwe.getMessage());
		}
	}

	@Test
	public void attributeValueNumberFail() {
		try {
			attributeInstanceNumber.setValue(STRING_VALUE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(AttributeType.NUMBER + ":" + STRING_VALUE, bwe.getMessage());
		}
	}

	@Test
	public void attributeValueDateFail() {
		try {
			attributeInstanceDate.setValue(STRING_VALUE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(AttributeType.DATE + ":" + STRING_VALUE, bwe.getMessage());
		}
	}

}
