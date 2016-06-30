package pt.ist.socialsoftware.blendedworkflow.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetEntityInstanceContextMethod extends TeardownRollbackTest {
	private static final String PARENT_GOAL = "parent";
	private static final String GOAL_NAME_ONE = "name one";
	private static final String GOAL_NAME_TWO = "name two";
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
	RelationBW relationOne;
	RelationBW relationTwo;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		DataModel dataModel = spec.getDataModel();
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attTwo = new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		relationOne = new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, entTwo,
				ROLENAME_ENT_TWO, "0..2", false);

		relationTwo = new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false,
				entThree, ROLENAME_ENT_THREE, "2..4", false);
	}

	@Test
	public void topGoalActivationEmptySuccessEmpty() {
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);

		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void topGoalActivationEmptySuccessEntity() {
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));

		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void activationEmptySuccessAttribute() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		assertTrue(goal.getEntityContext().contains(entOne));
		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);

		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void activationEmptySuccessAttributeFail() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		assertTrue(goal.getEntityContext().contains(entOne));
		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);

		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void activationAttributeSuccessAttributeFail() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);
		new AttributeInstance(entityInstance, attOne, NUMBER_VALUE);

		assertTrue(goal.getEntityContext().contains(entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void activationAttributeSuccessAttributeSuccess() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);
		new AttributeInstance(entityInstance, attTwo, NUMBER_VALUE);

		assertTrue(goal.getEntityContext().contains(entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void activationEntitySuccessAttributeFail() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		new EntityInstance(workflowInstance, entOne);

		assertTrue(goal.getEntityContext().contains(entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void activationEntitySuccessAttributeSuccess() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addActivationCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
		goal.addSuccessCondition(DefAttributeCondition.getDefAttributeCondition(attOne));

		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, entityInstanceTwo, relationOne);

		assertTrue(goal.getEntityContext().contains(entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceOne));
	}

	@Test
	public void successEntity() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entTwo));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_ONE));

		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, entityInstanceTwo, relationOne);

		assertTrue(goal.getEntityContext().contains(entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceOne));
	}

	@Test
	public void successEntityFull() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		parentGoal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		parentGoal.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goal = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goal.setParentGoal(parentGoal);
		goal.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entTwo));
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_ONE));

		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		new RelationInstance(entityInstanceOne, new EntityInstance(workflowInstance, entTwo), relationOne);
		new RelationInstance(entityInstanceOne, new EntityInstance(workflowInstance, entTwo), relationOne);

		assertTrue(goal.getEntityContext().contains(entOne));
		Set<EntityInstance> instanceContext = goal.getInstanceContext(workflowInstance, entOne);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void notEnoughInstancesInTheContext() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		Goal goalOne = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goalOne.setParentGoal(parentGoal);
		goalOne.addActivationCondition(
				DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ROLENAME_ENT_THREE));
		goalOne.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goalThree = new Goal(spec.getGoalModel(), GOAL_NAME_THREE);
		goalThree.setParentGoal(parentGoal);
		goalThree.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entThree));
		goalThree.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_ONE));

		new EntityInstance(workflowInstance, entThree);

		assertTrue(goalOne.getEntityContext().contains(entThree));
		Set<EntityInstance> instanceContext = goalOne.getInstanceContext(workflowInstance, entThree);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void enoughInstancesInTheContext() {
		Goal parentGoal = new Goal(spec.getGoalModel(), PARENT_GOAL);
		Goal goalOne = new Goal(spec.getGoalModel(), GOAL_NAME_ONE);
		goalOne.setParentGoal(parentGoal);
		goalOne.addActivationCondition(
				DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ROLENAME_ENT_THREE));
		goalOne.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entOne));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));
		goalOne.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_THREE));
		Goal goalThree = new Goal(spec.getGoalModel(), GOAL_NAME_THREE);
		goalThree.setParentGoal(parentGoal);
		goalThree.addSuccessCondition(DefEntityCondition.getDefEntityCondition(entThree));
		goalThree.addEntityInvariantCondition(MulCondition.getMulCondition(relationTwo, ROLENAME_ENT_ONE));

		new EntityInstance(workflowInstance, entThree);
		new EntityInstance(workflowInstance, entThree);

		assertTrue(goalOne.getEntityContext().contains(entThree));
		Set<EntityInstance> instanceContext = goalOne.getInstanceContext(workflowInstance, entThree);

		assertEquals(2, instanceContext.size());
	}

}
