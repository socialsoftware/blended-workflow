package pt.ist.socialsoftware.blendedworkflow.core.workflowinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetEnabledActivitySetTest extends TeardownRollbackTest {
	private static final String ACTIVITY_ONE = "activity one";
	private static final String ACTIVITY_TWO = "activity two";
	private static final String ACTIVITY_THREE = "activity three";
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ENT_THREE_NAME = "EntThree";
	private static final String DESCRIPTION = "description";
	private static final String ROLENAME_ONE = "ome";
	private static final String ROLENAME_TWO = "two";
	private static final String ROLENAME_THREE = "three";
	private static final String DEPENDENCE_PATH_THREE_TO_ONE = ENT_THREE_NAME + "." + ROLENAME_ONE;
	private static final String DEPENDENCE_PATH_THREE_TO_TWO = ENT_THREE_NAME + "." + ROLENAME_TWO;

	Specification spec;
	ActivityModel activityModel;
	Entity entOne;
	Entity entTwo;
	Entity entThree;
	Attribute attOne;
	RelationBW relationOneThree;
	RelationBW relationTwoThree;
	WorkflowInstance workflowInstance;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec", "author", DESCRIPTION, "version", "UID");
		DataModel dataModel = this.spec.getDataModel();
		this.activityModel = this.spec.getActivityModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.attOne = new Attribute(dataModel, this.entOne, "attOne", AttributeType.NUMBER, false, false, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.relationOneThree = new RelationBW(this.spec.getDataModel(), "oneThree", this.entOne, ROLENAME_ONE,
				Cardinality.ONE, false, this.entThree, ROLENAME_THREE, Cardinality.ZERO_MANY, false);
		this.relationTwoThree = new RelationBW(this.spec.getDataModel(), "twoThree", this.entTwo, ROLENAME_TWO,
				Cardinality.ONE, false, this.entThree, ROLENAME_THREE, Cardinality.ZERO_MANY, false);

		this.workflowInstance = new WorkflowInstance(this.spec, "name");
	}

	@Test
	public void testOne() {
		Activity activityOne = new Activity(this.activityModel, ACTIVITY_ONE, DESCRIPTION);
		activityOne.addPostCondition(this.entOne.getDefEntityCondition());

		Activity activityTwo = new Activity(this.activityModel, ACTIVITY_TWO, DESCRIPTION);
		activityTwo.addPostCondition(this.entTwo.getDefEntityCondition());

		Activity activityThree = new Activity(this.activityModel, ACTIVITY_THREE, DESCRIPTION);
		activityThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH_THREE_TO_ONE));
		activityThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH_THREE_TO_TWO));
		activityThree.addPostCondition(this.entThree.getDefEntityCondition());

		assertEquals(2, this.workflowInstance.getEnabledActivitySet().size());
		assertTrue(this.workflowInstance.getEnabledActivitySet().contains(activityOne));
		assertTrue(this.workflowInstance.getEnabledActivitySet().contains(activityTwo));
	}

	@Test
	public void testTwo() {
		Activity activityOne = new Activity(this.activityModel, ACTIVITY_ONE, DESCRIPTION);
		activityOne.addPostCondition(this.entOne.getDefEntityCondition());
		activityOne.addPostCondition(this.entTwo.getDefEntityCondition());

		Activity activityTwo = new Activity(this.activityModel, ACTIVITY_TWO, DESCRIPTION);
		activityTwo.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		activityTwo.addPostCondition(this.attOne.getDefAttributeCondition());

		assertEquals(1, this.workflowInstance.getEnabledActivitySet().size());
		assertTrue(this.workflowInstance.getEnabledActivitySet().contains(activityOne));
	}

}
