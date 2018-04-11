package pt.ist.socialsoftware.blendedworkflow.core.domain.activitymodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec");

		this.entityOne = new Entity(this.spec.getDataModel(), ENTITY_ONE_NAME, false);
		this.attributeOne = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_ONE_NAME,
				AttributeType.NUMBER, true);
		this.attributeTwo = new Attribute(this.spec.getDataModel(), this.entityOne, ATTRIBUTE_TWO_NAME,
				AttributeType.NUMBER, true);

		this.entityTwo = new Entity(this.spec.getDataModel(), ENTITY_TWO_NAME, false);
		this.attributeThree = new Attribute(this.spec.getDataModel(), this.entityTwo, ATTRIBUTE_THREE_NAME,
				AttributeType.BOOLEAN, true);

		this.relation = new RelationBW(this.spec.getDataModel(), "name", this.entityOne, ROLENAME_ONE, Cardinality.ONE,
				false, this.entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);

		this.entityThree = new Entity(this.spec.getDataModel(), ENTITY_THREE_NAME, false);
		this.attributeFour = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_FIVE_NAME,
				AttributeType.NUMBER, false);
		this.attributeFive = new Attribute(this.spec.getDataModel(), this.entityThree, ATTRIBUTE_FOUR_NAME,
				AttributeType.NUMBER, false);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH_ONE);

		new Dependence(this.spec.getDataModel(), this.attributeThree, DEPENDENCE_PATH_ONE);

		this.ruleOne = new Rule(this.entityOne, RULE_ONE_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_ONE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_ONE_NAME + "." + ATTRIBUTE_TWO_NAME),
						ComparisonOperator.EQUAL));

		this.ruleTwo = new Rule(this.entityThree, RULE_TWO_NAME,
				new Comparison(new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FIVE_NAME),
						new AttributeValueExpression(this.spec, ENTITY_THREE_NAME + "." + ATTRIBUTE_FOUR_NAME),
						ComparisonOperator.EQUAL));

		this.spec.getConditionModel().generateConditions();

		this.taskModel = this.spec.getActivityModel();

		this.taskOne = new Activity(this.spec.getActivityModel(), TASK_ONE, DESCRIPTION);
		this.taskOne.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		this.taskOne.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		this.taskOne.addRuleInvariant(this.ruleOne);

		this.taskTwo = new Activity(this.spec.getActivityModel(), TASK_TWO, DESCRIPTION);
		this.taskTwo.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, this.entityOne.getName()));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		this.taskTwo.addPostCondition(DefEntityCondition.getDefEntityCondition(this.entityThree));
		this.taskTwo
				.addMultiplicityInvariant(MulCondition.getMulCondition(this.relation, this.relation.getRolenameOne()));
		this.taskTwo
				.addMultiplicityInvariant(MulCondition.getMulCondition(this.relation, this.relation.getRolenameTwo()));

		this.taskThree = new Activity(this.spec.getActivityModel(), TASK_THREE, DESCRIPTION);
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, this.entityTwo.getName()));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, this.entityThree.getName()));
		this.taskThree.addPreCondition(DefPathCondition.getDefPathCondition(this.spec, DEPENDENCE_PATH_ONE));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFour));
		this.taskThree.addPostCondition(DefAttributeCondition.getDefAttributeCondition(this.attributeFive));
		this.taskThree.addRuleInvariant(this.ruleTwo);
	}

	@Test
	public void extractEmptyPostCodition() {
		Set<DefProductCondition> postConditionSet = new HashSet<>();
		try {
			this.taskModel.extractActivity(this.taskOne, NEW_TASK_NAME, DESCRIPTION, postConditionSet);
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CANNOT_ADD_TASK, bwe.getError());
			assertEquals(3, this.taskModel.getActivitySet().size());
		}
	}

	@Test
	public void extractDefEntity() {
		Set<DefProductCondition> postConditionSet = new HashSet<>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(this.entityOne));
		Activity task = this.taskModel.extractActivity(this.taskOne, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, this.taskModel.getActivitySet().size());
		assertTrue(this.taskModel.getActivity(TASK_ONE).getPreConditionSet().stream().map(d -> d.getPath().getValue())
				.collect(Collectors.toSet())
				.contains(DefEntityCondition.getDefEntityCondition(this.entityOne).getPath().getValue()));
		assertEquals(0, task.getMultiplicityInvariantSet().size());

		assertEquals(2, this.taskModel.getActivity(TASK_TWO).getMultiplicityInvariantSet().size());

		task.checkConsistency();
		this.taskModel.getActivity(TASK_ONE).checkConsistency();
		this.taskModel.checkModel();
	}

	@Test
	public void extractDefAttribute() {
		Set<DefProductCondition> postConditionSet = new HashSet<>();
		postConditionSet.add(DefAttributeCondition.getDefAttributeCondition(this.attributeOne));
		postConditionSet.add(DefAttributeCondition.getDefAttributeCondition(this.attributeTwo));
		Activity task = this.taskModel.extractActivity(this.taskOne, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, this.taskModel.getActivitySet().size());
		assertTrue(task.getRuleInvariantSet().contains(this.ruleOne));

		task.checkConsistency();
		this.taskModel.getActivity(TASK_ONE).checkConsistency();
		this.taskModel.checkModel();
	}

	@Test
	public void extractWithMultiplicityImpact() {
		Set<DefProductCondition> postConditionSet = new HashSet<>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		Activity task = this.taskModel.extractActivity(this.taskTwo, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, this.taskModel.getActivitySet().size());
		// assertTrue(task.getMultiplicityInvariantSet()
		// .contains(MulCondition.getMulCondition(relation,
		// relation.getRoleNameOne())));
		// assertTrue(task.getMultiplicityInvariantSet()
		// .contains(MulCondition.getMulCondition(relation,
		// relation.getRoleNameTwo())));
		assertEquals(2, this.taskModel.getActivity(NEW_TASK_NAME).getMultiplicityInvariantSet().size()
				+ this.taskModel.getActivity(TASK_ONE).getMultiplicityInvariantSet().size());
		assertTrue(this.taskModel.getActivity(NEW_TASK_NAME).getMultiplicityInvariantSet().size() == 0
				|| this.taskModel.getActivity(TASK_ONE).getMultiplicityInvariantSet().size() == 0);
		assertEquals(0, this.taskModel.getActivity(TASK_TWO).getMultiplicityInvariantSet().size());

		task.checkConsistency();
		this.taskModel.getActivity(TASK_TWO).checkConsistency();
		this.taskModel.checkModel();
	}

	@Test
	public void extractWithDependence() {
		Set<DefProductCondition> postConditionSet = new HashSet<>();
		postConditionSet.add(DefAttributeCondition.getDefAttributeCondition(this.attributeThree));
		Activity task = this.taskModel.extractActivity(this.taskThree, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(4, this.taskModel.getActivitySet().size());
		assertTrue(task.getPreConditionSet().stream().map(d -> d.getPath().getValue()).collect(Collectors.toSet())
				.contains(DEPENDENCE_PATH_ONE));
		assertFalse(this.taskModel.getActivity(TASK_TWO).getPreConditionSet().stream().map(d -> d.getPath().getValue())
				.collect(Collectors.toSet()).contains(DEPENDENCE_PATH_ONE));

		task.checkConsistency();
		this.taskModel.getActivity(TASK_THREE).checkConsistency();
		this.taskModel.checkModel();
	}

	@Test
	public void extractAndSequenceConditionStaysInSource() {
		this.taskTwo.addSequenceCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENTITY_TWO_NAME + "." + ROLENAME_ONE));
		Set<DefProductCondition> postConditionSet = new HashSet<>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(this.entityTwo));
		Activity task = this.taskModel.extractActivity(this.taskTwo, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(1, task.getSequenceConditionSet().size());
		assertEquals(0, this.taskModel.getActivity(TASK_TWO).getSequenceConditionSet().size());

		this.taskModel.checkModel();
	}

	@Test
	public void extractAndSequenceConditionMovesToTarget() {
		this.taskTwo.addSequenceCondition(
				DefPathCondition.getDefPathCondition(this.spec, ENTITY_TWO_NAME + "." + ROLENAME_ONE));
		Set<DefProductCondition> postConditionSet = new HashSet<>();
		postConditionSet.add(DefEntityCondition.getDefEntityCondition(this.entityThree));
		Activity task = this.taskModel.extractActivity(this.taskTwo, NEW_TASK_NAME, DESCRIPTION, postConditionSet);

		assertEquals(0, task.getSequenceConditionSet().size());
		assertEquals(1, this.taskModel.getActivity(TASK_TWO).getSequenceConditionSet().size());

		this.taskModel.checkModel();
	}

}
