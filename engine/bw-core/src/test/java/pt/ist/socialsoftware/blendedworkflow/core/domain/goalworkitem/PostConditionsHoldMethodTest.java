package pt.ist.socialsoftware.blendedworkflow.core.domain.goalworkitem;

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
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.core.domain.PostWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class PostConditionsHoldMethodTest extends TeardownRollbackTest {
	private static final String MY_SPEC = "My spec";
	private static final String ENT_ONE_ROLE = "entOneRole";
	private static final String ENT_TWO_ROLE = "entTwoRole";
	private static final String ENTITY_ONE = "EntityOne";
	private static final String ENTITY_TWO = "EntityTwo";
	private static final String PRODUCT_GOAL_NAME = "ProductGoalName";
	private static final String ASSOCIATION_GOAL_NAME = "AssociationGoalName";

	Specification spec = null;
	Entity entityOne;
	Attribute attributeOne;
	Entity entityTwo;
	DefEntityCondition defEntityCondition;
	RelationBW relationBW;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", MY_SPEC);
		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, "AttributeOne",
				AttributeType.NUMBER, false);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO, false);
		this.relationBW = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ENT_ONE_ROLE,
				Cardinality.ONE, false, this.entityTwo, ENT_TWO_ROLE, "2..4", false);

		this.spec.getConditionModel().generateConditions();

		this.workflowInstance = new WorkflowInstance(this.spec, PRODUCT_GOAL_NAME);
	}

	@Test
	public void defineEntity() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		ProductGoal productGoal = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_NAME, defProductConditions);
		productGoal.initProductGoal();

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, productGoal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityOne));
		postWorkItemArgument.addProductInstance(new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED));
		goalWorkItem.addPostCondition(postWorkItemArgument);

		assertTrue(goalWorkItem.postConditionsHold());
	}

	@Test
	public void defineTwoEntities() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		ProductGoal productGoal = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_NAME, defProductConditions);
		productGoal.initProductGoal();

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, productGoal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityOne));
		postWorkItemArgument.addProductInstance(new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED));
		goalWorkItem.addPostCondition(postWorkItemArgument);
		postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityTwo));
		postWorkItemArgument.addProductInstance(new EntityInstance(this.workflowInstance, this.entityTwo, ProductInstanceState.DEFINED));
		goalWorkItem.addPostCondition(postWorkItemArgument);

		assertTrue(goalWorkItem.postConditionsHold());
	}

	@Test
	public void defineAttribute() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		ProductGoal productGoal = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_NAME, defProductConditions);
		productGoal.initProductGoal();

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, productGoal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED);
		postWorkItemArgument.addProductInstance(new AttributeInstance(entityInstance, this.attributeOne, "124", 
				ProductInstanceState.DEFINED));
		goalWorkItem.addPostCondition(postWorkItemArgument);

		assertTrue(goalWorkItem.postConditionsHold());
	}

	@Test
	public void defineEntityAndAttribute() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		ProductGoal productGoal = new ProductGoal(this.spec.getGoalModel(), PRODUCT_GOAL_NAME, defProductConditions);
		productGoal.initProductGoal();

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, productGoal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityOne));
		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED);
		postWorkItemArgument.addProductInstance(entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);
		postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		postWorkItemArgument.addProductInstance(new AttributeInstance(entityInstance, this.attributeOne, "124", 
				ProductInstanceState.DEFINED));
		goalWorkItem.addPostCondition(postWorkItemArgument);

		assertTrue(goalWorkItem.postConditionsHold());
	}

	@Test
	public void associateEntityInstancesIn() {
		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_NAME,
				this.relationBW.getMulConditionSet());
		associationGoal.initAssociationGoal();

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ENT_ONE_ROLE, new EntityInstance(this.workflowInstance, this.entityTwo, 
				ProductInstanceState.DEFINED), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(entityInstanceOne, ENT_ONE_ROLE, new EntityInstance(this.workflowInstance, this.entityTwo, 
				ProductInstanceState.DEFINED), ENT_TWO_ROLE, this.relationBW);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ENT_ONE_ROLE, entityInstanceTwo, ENT_TWO_ROLE, this.relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, associationGoal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityOne));
		postWorkItemArgument.addProductInstance(entityInstanceOne);
		goalWorkItem.addPostCondition(postWorkItemArgument);
		postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityTwo));
		postWorkItemArgument.addProductInstance(entityInstanceTwo);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test
	public void associateEntityInstancesTop() {
		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_NAME,
				this.relationBW.getMulConditionSet());
		associationGoal.initAssociationGoal();

		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED);

		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(this.workflowInstance, this.entityTwo, 
				ProductInstanceState.DEFINED), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(this.workflowInstance, this.entityTwo, 
				ProductInstanceState.DEFINED), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(this.workflowInstance, this.entityTwo, 
				ProductInstanceState.DEFINED), ENT_TWO_ROLE, this.relationBW);
		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(this.workflowInstance, this.entityTwo, 
				ProductInstanceState.DEFINED), ENT_TWO_ROLE, this.relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, associationGoal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityOne));
		postWorkItemArgument.addProductInstance(entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();

		assertTrue(result);
	}

	@Test(expected = BWException.class)
	public void associateEntityInstancesBelow() {
		AssociationGoal associationGoal = new AssociationGoal(this.spec.getGoalModel(), ASSOCIATION_GOAL_NAME,
				this.relationBW.getMulConditionSet());
		associationGoal.initAssociationGoal();

		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED);

		new RelationInstance(entityInstance, ENT_ONE_ROLE, new EntityInstance(this.workflowInstance, this.entityTwo, 
				ProductInstanceState.DEFINED), ENT_TWO_ROLE, this.relationBW);

		GoalWorkItem goalWorkItem = new GoalWorkItem(this.workflowInstance, associationGoal);
		PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(goalWorkItem,
				DefEntityCondition.getDefEntityCondition(this.entityOne));
		postWorkItemArgument.addProductInstance(entityInstance);
		goalWorkItem.addPostCondition(postWorkItemArgument);

		boolean result = goalWorkItem.postConditionsHold();
	}

}
