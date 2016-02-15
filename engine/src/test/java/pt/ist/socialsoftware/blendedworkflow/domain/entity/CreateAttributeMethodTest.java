package pt.ist.socialsoftware.blendedworkflow.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateAttributeMethodTest extends TeardownRollbackTest {
	private static String ATT_NAME_ONE = "Attribute name one";
	private static String ATT_NAME_TWO = "Attribute name two";
	private static String ATT_NAME_THREE = "Attribute name three";
	private static String ATT_GROUP_NAME = "Attribute group name";
	private static String EXISTS_NAME = "Attribute name exist";
	private static String EMPTY_NAME = "";

	private Entity entity;
	private AttributeGroup attGroup;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		entity = new Entity(spec.getDataModel(), "Entity name", false);
		new AttributeBasic(spec.getDataModel(), entity, null, EXISTS_NAME, AttributeType.BOOLEAN, true, false, false);
		attGroup = new AttributeGroup(spec.getDataModel(), entity, ATT_GROUP_NAME, true);
	}

	@Test
	public void success() {
		AttributeBasic att1 = entity.createAttribute(null, ATT_NAME_ONE, AttributeType.STRING, true);
		AttributeBasic att2 = entity.createAttribute(null, ATT_NAME_TWO, AttributeType.BOOLEAN, false);
		AttributeBasic att3 = entity.createAttribute(null, ATT_NAME_THREE, AttributeType.NUMBER, false);

		assertEquals(5, entity.getAttributeSet().size());
		assertEquals(ATT_NAME_ONE, att1.getName());
		assertEquals(att1, entity.getAttribute(ATT_NAME_ONE).orElse(null));
		assertEquals(att2, entity.getAttribute(ATT_NAME_TWO).orElse(null));
		assertEquals(att3, entity.getAttribute(ATT_NAME_THREE).orElse(null));
		assertEquals(AttributeType.STRING, att1.getType());
		assertEquals(AttributeType.BOOLEAN, att2.getType());
		assertEquals(AttributeType.NUMBER, att3.getType());

	}

	@Test
	public void successWithGroup() {
		AttributeBasic att1 = entity.createAttribute(attGroup, ATT_NAME_ONE, AttributeType.STRING, true);

		assertEquals(3, entity.getAttributeSet().size());
		assertEquals(ATT_NAME_ONE, att1.getName());
		assertEquals(att1, entity.getAttribute(ATT_NAME_ONE).orElse(null));
		assertEquals(AttributeType.STRING, att1.getType());

		assertEquals(1, attGroup.getAttributeSet().size());
		assertEquals(att1, attGroup.getAttribute(ATT_NAME_ONE).orElse(null));
	}

	@Test
	public void existsName() {
		try {
			entity.createAttribute(null, EXISTS_NAME, AttributeType.NUMBER, true);
			fail("Able to create an attribute with the same name");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void emptyName() {
		try {
			entity.createAttribute(null, EMPTY_NAME, AttributeType.BOOLEAN, true);
			fail("Able to create an attribute with empty name");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ATTRIBUTE_NAME, bwe.getError());
		}
	}

	@Test
	public void nullName() {
		try {
			entity.createAttribute(null, null, AttributeType.STRING, true);
			fail("Able to create an attribute with null name");
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INVALID_ATTRIBUTE_NAME, bwe.getError());
		}
	}

}
