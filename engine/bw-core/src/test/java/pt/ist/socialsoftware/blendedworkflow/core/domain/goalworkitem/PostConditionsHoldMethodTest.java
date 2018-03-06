package pt.ist.socialsoftware.blendedworkflow.core.domain.goalworkitem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", MY_SPEC);
		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE, false);
		this.defEntityCondition = DefEntityCondition.getDefEntityCondition(this.entityOne);
		this.entityTwo = new Entity(this.spec.getDataModel(), "EntityTwo", false);
		this.relationBW = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ENT_ONE_ROLE,
				Cardinality.ONE, false, this.entityTwo, ENT_TWO_ROLE, "2..4", false);
		MulCondition.createMUlConditions(this.relationBW);

		this.goal = new Goal(this.spec.getGoalModel(), NAME);
		this.goal.addSuccessCondition(this.defEntityCondition);
		this.goal.addEntityInvariantCondition(MulCondition.getMulCondition(this.relationBW, ENT_TWO_ROLE));

		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
		this.goalWorkItem = new GoalWorkItem(this.workflowInstance, this.goal);
		this.entityInstance = new EntityInstance(this.workflowInstance, this.entityOne);
	}

	@Test
	public void associateEntityInstancesIn() {
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, this.goal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem, this.defEntityCondition);
		postWorkItemArgument.addProductInstance(this.entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void associateEntityInstancesTop() {
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, this.goal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem, this.defEntityCondition);
		postWorkItemArgument.addProductInstance(this.entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void associateEntityInstancesBelow() {
		new RelationInstance(this.entityInstance, ENT_ONE_ROLE,
				new EntityInstance(this.workflowInstance, this.entityTwo), ENT_TWO_ROLE, this.relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, this.goal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem, this.defEntityCondition);
		postWorkItemArgument.addProductInstance(this.entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

}
