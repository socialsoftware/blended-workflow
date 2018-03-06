package pt.ist.socialsoftware.blendedworkflow.core.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateAttributeMethodTest extends TeardownRollbackTest {
	private static String ATT_NAME_ONE = "Attribute name one";
	private static String ATT_NAME_TWO = "Attribute name two";
	private static String ATT_NAME_THREE = "Attribute name three";
	private static String ATT_GROUP_NAME = "Attribute group name";
	private static String EXISTS_NAME = "Attribute name exist";
	private static String EMPTY_NAME = "";

	private Entity entity;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec");
		this.entity = new Entity(spec.getDataModel(), "Entity name", false);
		new Attribute(spec.getDataModel(), this.entity, EXISTS_NAME, AttributeType.BOOLEAN, true, false, false);
	}

	@Test
	public void success() {
		Attribute att1 = this.entity.createAttribute(ATT_NAME_ONE, AttributeType.STRING, true);
		Attribute att2 = this.entity.createAttribute(ATT_NAME_TWO, AttributeType.BOOLEAN, false);
		Attribute att3 = this.entity.createAttribute(ATT_NAME_THREE, AttributeType.NUMBER, false);

		assertEquals(4, this.entity.getAttributeSet().size());
		assertEquals(ATT_NAME_ONE, att1.getName());
		assertEquals(att1, this.entity.getAttribute(ATT_NAME_ONE).orElse(null));
		assertEquals(att2, this.entity.getAttribute(ATT_NAME_TWO).orElse(null));
		assertEquals(att3, this.entity.getAttribute(ATT_NAME_THREE).orElse(null));
		assertEquals(AttributeType.STRING, att1.getType());
		assertEquals(AttributeType.BOOLEAN, att2.getType());
		assertEquals(AttributeType.NUMBER, att3.getType());

	}

	@Test
	public void successWithGroup() {
		Attribute att1 = this.entity.createAttribute(ATT_NAME_ONE, AttributeType.STRING, true);

		assertEquals(2, this.entity.getAttributeSet().size());
		assertEquals(ATT_NAME_ONE, att1.getName());
		assertEquals(att1, this.entity.getAttribute(ATT_NAME_ONE).orElse(null));
		assertEquals(AttributeType.STRING, att1.getType());
	}

	@Test
	public void existsName() {
		try {
			this.entity.createAttribute(EXISTS_NAME, AttributeType.NUMBER, true);
			fail("Able to create an attribute with the same name");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void emptyName() {
		try {
			this.entity.createAttribute(EMPTY_NAME, AttributeType.BOOLEAN, true);
			fail("Able to create an attribute with empty name");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ATTRIBUTE_NAME, bwe.getError());
		}
	}

	@Test
	public void nullName() {
		try {
			this.entity.createAttribute(null, AttributeType.STRING, true);
			fail("Able to create an attribute with null name");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ATTRIBUTE_NAME, bwe.getError());
		}
	}

}
