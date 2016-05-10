package pt.ist.socialsoftware.blendedworkflow.domain.activitymodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
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
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class ExtractActivityTest extends TeardownRollbackTest {
	private static final String DESCRIPTION = "Description";
	private static final String TASK_ONE = "TaskOne";
	private static final String TASK_TWO = "TaskTwo";
	private static final String TASK_THREE = "TaskThree";
	private static final String NEW_TASK_NAME = "NewTaskName";
	private static final String ENTITY_ONE_NAME = "EntityOne";
	private static final String ENTITY_TWO_NAME = "EntityTwo";
	private static final String ENTITY_THREE_NAME = "EntityThree";
	private static final String ATTRIBUTE_ONE_NAME = "att1";
	private static final String ATTRIBUTE_TWO_NAME = "att2";
	private static final String ATTRIBUTE_THREE_NAME = "att3";
	private static final String ATTRIBUTE_FOUR_NAME = "att4";
	private static final String ATTRIBUTE_FIVE_NAME = "att5";
	private static final String ROLENAME_ONE = "theOne";
	private static final String ROLENAME_TWO = "theTwo";
	private static final String DEPENDENCE_PATH_ONE = ENTITY_TWO_NAME + "." + ROLENAME_ONE + "." + ATTRIBUTE_TWO_NAME;
	private static final String RULE_ONE_NAME = "ruleOne";
	private static final String RULE_TWO_NAME = "ruleTwo";

	Specification spec;
	Entity entityOne;
	Entity entityTwo;
	Entity entityThree;
	Attribute attributeOne;
	Attribute attributeTwo;
	Attribute attributeThree;
	Attribute attributeFour;
	Attribute attributeFive;
	RelationBW relation;
	Rule ruleOne;
	Rule ruleTwo;

	ActivityModel taskModel;
	Activity taskOne;
	Activity taskTwo;
	Activity taskThree;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME, false);
		attributeOne = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_ONE_NAME, AttributeType.NUMBER, true,
				false, false);
		attributeTwo = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_TWO_NAME, AttributeType.NUMBER, true,
				false, false);

		entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME, false);
		attributeThree = new Attribute(spec.getDataModel(), entityTwo, ATTRIBUTE_THREE_NAME, AttributeType.BOOLEAN,
				true, false, false);

		relation = new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		entityThree = new Entity(spec.getDataModel(), ENTITY_THREE_NAME, false);
		attributeFour = new Attribute(spec.getDataModel(), entityThree, ATTRIBUTE_FIVE_NAME, AttributeType.NUMBER,
				false, false, false);
		attributeFive = new Attribute(spec.getDataModel(), entityThree, ATTRIBUTE_FOUR_NAME, AttributeType.NUMBER,
				false, false, false);

		new Dependence(spec.getDataModel(), attributeThree, DEPENDENCE_PATH_ONE);

		new Dependence(spec.getDataModel(), attributeThree, DEPENDENCE_PATH_ONE);

		ruleOne = new Rule(entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		ruleTwo = new Rule(entityThree, RULE_TWO_NAME,
				new Comparison(new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FIVE_NAME),
						new AttributeValueExpression(spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOUR_NAME),
						ComparisonOperator.EQUAL));

		spec.getConditionModel().generateConditions();

		taskModel = spec.getActivityModel();

		taskOne = new Activity(spec.getActivityModel(), TASK_ONE, DESCRIPTION);
		taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(entityOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeOne));
		taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeTwo));
		taskOne.addRuleInvariant(ruleOne);

		taskTwo = new Activity(spec.getActivityModel(), TASK_TWO, DESCRIPTION);
		taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(spec, entityOne.getName()));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(entityTwo));
		taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(entityThree));
		taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relation, relation.getRoleNameOne()));
		taskTwo.addMultiplicityInvariant(MulCondition.getMulCondition(relation, relation.getRoleNameTwo()));

		taskThree = new Activity(spec.getActivityModel(), TASK_THREE, DESCRIPTION);
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, entityTwo.getName()));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, entityThree.getName()));
		taskThree.addPreCondition(DefPathCondition.getDefPathCondition(spec, DEPENDENCE_PATH_ONE));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeThree));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeFour));
		taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(attributeFive));
		taskThree.addRuleInvariant(ruleTwo);
	}

	@Test
	public void extractEmptyPostCodition() {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
		try {
			taskModel.extractActivity(taskOne, NEW_TASK_NAME, DESCRIPTION, postConditionSet);
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals(3, taskModel.getActivitySet().size());
		}
	}

	@Test
	public void extractDefEntity() {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(entityOne));
		Activity task = taskModel.extractActivity(taskOne, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, taskModel.getActivitySet().size());
		assertTrue(taskModel.getActivity(TASK_ONE).getPreConditionSet().stream().map(d -> d.getPath().getValue())
				.collect(Collectors.toSet())
				.contains(DefEntityCondition.getDefEntityCondition(entityOne).getPath().getValue()));
		assertEquals(0, task.getMultiplicityInvariantSet().size());

		assertEquals(2, taskModel.getActivity(TASK_TWO).getMultiplicityInvariantSet().size());

		task.checkConsistency();
		taskModel.getActivity(TASK_ONE).checkConsistency();
		taskModel.checkModel();
	}

	@Test
	public void extractDefAttribute() {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
		postConditionSet.add(DefAttributeCondition.getDefAttributeCondition(attributeOne));
		postConditionSet.add(DefAttributeCondition.getDefAttributeCondition(attributeTwo));
		Activity task = taskModel.extractActivity(taskOne, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, taskModel.getActivitySet().size());
		assertTrue(task.getRuleInvariantSet().contains(ruleOne));

		task.checkConsistency();
		taskModel.getActivity(TASK_ONE).checkConsistency();
		taskModel.checkModel();
	}

	@Test
	public void extractWithMultiplicityImpact() {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(entityTwo));
		Activity task = taskModel.extractActivity(taskTwo, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, taskModel.getActivitySet().size());
		// assertTrue(task.getMultiplicityInvariantSet()
		// .contains(MulCondition.getMulCondition(relation,
		// relation.getRoleNameOne())));
		// assertTrue(task.getMultiplicityInvariantSet()
		// .contains(MulCondition.getMulCondition(relation,
		// relation.getRoleNameTwo())));
		assertEquals(2, taskModel.getActivity(NEW_TASK_NAME).getMultiplicityInvariantSet().size()
				+ taskModel.getActivity(TASK_ONE).getMultiplicityInvariantSet().size());
		assertTrue(taskModel.getActivity(NEW_TASK_NAME).getMultiplicityInvariantSet().size() == 0
				|| taskModel.getActivity(TASK_ONE).getMultiplicityInvariantSet().size() == 0);
		assertEquals(0, taskModel.getActivity(TASK_TWO).getMultiplicityInvariantSet().size());

		task.checkConsistency();
		taskModel.getActivity(TASK_TWO).checkConsistency();
		taskModel.checkModel();
	}

	@Test
	public void extractWithDependence() {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
		postConditionSet.add(DefAttributeCondition.getDefAttributeCondition(attributeThree));
		Activity task = taskModel.extractActivity(taskThree, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, taskModel.getActivitySet().size());
		assertTrue(task.getPreConditionSet().stream().map(d -> d.getPath().getValue()).collect(Collectors.toSet())
				.contains(DEPENDENCE_PATH_ONE));
		assertFalse(taskModel.getActivity(TASK_TWO).getPreConditionSet().stream().map(d -> d.getPath().getValue())
				.collect(Collectors.toSet()).contains(DEPENDENCE_PATH_ONE));

		task.checkConsistency();
		taskModel.getActivity(TASK_THREE).checkConsistency();
		taskModel.checkModel();
	}

	@Test
	public void extractAndSequenceConditionStaysInSource() {
		taskTwo.addSequenceCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_TWO_NAME + "." + ROLENAME_ONE));
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(entityTwo));
		Activity task = taskModel.extractActivity(taskTwo, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(1, task.getSequenceConditionSet().size());
		assertEquals(0, taskModel.getActivity(TASK_TWO).getSequenceConditionSet().size());

		taskModel.checkModel();
	}

	@Test
	public void extractAndSequenceConditionMovesToTarget() {
		taskTwo.addSequenceCondition(DefPathCondition.getDefPathCondition(spec, ENTITY_TWO_NAME + "." + ROLENAME_ONE));
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(entityThree));
		Activity task = taskModel.extractActivity(taskTwo, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(0, task.getSequenceConditionSet().size());
		assertEquals(1, taskModel.getActivity(TASK_TWO).getSequenceConditionSet().size());

		taskModel.checkModel();
	}

}
