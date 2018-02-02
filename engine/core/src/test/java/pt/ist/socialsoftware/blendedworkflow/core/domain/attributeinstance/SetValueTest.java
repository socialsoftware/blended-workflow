package pt.ist.socialsoftware.blendedworkflow.core.domain.attributeinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		this.entity = new Entity(this.spec.getDataModel(), "entityName", false);
		Attribute attributeString = new Attribute(this.spec.getDataModel(), this.entity, "name1",
				Attribute.AttributeType.STRING, false, false, false);
		Attribute attributeBoolean = new Attribute(this.spec.getDataModel(), this.entity, "name2",
				Attribute.AttributeType.BOOLEAN, false, false, false);
		Attribute attributeNumber = new Attribute(this.spec.getDataModel(), this.entity, "name3",
				Attribute.AttributeType.NUMBER, false, false, false);
		Attribute attributeDate = new Attribute(this.spec.getDataModel(), this.entity, "name4",
				Attribute.AttributeType.DATE, false, false, false);

		WorkflowInstance workflowInstance = new WorkflowInstance(this.spec, "name");
		EntityInstance entityInstance = new EntityInstance(workflowInstance, this.entity);
		this.attributeInstanceString = new AttributeInstance(entityInstance, attributeString, null);
		this.attributeInstanceBoolean = new AttributeInstance(entityInstance, attributeBoolean, null);
		this.attributeInstanceNumber = new AttributeInstance(entityInstance, attributeNumber, null);
		this.attributeInstanceDate = new AttributeInstance(entityInstance, attributeDate, null);
	}

	@Test
	public void successString() {
		this.attributeInstanceString.setValue(STRING_VALUE);

		assertEquals(STRING_VALUE, this.attributeInstanceString.getValue());
	}

	@Test
	public void successNumber() {
		this.attributeInstanceNumber.setValue("123");

		assertEquals("123", this.attributeInstanceNumber.getValue());
	}

	@Test
	public void successBoolean() {
		this.attributeInstanceBoolean.setValue("true");

		assertEquals("true", this.attributeInstanceBoolean.getValue());
	}

	@Test
	public void successDate() {
		this.attributeInstanceDate.setValue("12/12/2012");

		assertEquals("12/12/2012", this.attributeInstanceDate.getValue());
	}

	@Test
	public void attributeValueBoolFail() {
		try {
			this.attributeInstanceBoolean.setValue(STRING_VALUE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(AttributeType.BOOLEAN + ":" + STRING_VALUE, bwe.getMessage());
		}
	}

	@Test
	public void attributeValueNumberFail() {
		try {
			this.attributeInstanceNumber.setValue(STRING_VALUE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(AttributeType.NUMBER + ":" + STRING_VALUE, bwe.getMessage());
		}
	}

	@Test
	public void attributeValueDateFail() {
		try {
			this.attributeInstanceDate.setValue(STRING_VALUE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(AttributeType.DATE + ":" + STRING_VALUE, bwe.getMessage());
		}
	}

}
