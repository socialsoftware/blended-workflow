package pt.ist.socialsoftware.blendedworkflow.core.domain.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CheckCyclesMethodTest extends TeardownRollbackTest {
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
	private static final String ACTIVITY_ONE = "activityOne";
	private static final String ACTIVITY_TWO = "activityTwo";
	private static final String ACTIVITY_THREE = "activityThree";
	private static final String ACTIVITY_FOUR = "activityFour";

	Specification spec;
	ActivityModel taskModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	Attribute attFour;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec", "author", DESCRIPTION, "version", "UID");
		DataModel dataModel = this.spec.getDataModel();
		this.taskModel = this.spec.getActivityModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		this.attFour = new Attribute(dataModel, this.entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false, this.entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, this.entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relTwoThree", this.entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false,
				this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
	}

	@Test
	public void successEmptyPrecondition() throws BWException {
		Activity task = new Activity(this.taskModel, ACTIVITY_ONE, DESCRIPTION);

		task.checkCycles(this.taskModel.getActivitySequences());
	}

	@Test
	public void successDependsOnOtherActivity() {
		Activity taskOne = new Activity(this.taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);

		Activity taskTwo = new Activity(this.taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));

		taskOne.checkCycles(this.taskModel.getActivitySequences());
	}

	@Test
	public void successDependsTransitive() {
		Activity taskOne = new Activity(this.taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		taskOne.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_ONE_NAME + "." + ATT_ONE_NAME));

		Activity taskTwo = new Activity(this.taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));

		Activity taskThree = new Activity(this.taskModel, ACTIVITY_THREE, DESCRIPTION);
		taskThree.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_TWO_NAME));
		taskThree.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_THREE_NAME + "." + ATT_FOUR_NAME));

		Activity taskFour = new Activity(this.taskModel, ACTIVITY_FOUR, DESCRIPTION);
		taskFour.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));

		taskOne.checkCycles(this.taskModel.getActivitySequences());
	}

	@Test
	public void failDependsTransitiveAtributes() {
		Activity taskOne = new Activity(this.taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		taskOne.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_ONE_NAME + "." + ATT_ONE_NAME));

		Activity taskTwo = new Activity(this.taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));

		Activity taskThree = new Activity(this.taskModel, ACTIVITY_THREE, DESCRIPTION);
		taskThree.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME));
		taskThree.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_THREE_NAME + "." + ATT_FOUR_NAME));

		try {
			taskOne.checkCycles(this.taskModel.getActivitySequences());
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failDependsTransitiveWithSeveralPreAndPostConditions() {
		Activity taskOne = new Activity(this.taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		taskOne.getPreConditionSet()
				.add(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));
		taskOne.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_ONE_NAME + "." + ATT_ONE_NAME));

		Activity taskTwo = new Activity(this.taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));

		Activity taskThree = new Activity(this.taskModel, ACTIVITY_THREE, DESCRIPTION);
		taskThree.getPreConditionSet().add(DefPathCondition.getDefPathCondition(this.spec,
				ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME));
		taskThree.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttributeCondition(this.spec, ENT_THREE_NAME + "." + ATT_FOUR_NAME));

		try {
			taskOne.checkCycles(this.taskModel.getActivitySequences());
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

}
