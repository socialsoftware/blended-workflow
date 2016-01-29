package pt.ist.socialsoftware.blendedworkflow.domain.conditionmodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.ConditionModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
	AttributeBasic attributeOne;
	AttributeBasic attributeTwo;
	AttributeBasic attributeThree;
	RelationBW relation;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		attributeOne = new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true, false, false);
		attributeTwo = new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true, false, false);

		entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
		attributeThree = new AttributeBasic(spec.getDataModel(), entityTwo, null, ATTRIBUTE_THREE_NAME,
				AttributeType.BOOLEAN, true, false, false);

		relation = new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		entityThree = new Entity(spec.getDataModel(), "Entity three name", false);

		new Dependence(spec.getDataModel(), attributeThree,
				ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME);

		new Rule(spec.getDataModel(), RULE_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));
	}

	@Test
	public void success() {
		ConditionModel conditionModel = spec.getConditionModel();

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
