package pt.ist.socialsoftware.blendedworkflow.core.domain.goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AssociationGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetEntityInstanceContextMethodTest extends TeardownRollbackTest {
	private static final String GOAL_NAME_ONE = "name one";
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
	RelationBW relationOneTwo;
	RelationBW relationOneThree;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.workflowInstance = new WorkflowInstance(this.spec, "WorkflowInstanceName");

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.entThree = new Entity(dataModel, ENT_THREE_NAME, false);

		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true);
		this.attFour = new Attribute(dataModel, this.entTwo, ATT_FOUR_NAME, AttributeType.NUMBER, true);

		new Dependence(dataModel, this.attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);

		this.relationOneTwo = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE,
				false, this.entTwo, ROLENAME_ENT_TWO, "0..2", false);

		this.relationOneThree = new RelationBW(dataModel, "relOneThree", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE,
				false, this.entThree, ROLENAME_ENT_THREE, "2..4", false);

		this.spec.getConditionModel().generateConditions();
	}

	@Test
	public void entitySuccessConditionAndNoInstance() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entOne));
		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void entitySuccessConditionAndAttributeOK() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void attributeSuccessConditionAndNoInstance() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void attributeSuccessConditionAndInstanceOK() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstance));
	}

	@Test
	public void attributeSuccessConditionAndInstanceNoOK() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		new AttributeInstance(entityInstance, this.attOne, "123", ProductInstanceState.DEFINED);

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void attributeSuccessConditionAndDependenceAndNoInstance() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attThree));

		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		assertTrue(goal.getEntityContext().contains(this.entTwo));

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entTwo);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void attributeSuccessConditionAndDependenceAndInstanceOK() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attThree));

		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		assertTrue(goal.getEntityContext().contains(this.entTwo));
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOneTwo);
		new AttributeInstance(entityInstanceOne, this.attOne, "123", ProductInstanceState.DEFINED);

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entTwo);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceTwo));
	}

	@Test
	public void attributeSuccessConditionAndDependenceAndInstanceNoOK() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attThree));

		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		assertTrue(goal.getEntityContext().contains(this.entTwo));
		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOneTwo);

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entTwo);

		assertTrue(instanceContext.isEmpty());
	}

	@Test
	public void twoAttributeSuccessConditionAndInstancesOK() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attFour));

		ProductGoal goal = new ProductGoal(this.spec.getGoalModel(), GOAL_NAME_ONE, defProductConditions);
		goal.initProductGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		assertTrue(goal.getEntityContext().contains(this.entTwo));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOneTwo);

		Set<EntityInstance> instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceOne));

		instanceContext = goal.getEntityInstanceContext(this.workflowInstance, this.entTwo);

		assertEquals(1, instanceContext.size());
		assertTrue(instanceContext.contains(entityInstanceTwo));
	}

	@Test
	public void multiplicityConditionsAndInstanceOK() {
		AssociationGoal goal = new AssociationGoal(this.spec.getGoalModel(), GOAL_NAME_ONE,
				this.relationOneTwo.getMulConditionSet());
		goal.initAssociationGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		assertTrue(goal.getEntityContext().contains(this.entTwo));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);

		Set<EntityInstance> instanceContextOne = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);
		Set<EntityInstance> instanceContextTwo = goal.getEntityInstanceContext(this.workflowInstance, this.entTwo);

		assertEquals(1, instanceContextOne.size());
		assertTrue(instanceContextOne.contains(entityInstanceOne));
		assertEquals(1, instanceContextTwo.size());
		assertTrue(instanceContextTwo.contains(entityInstanceTwo));
	}

	@Test
	public void multiplicityConditionsAndInstanceNoOK() {
		AssociationGoal goal = new AssociationGoal(this.spec.getGoalModel(), GOAL_NAME_ONE,
				this.relationOneTwo.getMulConditionSet());
		goal.initAssociationGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		assertTrue(goal.getEntityContext().contains(this.entTwo));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);

		Set<EntityInstance> instanceContextOne = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);
		Set<EntityInstance> instanceContextTwo = goal.getEntityInstanceContext(this.workflowInstance, this.entTwo);

		assertEquals(1, instanceContextOne.size());
		assertTrue(instanceContextOne.contains(entityInstanceOne));
		assertTrue(instanceContextTwo.isEmpty());
	}

	@Test
	public void multiplicityConditionsAndInstanceNotEnough() {
		AssociationGoal goal = new AssociationGoal(this.spec.getGoalModel(), GOAL_NAME_ONE,
				this.relationOneThree.getMulConditionSet());
		goal.initAssociationGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		assertTrue(goal.getEntityContext().contains(this.entThree));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		new EntityInstance(this.workflowInstance, this.entThree, ProductInstanceState.DEFINED);

		Set<EntityInstance> instanceContextOne = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);
		Set<EntityInstance> instanceContextThree = goal.getEntityInstanceContext(this.workflowInstance, this.entThree);

		assertEquals(1, instanceContextOne.size());
		assertTrue(instanceContextOne.contains(entityInstanceOne));
		assertTrue(instanceContextThree.isEmpty());
	}

	@Test
	public void multiplicityConditionsAndInstanceForMultiplicityBiggerThanOne() {
		AssociationGoal goal = new AssociationGoal(this.spec.getGoalModel(), GOAL_NAME_ONE,
				this.relationOneThree.getMulConditionSet());
		goal.initAssociationGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		assertTrue(goal.getEntityContext().contains(this.entThree));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceThreeOne = new EntityInstance(this.workflowInstance, this.entThree, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceThreeTwo = new EntityInstance(this.workflowInstance, this.entThree, ProductInstanceState.DEFINED);

		Set<EntityInstance> instanceContextOne = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);
		assertEquals(1, instanceContextOne.size());
		assertTrue(instanceContextOne.contains(entityInstanceOne));

		Set<EntityInstance> instanceContextThree = goal.getEntityInstanceContext(this.workflowInstance, this.entThree);

		assertEquals(2, instanceContextThree.size());
		assertTrue(instanceContextThree.contains(entityInstanceThreeOne));
		assertTrue(instanceContextThree.contains(entityInstanceThreeTwo));
	}

	@Test
	public void multiplicityConditionsAndInstanceAlreadyAssociated() {
		AssociationGoal goal = new AssociationGoal(this.spec.getGoalModel(), GOAL_NAME_ONE,
				this.relationOneTwo.getMulConditionSet());
		goal.initAssociationGoal();

		assertTrue(goal.getEntityContext().contains(this.entOne));
		assertTrue(goal.getEntityContext().contains(this.entTwo));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOneTwo);

		Set<EntityInstance> instanceContextOne = goal.getEntityInstanceContext(this.workflowInstance, this.entOne);
		Set<EntityInstance> instanceContextTwo = goal.getEntityInstanceContext(this.workflowInstance, this.entTwo);

		assertEquals(1, instanceContextOne.size());
		assertTrue(instanceContextOne.contains(entityInstanceOne));
		assertTrue(instanceContextTwo.isEmpty());
	}

}
