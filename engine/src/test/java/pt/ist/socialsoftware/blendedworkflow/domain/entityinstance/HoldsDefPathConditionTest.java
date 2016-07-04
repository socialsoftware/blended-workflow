package pt.ist.socialsoftware.blendedworkflow.domain.entityinstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		DataModel dataModel = spec.getDataModel();
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");

		entOne = new Entity(dataModel, ENT_ONE, false);
		entTwo = new Entity(dataModel, ENT_TWO, false);
		entThree = new Entity(dataModel, ENY_THREE, false);

		attOne = new Attribute(dataModel, entOne, ATT_ONE_NAME, AttributeType.NUMBER, true, false, false);
		attTwo = new Attribute(dataModel, entOne, ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

		attThree = new Attribute(dataModel, entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true, false, false);

		attFour = new Attribute(dataModel, entThree, ATT_FOUR_NAME, AttributeType.NUMBER, true, false, false);

		relationOne = new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false,
				entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		relationTwo = new RelationBW(dataModel, "relOneThree", entOne, ROLENAME_ENT_ONE, Cardinality.ONE, false,
				entThree, ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

		relationThree = new RelationBW(dataModel, "relTwoThree", entThree, ROLENAME_ENT_THREE, Cardinality.ZERO_MANY,
				false, entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);
	}

	@Test
	public void defPathConditionRefersEntityOfInstance() {
		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);

		boolean result = entityInstance.holdsDefPathCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE));

		assertTrue(result);
	}

	@Test
	public void defPathConditionStartsBeforeEntityOfInstanceSuccess() {
		EntityInstance entityInstance = new EntityInstance(workflowInstance, entTwo);

		boolean result = entityInstance
				.holdsDefPathCondition(DefPathCondition.getDefPathCondition(spec, ENT_ONE + "." + ROLENAME_ENT_TWO));

		assertTrue(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongSuccess() {
		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO, relationOne);
		EntityInstance entityInstanceThree = new EntityInstance(workflowInstance, entThree);
		new RelationInstance(entityInstanceThree, ROLENAME_ENT_THREE, entityInstanceTwo, ROLENAME_ENT_TWO,
				relationThree);
		new AttributeInstance(entityInstanceThree, attFour, "123");

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));

		assertTrue(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongSuccessStartsBefore() {
		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO, relationOne);
		EntityInstance entityInstanceThree = new EntityInstance(workflowInstance, entThree);
		new RelationInstance(entityInstanceThree, ROLENAME_ENT_THREE, entityInstanceTwo, ROLENAME_ENT_TWO,
				relationThree);
		new AttributeInstance(entityInstanceThree, attFour, "123");

		boolean result = entityInstanceTwo.holdsDefPathCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));

		assertTrue(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongFailAttribute() {
		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO, relationOne);
		EntityInstance entityInstanceThree = new EntityInstance(workflowInstance, entThree);
		new RelationInstance(entityInstanceThree, ROLENAME_ENT_THREE, entityInstanceTwo, ROLENAME_ENT_TWO,
				relationThree);

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE + "." + ATT_FOUR_NAME));

		assertFalse(result);
	}

	@Test
	public void defPathConditionSeveralStepsLongFailEntity() {
		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO, relationOne);

		boolean result = entityInstanceOne.holdsDefPathCondition(DefPathCondition.getDefPathCondition(spec,
				ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ROLENAME_ENT_THREE));

		assertFalse(result);
	}

	@Test
	public void pathHasSeveralInstancesSuccess() {
		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwoOne = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoOne, ROLENAME_ENT_TWO, relationOne);
		new AttributeInstance(entityInstanceTwoOne, attThree, "321");
		EntityInstance entityInstanceTwoTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoTwo, ROLENAME_ENT_TWO, relationOne);
		new AttributeInstance(entityInstanceTwoTwo, attThree, "321");

		boolean result = entityInstanceOne.holdsDefPathCondition(
				DefPathCondition.getDefPathCondition(spec, ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));

		assertTrue(result);
	}

	@Test
	public void pathHasSeveralInstancesFail() {
		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwoOne = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoOne, ROLENAME_ENT_TWO, relationOne);
		new AttributeInstance(entityInstanceTwoOne, attThree, "321");
		EntityInstance entityInstanceTwoTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoTwo, ROLENAME_ENT_TWO, relationOne);

		boolean result = entityInstanceOne.holdsDefPathCondition(
				DefPathCondition.getDefPathCondition(spec, ENT_ONE + "." + ROLENAME_ENT_TWO + "." + ATT_THREE_NAME));

		assertFalse(result);
	}

}
