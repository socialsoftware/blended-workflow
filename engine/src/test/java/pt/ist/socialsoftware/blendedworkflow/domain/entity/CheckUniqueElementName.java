package pt.ist.socialsoftware.blendedworkflow.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckUniqueElementName extends TeardownRollbackTest {
	private static final String ENTITY_NAME_ONE = "EntityOne";
	private static final String ENTITY_NAME_TWO = "EntityTwo";
	private static final String ELEMENT_NAME_TWO = "nameTwo";
	private static final String ELEMENT_NAME_THREE = "nameThree";
	private static final String ELEMENT_NAME_FOUR = "nameFour";

	Specification spec = null;
	private Entity entityOne = null;
	private Entity entityTwo = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		entityOne = new Entity(spec.getDataModel(), ENTITY_NAME_ONE, false);
		entityTwo = new Entity(spec.getDataModel(), ENTITY_NAME_TWO, false);

		new Attribute(spec.getDataModel(), entityOne, ELEMENT_NAME_TWO, AttributeType.BOOLEAN, false, false,
				false);
		new RelationBW(spec.getDataModel(), "relation", entityOne, "entity", Cardinality.ONE, false, entityTwo,
				ELEMENT_NAME_THREE, Cardinality.ONE_MANY, false);
		new Rule(entityOne, ELEMENT_NAME_FOUR, null);
	}

	@Test
	public void attributeBasicRuleClash() {
		try {
			new Attribute(spec.getDataModel(), entityOne, ELEMENT_NAME_FOUR, AttributeType.BOOLEAN, false, false,
					false);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void relationRuleClash() {
		try {
			new RelationBW(spec.getDataModel(), "relationTwo", entityOne, "entityOne", Cardinality.ONE, false,
					entityTwo, ELEMENT_NAME_FOUR, Cardinality.ONE_MANY, false);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void attributeBasicRelationClash() {
		try {
			new Attribute(spec.getDataModel(), entityOne, ELEMENT_NAME_THREE, AttributeType.BOOLEAN, false, false,
					false);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void ruleRelationClash() {
		try {
			new Rule(entityOne, ELEMENT_NAME_THREE, null);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void ruleAttributeBasicClash() {
		try {
			new Rule(entityOne, ELEMENT_NAME_TWO, null);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

}
