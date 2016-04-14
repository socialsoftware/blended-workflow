package pt.ist.socialsoftware.blendedworkflow.domain.attributeinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateAttributeInstanceTest extends TeardownRollbackTest {
	private static final String STRING_VALUE = "value";

	Specification spec = null;
	Entity entity = null;
	Attribute attribute = null;
	EntityInstance entityInstance = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		entity = new Entity(spec.getDataModel(), "entityName", false);
		attribute = new Attribute(spec.getDataModel(), entity, "name", Attribute.AttributeType.STRING, false, false,
				false);

		WorkflowInstance workflowInstance = new WorkflowInstance(spec, "name");
		entityInstance = new EntityInstance(workflowInstance, entity);
	}

	@Test
	public void success() {
		AttributeInstance attributeInstance = new AttributeInstance(entityInstance, attribute, STRING_VALUE);

		assertEquals(entityInstance, attributeInstance.getEntityInstance());
		assertEquals(attribute, attributeInstance.getAttribute());
		assertEquals(STRING_VALUE, attributeInstance.getValue());
	}

	@Test
	public void attributeDoesNotBelongToEntity() {
		Entity otherEntity = new Entity(spec.getDataModel(), "otherEntityName", false);
		Attribute otherAttribute = new Attribute(spec.getDataModel(), otherEntity, "name",
				Attribute.AttributeType.STRING, false, false, false);

		try {
			new AttributeInstance(entityInstance, otherAttribute, STRING_VALUE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CREATE_ATTRIBUTE_INSTANCE, bwe.getError());
			assertEquals(entity.getName() + ":" + otherAttribute.getName(), bwe.getMessage());
		}

	}

}
