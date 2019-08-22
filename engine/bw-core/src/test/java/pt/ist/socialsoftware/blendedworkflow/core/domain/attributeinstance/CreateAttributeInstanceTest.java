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
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateAttributeInstanceTest extends TeardownRollbackTest {
	private static final String STRING_VALUE = "value";

	Specification spec = null;
	Entity entity = null;
	Attribute attribute = null;
	EntityInstance entityInstance = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		this.entity = new Entity(this.spec.getDataModel(), "entityName", false);
		this.attribute = new Attribute(this.spec.getDataModel(), this.entity, "name", Attribute.AttributeType.STRING,
				false);

		WorkflowInstance workflowInstance = new WorkflowInstance(this.spec, "name");
		this.entityInstance = new EntityInstance(workflowInstance, this.entity, ProductInstanceState.DEFINED);
	}

	@Test
	public void success() {
		AttributeInstance attributeInstance = new AttributeInstance(this.entityInstance, this.attribute, STRING_VALUE, 
				ProductInstanceState.DEFINED);

		assertEquals(this.entityInstance, attributeInstance.getEntityInstance());
		assertEquals(this.attribute, attributeInstance.getAttribute());
		assertEquals(STRING_VALUE, attributeInstance.getValue());
	}

	@Test
	public void attributeDoesNotBelongToEntity() {
		Entity otherEntity = new Entity(this.spec.getDataModel(), "otherEntityName", false);
		Attribute otherAttribute = new Attribute(this.spec.getDataModel(), otherEntity, "name",
				Attribute.AttributeType.STRING, false);

		try {
			new AttributeInstance(this.entityInstance, otherAttribute, STRING_VALUE, ProductInstanceState.DEFINED);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.ATTRIBUTEINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals(this.entity.getName() + ":" + otherAttribute.getName(), bwe.getMessage());
		}

	}

}
