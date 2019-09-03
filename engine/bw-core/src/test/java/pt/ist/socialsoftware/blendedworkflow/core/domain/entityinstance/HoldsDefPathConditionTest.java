package pt.ist.socialsoftware.blendedworkflow.core.domain.entityinstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class HoldsDefPathConditionTest extends TeardownRollbackTest {
	private static final String ENY_THREE = "EnyThree";
	private static final String ENT_TWO = "EntTwo";
	private static final String ENT_ONE = "EntOne";
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

		this.entOne = new Entity(dataModel, ENT_ONE, false);
		this.entTwo = new Entity(dataModel, ENT_TWO, false);
		this.entThree = new Entity(dataModel, ENY_THREE, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true);

		this.attFour = new Attribute(dataModel, this.entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true);

		this.relationOne = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY,
				false, this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		this.relationTwo = new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE,
				false, this.entThree, ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		this.relationThree = new RelationBW(dataModel, "relTwoThree", this.entThree, ROLENAME_ENT_THREE,
				Cardinality.ZERO_MANY, false, this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
	}

	@Test
	public void defPathConditionRefersEntityOfInstance() {
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);

		boolean result = entityInstance.holdsDefPathCondition(DefPathCondition.getDefPathCondition(this.spec, ENT_ONE));

		assertTrue(result);
	}

	@Test
	public void defPathConditionStartsBeforeEntityOfInstanceSuccess() {
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);

		boolean result = entityInstance.holdsDefPathCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENT_ONE + "." + ROLENAME_ENT_TWO));

		assertTrue(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongSuccess() {
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOne);
		EntityInstance entityInstanceThree = new EntityInstance(this.workflowInstance, this.entThree, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceThree, ROLENAME_ENT_THREE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationThree);
		new AttributeInstance(entityInstanceThree, this.attFour, "123", ProductInstanceState.DEFINED);

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));

		assertTrue(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongSuccessStartsBefore() {
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOne);
		EntityInstance entityInstanceThree = new EntityInstance(this.workflowInstance, this.entThree, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceThree, ROLENAME_ENT_THREE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationThree);
		new AttributeInstance(entityInstanceThree, this.attFour, "123", ProductInstanceState.DEFINED);

		boolean result = entityInstanceTwo.holdsDefPathCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));

		assertTrue(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongFailAttribute() {
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOne);
		EntityInstance entityInstanceThree = new EntityInstance(this.workflowInstance, this.entThree, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceThree, ROLENAME_ENT_THREE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationThree);

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));

		assertFalse(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongFailEntity() {
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOne);

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE));

		assertFalse(result);
	}

	@Test
	public void pathHasSeveralInstancesSuccess() {
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwoOne = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoOne, ROLENAME_ENT_TWO,
				this.relationOne);
		new AttributeInstance(entityInstanceTwoOne, this.attThree, "321", ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwoTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoTwo, ROLENAME_ENT_TWO,
				this.relationOne);
		new AttributeInstance(entityInstanceTwoTwo, this.attThree, "321", ProductInstanceState.DEFINED);

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));

		assertTrue(result);
	}

	@Test
	public void pathHasSeveralInstancesFail() {
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwoOne = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoOne, ROLENAME_ENT_TWO,
				this.relationOne);
		new AttributeInstance(entityInstanceTwoOne, this.attThree, "321", ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwoTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoTwo, ROLENAME_ENT_TWO,
				this.relationOne);

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(this.spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));

		assertFalse(result);
	}

}
