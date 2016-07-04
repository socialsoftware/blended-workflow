package pt.ist.socialsoftware.blendedworkflow.domain.goalworkitem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class PostConditionsHoldMethodTest extends TeardownRollbackTest {
	private static final String ENT_TWO_ROLE = "entTwoRole";
	private static final String ENT_ONE_ROLE = "entOneRole";
	private static final String ENTITY_ONE = "EntityOne";
	private static final String MY_SPEC = "My spec";
	private static final String NAME = "name";

	Specification spec = null;
	Entity entityOne;
	Entity entityTwo;
	DefEntityCondition defEntityCondition;
	RelationBW relationBW;

	Goal goal = null;

	WorkflowInstance workflowInstance = null;
	GoalWorkItem goalWorkItem;
	EntityInstance entityInstance;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", MY_SPEC, "author", "description", "version", "UID");
		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE, false);
		defEntityCondition = DefEntityCondition.getDefEntityCondition(entityOne);
		entityTwo = new Entity(spec.getDataModel(), "EntityTwo", false);
		relationBW = new RelationBW(spec.getDataModel(), "name", entityOne, ENT_ONE_ROLE, Cardinality.ONE, false,
				entityTwo, ENT_TWO_ROLE, "2..4", false);
		MulCondition.createMUlConditions(relationBW);

		goal = new Goal(spec.getGoalModel(), NAME);
		goal.addSuccessCondition(defEntityCondition);
		goal.addEntityInvariantCondition(MulCondition.getMulCondition(relationBW, ENT_TWO_ROLE));

		workflowInstance = new WorkflowInstance(spec, NAME);
		goalWorkItem = new GoalWorkItem(workflowInstance, goal);
		entityInstance = new EntityInstance(workflowInstance, entityOne);
	}

	@Test
	public void associateEntityInstancesIn() {
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(workflowInstance, goal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void associateEntityInstancesTop() {
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(workflowInstance, goal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void associateEntityInstancesBelow() {
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(workflowInstance, entityTwo),
				ENT_TWO_ROLE, relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(workflowInstance, goal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem, defEntityCondition);
		postWorkItemArgument.addProductInstance(entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

}
