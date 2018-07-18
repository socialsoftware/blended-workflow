package pt.ist.socialsoftware.blendedworkflow.core.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AssociationGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class ExtractProductGoalMethodTest extends TeardownRollbackTest {
	private static final String ENTITY_TWO_NAME = "EntTwo";
	private static final String ENTITY_ONE_NAME = "EntOne";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_FIVE_NAME = "att5";
	private static final String ATTRIBUTE_SIX_NAME = "att6";
	private static final String RULE_CONDITION = "rule";
	private static final String ROLENAME_ONE = "theOne";
	private static final String ROLENAME_TWO = "theTwo";
	private static final String DEPENDENCE_PATH_ONE = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String DEPENDENCE_PATH_TWO = ENTITY_ONE_NAME + "." + ROLENAME_TWO + "." + ATTRIBUTE_SIX_NAME;
	private static final String GOAL_ONE = "goalOne";
	private static final String GOAL_TWO = "goalTwo";
	private static final String GOAL_OTHERS = "goalOthers";
	private static final String GOAL_ASSOCIATION = "goalAssociation";

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	Attribute attributeFour;
	Attribute attributeFive;
	Attribute attributeSix;
	RelationBW relation;
	Rule rule;
	ProductGoal goalOne;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true);
		this.attributeTwo = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true);
		this.attributeFive = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_FIVE_NAME,
				AttributeType.NUMBER, true);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.attributeThree = new Attribute(this.spec.getDataModel(), this.entityTwo, "att3", AttributeType.BOOLEAN,
				true);
		this.attributeFour = new Attribute(this.spec.getDataModel(), this.entityTwo, "att4", AttributeType.STRING,
				true);
		this.attributeSix = new Attribute(this.spec.getDataModel(), this.entityTwo, ATTRIBUTE_SIX_NAME,
				AttributeType.NUMBER, true);

		this.relation = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		new Dependence(this.spec.getDataModel(), this.attributeFour, DEPENDENCE_PATH_ONE);
		new Dependence(this.spec.getDataModel(), this.attributeFive, DEPENDENCE_PATH_TWO);

		this.rule = new Rule(this.entityOne, RULE_CONDITION,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.spec.getDataModel().checkPaths();

		this.spec.getConditionModel().generateConditions();
	}

	@Test
	public void sourceDoesNotContainCondition() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductOtherConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));

		try {
			this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void sourceCannotEndWithEmptySuccessCondition() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductOtherConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));

		try {
			this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void conditionsToExtractShouldNotBeEmpty() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductOtherConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();

		try {
			this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void implicitEntityAttributeDependence() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));

		try {
			this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void explicitAttributeAttributeDependence() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductOtherConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));

		try {
			this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void separateDefAttFromDefEnt() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductOtherConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));

		ProductGoal result = this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);

		assertEquals(4, this.spec.getGoalModel().getGoalSet().size());
		assertEquals(1, this.goalOne.getSuccessConditionSet().size());
		assertTrue(this.goalOne.getSuccessConditionSet()
				.contains(DefEntityCondition.getDefEntityCondition(this.entityOne)));
		assertEquals(1, result.getSuccessConditionSet().size());
		assertTrue(result.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeOne)));
		assertTrue(result.getActivationConditionSet()
				.contains(DefPathCondition.getDefPathCondition(this.spec, this.entityOne.getFullPath())));

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void successOne() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));

		ProductGoal result = this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);

		assertEquals(GOAL_TWO, result.getName());
		assertEquals(1, result.getSuccessConditionSet().size());
		assertTrue(result.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeOne)));
		assertEquals(1, result.getActivationConditionSet().size());
		assertTrue(result.getActivationConditionSet()
				.contains(DefPathCondition.getDefPathCondition(this.spec, this.entityOne.getFullPath())));
		assertEquals(0, result.getAttributeInvariantConditionSet().size());

		assertEquals(GOAL_ONE, this.goalOne.getName());
		assertEquals(2, this.goalOne.getSuccessConditionSet().size());
		assertTrue(this.goalOne.getSuccessConditionSet()
				.contains(DefEntityCondition.getDefEntityCondition(this.entityTwo)));
		assertTrue(this.goalOne.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeThree)));
		assertEquals(0, this.goalOne.getActivationConditionSet().size());
		assertEquals(0, this.goalOne.getAttributeInvariantConditionSet().size());

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void successTwo() {
		Set<DefProductCondition> defProductOtherConditions = new HashSet<DefProductCondition>();
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		defProductOtherConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeSix));
		ProductGoal otherGoal = new ProductGoal(this.spec.getGoalModel(), GOAL_OTHERS, defProductOtherConditions);
		otherGoal.initProductGoal();

		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), GOAL_ASSOCIATION,
				this.relation.getMulConditionSet());
		associationGoal.initAssociationGoal();

		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.goalOne = new ProductGoal(this.spec.getGoalModel(), GOAL_ONE, defProductConditions);
		this.goalOne.initProductGoal();

		Set<DefProductCondition> successConditions = new HashSet<>();
		successConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		successConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));

		ProductGoal result = this.spec.getGoalModel().extractProductGoal(this.goalOne, GOAL_TWO, successConditions);

		assertEquals(GOAL_TWO, result.getName());
		assertEquals(3, result.getSuccessConditionSet().size());
		assertTrue(result.getSuccessConditionSet().contains(DefEntityCondition.getDefEntityCondition(this.entityTwo)));
		assertTrue(result.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeOne)));
		assertTrue(result.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeThree)));
		assertEquals(1, result.getActivationConditionSet().size());
		assertEquals(0, result.getAttributeInvariantConditionSet().size());

		assertEquals(GOAL_ONE, this.goalOne.getName());
		assertEquals(1, this.goalOne.getSuccessConditionSet().size());
		assertTrue(this.goalOne.getSuccessConditionSet()
				.contains(DefEntityCondition.getDefEntityCondition(this.entityOne)));
		assertEquals(0, this.goalOne.getActivationConditionSet().size());
		assertEquals(0, this.goalOne.getEntityInvariantConditionSet().size());
		assertEquals(1, this.goalOne.getAttributeInvariantConditionSet().size());

		this.spec.getGoalModel().checkModel();
	}

}