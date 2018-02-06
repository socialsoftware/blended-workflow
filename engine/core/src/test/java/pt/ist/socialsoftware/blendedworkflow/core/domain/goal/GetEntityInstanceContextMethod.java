package pt.ist.socialsoftware.blendedworkflow.core.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetEntityInstanceContextMethod extends TeardownRollbackTest {
	private static final String PARENT_GOAL = "parent";
	private static final String GOAL_NAME_ONE = "name one";
	private static final String GOAL_NAME_THREE = "name three";
	private static final String NUMBER_VALUE = "1234";
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ENT_THREE_NAME = "EntThree";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String ATT_THREE_NAME = "AttThree";
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";
	private static final String ROLENAME_ENT_THREE = "entThree";

	Specification spec = null;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	RelationBW relationOneTwo;
	RelationBW relationOneThree;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.workflowInstance = new WorkflowInstance(this.spec, "WorkflowInstanceName");

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		this.relationOneTwo = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE,
				false, this.entTwo, ROLENAME_ENT_TWO, "0..2", false);

		this.relationOneThree = new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE,
				false, this.entThree, ROLENAME_ENT_THREE, "2..4", false);
	}

	@Test
	public void topGoalActivationEmptySuccessEmpty() {
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);

		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void topGoalActivationEmptySuccessEntity() {
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));

		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void activationEmptySuccessAttribute() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		assertTrue(goal.getEntityContext().contains(this.entOne));
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne);

		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void activationEmptySuccessAttributeFail() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		assertTrue(goal.getEntityContext().contains(this.entOne));
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne);

		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void activationAttributeSuccessAttributeFail() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne);
		new AttributeInstance(entityInstance, this.attOne, NUMBER_VALUE);

		assertTrue(goal.getEntityContext().contains(this.entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void activationAttributeSuccessAttributeSuccess() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne);
		new AttributeInstance(entityInstance, this.attTwo, NUMBER_VALUE);

		assertTrue(goal.getEntityContext().contains(this.entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void activationEntitySuccessAttributeFail() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		new EntityInstance(this.workflowInstance, this.entOne);

		assertTrue(goal.getEntityContext().contains(this.entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void activationEntitySuccessAttributeSuccess() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOneTwo);

		assertTrue(goal.getEntityContext().contains(this.entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceOne));
	}

	@Test
	public void successEntity() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_ONE));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOneTwo);

		assertTrue(goal.getEntityContext().contains(this.entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceOne));
	}

	@Test
	public void successEntityFull() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goal = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_ONE));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE,
				new EntityInstance(this.workflowInstance, this.entTwo), ROLENAME_ENT_TWO, this.relationOneTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE,
				new EntityInstance(this.workflowInstance, this.entTwo), ROLENAME_ENT_TWO, this.relationOneTwo);

		assertTrue(goal.getEntityContext().contains(this.entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void notEnoughInstancesInTheContext() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		Goal goalOne = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goalOne.setParentGoal(parentGoal);
		goalOne.addActivationCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ROLENAME_ENT_THREE));
		goalOne.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goalThree = new Goal(this.spec.getGoalModel(), GOAL_NAME_THREE);
		goalThree.setParentGoal(parentGoal);
		goalThree.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entThree));
		goalThree.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_ONE));

		new EntityInstance(this.workflowInstance, this.entThree);

		assertTrue(goalOne.getEntityContext().contains(this.entThree));
		Set<EntityInstance> instanceContext = goalOne.getInstanceContext(this.workflowInstance, this.entThree);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void enoughInstancesInTheContext() {
		Goal parentGoal = new Goal(this.spec.getGoalModel(), PARENT_GOAL);
		Goal goalOne = new Goal(this.spec.getGoalModel(), GOAL_NAME_ONE);
		goalOne.setParentGoal(parentGoal);
		goalOne.addActivationCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ROLENAME_ENT_THREE));
		goalOne.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneTwo, ROLENAME_ENT_TWO));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_THREE));
		Goal goalThree = new Goal(this.spec.getGoalModel(), GOAL_NAME_THREE);
		goalThree.setParentGoal(parentGoal);
		goalThree.addSuccessCondition(DefEntityCondition.getDefEntityCondition(this.entThree));
		goalThree.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationOneThree, ROLENAME_ENT_ONE));

		new EntityInstance(this.workflowInstance, this.entThree);
		new EntityInstance(this.workflowInstance, this.entThree);

		assertTrue(goalOne.getEntityContext().contains(this.entThree));
		Set<EntityInstance> instanceContext = goalOne.getInstanceContext(this.workflowInstance, this.entThree);

		assertEquals(2, instanceContext.size());
	}

}
