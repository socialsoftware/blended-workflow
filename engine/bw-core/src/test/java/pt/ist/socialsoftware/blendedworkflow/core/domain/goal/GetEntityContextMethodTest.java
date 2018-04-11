package pt.ist.socialsoftware.blendedworkflow.core.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.goalModel = this.spec.getGoalModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true);

		this.attFour = new Attribute(dataModel, this.entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true);

		this.oneTwo = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false,
				this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		this.oneThree = new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false,
				this.entThree, ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		this.twoThree = new RelationBW(dataModel, "relTwoThree", this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY,
				false, this.entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false);

		this.goal = new Goal(this.goalModel, GOAL_ONE);
	}

	@Test
	public void emptyActivationAndSuccess() {
		Set<Entity> entityContext = this.goal.getEntityContext();

		assertTrue(entityContext.isEmpty());
	}

	@Test
	public void emptyActivation() {
		this.goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = this.goal.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entTwo));
		assertTrue(entityContext.contains(this.entThree));
	}

	@Test
	public void simpleEntityActivation() {
		this.goal.addActivationCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
		this.goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = this.goal.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entTwo));
		assertTrue(entityContext.contains(this.entThree));
	}

	@Test
	public void simpleAttributeActivation() {
		this.goal.addActivationCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		this.goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = this.goal.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entTwo));
		assertTrue(entityContext.contains(this.entThree));
	}

	@Test
	public void attributeSuccessParentCreatesEntity() {
		Goal parentGoal = new Goal(this.goalModel, "parent");
		parentGoal.addSubGoal(this.goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		this.goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		Set<Entity> entityContext = this.goal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
	}

	@Test
	public void attributeSuccessAttributeActivation() {
		Goal parentGoal = new Goal(this.goalModel, "parent");
		parentGoal.addSubGoal(this.goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		this.goal.addActivationCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		this.goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		Set<Entity> entityContext = this.goal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
	}

	@Test
	public void entitySuccessEntitySuccessParent() {
		Goal parentGoal = new Goal(this.goalModel, "parent");
		parentGoal.addSubGoal(this.goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		this.goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_ONE));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.twoThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = this.goal.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
		assertTrue(entityContext.contains(this.entThree));
	}

	@Test
	public void entitySuccessAttributeActivationEntitySuccessParent() {
		Goal parentGoal = new Goal(this.goalModel, "parent");
		parentGoal.addSubGoal(this.goal);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		this.goal.addActivationCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		this.goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_ONE));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.twoThree, ROLENAME_ENT_THREE));

		Set<Entity> entityContext = this.goal.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
		assertTrue(entityContext.contains(this.entThree));
	}

	@Test
	public void successEntityDoesNotHaveEntityContextForSibling() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), "parent");

		this.goal.setParentGoal(parentGoal);
		this.goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_TWO));
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneThree, ROLENAME_ENT_THREE));

		Goal goalOne = new Goal(this.spec.getGoalModel(), "other");
		goalOne.setParentGoal(parentGoal);
		goalOne.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(this.oneTwo, ROLENAME_ENT_ONE));

		Set<Entity> entityContext = this.goal.getEntityContext();
		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entTwo));
		assertTrue(entityContext.contains(this.entThree));

		entityContext = goalOne.getEntityContext();
		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
	}

}
