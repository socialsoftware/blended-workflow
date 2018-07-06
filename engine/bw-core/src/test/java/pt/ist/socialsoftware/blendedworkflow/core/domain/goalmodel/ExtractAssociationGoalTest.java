package pt.ist.socialsoftware.blendedworkflow.core.domain.goalmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AssociationGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class ExtractAssociationGoalTest extends TeardownRollbackTest {
	private static final String ENTITY_TWO_NAME = "Entity two name";
	private static final String ENTITY_ONE_NAME = "Entity one name";
	private static final String ENTITY_THREE_NAME = "Entity three name";
	private static final String ROLENAME_ONE = "theOne";
	private static final String ROLENAME_TWO = "theTwo";
	private static final String ROLENAME_THREE = "theThree";
	private static final String GOAL_TWO = "goalTwo";
	private static final String GOAL_ONE = "goalOne";

	Specification spec;
	GoalModel goalModel;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	RelationBW relationOne;
	RelationBW relationTwo;
	AssociationGoal goalOne;
	AssociationGoal goalTwo;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		this.goalModel = this.spec.getGoalModel();

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.entityThree = new Entity(this.spec.getDataModel(), ENTITY_THREE_NAME, false);

		this.relationOne = new RelationBW(this.spec.getDataModel(), "nameOne", this.entityOne, ROLENAME_ONE,
				Cardinality.ONE, false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		this.relationTwo = new RelationBW(this.spec.getDataModel(), "nameTwo", this.entityTwo, ROLENAME_TWO,
				Cardinality.ONE, false, this.entityThree, ROLENAME_THREE, Cardinality.ZERO_MANY, false);

		this.spec.getDataModel().checkPaths();

		this.spec.getConditionModel().generateConditions();
	}

	@Test
	public void cannotExtractAllConditions() {
		this.goalOne = new AssociationGoal(this.spec.getGoalModel(), GOAL_ONE, this.relationOne.getMulConditionSet());

		Set<MulCondition> successConditions = new HashSet<>();
		this.relationOne.getMulConditionSet().forEach(m -> successConditions.add(m));

		try {
			this.goalModel.extractAssociationGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void conditionsToExtractCannotBeEmpty() {
		this.goalOne = new AssociationGoal(this.spec.getGoalModel(), GOAL_ONE, this.relationOne.getMulConditionSet());

		Set<MulCondition> successConditions = new HashSet<>();

		try {
			this.goalModel.extractAssociationGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void cannotExtractASingleMulCondition() {
		this.goalOne = new AssociationGoal(this.spec.getGoalModel(), GOAL_ONE, this.relationOne.getMulConditionSet());

		Set<MulCondition> successConditions = new HashSet<>();
		successConditions.add(this.relationOne.getMulConditionSet().stream().findFirst().get());

		try {
			this.goalModel.extractAssociationGoal(this.goalOne, GOAL_TWO, successConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_EXTRACT_GOAL, bwe.getError());
		}
	}

	@Test
	public void success() {
		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.addAll(this.relationOne.getMulConditionSet());
		mulConditions.addAll(this.relationTwo.getMulConditionSet());
		this.goalOne = new AssociationGoal(this.spec.getGoalModel(), GOAL_ONE, mulConditions);

		Set<MulCondition> successConditions = new HashSet<>();
		successConditions.addAll(this.relationOne.getMulConditionSet());

		AssociationGoal result = this.goalModel.extractAssociationGoal(this.goalOne, GOAL_TWO, successConditions);

		assertEquals(2, result.getEntityInvariantConditionSet().size());
		assertTrue(result.getEntityInvariantConditionSet().containsAll(this.relationOne.getMulConditionSet()));
		assertEquals(2, result.getActivationConditionSet().size());

		assertEquals(2, this.goalOne.getEntityInvariantConditionSet().size());
		assertTrue(this.goalOne.getEntityInvariantConditionSet().containsAll(this.relationTwo.getMulConditionSet()));
		assertEquals(2, this.goalOne.getActivationConditionSet().size());
	}

}
