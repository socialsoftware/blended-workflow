package org.blended.common.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.dto.AttributeDTO;
import org.blended.common.repository.resttemplate.dto.DefAttributeConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefEntityConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefProductConditionSetDTO;
import org.blended.common.repository.resttemplate.dto.DependenceDTO;
import org.blended.common.repository.resttemplate.dto.EntityDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO.Type;
import org.blended.common.repository.resttemplate.dto.GoalDTO;
import org.blended.common.repository.resttemplate.dto.MulConditionDTO;
import org.blended.common.repository.resttemplate.dto.RelationDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.common.repository.resttemplate.dto.SpecDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeAndExtractGoalTest {
	private static final String RULE_NAME = "ruleName";
	private static final String ROLENAME_TWO = "entityTwo";
	private static final String ROLENAME_ONE = "entityOne";
	private static final String ATT_THREE = "att3";
	private static final String ATT_TWO = "att2";
	private static final String ATT_ONE = "att1";
	private static final String ENTITY_TWO = "EntityTwo";
	private static final String ENTITY_ONE = "EntityOne";
	private static final String SUB_GOAL_ONE = "SubGoalOne";
	private static final String SUB_GOAL_TWO = "SubGoalTwo";
	private static final String SUB_GOAL_TWO_ONE = "SubGoalTwoOne";
	private static final String SUB_GOAL_THREE = "SubGoalThree";
	private static final String TOP_GOAL = "TopGoal";
	private static final String TEST_SPEC_ID = "TestSpecId";

	private static Logger log = LoggerFactory.getLogger(MergeAndExtractGoalTest.class);

	private CommonInterface ci;

	@Before
	public void setUp() {
		log.debug("setUp");

		ci = CommonInterface.getInstance();

		try {
			ci.getSpecBySpecId(TEST_SPEC_ID);
			ci.deleteSpecification(TEST_SPEC_ID);
		} catch (RepositoryException re) {
			log.debug("getSpec: {}", re.getMessage());
		}
		ci.createSpec(new SpecDTO(TEST_SPEC_ID, "spec model"));

		EntityDTO entityDTO = ci.createEntity(new EntityDTO(TEST_SPEC_ID, ENTITY_ONE, false));
		ci.createEntityAchieveCondition(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_ONE));

		AttributeDTO attOneDTO = ci
				.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityDTO.getExtId(), null, ATT_ONE, "Number", false));

		ci.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityDTO.getExtId(), null, ATT_TWO, "Number", false));

		entityDTO = ci.createEntity(new EntityDTO(TEST_SPEC_ID, ENTITY_TWO, false));
		ci.createEntityAchieveCondition(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO));

		ci.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityDTO.getExtId(), null, ATT_THREE, "String", false));

		ci.createRelation(new RelationDTO(TEST_SPEC_ID, "RelationName", ENTITY_ONE, ROLENAME_ONE, "0..1", ENTITY_TWO,
				ROLENAME_TWO, "*"));

		ci.createDependence(new DependenceDTO(TEST_SPEC_ID, attOneDTO.getEntityName() + "." + attOneDTO.getName(),
				ENTITY_ONE + "." + ATT_TWO));

		ci.createRule(new RuleDTO(TEST_SPEC_ID, RULE_NAME,
				new ExpressionDTO(TEST_SPEC_ID, ExpressionDTO.Type.EQUAL,
						new ExpressionDTO(TEST_SPEC_ID, Type.ATT_VALUE, ENTITY_ONE + "." + ATT_ONE),
						new ExpressionDTO(TEST_SPEC_ID, Type.ATT_VALUE, ENTITY_ONE + "." + ATT_TWO))));
	}

	@After
	public void tearDown() {
		log.debug("tearDown");
		try {
			ci.deleteSpecification(TEST_SPEC_ID);
		} catch (RepositoryException re) {
			log.debug("createSpec: {}", re.getMessage());
		}
	}

	@Test
	public void mergeAndExtractGoals() {
		// create goals
		ci.createGoal(new GoalDTO(TEST_SPEC_ID, TOP_GOAL));

		GoalDTO goalDTO = ci.getGoalByName(TEST_SPEC_ID, TOP_GOAL);
		assertEquals(TEST_SPEC_ID, goalDTO.getSpecId());
		assertEquals(TOP_GOAL, goalDTO.getName());

		goalDTO = ci.createGoal(new GoalDTO(TEST_SPEC_ID, SUB_GOAL_ONE));
		ci.addSubGoal(TOP_GOAL, goalDTO);

		goalDTO = ci.createGoal(new GoalDTO(TEST_SPEC_ID, SUB_GOAL_TWO));
		ci.addSubGoal(TOP_GOAL, goalDTO);

		goalDTO = ci.createGoal(new GoalDTO(TEST_SPEC_ID, SUB_GOAL_TWO_ONE));
		ci.addSubGoal(SUB_GOAL_TWO, goalDTO);

		// add subgoals
		Set<GoalDTO> goalsDTO = ci.getSubGoals(TEST_SPEC_ID, TOP_GOAL);
		assertEquals(2, goalsDTO.size());
		assertTrue(goalsDTO.stream().map((g) -> g.getName()).anyMatch((n) -> n.equals(SUB_GOAL_ONE)));
		assertTrue(goalsDTO.stream().map((g) -> g.getName()).anyMatch((n) -> n.equals(SUB_GOAL_TWO)));

		// add suc conditions
		ci.associateEntityToGoalSuccess(TEST_SPEC_ID, TOP_GOAL, ENTITY_ONE);

		Set<DefEntityConditionDTO> defsEnt = ci.getGoalSuccessEntitySet(TEST_SPEC_ID, TOP_GOAL);
		assertEquals(1, defsEnt.size());
		assertEquals(Stream.of(ENTITY_ONE).sorted().collect(Collectors.joining(",")),
				defsEnt.stream().map((def) -> def.getEntityName()).sorted().collect(Collectors.joining(",")));

		ci.associateAttributeToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_ONE, ENTITY_ONE + "." + ATT_ONE);

		ci.associateEntityToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_TWO, ENTITY_TWO);

		ci.associateAttributeToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_TWO, ENTITY_ONE + "." + ATT_TWO);

		defsEnt = ci.getGoalSuccessEntitySet(TEST_SPEC_ID, SUB_GOAL_TWO);
		assertEquals(1, defsEnt.size());
		assertEquals(Stream.of(ENTITY_TWO).collect(Collectors.joining(",")),
				defsEnt.stream().map((def) -> def.getEntityName()).sorted().collect(Collectors.joining(",")));
		Set<DefAttributeConditionDTO> defsAtt = ci.getGoalSuccessAttributeSet(TEST_SPEC_ID, SUB_GOAL_TWO);
		assertEquals(1, defsAtt.size());
		assertEquals(ATT_TWO,
				ci.getAttribute(TEST_SPEC_ID, defsAtt.stream().findAny().get().getAttributeExtId()).getName());

		ci.associateAttributeToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_TWO_ONE, ENTITY_TWO + "." + ATT_THREE);

		// add act conditions
		ci.associateAttributeToGoalActivation(TEST_SPEC_ID, SUB_GOAL_ONE, ENTITY_ONE + "." + ATT_TWO);
		defsAtt = ci.getGoalActivationAttributeSet(TEST_SPEC_ID, SUB_GOAL_ONE);
		assertEquals(1, defsAtt.size());
		assertEquals(ATT_TWO,
				ci.getAttribute(TEST_SPEC_ID, defsAtt.stream().findAny().get().getAttributeExtId()).getName());

		// add mul conditions
		ci.associateMulToGoalInvariant(TEST_SPEC_ID, TOP_GOAL,
				new MulConditionDTO(TEST_SPEC_ID, ENTITY_ONE + "." + ROLENAME_TWO, "*"));

		ci.associateMulToGoalInvariant(TEST_SPEC_ID, SUB_GOAL_TWO,
				new MulConditionDTO(TEST_SPEC_ID, ENTITY_TWO + "." + ROLENAME_ONE, "0..1"));

		Set<MulConditionDTO> mulsDTO = ci.getGoalMulInvSet(TEST_SPEC_ID, TOP_GOAL);
		assertEquals(1, mulsDTO.size());
		assertEquals(ENTITY_ONE + "." + ROLENAME_TWO,
				mulsDTO.stream().map((m) -> m.getRolePath()).collect(Collectors.joining()));

		// add rule condition
		ci.associateRuleToGoalInvariant(TEST_SPEC_ID, SUB_GOAL_ONE, new RuleDTO(TEST_SPEC_ID, RULE_NAME));
		Set<RuleDTO> rulesDTO = ci.getGoalRuleInvSet(TEST_SPEC_ID, SUB_GOAL_ONE);
		assertEquals(1, rulesDTO.size());
		assertEquals(RULE_NAME, rulesDTO.stream().map((r) -> r.getName()).collect(Collectors.joining()));

		ci.associateRuleToGoalInvariant(TEST_SPEC_ID, SUB_GOAL_TWO, new RuleDTO(TEST_SPEC_ID, RULE_NAME));

		// fail to merge parent and child goals due to a conflict
		try {
			goalDTO = ci.mergeGoals(TEST_SPEC_ID, SUB_GOAL_ONE + SUB_GOAL_TWO, TOP_GOAL, SUB_GOAL_ONE);
		} catch (RepositoryException re) {
			assertEquals("UNMERGEABLE_GOALS", re.getError().getType());
		}

		// merge siblings goalOne + golaTwo
		goalDTO = ci.mergeGoals(TEST_SPEC_ID, SUB_GOAL_ONE + SUB_GOAL_TWO, SUB_GOAL_ONE, SUB_GOAL_TWO);

		assertEquals(SUB_GOAL_ONE + SUB_GOAL_TWO, goalDTO.getName());

		// get super goal
		GoalDTO parentGoalDTO = ci.getParentGoal(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(TOP_GOAL, parentGoalDTO.getName());

		// get sub goals
		Set<GoalDTO> subGoals = ci.getSubGoals(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(SUB_GOAL_TWO_ONE, subGoals.stream().map((g) -> g.getName()).collect(Collectors.joining()));

		// get activation entity achieve conditions
		defsEnt = ci.getGoalActivationEntitySet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, defsEnt.size());

		// get activation attribute achieve conditions
		defsAtt = ci.getGoalActivationAttributeSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, defsAtt.size());

		// get success entity achieve conditions
		defsEnt = ci.getGoalSuccessEntitySet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, defsEnt.size());
		assertEquals(Stream.of(ENTITY_TWO).collect(Collectors.joining()),
				defsEnt.stream().map((def) -> def.getEntityName()).collect(Collectors.joining()));

		// get success attribute achieve conditions
		defsAtt = ci.getGoalSuccessAttributeSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(2, defsAtt.size());
		assertEquals(Stream.of(ATT_ONE, ATT_TWO).sorted().collect(Collectors.joining()),
				defsAtt.stream().map((def) -> ci.getAttribute(TEST_SPEC_ID, def.getAttributeExtId()).getName()).sorted()
						.collect(Collectors.joining()));

		// get multiplicity invariants
		mulsDTO = ci.getGoalMulInvSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, mulsDTO.size());
		assertEquals(ENTITY_TWO + "." + ROLENAME_ONE,
				mulsDTO.stream().map((m) -> m.getRolePath()).collect(Collectors.joining()));

		// get rule invariants
		rulesDTO = ci.getGoalRuleInvSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, rulesDTO.size());
		assertEquals(RULE_NAME, rulesDTO.stream().map((r) -> r.getName()).collect(Collectors.joining()));

		// merge parent and child - returns the top goal (changed): after there
		// are 2 goals Top and goalTwoOne
		goalDTO = ci.mergeGoals(TEST_SPEC_ID, TOP_GOAL, TOP_GOAL, goalDTO.getName());

		assertEquals(TOP_GOAL, goalDTO.getName());

		// extract childOne from parent: after there are 3 goals Top, goalOne
		// and goalTwoOne
		Set<DefEntityConditionDTO> defEnts = new HashSet<DefEntityConditionDTO>();

		Set<DefAttributeConditionDTO> defAtts = new HashSet<DefAttributeConditionDTO>();
		defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID, ENTITY_ONE + "." + ATT_ONE));

		DefProductConditionSetDTO successCondition = new DefProductConditionSetDTO(defEnts, defAtts);

		goalDTO = ci.extractChildGoal(TEST_SPEC_ID, SUB_GOAL_ONE, TOP_GOAL, successCondition);

		// get super goal
		parentGoalDTO = ci.getParentGoal(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(TOP_GOAL, parentGoalDTO.getName());

		// get sub goals
		subGoals = ci.getSubGoals(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, subGoals.size());

		// get activation entity achieve conditions
		defsEnt = ci.getGoalActivationEntitySet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, defsEnt.size());

		// get activation attribute achieve conditions
		defsAtt = ci.getGoalActivationAttributeSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, defsAtt.size());
		assertEquals(ATT_TWO,
				ci.getAttribute(TEST_SPEC_ID, defsAtt.stream().findFirst().get().getAttributeExtId()).getName());

		// get success entity achieve conditions
		defsEnt = ci.getGoalSuccessEntitySet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, defsEnt.size());

		// get success attribute achieve conditions
		defsAtt = ci.getGoalSuccessAttributeSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, defsAtt.size());
		assertEquals(ATT_ONE,
				ci.getAttribute(TEST_SPEC_ID, defsAtt.stream().findFirst().get().getAttributeExtId()).getName());

		// get multiplicity invariants
		mulsDTO = ci.getGoalMulInvSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, mulsDTO.size());

		// get rule invariants
		rulesDTO = ci.getGoalRuleInvSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, rulesDTO.size());
		assertEquals(RULE_NAME, rulesDTO.stream().map((r) -> r.getName()).collect(Collectors.joining()));

		// merge childTwoOne with topGoal
		ci.mergeGoals(TEST_SPEC_ID, TOP_GOAL, SUB_GOAL_TWO_ONE, TOP_GOAL);

		// extract childTwo + childTwoOne from parent
		defEnts.clear();
		defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO));

		defAtts.clear();
		defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID, ENTITY_ONE + "." + ATT_TWO));
		defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID, ENTITY_TWO + "." + ATT_THREE));

		ci.extractChildGoal(TEST_SPEC_ID, SUB_GOAL_TWO, TOP_GOAL, new DefProductConditionSetDTO(defEnts, defAtts));

		// extract childThree from childTwo
		defEnts.clear();
		defEnts.add(new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO));

		defAtts.clear();
		defAtts.add(new DefAttributeConditionDTO(TEST_SPEC_ID, ENTITY_TWO + "." + ATT_THREE));

		goalDTO = ci.extractSiblingGoal(TEST_SPEC_ID, SUB_GOAL_THREE, SUB_GOAL_TWO,
				new DefProductConditionSetDTO(defEnts, defAtts));
		assertEquals(SUB_GOAL_THREE, goalDTO.getName());

		// get super goal
		parentGoalDTO = ci.getParentGoal(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(TOP_GOAL, parentGoalDTO.getName());

		// get sub goals
		subGoals = ci.getSubGoals(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, subGoals.size());

		// get activation entity achieve conditions
		defsEnt = ci.getGoalActivationEntitySet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, defsEnt.size());

		// get activation attribute achieve conditions
		defsAtt = ci.getGoalActivationAttributeSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, defsAtt.size());

		// get success entity achieve conditions
		defsEnt = ci.getGoalSuccessEntitySet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, defsEnt.size());
		assertEquals(ENTITY_TWO, defsEnt.stream().findFirst().get().getEntityName());

		// get success attribute achieve conditions
		defsAtt = ci.getGoalSuccessAttributeSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, defsAtt.size());
		assertEquals(ATT_THREE,
				ci.getAttribute(TEST_SPEC_ID, defsAtt.stream().findFirst().get().getAttributeExtId()).getName());

		// get multiplicity invariants
		mulsDTO = ci.getGoalMulInvSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(1, mulsDTO.size());

		// get rule invariants
		rulesDTO = ci.getGoalRuleInvSet(TEST_SPEC_ID, goalDTO.getName());
		assertEquals(0, rulesDTO.size());

	}

}
