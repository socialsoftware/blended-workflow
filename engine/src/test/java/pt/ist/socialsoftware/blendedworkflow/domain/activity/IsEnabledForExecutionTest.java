package pt.ist.socialsoftware.blendedworkflow.domain.activity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class IsEnabledForExecutionTest extends TeardownRollbackTest {
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

	Specification spec = null;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	Attribute attFour;
	RelationBW relationOne;
	RelationBW relationTwo;
	RelationBW relationThree;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		DataModel dataModel = spec.getDataModel();
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attTwo = new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		attFour = new Attribute(dataModel, entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		relationOne = new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false, entTwo,
				ROLENAME_ENT_TWO, "0..2", false);

		relationTwo = new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false,
				entThree, ROLENAME_ENT_THREE, "2..4", false);

		relationThree = new RelationBW(dataModel, "relTwoThree", entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY,
				false, entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
	}

	@Test
	public void emptyEntityContext() {
		Activity activity = new Activity(spec.getActivityModel(), "name", "description");
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(entOne));

		boolean result = activity.isEnabledForExecution(workflowInstance);

		assertTrue(result);
	}

	@Test
	public void emptyInstanceContext() {
		Activity activity = new Activity(spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE_NAME));
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(entTwo));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_ONE));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));

		boolean result = activity.isEnabledForExecution(workflowInstance);

		assertFalse(result);
	}

}
