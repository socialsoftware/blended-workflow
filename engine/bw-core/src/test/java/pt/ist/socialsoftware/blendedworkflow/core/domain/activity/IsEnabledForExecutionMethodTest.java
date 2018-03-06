package pt.ist.socialsoftware.blendedworkflow.core.domain.activity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class IsEnabledForExecutionMethodTest extends TeardownRollbackTest {
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
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.workflowInstance = new WorkflowInstance(this.spec, "WorkflowInstanceName");

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		this.attFour = new Attribute(dataModel, this.entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		this.relationOne = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false,
				this.entTwo, ROLENAME_ENT_TWO, "0..2", false);

		this.relationTwo = new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE,
				false, this.entThree, ROLENAME_ENT_THREE, "2..4", false);

		this.relationThree = new RelationBW(dataModel, "relTwoThree", this.entThree, ROLENAME_ENT_THREE,
				Cardinality.ZERO_MANY, false, this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
	}

	@Test
	public void emptyEntityContext() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entOne));

		boolean result = activity.isEnabledForExecution(this.workflowInstance);

		assertTrue(result);
	}

	@Test
	public void emptyInstanceContext() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_ONE));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_TWO));

		boolean result = activity.isEnabledForExecution(this.workflowInstance);

		assertFalse(result);
	}

}
