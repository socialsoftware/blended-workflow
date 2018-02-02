package pt.ist.socialsoftware.blendedworkflow.core.domain.conditionmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ConditionModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GenerateConditionsMethodTest extends TeardownRollbackTest {
	private static final String ENTITY_ONE_NAME = "Entity one name";
	private static final String ENTITY_TWO_NAME = "Entity two name";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att2";
	private static final String ROLENAME_ONE = "theOne";
	private static final String ROLENAME_TWO = "theTwo";
	private static final String RULE_NAME = "rule";

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	RelationBW relation;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true, false, false);
		this.attributeTwo = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true, false, false);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.attributeThree = new Attribute(this.spec.getDataModel(), this.entityTwo, ATTRIBUTE_THREE_NAME,
				AttributeType.BOOLEAN, true, false, false);

		this.relation = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		this.entityThree = new Entity(this.spec.getDataModel(), "Entity three name", false);

		new Dependence(this.spec.getDataModel(), this.attributeThree,
				ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME);

		new Rule(this.entityOne, RULE_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));
	}

	@Test
	public void success() {
		ConditionModel conditionModel = this.spec.getConditionModel();

		assertEquals(0, conditionModel.getEntityAchieveConditionSet().size());
		assertEquals(0, conditionModel.getAttributeAchieveConditionSet().size());
		assertEquals(0, conditionModel.getEntityInvariantConditionSet().size());
		assertEquals(0, conditionModel.getAttributeInvariantConditionSet().size());
		assertEquals(0, conditionModel.getEntityDependenceConditionSet().size());
		assertEquals(0, conditionModel.getAttributeDependenceConditionSet().size());

		conditionModel.generateConditions();

		assertEquals(3, conditionModel.getEntityAchieveConditionSet().size());
		assertEquals(3, conditionModel.getAttributeAchieveConditionSet().size());
		assertEquals(2, conditionModel.getEntityInvariantConditionSet().size());
		assertEquals(1, conditionModel.getAttributeInvariantConditionSet().size());
		assertEquals(0, conditionModel.getEntityDependenceConditionSet().size());
		assertEquals(1, conditionModel.getAttributeDependenceConditionSet().size());

	}

}
