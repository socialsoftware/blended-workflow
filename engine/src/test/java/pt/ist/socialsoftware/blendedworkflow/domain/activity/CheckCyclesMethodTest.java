package pt.ist.socialsoftware.blendedworkflow.domain.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
		spec = new Specification("SpecId", "My spec", "author", DESCRIPTION, "version", "UID");
		DataModel dataModel = spec.getDataModel();
		taskModel = spec.getActivityModel();

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attTwo = new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		attFour = new Attribute(dataModel, entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false, entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, entThree,
				ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		new RelationBW(dataModel, "relTwoThree", entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
				ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
	}

	@Test
	public void successEmptyPrecondition() throws BWException {
		Activity task = new Activity(taskModel, ACTIVITY_ONE, DESCRIPTION);

		task.checkCycles(taskModel.getActivitySequences());
	}

	@Test
	public void successDependsOnOtherActivity() {
		Activity taskOne = new Activity(taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME);

		Activity taskTwo = new Activity(taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));

		taskOne.checkCycles(taskModel.getActivitySequences());
	}

	@Test
	public void successDependsTransitive() {
		Activity taskOne = new Activity(taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		taskOne.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_ONE_NAME + "." + ATT_ONE_NAME));

		Activity taskTwo = new Activity(taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));

		Activity taskThree = new Activity(taskModel, ACTIVITY_THREE, DESCRIPTION);
		taskThree.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_TWO_NAME));
		taskThree.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_THREE_NAME + "." + ATT_FOUR_NAME));

		Activity taskFour = new Activity(taskModel, ACTIVITY_FOUR, DESCRIPTION);
		taskFour.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));

		taskOne.checkCycles(taskModel.getActivitySequences());
	}

	@Test
	public void failDependsTransitiveAtributes() {
		Activity taskOne = new Activity(taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		taskOne.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_ONE_NAME + "." + ATT_ONE_NAME));

		Activity taskTwo = new Activity(taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));

		Activity taskThree = new Activity(taskModel, ACTIVITY_THREE, DESCRIPTION);
		taskThree.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME));
		taskThree.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_THREE_NAME + "." + ATT_FOUR_NAME));

		try {
			taskOne.checkCycles(taskModel.getActivitySequences());
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

	@Test
	public void failDependsTransitiveWithSeveralPreAndPostConditions() {
		Activity taskOne = new Activity(taskModel, ACTIVITY_ONE, DESCRIPTION);
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE_NAME + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));
		taskOne.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));
		taskOne.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_ONE_NAME + "." + ATT_ONE_NAME));

		Activity taskTwo = new Activity(taskModel, ACTIVITY_TWO, DESCRIPTION);
		taskTwo.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_TWO_NAME + "." + ATT_THREE_NAME));
		taskTwo.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_ONE_NAME + "." + ATT_TWO_NAME));

		Activity taskThree = new Activity(taskModel, ACTIVITY_THREE, DESCRIPTION);
		taskThree.getPreConditionSet().add(DefPathCondition.getDefPathCondition(spec,
				ENT_THREE_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME));
		taskThree.getPostConditionSet()
				.add(DefAttributeCondition.getDefAttribute(spec, ENT_THREE_NAME + "." + ATT_FOUR_NAME));

		try {
			taskOne.checkCycles(taskModel.getActivitySequences());
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}
	}

}
