package pt.ist.socialsoftware.blendedworkflow.domain.entityinstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class AttributesNotDefinedMethodTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";

	Specification spec = null;
	Entity entOne;
	Attribute attOne;
	Attribute attTwo;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		DataModel dataModel = spec.getDataModel();
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);

		attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attTwo = new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);
	}

	@Test
	public void attributeIsNotDefined() {
		Set<Attribute> attributes = new HashSet<Attribute>();
		attributes.add(attOne);

		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);

		boolean result = entityInstance.attributesNotDefined(attributes);

		assertTrue(result);
	}

	@Test
	public void twoAttributesOneIsNotDefined() {
		Set<Attribute> attributes = new HashSet<Attribute>();
		attributes.add(attOne);
		attributes.add(attTwo);

		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);
		new AttributeInstance(entityInstance, attOne, "123");

		boolean result = entityInstance.attributesNotDefined(attributes);

		assertFalse(result);
	}

	@Test
	public void twoAttributesNoneIsDefined() {
		Set<Attribute> attributes = new HashSet<Attribute>();
		attributes.add(attOne);
		attributes.add(attTwo);

		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);

		boolean result = entityInstance.attributesNotDefined(attributes);

		assertTrue(result);
	}

}
