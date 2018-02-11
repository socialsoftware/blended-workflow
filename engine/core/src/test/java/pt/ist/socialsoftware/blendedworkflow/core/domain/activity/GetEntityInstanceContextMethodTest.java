package pt.ist.socialsoftware.blendedworkflow.core.domain.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetEntityInstanceContextMethodTest extends TeardownRollbackTest {
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
	RelationBW relationOne;
	RelationBW relationTwo;

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

		this.relationOne = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false,
				this.entTwo, ROLENAME_ENT_TWO, "0..2", false);

		this.relationTwo = new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE,
				false, this.entThree, ROLENAME_ENT_THREE, "2..4", false);

	}

	@Test
	public void emptyInstanceContext() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entOne));

		Set<EntityInstance> instanceContext = activity.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(0, instanceContext.size());
	}

	@Test
	public void attributeIsNotDefinedAndSingleEntity() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne);

		Set<EntityInstance> instanceContext = activity.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void oneAttributeIsNotDefinedAndTwoEntity() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		activity.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attOne));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne);
		new AttributeInstance(entityInstanceOne, this.attOne, "123");
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entOne);

		Set<EntityInstance> instanceContext = activity.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceTwo));
	}

	@Test
	public void oneEntityIsNotDefined() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_ONE));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne);

		Set<EntityInstance> instanceContext = activity.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void oneEntityInstanceIsDefinedTheOtherDont() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE_NAME));
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entTwo));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_ONE));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstanceOneOne = new EntityInstance(this.workflowInstance, this.entOne);
		EntityInstance entityInstanceOneTwo = new EntityInstance(this.workflowInstance, this.entOne);
		EntityInstance entityInstanceTwoOne = new EntityInstance(this.workflowInstance, this.entTwo);
		EntityInstance entityInstanceTwoTwo = new EntityInstance(this.workflowInstance, this.entTwo);

		new RelationInstance(entityInstanceOneOne, ROLENAME_ENT_ONE, entityInstanceTwoOne, ROLENAME_ENT_TWO,
				this.relationOne);
		new RelationInstance(entityInstanceOneOne, ROLENAME_ENT_ONE, entityInstanceTwoTwo, ROLENAME_ENT_TWO,
				this.relationOne);

		Set<EntityInstance> instanceContext = activity.getInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceOneTwo));
	}

	@Test
	public void enoughInstancesInInstanceContext() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_THREE_NAME));
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationTwo, ROLENAME_ENT_ONE));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationTwo, ROLENAME_ENT_THREE));

		EntityInstance entityInstanceThreeOne = new EntityInstance(this.workflowInstance, this.entThree);
		EntityInstance entityInstanceThreeTwo = new EntityInstance(this.workflowInstance, this.entThree);

		Set<EntityInstance> instanceContext = activity.getInstanceContext(this.workflowInstance, this.entThree);

		assertEquals(2, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceThreeOne));
		assertTrue(instanceContext.contains(entityInstanceThreeTwo));
	}

	@Test
	public void notEnoughInstancesInInstanceContext() {
		Activity activity = new Activity(this.spec.getActivityModel(), "name", "description");
		activity.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_THREE_NAME));
		activity.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entOne));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationTwo, ROLENAME_ENT_ONE));
		activity.addMultiplicityInvariant(MulCondition.getMulCondition(this.relationTwo, ROLENAME_ENT_THREE));

		new EntityInstance(this.workflowInstance, this.entThree);

		Set<EntityInstance> instanceContext = activity.getInstanceContext(this.workflowInstance, this.entThree);

		assertEquals(0, instanceContext.size());
	}

}
