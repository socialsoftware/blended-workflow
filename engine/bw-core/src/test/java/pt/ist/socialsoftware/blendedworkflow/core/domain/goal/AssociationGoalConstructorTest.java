package pt.ist.socialsoftware.blendedworkflow.core.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AssociationGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class AssociationGoalConstructorTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ROLENAME_ONE = "entOne";
	private static final String ROLENAME_TWO = "entTwo";
	private static final String GOAL_ONE = "goalOne";

	Specification spec;
	GoalModel goalModel;
	Entity entOne;
	Entity entTwo;
	RelationBW relation;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.goalModel = this.spec.getGoalModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);

		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);

		this.relation = new RelationBW(dataModel, "relation", this.entOne, ROLENAME_ONE, Cardinality.ONE, this.entTwo,
				ROLENAME_TWO, Cardinality.ONE_MANY);
	}

	@Test(expected = BWException.class)
	public void testEmptyAssociationGoal() {
		AssociationGoal goal = new AssociationGoal(this.goalModel, GOAL_ONE, new HashSet<>());
		goal.initAssociationGoal();
	}

	@Test
	public void testAssociationGoal() {
		AssociationGoal goal = new AssociationGoal(this.goalModel, GOAL_ONE, this.relation.getMulConditionSet());
		goal.initAssociationGoal();

		assertEquals(2, goal.getActivationConditionSet().size());
		assertTrue(goal.getActivationConditionSet().stream().map(p -> p.getTargetOfPath()).collect(Collectors.toSet())
				.contains(this.entOne));
		assertTrue(goal.getActivationConditionSet().stream().map(p -> p.getTargetOfPath()).collect(Collectors.toSet())
				.contains(this.entTwo));
	}

	@Test(expected = BWException.class)
	public void testAssociationGoalErrorNoPairOfMulConditions() {
		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.add(this.relation.getMulConditionSet().stream().findFirst().get());

		AssociationGoal goal = new AssociationGoal(this.goalModel, GOAL_ONE, mulConditions);
		goal.initAssociationGoal();
	}

	@Test(expected = BWException.class)
	public void testThatAssociationGoalHasNoSuccessConditions() {
		AssociationGoal goal = new AssociationGoal(this.goalModel, GOAL_ONE, this.relation.getMulConditionSet());
		goal.initAssociationGoal();

		goal.addSuccessCondition(this.entOne.getDefCondition());
	}

}
