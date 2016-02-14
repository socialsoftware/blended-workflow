package pt.ist.socialsoftware.blendedworkflow.domain.taskmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class MergeTasksTest extends TeardownRollbackTest {
	private static final String ATTRIBUTE_FOURTWO_NAME = "att42";
	private static final String ATTRIBUTE_FOURONE_NAME = "att41";
	private static final String TASK_ONE = "TaskOne";
	private static final String TASK_TWO = "TaskTwo";
	private static final String TASK_THREE = "TaskThree";
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_THREE_NAME = "EntityThree";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String ATTRIBUTE_FOUR_NAME = "att4";
	private static final String ROLENAME_ONE = "theOne";
	private static final String DEPENDENCE_PATH_ONE = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String ROLENAME_TWO = "theTwo";
	private static final String RULE_ONE_NAME = "ruleOne";
	private static final String RULE_TWO_NAME = "ruleTwo";

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	AttributeBasic attributeOne;
	AttributeBasic attributeTwo;
	AttributeBasic attributeThree;
	AttributeGroup attributeFour;
	AttributeBasic attributeFourOne;
	AttributeBasic attributeFourTwo;
	RelationBW relation;
	Rule ruleOne;
	Rule ruleTwo;

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
		attributeFour = new AttributeGroup(spec.getDataModel(), entityThree, ATTRIBUTE_FOUR_NAME, false);
		attributeFourOne = new AttributeBasic(spec.getDataModel(), entityThree, attributeFour, ATTRIBUTE_FOURONE_NAME,
				AttributeType.NUMBER, false, false, false);
		attributeFourTwo = new AttributeBasic(spec.getDataModel(), entityThree, attributeFour, ATTRIBUTE_FOURTWO_NAME,
				AttributeType.NUMBER, false, false, false);

		new Dependence(spec.getDataModel(), attributeThree, DEPENDENCE_PATH_ONE);

		ruleOne = new Rule(entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		ruleTwo = new Rule(entityThree, RULE_TWO_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURONE_NAME),
						new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOURTWO_NAME),
						ComparisonOperator.EQUAL));

		spec.getConditionModel().generateConditions();

		taskModel = spec.getTaskModel();

		taskOne = new Task(spec.getTaskModel(), TASK_ONE, "Description");
		taskOne.addPostCondition(DefEntityCondition.getDefEntity(entityOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttribute(attributeOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttribute(attributeTwo));
		taskOne.addRuleInvariant(ruleOne);

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
		taskThree.addRuleInvariant(ruleTwo);

	}

	@Test
	public void mergeTasksOneTwo() {
		Task task = taskModel.mergeTasks("newTask", "taskDescription", taskOne, taskTwo);

		assertEquals(2, taskModel.getTasksSet().size());

		assertFalse(task.getPreConditionSet().stream().map(d -> d.getTargetOfPath()).collect(Collectors.toSet())
				.contains(ENTITY_ONE_NAME));

		assertTrue(taskModel.checkModel());
		assertTrue(task.checkConsistency());
	}

	@Test
	public void mergeTasksTwoThree() {
		Task task = taskModel.mergeTasks("newTask", "taskDescription", taskTwo, taskThree);

		assertTrue(taskModel.checkModel());
		assertTrue(task.checkConsistency());
	}

	@Test
	public void mergeTasksOneThree() {
		Task task = taskModel.mergeTasks("newTask", "taskDescription", taskOne, taskThree);

		assertTrue(taskModel.checkModel());
		assertTrue(task.checkConsistency());
	}

}
