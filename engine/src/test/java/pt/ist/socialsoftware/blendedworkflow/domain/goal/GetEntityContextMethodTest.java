package pt.ist.socialsoftware.blendedworkflow.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetEntityContextMethodTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ENT_THREE_NAME = "EntThree";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String ATT_THREE_NAME = "AttThree";
	private static final String ATT_FOUR_NAME = "AttFour";
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";
	private static final String ROLENAME_ENT_THREE = "entThree";
	private static final String DESCRIPTION = "description";
	private static final String GOAL_ONE = "goalOne";

	Specification spec;
	GoalModel goalModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	Attribute attFour;
	RelationBW oneTwo;
	RelationBW twoThree;
	RelationBW oneThree;
	Goal goal;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", DESCRIPTION, "version", "UID");
		DataModel dataModel = spec.getDataModel();
		goalModel = spec.getGoalModel();

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attTwo = new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		attFour = new Attribute(dataModel, entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		oneTwo = new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false, entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		oneThree = new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		twoThree = new RelationBW(dataModel, "relTwoThree", entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false,
				entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false);

		goal = new Goal(goalModel, GOAL_ONE);
	}

	@Test
	public void emptyActivationAndSuccess() {
		Set<Entity> entityContext = goal.getEntityContext();

		assertTrue(entityContext.isEmpty());
	}

	@Test
	public void emptyActivation() {
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = goal.getEntityContext();

		assertTrue(entityContext.isEmpty());
	}

	@Test
	public void simpleEntityActivation() {
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = goal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(entTwo));
	}

	@Test
	public void simpleAttributeActivation() {
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = goal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(entTwo));
	}

	@Test
	public void attributeSuccessParentCreatesEntity() {
		Goal parentGoal = new Goal(goalModel, "parent");
		parentGoal.addSubGoal(goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneThree, ROLENAME_ENT_THREE));

		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		Set<Entity> entityContext = goal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(entOne));
	}

	@Test
	public void attributeSuccessAttributeActivation() {
		Goal parentGoal = new Goal(goalModel, "parent");
		parentGoal.addSubGoal(goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneThree, ROLENAME_ENT_THREE));

		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		Set<Entity> entityContext = goal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(entOne));
	}

	@Test
	public void entitySuccessEntitySuccessParent() {
		Goal parentGoal = new Goal(goalModel, "parent");
		parentGoal.addSubGoal(goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneThree, ROLENAME_ENT_THREE));

		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entTwo));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_ONE));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(twoThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = goal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(entOne));
	}

	@Test
	public void entitySuccessAttributeActivationEntitySuccessParent() {
		Goal parentGoal = new Goal(goalModel, "parent");
		parentGoal.addSubGoal(goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(oneThree, ROLENAME_ENT_THREE));

		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entTwo));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_ONE));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(twoThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = goal.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(entOne));
		assertTrue(entityContext.contains(entThree));
	}

	@Test
	public void successEntityDoesNotHaveEntityContextForSibling() {
		Goal parentGoal = new Goal(spec.getGoalModel(), "parent");

		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_TWO));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(twoThree, ROLENAME_ENT_THREE));

		Goal goalOne = new Goal(spec.getGoalModel(), "other");
		goalOne.setParentGoal(parentGoal);
		goalOne.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entTwo));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(oneTwo, ROLENAME_ENT_ONE));

		Set<Entity> entityContext = goal.getEntityContext();
		assertTrue(entityContext.isEmpty());

		entityContext = goalOne.getEntityContext();
		assertTrue(entityContext.isEmpty());
	}

}
