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
import org.blended.common.repository.resttemplate.dto.EntityDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO;
import org.blended.common.repository.resttemplate.dto.GoalDTO;
import org.blended.common.repository.resttemplate.dto.MulConditionDTO;
import org.blended.common.repository.resttemplate.dto.RelationDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.common.repository.resttemplate.dto.SpecDTO;
import org.blended.common.repository.resttemplate.dto.SuccessConditionDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoalModelTest {
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
    private static final String TOP_GOAL = "TopGoal";
    private static final String TEST_SPEC_ID = "TestSpecId";

    private static Logger log = LoggerFactory.getLogger(GoalModelTest.class);

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

        EntityDTO entityVO = ci
                .createEntity(new EntityDTO(TEST_SPEC_ID, ENTITY_ONE, false));
        ci.createEntityAchieveCondition(
                new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_ONE, false));

        ci.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityVO.getExtId(),
                null, ATT_ONE, "Boolean", false));

        ci.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityVO.getExtId(),
                null, ATT_TWO, "Number", false));

        entityVO = ci
                .createEntity(new EntityDTO(TEST_SPEC_ID, ENTITY_TWO, false));
        ci.createEntityAchieveCondition(
                new DefEntityConditionDTO(TEST_SPEC_ID, ENTITY_TWO, false));

        ci.createAttribute(new AttributeDTO(TEST_SPEC_ID, entityVO.getExtId(),
                null, ATT_THREE, "String", false));

        ci.createRelation(
                new RelationDTO(TEST_SPEC_ID, "RelationName", ENTITY_ONE,
                        ROLENAME_ONE, "0..1", ENTITY_TWO, ROLENAME_TWO, "*"));

        ci.createRule(new RuleDTO(TEST_SPEC_ID, RULE_NAME, new ExpressionDTO(
                TEST_SPEC_ID, ExpressionDTO.Type.BOOL, "True")));
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
    public void createGoalModel() {
        // create goals
        ci.createGoal(new GoalDTO(TEST_SPEC_ID, TOP_GOAL));

        GoalDTO goalVO = ci.getGoalByName(TEST_SPEC_ID, TOP_GOAL);
        assertEquals(TEST_SPEC_ID, goalVO.getSpecId());
        assertEquals(TOP_GOAL, goalVO.getName());

        goalVO = ci.createGoal(new GoalDTO(TEST_SPEC_ID, SUB_GOAL_ONE));
        ci.addSubGoal(TOP_GOAL, goalVO);

        goalVO = ci.createGoal(new GoalDTO(TEST_SPEC_ID, SUB_GOAL_TWO));
        ci.addSubGoal(TOP_GOAL, goalVO);

        goalVO = ci.createGoal(new GoalDTO(TEST_SPEC_ID, SUB_GOAL_TWO_ONE));
        ci.addSubGoal(SUB_GOAL_TWO, goalVO);

        // add subgoals
        Set<GoalDTO> goalsVO = ci.getSubGoals(TEST_SPEC_ID, TOP_GOAL);
        assertEquals(2, goalsVO.size());
        assertTrue(goalsVO.stream().map((g) -> g.getName())
                .anyMatch((n) -> n.equals(SUB_GOAL_ONE)));
        assertTrue(goalsVO.stream().map((g) -> g.getName())
                .anyMatch((n) -> n.equals(SUB_GOAL_TWO)));

        // add suc conditions
        ci.associateEntityToGoalSuccess(TEST_SPEC_ID, TOP_GOAL, ENTITY_ONE);

        Set<DefEntityConditionDTO> defsEnt = ci
                .getGoalSuccessEntitySet(TEST_SPEC_ID, TOP_GOAL);
        assertEquals(1, defsEnt.size());
        assertEquals(
                Stream.of(ENTITY_ONE).sorted().collect(Collectors.joining(",")),
                defsEnt.stream().map((def) -> def.getEntityName()).sorted()
                        .collect(Collectors.joining(",")));

        Set<String> paths = new HashSet<String>();
        paths.add(ENTITY_ONE + "." + ATT_ONE);
        ci.associateAttributeToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_ONE, paths);

        ci.associateEntityToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_TWO, ENTITY_TWO);

        paths.clear();
        paths.add(ENTITY_ONE + "." + ATT_TWO);
        ci.associateAttributeToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_TWO, paths);

        defsEnt = ci.getGoalSuccessEntitySet(TEST_SPEC_ID, SUB_GOAL_TWO);
        assertEquals(1, defsEnt.size());
        assertEquals(Stream.of(ENTITY_TWO).collect(Collectors.joining(",")),
                defsEnt.stream().map((def) -> def.getEntityName()).sorted()
                        .collect(Collectors.joining(",")));
        Set<DefAttributeConditionDTO> defsAtt = ci
                .getGoalSuccessAttributeSet(TEST_SPEC_ID, SUB_GOAL_TWO);
        assertEquals(1, defsAtt.size());
        assertEquals(ATT_TWO,
                ci.getAttribute(TEST_SPEC_ID,
                        defsAtt.stream().findAny().get().getAttributeExtId())
                .getName());

        paths.clear();
        paths.add(ENTITY_TWO + "." + ATT_THREE);
        ci.associateAttributeToGoalSuccess(TEST_SPEC_ID, SUB_GOAL_TWO_ONE,
                paths);

        // add act conditions
        paths.clear();
        paths.add(ENTITY_ONE + "." + ATT_TWO);
        ci.associateAttributeToGoalActivation(TEST_SPEC_ID, SUB_GOAL_ONE,
                paths);
        defsAtt = ci.getGoalActivationAttributeSet(TEST_SPEC_ID, SUB_GOAL_ONE);
        assertEquals(1, defsAtt.size());
        assertEquals(ATT_TWO,
                ci.getAttribute(TEST_SPEC_ID,
                        defsAtt.stream().findAny().get().getAttributeExtId())
                .getName());

        // add mul conditions
        ci.associateMulToGoalInvariant(TEST_SPEC_ID, TOP_GOAL,
                new MulConditionDTO(TEST_SPEC_ID,
                        ENTITY_ONE + "." + ROLENAME_TWO, "*"));

        ci.associateMulToGoalInvariant(TEST_SPEC_ID, SUB_GOAL_TWO,
                new MulConditionDTO(TEST_SPEC_ID,
                        ENTITY_TWO + "." + ROLENAME_ONE, "0..1"));

        Set<MulConditionDTO> mulsVO = ci.getGoalMulInvSet(TEST_SPEC_ID,
                TOP_GOAL);
        assertEquals(1, mulsVO.size());
        assertEquals(ENTITY_ONE + "." + ROLENAME_TWO, mulsVO.stream()
                .map((m) -> m.getRolePath()).collect(Collectors.joining()));

        // add rule condition
        ci.associateRuleToGoalInvariant(TEST_SPEC_ID, SUB_GOAL_ONE,
                new RuleDTO(TEST_SPEC_ID, RULE_NAME));
        Set<RuleDTO> rulesVO = ci.getGoalRuleInvSet(TEST_SPEC_ID, SUB_GOAL_ONE);
        assertEquals(1, rulesVO.size());
        assertEquals(RULE_NAME, rulesVO.stream().map((r) -> r.getName())
                .collect(Collectors.joining()));

        ci.associateRuleToGoalInvariant(TEST_SPEC_ID, SUB_GOAL_TWO,
                new RuleDTO(TEST_SPEC_ID, RULE_NAME));

        // fail to merge parent and child goals due to a conflict
        try {
            goalVO = ci.mergeGoals(TEST_SPEC_ID, SUB_GOAL_ONE + SUB_GOAL_TWO,
                    TOP_GOAL, SUB_GOAL_ONE);
        } catch (RepositoryException re) {
            assertEquals("UNMERGEABLE_GOALS", re.getError().getType());
        }

        // merge siblings
        goalVO = ci.mergeGoals(TEST_SPEC_ID, SUB_GOAL_ONE + SUB_GOAL_TWO,
                SUB_GOAL_ONE, SUB_GOAL_TWO);

        assertEquals(SUB_GOAL_ONE + SUB_GOAL_TWO, goalVO.getName());

        // get super goal
        GoalDTO parentGoalVO = ci.getParentGoal(TEST_SPEC_ID, goalVO.getName());
        assertEquals(TOP_GOAL, parentGoalVO.getName());

        // get sub goals
        Set<GoalDTO> subGoals = ci.getSubGoals(TEST_SPEC_ID, goalVO.getName());
        assertEquals(SUB_GOAL_TWO_ONE, subGoals.stream().map((g) -> g.getName())
                .collect(Collectors.joining()));

        // get activation entity achieve conditions
        defsEnt = ci.getGoalActivationEntitySet(TEST_SPEC_ID, goalVO.getName());
        assertEquals(0, defsEnt.size());

        // get activation attribute achieve conditions
        defsAtt = ci.getGoalActivationAttributeSet(TEST_SPEC_ID,
                goalVO.getName());
        assertEquals(0, defsAtt.size());

        // get success entity achieve conditions
        defsEnt = ci.getGoalSuccessEntitySet(TEST_SPEC_ID, goalVO.getName());
        assertEquals(1, defsEnt.size());
        assertEquals(Stream.of(ENTITY_TWO).collect(Collectors.joining()),
                defsEnt.stream().map((def) -> def.getEntityName())
                        .collect(Collectors.joining()));

        // get success attribute achieve conditions
        defsAtt = ci.getGoalSuccessAttributeSet(TEST_SPEC_ID, goalVO.getName());
        assertEquals(2, defsAtt.size());
        assertEquals(
                Stream.of(ATT_ONE, ATT_TWO).sorted()
                        .collect(Collectors.joining()),
                defsAtt.stream()
                        .map((def) -> ci.getAttribute(TEST_SPEC_ID,
                                def.getAttributeExtId()).getName())
                        .sorted().collect(Collectors.joining()));

        // get multiplicity invariants
        mulsVO = ci.getGoalMulInvSet(TEST_SPEC_ID, goalVO.getName());
        assertEquals(1, mulsVO.size());
        assertEquals(ENTITY_TWO + "." + ROLENAME_ONE, mulsVO.stream()
                .map((m) -> m.getRolePath()).collect(Collectors.joining()));

        // get rule invariants
        rulesVO = ci.getGoalRuleInvSet(TEST_SPEC_ID, goalVO.getName());
        assertEquals(1, rulesVO.size());
        assertEquals(RULE_NAME, rulesVO.stream().map((r) -> r.getName())
                .collect(Collectors.joining()));

        // merge parent and child - returns the top goal (changed)
        goalVO = ci.mergeGoals(TEST_SPEC_ID, TOP_GOAL, TOP_GOAL,
                goalVO.getName());

        assertEquals(TOP_GOAL, goalVO.getName());

        Set<DefEntityConditionDTO> defEnts = new HashSet<DefEntityConditionDTO>();
        defEnts.add(new DefEntityConditionDTO("SSS", "EEE", true));

        Set<DefAttributeConditionDTO> defAtts = new HashSet<DefAttributeConditionDTO>();
        Set<String> pathx = new HashSet<String>();
        pathx.add("p1");
        pathx.add("p2");
        defAtts.add(new DefAttributeConditionDTO("SSS", pathx, true));

        SuccessConditionDTO successCondition = new SuccessConditionDTO();
        successCondition.setDefEnts(defEnts);
        successCondition.setDefAtts(defAtts);

        ci.extractSiblingGoal(TEST_SPEC_ID, "newGoal", TOP_GOAL,
                successCondition);
    }

}
