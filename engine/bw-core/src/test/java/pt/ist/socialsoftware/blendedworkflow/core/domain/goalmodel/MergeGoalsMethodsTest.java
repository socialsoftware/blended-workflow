package pt.ist.socialsoftware.blendedworkflow.core.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class MergeGoalsMethodsTest extends TeardownRollbackTest {
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_THREE_NAME = "EntityThree";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String ATTRIBUTE_FOUR_NAME = "att4";
	private static final String RULE_CONDITION = "rule";
	private static final String ROLENAME_ONE = "theOne";
	private static final String ROLENAME_TWO = "theTwo";
	private static final String ROLENAME_THREE = "theThree";
	private static final String PRODUCT_GOAL_ONE = "goalOne";
	private static final String PRODUCT_GOAL_TWO = "goalTwo";
	private static final String PRODUCT_GOAL_THREE = "goalThree";
	private static final String PRODUCT_GOAL_OTHER = "goalOtherProduct";
	private static final String ASSOCIATION_GOAL_ONE = "AssociationGoalOne";
	private static final String ASSOCIATION_GOAL_TWO = "AssociationGoalTwo";
	private static final String ASSOCIATION_GOAL_OTHER = "goalOtherAssociation";
	private static final String DEPENDENCE_PATH_ONE = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_ONE_NAME;
	private static final String DEPENDENCE_PATH_TWO = ENTITY_ONE_NAME + "." + ROLENAME_TWO + "." + ATTRIBUTE_THREE_NAME;

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	Attribute attributeFour;
	RelationBW relationOne;
	RelationBW relationTwo;
	ProductGoal productGoalOne;
	ProductGoal productGoalTwo;
	ProductGoal productGoalThree;
	ProductGoal productGoalOther;
	AssociationGoal associationGoalOther;
	AssociationGoal associationGoalOne;
	AssociationGoal associationGoalTwo;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true);
		this.attributeTwo = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.attributeThree = new Attribute(this.spec.getDataModel(), this.entityTwo, ATTRIBUTE_THREE_NAME,
				AttributeType.BOOLEAN, true);
		this.attributeFour = new Attribute(this.spec.getDataModel(), this.entityTwo, ATTRIBUTE_FOUR_NAME,
				AttributeType.BOOLEAN, true);

		this.entityThree = new Entity(this.spec.getDataModel(), ENTITY_THREE_NAME, false);

		this.relationOne = new RelationBW(this.spec.getDataModel(), "nameOne", this.entityOne, ROLENAME_ONE,
				Cardinality.ONE, false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);
		this.relationTwo = new RelationBW(this.spec.getDataModel(), "nameTwo", this.entityOne, ROLENAME_ONE,
				Cardinality.ONE, false, this.entityThree, ROLENAME_THREE, Cardinality.ZERO_MANY, false);

		new Dependence(this.spec.getDataModel(), this.attributeFour, DEPENDENCE_PATH_ONE);
		new Dependence(this.spec.getDataModel(), this.attributeTwo, DEPENDENCE_PATH_TWO);

		new Rule(this.entityOne, RULE_CONDITION,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.spec.getDataModel().checkPaths();

		this.spec.getConditionModel().generateConditions();
	}

	@Test
	public void mergeProductionGoals() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.productGoalOther = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_OTHER, defProductConditions);
		this.productGoalOther.initProductGoal();

		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.addAll(this.relationOne.getMulConditionSet());
		mulConditions.addAll(this.relationTwo.getMulConditionSet());
		this.associationGoalOther = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_OTHER,
				mulConditions);

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.productGoalOne = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_ONE, defProductConditions);
		this.productGoalOne.initProductGoal();

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.productGoalTwo = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_TWO, defProductConditions);
		this.productGoalTwo.initProductGoal();

		this.spec.getGoalModel().checkModel();

		Goal merged = this.spec.getGoalModel().mergeGoals(PRODUCT_GOAL_THREE, this.productGoalOne, this.productGoalTwo);

		assertFalse(this.spec.getGoalModel().existsGoal(PRODUCT_GOAL_ONE));
		assertFalse(this.spec.getGoalModel().existsGoal(PRODUCT_GOAL_TWO));
		assertEquals(PRODUCT_GOAL_THREE, merged.getName());
		assertEquals(2, merged.getSuccessConditionSet().size());
		assertTrue(merged.getSuccessConditionSet()
				.contains(DefAttributeCondition.getDefAttributeCondition(this.attributeOne)));
		assertTrue(merged.getSuccessConditionSet().contains(DefEntityCondition.getDefEntityCondition(this.entityOne)));
		assertEquals(0, merged.getActivationConditionSet().size());
		assertEquals(0, merged.getEntityInvariantConditionSet().size());
		assertEquals(1, merged.getAttributeInvariantConditionSet().size());

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void mergeAssociationGoals() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.productGoalOther = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_OTHER, defProductConditions);
		this.productGoalOther.initProductGoal();

		this.associationGoalOne = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_ONE,
				this.relationOne.getMulConditionSet());
		this.associationGoalOne.initAssociationGoal();

		this.associationGoalTwo = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_TWO,
				this.relationTwo.getMulConditionSet());
		this.associationGoalTwo.initAssociationGoal();

		this.spec.getGoalModel().checkModel();

		Goal result = this.spec.getGoalModel().mergeGoals(ASSOCIATION_GOAL_ONE + ASSOCIATION_GOAL_TWO,
				this.associationGoalOne, this.associationGoalTwo);

		assertFalse(this.spec.getGoalModel().existsGoal(ASSOCIATION_GOAL_ONE));
		assertFalse(this.spec.getGoalModel().existsGoal(ASSOCIATION_GOAL_TWO));
		assertEquals(ASSOCIATION_GOAL_ONE + ASSOCIATION_GOAL_TWO, result.getName());

		assertEquals(4, result.getEntityInvariantConditionSet().size());
		assertTrue(result.getEntityInvariantConditionSet().containsAll(this.relationOne.getMulConditionSet()));
		assertTrue(result.getEntityInvariantConditionSet().containsAll(this.relationTwo.getMulConditionSet()));
		result.getActivationConditionSet().stream().forEach(def -> System.out.println(def.getPath().getValue()));
		assertEquals(3, result.getActivationConditionSet().size());
		Set<String> paths = result.getActivationConditionSet().stream().map(def -> def.getPath().getValue())
				.collect(Collectors.toSet());
		assertTrue(paths.contains(ENTITY_ONE_NAME));
		assertTrue(paths.contains(ENTITY_TWO_NAME));
		assertTrue(paths.contains(ENTITY_THREE_NAME));

		assertEquals(0, result.getSuccessConditionSet().size());
		assertEquals(0, result.getAttributeInvariantConditionSet().size());

		this.spec.getGoalModel().checkModel();
	}

	@Test
	public void conflictDueToCircularityImplicitDependenceBetweenAttributes() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.productGoalOther = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_OTHER, defProductConditions);
		this.productGoalOther.initProductGoal();

		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.addAll(this.relationOne.getMulConditionSet());
		mulConditions.addAll(this.relationTwo.getMulConditionSet());
		this.associationGoalOther = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_OTHER,
				mulConditions);

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.productGoalOne = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_ONE, defProductConditions);
		this.productGoalOne.initProductGoal();

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.productGoalTwo = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_TWO, defProductConditions);
		this.productGoalTwo.initProductGoal();

		this.spec.getGoalModel().checkModel();

		try {
			this.spec.getGoalModel().mergeGoals("Name", this.productGoalOne, this.productGoalTwo);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}

	}

	@Test
	public void conflictDueToCircularityImplicitAndExplictDependenceBetweenAttributes() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.productGoalOther = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_OTHER, defProductConditions);
		this.productGoalOther.initProductGoal();

		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.addAll(this.relationOne.getMulConditionSet());
		mulConditions.addAll(this.relationTwo.getMulConditionSet());
		this.associationGoalOther = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_OTHER,
				mulConditions);

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.productGoalOne = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_ONE, defProductConditions);
		this.productGoalOne.initProductGoal();

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.productGoalTwo = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_TWO, defProductConditions);
		this.productGoalTwo.initProductGoal();

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		this.productGoalThree = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_THREE, defProductConditions);
		this.productGoalThree.initProductGoal();

		this.spec.getGoalModel().checkModel();

		try {
			this.spec.getGoalModel().mergeGoals("Name", this.productGoalOne, this.productGoalThree);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}

	}

	@Test
	public void conflictDueToCircularityExplictDependenceBetweenAttributes() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.productGoalOther = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_OTHER, defProductConditions);
		this.productGoalOther.initProductGoal();

		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.addAll(this.relationOne.getMulConditionSet());
		mulConditions.addAll(this.relationTwo.getMulConditionSet());
		this.associationGoalOther = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_OTHER,
				mulConditions);

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		this.productGoalOne = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_ONE, defProductConditions);
		this.productGoalOne.initProductGoal();

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.productGoalTwo = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_TWO, defProductConditions);
		this.productGoalTwo.initProductGoal();

		defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		this.productGoalThree = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_THREE, defProductConditions);
		this.productGoalThree.initProductGoal();

		this.spec.getGoalModel().checkModel();

		try {
			this.spec.getGoalModel().mergeGoals("Name", this.productGoalTwo, this.productGoalThree);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.INCONSISTENT_GOALMODEL, bwe.getError());
		}

	}

	@Test(expected = BWException.class)
	public void mergeProductAssociationGoalsFail() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.productGoalOther = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_OTHER, defProductConditions);
		this.productGoalOther.initProductGoal();

		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.addAll(this.relationOne.getMulConditionSet());
		mulConditions.addAll(this.relationTwo.getMulConditionSet());
		this.associationGoalOther = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_OTHER,
				mulConditions);

		this.spec.getGoalModel().mergeGoals("Name", this.productGoalOther, this.associationGoalOther);
	}

}
