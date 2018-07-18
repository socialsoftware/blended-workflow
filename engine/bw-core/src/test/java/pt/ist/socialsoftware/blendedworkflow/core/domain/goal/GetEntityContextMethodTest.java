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
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductGoal;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetEntityContextMethodTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ATT_ONE_NAME = "AttOne";
	private static final String ATT_TWO_NAME = "AttTwo";
	private static final String ATT_THREE_NAME = "AttThree";
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";
	private static final String GOAL_ONE = "goalOne";

	Specification spec;
	GoalModel goalModel;
	Entity entOne;
	Entity entTwo;
	Attribute attOne;
	Attribute attTwo;
	Attribute attThree;
	RelationBW relation;
	ProductGoal productGoal;
	AssociationGoal associationGoal;

	@Override
	public void populate4Test() throws BWException {
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.goalModel = this.spec.getGoalModel();

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.attOne = new Attribute(dataModel, this.entOne, ATT_ONE_NAME, AttributeType.NUMBER, true);
		this.attTwo = new Attribute(dataModel, this.entOne, ATT_TWO_NAME, AttributeType.NUMBER, false);

		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
		this.attThree = new Attribute(dataModel, this.entTwo, ATT_THREE_NAME, AttributeType.NUMBER, true);

		this.relation = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY,
				false, this.entTwo, ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

		new Dependence(dataModel, this.attThree, ENT_TWO_NAME + "." + ROLENAME_ENT_ONE + "." + ATT_ONE_NAME);

		this.spec.getConditionModel().generateConditions();
	}

	@Test
	public void emptyActivationAndSuccess() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entOne));
		this.productGoal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		this.productGoal.initProductGoal();

		Set<Entity> entityContext = this.productGoal.getEntityContext();

		assertTrue(entityContext.isEmpty());
	}

	@Test
	public void successForAssociation() {
		this.associationGoal = new AssociationGoal(this.goalModel, GOAL_ONE, this.relation.getMulConditionSet());
		this.associationGoal.initAssociationGoal();

		Set<Entity> entityContext = this.associationGoal.getEntityContext();

		assertEquals(2, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
		assertTrue(entityContext.contains(this.entTwo));
	}

	@Test
	public void defineAttributeAndItsEntityIsDefinedInAnotherGoal() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		this.productGoal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		this.productGoal.initProductGoal();

		Set<Entity> entityContext = this.productGoal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
	}

	@Test
	public void defineAttributeAndItsEntity() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entOne));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attOne));
		this.productGoal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		this.productGoal.initProductGoal();

		Set<Entity> entityContext = this.productGoal.getEntityContext();

		assertEquals(0, entityContext.size());
	}

	@Test
	public void defineAttributeHasDependenceAndItsEntityIsCreated() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefEntityCondition.getDefEntityCondition(this.entTwo));
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attThree));
		this.productGoal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		this.productGoal.initProductGoal();

		Set<Entity> entityContext = this.productGoal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(this.entOne));
	}

	@Test
	public void defineAttributeHasDependenceAndItsEntityAlreadyExists() {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(DefAttributeCondition.getDefAttributeCondition(this.attThree));
		this.productGoal = new ProductGoal(this.goalModel, GOAL_ONE, defProductConditions);
		this.productGoal.initProductGoal();

		Set<Entity> entityContext = this.productGoal.getEntityContext();

		assertEquals(1, entityContext.size());
		assertTrue(entityContext.contains(this.entTwo));
	}

}
