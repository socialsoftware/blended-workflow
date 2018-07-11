package pt.ist.socialsoftware.blendedworkflow.core.workflowinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AssociationGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetEnabledGoalSetTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String ROLENAME_ONE = "entOne";
	private static final String ROLENAME_TWO = "entTwo";
	private static final String GOAL_ONE = "goalOne";
	private static final String GOAL_TWO = "goalTwo";
	private static final String NAME = "name";

	Specification spec;
	GoalModel goalModel;
	Entity entOne;
	Entity entTwo;
	Attribute attOne;
	Attribute attTwo;
	RelationBW relation;
	WorkflowInstance workflowInstance;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.goalModel = this.spec.getGoalModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);

		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		this.relation = new RelationBW(dataModel, "relation", this.entOne, ROLENAME_ONE, "1", this.entTwo, ROLENAME_TWO,
				"1..*");

		new Dependence(dataModel, this.attTwo, ENT_ONE_NAME + "." + ATT_ONE_NAME);

		this.spec.getConditionModel().generateConditions();

		this.workflowInstance = new WorkflowInstance(this.spec, NAME);

		new EntityInstance(this.workflowInstance, this.entOne);
		new EntityInstance(this.workflowInstance, this.entTwo);
	}

	@Test
	public void testSingleProductGoal() {
		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(this.entOne.getDefEntityCondition());
		defProductConditions.add(this.attOne.getDefAttributeCondition());
		defProductConditions.add(this.attTwo.getDefAttributeCondition());

		ProductGoal goal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		goal.initProductGoal();

		assertEquals(1, this.workflowInstance.getEnabledGoalSet().size());
		assertTrue(this.workflowInstance.getEnabledGoalSet().contains(goal));
	}

	@Test
	public void testSingleAssociationGoal() {
		AssociationGoal goal = new AssociationGoal(this.goalModel, GOAL_ONE, this.relation.getMulConditionSet());
		goal.initAssociationGoal();

		assertEquals(1, this.workflowInstance.getEnabledGoalSet().size());
		assertTrue(this.workflowInstance.getEnabledGoalSet().contains(goal));
	}

	@Test
	public void testTwoProductGoals() {
		Set<DefProductCondition> defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(this.entOne.getDefEntityCondition());
		defProductConditions.add(this.attOne.getDefAttributeCondition());
		ProductGoal goalOne = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);

		defProductConditions = new HashSet<DefProductCondition>();
		defProductConditions.add(this.attTwo.getDefAttributeCondition());

		ProductGoal goalTwo = new ProductGoal(this.goalModel, GOAL_TWO, defProductConditions);

		Set<Goal> enabledGoals = this.workflowInstance.getEnabledGoalSet();
		assertEquals(2, enabledGoals.size());
		assertTrue(enabledGoals.contains(goalOne));
		assertTrue(enabledGoals.contains(goalTwo));
	}

}
