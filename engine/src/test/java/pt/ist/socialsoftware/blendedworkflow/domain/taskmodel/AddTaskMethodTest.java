package pt.ist.socialsoftware.blendedworkflow.domain.taskmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class AddTaskMethodTest extends TeardownRollbackTest {
	private static final String TASK_ONE = "TaskOne";
	private static final String TASK_TWO = "TaskTwo";
	private static final String TASK_THREE = "TaskThree";
	private static final String NEW_TASK_NAME = "NewTaskName";
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_THREE_NAME = "EntityThree";
	private static final String ENTITY_EXISTS = "EntityExists";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String ATTRIBUTE_FIVE_NAME = "att5";
	private static final String ATTRIBUTE_FOUR_NAME = "att4";
	private static final String ATTRIBUTE_SIX_NAME = "att6";
	private static final String ROLENAME_ONE = "theOne";
	private static final String DEPENDENCE_PATH_ONE = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String ROLENAME_TWO = "theTwo";
	private static final String ROLENAME_EXISTS = "theExists";
	private static final String RULE_ONE_NAME = "ruleOne";
	private static final String RULE_TWO_NAME = "ruleTwo";
	private static final String RULE_THREE_NAME = "ruleThree";

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	Entity entityExists;
	AttributeBasic attributeOne;
	AttributeBasic attributeTwo;
	AttributeBasic attributeThree;
	AttributeBasic attributeFour;
	AttributeBasic attributeFive;
	AttributeBasic attributeSix;
	RelationBW relation;
	RelationBW relationExists;
	Rule ruleOne;
	Rule ruleTwo;
	Rule ruleThree;

	TaskModel taskModel;
	Task taskOne;
	Task taskTwo;
	Task taskThree;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		attributeOne = new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true, false, false);
		attributeTwo = new AttributeBasic(spec.getDataModel(), entityOne, null, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true, false, false);

		entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
		attributeThree = new AttributeBasic(spec.getDataModel(), entityTwo, null, ATTRIBUTE_THREE_NAME,
				AttributeType.BOOLEAN, true, false, false);

		relation = new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		entityThree = new Entity(spec.getDataModel(), ENTITY_THREE_NAME, false);
		attributeFour = new AttributeBasic(spec.getDataModel(), entityThree, null, ATTRIBUTE_FOUR_NAME,
				AttributeType.NUMBER, false, false, false);
		attributeSix = new AttributeBasic(spec.getDataModel(), entityThree, null, ATTRIBUTE_SIX_NAME,
				AttributeType.NUMBER, false, false, false);

		entityExists = new Entity(spec.getDataModel(), ENTITY_EXISTS, true);
		attributeFive = new AttributeBasic(spec.getDataModel(), entityExists, null, ATTRIBUTE_FIVE_NAME,
				AttributeType.NUMBER, true, false, false);

		relationExists = new RelationBW(spec.getDataModel(), "nameTwo", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityExists, ROLENAME_EXISTS, Cardinality.ZERO_MANY, false);

		new Dependence(spec.getDataModel(), attributeThree, DEPENDENCE_PATH_ONE);

		ruleOne = new Rule(entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		ruleTwo = new Rule(entityThree, RULE_TWO_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOUR_NAME),
						new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_SIX_NAME),
						ComparisonOperator.EQUAL));

		ruleThree = new Rule(entityOne, RULE_THREE_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec,
								ENTITY_ONE_NAME + "." + ROLENAME_EXISTS + "." + ATTRIBUTE_FIVE_NAME),
				ComparisonOperator.EQUAL));

		spec.getConditionModel().generateConditions();

		taskModel = spec.getTaskModel();

		taskOne = new Task(spec.getTaskModel(), TASK_ONE, "Description");
		taskOne.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_EXISTS));
		taskOne.addPostCondition(DefEntityCondition.getDefEntity(entityOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttribute(attributeOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttribute(attributeTwo));
		taskOne.addMultiplicityInvariant(MulCondition.getMulCondition(relationExists, relationExists.getRoleNameOne()));
		taskOne.addMultiplicityInvariant(MulCondition.getMulCondition(relationExists, relationExists.getRoleNameTwo()));
		taskOne.addRuleInvariant(ruleOne);
		taskOne.addRuleInvariant(ruleThree);

		taskTwo = new Task(spec.getTaskModel(), TASK_TWO, "Description");
		taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_ONE_NAME));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntity(entityTwo));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntity(entityThree));
		taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relation, relation.getRoleNameOne()));
		taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relation, relation.getRoleNameTwo()));

		taskThree = new Task(spec.getTaskModel(), TASK_THREE, "Description");
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_TWO_NAME));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_THREE_NAME));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, DEPENDENCE_PATH_ONE));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttribute(attributeThree));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttribute(attributeFour));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttribute(attributeSix));
		taskThree.addRuleInvariant(ruleTwo);

		taskModel.checkModel();
	}

	@Test
	public void emptyPostConditionSet() {
		taskThree.removePostCondition(DefAttributeCondition.getDefAttribute(attributeTwo));

		try {
			Set<DefProductCondition> postConditions = new HashSet<DefProductCondition>();
			taskModel.addTask(NEW_TASK_NAME, "Description", postConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals("empty post condition set", bwe.getMessage());
		}
	}

	@Test
	public void allActivitiesWereCreated() {
		try {
			Set<DefProductCondition> postConditions = new HashSet<DefProductCondition>();
			postConditions.add(DefEntityCondition.getDefEntity(entityOne));
			taskModel.addTask(NEW_TASK_NAME, "Description", postConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals("condition already used", bwe.getMessage());
		}
	}

	@Test
	public void conditionBelongsToOtherPostCondition() {
		taskThree.removePostCondition(DefAttributeCondition.getDefAttribute(attributeTwo));
		try {
			Set<DefProductCondition> postConditions = new HashSet<DefProductCondition>();
			postConditions.add(DefEntityCondition.getDefEntity(entityOne));
			taskModel.addTask(NEW_TASK_NAME, "Description", postConditions);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals("condition already used", bwe.getMessage());
		}
	}

}
