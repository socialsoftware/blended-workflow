package pt.ist.socialsoftware.blendedworkflow.domain.taskmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
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
    private static final String ANOTHER_TASK = "anotherTask";
    private static final String ENTITY_ONE_NAME = "Entity one name";
    private static final String ENTITY_TWO_NAME = "Entity two name";
    private static final String ENTITY_THREE_NAME = "Entity three name";
    private static final String ATTRIBUTE_ONE_NAME = "att1";
    private static final String ATTRIBUTE_TWO_NAME = "att2";
    private static final String ATTRIBUTE_THREE_NAME = "att3";
    private static final String ATTRIBUTE_FOUR_NAME = "att4";
    private static final String ROLENAME_ONE = "theOne";
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
        spec = new Specification("SpecId", "My spec", "author", "description",
                "version", "UID");

        entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
        attributeOne = new AttributeBasic(spec.getDataModel(), entityOne, null,
                ATTRIBUTE_ONE_NAME, AttributeType.NUMBER, true, false, false);
        attributeTwo = new AttributeBasic(spec.getDataModel(), entityOne, null,
                ATTRIBUTE_TWO_NAME, AttributeType.NUMBER, true, false, false);

        entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
        attributeThree = new AttributeBasic(spec.getDataModel(), entityTwo,
                null, ATTRIBUTE_THREE_NAME, AttributeType.BOOLEAN, true, false,
                false);

        relation = new RelationBW(spec.getDataModel(), "name", entityOne,
                ROLENAME_ONE, Cardinality.ONE, false, entityTwo, ROLENAME_TWO,
                Cardinality.ZERO_MANY, false);

        entityThree = new Entity(spec.getDataModel(), ENTITY_THREE_NAME, false);
        attributeFour = new AttributeGroup(spec.getDataModel(), entityThree,
                ATTRIBUTE_FOUR_NAME, false);
        attributeFourOne = new AttributeBasic(spec.getDataModel(), entityThree,
                attributeFour, "att41", AttributeType.NUMBER, false, false,
                false);
        attributeFourTwo = new AttributeBasic(spec.getDataModel(), entityThree,
                attributeFour, "att42", AttributeType.NUMBER, false, false,
                false);

        new Dependence(spec.getDataModel(), attributeThree, ENTITY_TWO_NAME
                + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME);

        ruleOne = new Rule(spec.getDataModel(), RULE_ONE_NAME,
                new Comparison(new AttributeValueExpression(attributeOne),
                        new AttributeValueExpression(attributeTwo),
                        ComparisonOperator.EQUAL));

        ruleTwo = new Rule(spec.getDataModel(), RULE_TWO_NAME,
                new Comparison(new AttributeValueExpression(attributeFourOne),
                        new AttributeValueExpression(attributeFourTwo),
                        ComparisonOperator.EQUAL));

        spec.getConditionModel().generateConditions();

        taskModel = spec.getTaskModel();

        taskOne = new Task(spec.getTaskModel(), TASK_ONE, "Description");
        taskOne.addPostCondition(DefEntityCondition.getDefEntity(entityOne));
        taskOne.addPostCondition(
                DefAttributeCondition.getDefAttribute(attributeOne));
        taskOne.addPostCondition(
                DefAttributeCondition.getDefAttribute(attributeTwo));
        taskOne.addRuleInvariant(ruleOne);

        taskTwo = new Task(spec.getTaskModel(), TASK_TWO, "Description");
        taskTwo.addPreCondition(DefEntityCondition.getDefEntity(entityOne));
        taskTwo.addPostCondition(DefEntityCondition.getDefEntity(entityTwo));
        taskTwo.addPostCondition(DefEntityCondition.getDefEntity(entityThree));
        taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relation,
                relation.getRoleNameOne()));
        taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relation,
                relation.getRoleNameTwo()));

        taskThree = new Task(spec.getTaskModel(), TASK_THREE, "Description");
        taskThree.addPreCondition(DefEntityCondition.getDefEntity(entityTwo));
        taskThree.addPreCondition(DefEntityCondition.getDefEntity(entityThree));
        taskThree.addPreCondition(
                DefAttributeCondition.getDefAttribute(attributeTwo));
        taskThree.addPostCondition(
                DefAttributeCondition.getDefAttribute(attributeThree));
        taskThree.addPostCondition(
                DefAttributeCondition.getDefAttribute(attributeFour));
        taskThree.addRuleInvariant(ruleTwo);

    }

    @Test
    public void emptyPostConditionSet() {
        taskThree.removePostCondition(
                DefAttributeCondition.getDefAttribute(attributeTwo));

        try {
            Set<Condition> postConditions = new HashSet<Condition>();
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
            Set<Condition> postConditions = new HashSet<Condition>();
            postConditions.add(DefEntityCondition.getDefEntity(entityOne));
            taskModel.addTask(NEW_TASK_NAME, "Description", postConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
            assertEquals(
                    "all achieve conditions already belong to a post condition",
                    bwe.getMessage());
        }
    }

    @Test
    public void conditionBelongsToOtherPostCondition() {
        taskThree.removePostCondition(
                DefAttributeCondition.getDefAttribute(attributeTwo));
        try {
            Set<Condition> postConditions = new HashSet<Condition>();
            postConditions.add(DefEntityCondition.getDefEntity(entityOne));
            taskModel.addTask(NEW_TASK_NAME, "Description", postConditions);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
            assertEquals("condition already used", bwe.getMessage());
        }
    }

    @Test
    public void successWithOutMultiplicityFisrts() {
        taskThree.delete();

        Set<Condition> postConditions = new HashSet<Condition>();
        postConditions
                .add(DefAttributeCondition.getDefAttribute(attributeThree));
        postConditions
                .add(DefAttributeCondition.getDefAttribute(attributeFour));
        Task task = taskModel.addTask(NEW_TASK_NAME, "Description",
                postConditions);

        assertEquals(NEW_TASK_NAME, task.getName());
        assertEquals(2, task.getPostConditionSet().size());
        assertTrue(task.getPostConditionSet().contains(
                DefAttributeCondition.getDefAttribute(attributeThree)));
        assertTrue(task.getPostConditionSet().contains(
                DefAttributeCondition.getDefAttribute(attributeFour)));
        assertEquals(3, task.getPreConditionSet().size());
        assertTrue(task.getPreConditionSet()
                .contains(DefEntityCondition.getDefEntity(entityTwo)));
        assertTrue(task.getPreConditionSet()
                .contains(DefEntityCondition.getDefEntity(entityThree)));
        assertTrue(task.getPreConditionSet()
                .contains(DefAttributeCondition.getDefAttribute(attributeTwo)));
        assertEquals(0, task.getMultiplicityInvariantSet().size());
        assertEquals(1, task.getRuleInvariantSet().size());
        assertTrue(task.getRuleInvariantSet().contains(ruleTwo));
        assertTrue(taskModel.checkModel());
    }

    @Test
    public void successWithOutMultiplicitySecond() {
        taskOne.delete();

        Set<Condition> postConditions = new HashSet<Condition>();
        postConditions.add(DefEntityCondition.getDefEntity(entityOne));
        postConditions.add(DefAttributeCondition.getDefAttribute(attributeOne));
        postConditions.add(DefAttributeCondition.getDefAttribute(attributeTwo));
        Task task = taskModel.addTask(NEW_TASK_NAME, "Description",
                postConditions);

        assertEquals(NEW_TASK_NAME, task.getName());
        assertEquals(3, task.getPostConditionSet().size());
        assertTrue(taskModel.checkModel());
    }

    @Test
    public void successWithMultiplicity() {
        taskTwo.delete();

        Set<Condition> postConditions = new HashSet<Condition>();
        postConditions.add(DefEntityCondition.getDefEntity(entityTwo));
        postConditions.add(DefEntityCondition.getDefEntity(entityThree));
        Task task = taskModel.addTask(NEW_TASK_NAME, "Description",
                postConditions);

        assertEquals(NEW_TASK_NAME, task.getName());
        assertEquals(2, task.getPostConditionSet().size());

        assertEquals(2, task.getMultiplicityInvariantSet().size());
        assertTrue(task.getMultiplicityInvariantSet().contains(MulCondition
                .getMulCondition(relation, relation.getRoleNameOne())));
        assertTrue(task.getMultiplicityInvariantSet().contains(MulCondition
                .getMulCondition(relation, relation.getRoleNameTwo())));

        assertTrue(taskModel.checkModel());
    }

}
