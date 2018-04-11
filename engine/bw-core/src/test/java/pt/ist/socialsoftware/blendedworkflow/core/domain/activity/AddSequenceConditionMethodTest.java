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
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.taskModel = this.spec.getActivityModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true);

		this.attFour = new Attribute(dataModel, this.entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true);

		RelationBW relationOne = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE,
				Cardinality.ONE_MANY, false, this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		// new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE,
		// Cardinality.ONE, false, entThree,
		// ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);
		//
		// new RelationBW(dataModel, "relTwoThree", entThree,
		// ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
		// ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
		//
		this.spec.getConditionModel().generateConditions();

		this.taskOne = new Activity(this.spec.getActivityModel(), TASK_ONE, "Description");
		this.taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attTwo));

		this.taskTwo = new Activity(this.spec.getActivityModel(), TASK_TWO, "Description");
		this.taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entThree));
		this.taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relationOne, relationOne.getRolenameOne()));
		this.taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relationOne, relationOne.getRolenameTwo()));

		this.taskThree = new Activity(this.spec.getActivityModel(), TASK_THREE, "Description");
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_TWO_NAME));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_THREE_NAME));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH_ONE));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attThree));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attFour));

		this.taskModel.checkModel();
	}

	@Test
	public void success() {
		this.taskThree.addSequenceCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME));

	}

	@Test
	public void failPathSource() {
		try {
			this.taskOne.addSequenceCondition(
					DefPathCondition.getDefPathCondition(this.spec, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.SEQUENCE_CONDITION_INVALID, bwe.getError());
		}
	}

	@Test
	public void failCyclic() {
		try {
			this.taskOne.addSequenceCondition(
					DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME + "." + ROLENAME_ENT_TWO));
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DEPENDENCE_CIRCULARITY, bwe.getError());
		}

	}

}
