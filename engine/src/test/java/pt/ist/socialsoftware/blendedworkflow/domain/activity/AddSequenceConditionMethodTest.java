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
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class AddSequenceConditionMethodTest extends TeardownRollbackTest {
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
	private static final String DEPENDENCE_PATH_ONE = ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_TWO_NAME;
	private static final String DESCRIPTION = "description";
	private static final String TASK_ONE = "activityOne";
	private static final String TASK_TWO = "activityTwo";
	private static final String TASK_THREE = "activityThree";

	Specification spec;
	ActivityModel taskModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	Attribute attFour;
	Activity taskOne;
	Activity taskTwo;
	Activity taskThree;

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

		RelationBW relationOne = new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY,
				false, entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		// new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE,
		// Cardinality.ONE, false, entThree,
		// ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);
		//
		// new RelationBW(dataModel, "relTwoThree", entThree,
		// ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
		// ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
		//
		spec.getConditionModel().generateConditions();

		taskOne = new Activity(spec.getActivityModel(), TASK_ONE, "Description");
		taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(entOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attTwo));

		taskTwo = new Activity(spec.getActivityModel(), TASK_TWO, "Description");
		taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(entTwo));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(entThree));
		taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relationOne, relationOne.getRoleNameOne()));
		taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relationOne, relationOne.getRoleNameTwo()));

		taskThree = new Activity(spec.getActivityModel(), TASK_THREE, "Description");
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_TWO_NAME));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_THREE_NAME));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, DEPENDENCE_PATH_ONE));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attThree));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attFour));

		taskModel.checkModel();
	}

	@Test
	public void success() {
		taskThree.addSequenceCondition(
				DefPathCondition.getDefPathCondition(spec, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME));

	}

	@Test
	public void failPathSource() {
		try {
			taskOne.addSequenceCondition(
					DefPathCondition.getDefPathCondition(spec, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.SEQUENCE_CONDITION_INVALID, bwe.getError());
		}
	}

	@Test
	public void failCyclic() {
		try {
			taskOne.addSequenceCondition(
					DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}

	}

}
