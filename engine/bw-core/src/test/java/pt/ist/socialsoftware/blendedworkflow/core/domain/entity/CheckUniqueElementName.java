package pt.ist.socialsoftware.blendedworkflow.core.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec");
		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_NAME_ONE, false);
		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_NAME_TWO, false);

		new Attribute(this.spec.getDataModel(), this.entityOne, ELEMENT_NAME_TWO, AttributeType.BOOLEAN, false);
		new RelationBW(this.spec.getDataModel(), "relation", this.entityOne, "entity", Cardinality.ONE, false,
				this.entityTwo, ELEMENT_NAME_THREE, Cardinality.ONE_MANY, false);
		new Rule(this.entityOne, ELEMENT_NAME_FOUR, null);
	}

	@Test
	public void attributeBasicRuleClash() {
		try {
			new Attribute(this.spec.getDataModel(), this.entityOne, ELEMENT_NAME_FOUR, AttributeType.BOOLEAN, false);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void relationRuleClash() {
		try {
			new RelationBW(this.spec.getDataModel(), "relationTwo", this.entityOne, "entityOne", Cardinality.ONE, false,
					this.entityTwo, ELEMENT_NAME_FOUR, Cardinality.ONE_MANY, false);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void attributeBasicRelationClash() {
		try {
			new Attribute(this.spec.getDataModel(), this.entityOne, ELEMENT_NAME_THREE, AttributeType.BOOLEAN, false);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void ruleRelationClash() {
		try {
			new Rule(this.entityOne, ELEMENT_NAME_THREE, null);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

	@Test
	public void ruleAttributeBasicClash() {
		try {
			new Rule(this.entityOne, ELEMENT_NAME_TWO, null);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
		}
	}

}
