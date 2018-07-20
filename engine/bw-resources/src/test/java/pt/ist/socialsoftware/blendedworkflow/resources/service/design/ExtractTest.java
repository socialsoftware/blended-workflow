package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.ExtractGoalDto;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.req.ResourcesMergeOperationDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.execution.ExecutionResourcesInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ExtractTest extends TeardownRollbackTest {
    private static final String USERNAME_1 = "User1";
    private static final String USERNAME_2 = "User2";
    private static final String USERNAME_3 = "User3";
    private static final String SPEC_ID = "SpecTest";
    private static final String WORKFLOW_ID = "workflow-1";
    private static final String ENT_1 = "Ent1";
    private static final String ATTR_1 = "Attr1";
    private static final String UNIT = "Unit";
    private static final String POSITION = "Pos";
    private Logger logger = LoggerFactory.getLogger(ExtractTest.class);

    private WorkflowInstanceDto _workflowInstanceDto;
    private WorkflowInstance instance;

    private Specification spec;
    private DesignResourcesInterface designer;
    private ExecutionResourcesInterface edi;
    private Person person1;
    private Person person2;
    private Person person3;
    private Unit unit;
    private Position position;
    private ExtractGoalDto dto;

    @Override
    public void populate4Test() {
        designer = DesignResourcesInterface.getInstance();
        edi = ExecutionResourcesInterface.getInstance();
        spec = designer.createSpecification(new SpecDto(SPEC_ID, SPEC_ID));

        Entity entity = designer.createEntity(new EntityDto(SPEC_ID, ENT_1, false, true));
        Attribute attribute = designer.createAttribute(new AttributeDto(SPEC_ID, entity.getExternalId(), ENT_1, ATTR_1,"Number", false));
        designer.createResourceModel(SPEC_ID);
        unit = designer.createUnit(new UnitDto(SPEC_ID, UNIT, ""));
        position = designer.createPosition(new PositionDto(SPEC_ID, POSITION, UNIT, ""));
        person1 = designer.createPerson(new PersonDto(SPEC_ID, USERNAME_1, "", new ArrayList<>(), new ArrayList<>()));
        person2 = designer.createPerson(new PersonDto(SPEC_ID, USERNAME_2, "", new ArrayList<>(), new ArrayList<>()));
        person3 = designer.createPerson(new PersonDto(SPEC_ID, USERNAME_3, "", new ArrayList<>(), Arrays.asList(POSITION)));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprIsPersonDto(USERNAME_1)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprIsPersonDto(USERNAME_2)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1 + "." + ATTR_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprHasPositionDto(POSITION)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1 + "." + ATTR_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprIsPersonDto(USERNAME_1)
        ));

        designer.generateConditionModel(SPEC_ID);
        designer.generateActivityModel(SPEC_ID);
        designer.generateGoalModel(SPEC_ID);
        designer.generateEnrichedModels(SPEC_ID);

    }

    @Test
    public void extractActivities() throws Exception {

        designer.mergeActivities(new ResourcesMergeOperationDto(SPEC_ID, "Merged", ENT_1, ENT_1 + "." + ATTR_1, MergeResourcesPolicy.RELAXED));

        DefPathConditionDto defPathConditionDTO = new DefPathConditionDto();
        defPathConditionDTO.setSpecId(SPEC_ID);
        defPathConditionDTO.setPath(ENT_1 + "." + ATTR_1);
        designer.extractActivity(SPEC_ID, "SPLIT", "", "Merged", new HashSet<>(Arrays.asList(defPathConditionDTO)));

        Set<Activity> activities = spec.getActivityModel().getActivitySet();
        assertEquals(2, activities.size());
        Activity activity = activities.stream().filter(activity1 -> activity1.getName().equals("SPLIT")).findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_ACTIVITIES_AVAILABLE));
        assertEquals(RALExprOr.class, activity.getResponsibleFor().getClass());
        RALExprOr expr = (RALExprOr) activity.getResponsibleFor();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprHasPosition.class, expr.getRightExpression().getClass());

        assertEquals(RALExprOr.class, activity.getInforms().getClass());
        expr = (RALExprOr) activity.getInforms();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprIsPerson.class, expr.getRightExpression().getClass());

        activity = activities.stream().filter(activity1 -> activity1.getName().equals("Merged")).findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_ACTIVITIES_AVAILABLE));
        assertEquals(RALExprOr.class, activity.getResponsibleFor().getClass());
        expr = (RALExprOr) activity.getResponsibleFor();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprHasPosition.class, expr.getRightExpression().getClass());

        assertEquals(RALExprOr.class, activity.getInforms().getClass());
        expr = (RALExprOr) activity.getInforms();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprIsPerson.class, expr.getRightExpression().getClass());
    }


    @Test
    public void extractGoals() throws Exception {

        designer.mergeGoals(new ResourcesMergeOperationDto(SPEC_ID, "Merged", ENT_1, ENT_1 + "." + ATTR_1, MergeResourcesPolicy.RELAXED));

        DefPathConditionDto defPathConditionDTO = new DefPathConditionDto();
        defPathConditionDTO.setSpecId(SPEC_ID);
        defPathConditionDTO.setPath(ENT_1 + "." + ATTR_1);
        dto = new ExtractGoalDto();
        dto.setNewGoalName("SPLIT");
        dto.setSourceGoalName("Merged");
        dto.setSuccessConditions(new HashSet<>(Arrays.asList(defPathConditionDTO)));
        designer.extractGoal(SPEC_ID, dto);

        Set<Goal> goalSet = spec.getGoalModel().getGoalSet();
        assertEquals(2, goalSet.size());
        Goal goal = goalSet.stream().filter(g -> g.getName().equals("SPLIT")).findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_GOALS_AVAILABLE));
        assertEquals(RALExprOr.class, goal.getResponsibleFor().getClass());
        RALExprOr expr = (RALExprOr) goal.getResponsibleFor();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprHasPosition.class, expr.getRightExpression().getClass());

        assertEquals(RALExprOr.class, goal.getInforms().getClass());
        expr = (RALExprOr) goal.getInforms();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprIsPerson.class, expr.getRightExpression().getClass());

        goal = goalSet.stream().filter(g -> g.getName().equals("Merged")).findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_GOALS_AVAILABLE));
        assertEquals(RALExprOr.class, goal.getResponsibleFor().getClass());
        expr = (RALExprOr) goal.getResponsibleFor();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprHasPosition.class, expr.getRightExpression().getClass());

        assertEquals(RALExprOr.class, goal.getInforms().getClass());
        expr = (RALExprOr) goal.getInforms();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprIsPerson.class, expr.getRightExpression().getClass());
    }



}
