package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.w3c.dom.Attr;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.MergeOperationDto;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.execution.ExecutionResourcesInterface;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MergeTest extends TeardownRollbackTest {
    private static final String USERNAME_1 = "User1";
    private static final String USERNAME_2 = "User2";
    private static final String USERNAME_3 = "User3";
    private static final String SPEC_ID = "SpecTest";
    private static final String WORKFLOW_ID = "workflow-1";
    private static final String ENT_1 = "Ent1";
    private static final String ATTR_1 = "Attr1";
    private static final String UNIT = "Unit";
    private static final String POSITION = "Pos";
    private Logger logger = LoggerFactory.getLogger(MergeTest.class);

    private WorkflowInstanceDTO workflowInstanceDTO;
    private WorkflowInstance instance;

    private Specification spec;
    private DesignResourcesInterface designer;
    private ExecutionResourcesInterface edi;
    private Person person1;
    private Person person2;
    private Person person3;
    private Unit unit;
    private Position position;

    @Override
    public void populate4Test() {
        designer = DesignResourcesInterface.getInstance();
        edi = ExecutionResourcesInterface.getInstance();
        spec = designer.createSpecification(new SpecDTO(SPEC_ID, SPEC_ID));

        Entity entity = designer.createEntity(new EntityDTO(SPEC_ID, ENT_1, false, true));
        Attribute attribute = designer.createAttribute(new AttributeDTO(SPEC_ID, entity.getExternalId(), ENT_1, ATTR_1,"Number", false));
        designer.createResourceModel(SPEC_ID);
        unit = designer.createUnit(new UnitDTO(SPEC_ID, UNIT, ""));
        position = designer.createPosition(new PositionDTO(SPEC_ID, POSITION, UNIT, ""));
        person1 = designer.createPerson(new PersonDTO(SPEC_ID, USERNAME_1, "", new ArrayList<>(), new ArrayList<>()));
        person2 = designer.createPerson(new PersonDTO(SPEC_ID, USERNAME_2, "", new ArrayList<>(), new ArrayList<>()));
        person3 = designer.createPerson(new PersonDTO(SPEC_ID, USERNAME_3, "", new ArrayList<>(), Arrays.asList(POSITION)));

    }

    @Test
    public void mergeActivitiesWithBothAssignments() throws Exception {
        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprIsPersonDTO(USERNAME_1)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprIsPersonDTO(USERNAME_2)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1 + "." + ATTR_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprHasPositionDTO(POSITION)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1 + "." + ATTR_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprIsPersonDTO(USERNAME_1)
        ));

        designer.generateConditionModel(SPEC_ID);
        designer.generateActivityModel(SPEC_ID);
        designer.generateGoalModel(SPEC_ID);
        designer.generateEnrichedModels(SPEC_ID);

        designer.mergeActivities(new MergeOperationDto(SPEC_ID, "Merged", ENT_1, ENT_1 + "." + ATTR_1));

        Set<Activity> activities = spec.getActivityModel().getActivitySet();
        assertEquals(1, activities.size());
        Activity activity = activities.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_ACTIVITIES_AVAILABLE));
        assertEquals(RALExprOr.class, activity.getResponsibleFor().getClass());
        RALExprOr expr = (RALExprOr) activity.getResponsibleFor();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprHasPosition.class, expr.getRightExpression().getClass());

        assertEquals(RALExprOr.class, activity.getInforms().getClass());
        expr = (RALExprOr) activity.getInforms();
        assertEquals(RALExprIsPerson.class, expr.getLeftExpression().getClass());
        assertEquals(RALExprIsPerson.class, expr.getRightExpression().getClass());
    }

    @Test
    public void mergeActivitiesWithPreviousAssignment() throws Exception {
        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprIsPersonDTO(USERNAME_1)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprIsPersonDTO(USERNAME_2)
        ));

        designer.generateConditionModel(SPEC_ID);
        designer.generateActivityModel(SPEC_ID);
        designer.generateGoalModel(SPEC_ID);
        designer.generateEnrichedModels(SPEC_ID);

        designer.mergeActivities(new MergeOperationDto(SPEC_ID, "Merged", ENT_1, ENT_1 + "." + ATTR_1));

        Set<Activity> activities = spec.getActivityModel().getActivitySet();
        assertEquals(1, activities.size());
        Activity activity = activities.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_ACTIVITIES_AVAILABLE));
        assertEquals(RALExprIsPerson.class, activity.getResponsibleFor().getClass());
        assertEquals(RALExprIsPerson.class, activity.getInforms().getClass());
    }

    @Test
    public void mergeActivitiesWithForwardAssignment() throws Exception {
        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1 + "." + ATTR_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprIsPersonDTO(USERNAME_1)
        ));

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                ENT_1 + "." + ATTR_1,
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprIsPersonDTO(USERNAME_2)
        ));

        designer.generateConditionModel(SPEC_ID);
        designer.generateActivityModel(SPEC_ID);
        designer.generateGoalModel(SPEC_ID);
        designer.generateEnrichedModels(SPEC_ID);

        designer.mergeActivities(new MergeOperationDto(SPEC_ID, "Merged", ENT_1, ENT_1 + "." + ATTR_1));

        Set<Activity> activities = spec.getActivityModel().getActivitySet();
        assertEquals(1, activities.size());
        Activity activity = activities.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_ACTIVITIES_AVAILABLE));
        assertEquals(RALExprIsPerson.class, activity.getResponsibleFor().getClass());
        assertEquals(RALExprIsPerson.class, activity.getInforms().getClass());
    }

    @Test
    public void mergeActivitiesWithNoAssignments() throws Exception {
        designer.generateConditionModel(SPEC_ID);
        designer.generateActivityModel(SPEC_ID);
        designer.generateGoalModel(SPEC_ID);
        designer.generateEnrichedModels(SPEC_ID);

        designer.mergeActivities(new MergeOperationDto(SPEC_ID, "Merged", ENT_1, ENT_1 + "." + ATTR_1));

        Set<Activity> activities = spec.getActivityModel().getActivitySet();
        assertEquals(1, activities.size());
        Activity activity = activities.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_ACTIVITIES_AVAILABLE));
        assertNull(activity.getResponsibleFor());
        assertNull(activity.getInforms());
    }
}
