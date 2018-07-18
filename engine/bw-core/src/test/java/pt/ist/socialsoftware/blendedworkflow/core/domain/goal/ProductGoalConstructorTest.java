package pt.ist.socialsoftware.blendedworkflow.core.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class ProductGoalConstructorTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String GOAL_ONE = "goalOne";

	Specification spec;
	GoalModel goalModel;
	Entity entOne;
	Attribute attOne;
	Attribute attTwo;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		new Dependence(dataModel, this.attTwo, ENT_ONE_NAME + "." + ATT_ONE_NAME);

		this.spec.getConditionModel().generateConditions();

		this.goalModel = this.spec.getGoalModel();
	}

	@Test(expected = BWException.class)
	public void testEmptyProductGoal() {
		ProductGoal goal = new ProductGoal(this.goalModel, GOAL_ONE, new HashSet<>());
		goal.initProductGoal();
	}

	@Test
	public void testEntityGoal() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(this.entOne.getDefCondition());

		ProductGoal goal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		goal.initProductGoal();

		assertEquals(0, goal.getActivationConditionSet().size());
	}

	@Test
	public void testAttributeGoal() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(this.attOne.getDefAttributeCondition());

		ProductGoal goal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		goal.initProductGoal();

		assertEquals(1, goal.getActivationConditionSet().size());
		assertEquals(this.entOne,
				goal.getActivationConditionSet().stream().map(p -> p.getTargetOfPath()).findFirst().get());
	}

	@Test
	public void testAttributeWithDependenceGoal() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(this.attTwo.getDefAttributeCondition());

		ProductGoal goal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		goal.initProductGoal();

		assertEquals(2, goal.getActivationConditionSet().size());
		assertTrue(goal.getActivationConditionSet().stream().map(p -> p.getTargetOfPath()).collect(Collectors.toSet())
				.contains(this.entOne));
		assertTrue(goal.getActivationConditionSet().stream().map(p -> p.getTargetOfPath()).collect(Collectors.toSet())
				.contains(this.attOne));
	}

	@Test(expected = BWException.class)
	public void testThatProductGoalHasNoMulConditions() {
		Entity entTwo = new Entity(this.spec.getDataModel(), "EntTwo", false);
		RelationBW relation = new RelationBW(this.spec.getDataModel(), "relation", this.entOne, "entOne",
				Cardinality.ONE, entTwo, "entTwo", Cardinality.ONE_MANY);

		this.spec.getConditionModel().generateConditions();

		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(this.entOne.getDefCondition());

		ProductGoal goal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		goal.initProductGoal();

		relation.getMulConditionSet().stream().forEach(m -> goal.addEntityInvariantCondition(m));
	}

}
