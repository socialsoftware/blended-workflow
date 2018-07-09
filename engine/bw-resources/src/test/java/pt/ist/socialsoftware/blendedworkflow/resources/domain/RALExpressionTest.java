package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.execution.ExecutionResourcesInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RALExpressionTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignResourcesInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;

    private Capability _capability1;

    private Role _role1;
    private Role _role2;

    private Unit _unit1;
    private Unit _unit2;

    private Position _position1;
    private Position _position2;
    private Position _position3;
    private Position _position4;
    private Position _position5;
    private Role _role3;
    private WorkflowInstance _workflowInstance;
    private ExecutionResourcesInterface edi;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID, SPEC_NAME));
        designer = DesignResourcesInterface.getInstance();
        edi = ExecutionResourcesInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());

        _capability1 = new Capability(_resourceModel, "Cap1_Pop", null);

        _role1 = new Role(_resourceModel, "Role1_Pop", null);
        _role2 = new Role(_resourceModel, "Role2_Pop", null);
        _role3 = new Role(_resourceModel, "Role3_Pop", null);

        _unit1 = new Unit(_resourceModel, "Unit1_Pop", null);
        _unit2 = new Unit(_resourceModel, "Unit2_Pop", null);

        _position1 = new Position(_resourceModel, "Pos1_Pop", "test", _unit1);
        _position2 = new Position(_resourceModel, "Pos2_Pop", "test", _unit1, Arrays.asList(_role1, _role2), Arrays.asList(_position1), _position1);
        _position3 = new Position(_resourceModel, "Pos3_Pop", "test", _unit1);
        _position4 = new Position(_resourceModel, "Pos5_Pop", "test", _unit1, Arrays.asList(_role1, _role2), Arrays.asList(_position2, _position3), _position2);
        _position5 = new Position(_resourceModel, "Pos6_Pop", "test", _unit2, Arrays.asList(_role1, _role3), Arrays.asList(_position1, _position4), _position4);

        _workflowInstance = new WorkflowInstance(spec, "Instance1");
    }

    @Test
    public void testHasCapabilityGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", new ArrayList<>(), Arrays.asList(_capability1));
        Person person2 = new Person(_resourceModel, "Test2", "");
        Person person3 = new Person(_resourceModel, "Test3", "", new ArrayList<>(), Arrays.asList(_capability1));

        RALExpression expression = new RALExprHasCapability(_resourceModel, _capability1);
        assertEquals(2, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1, person3)));
        SetOfRequiredResources set = expression.getSetOfRequiredResources();
        assertTrue(set.getCapabilities().stream().map(CapabilityDTO::getName)
                .collect(Collectors.toList()).contains(_capability1.getDTO().getName()));
        assertTrue(expression.isConsistent());
    }

    @Test
    public void testHasPositionGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position1), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "");
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position1), new ArrayList<>());

        RALExpression expression = new RALExprHasPosition(_resourceModel, _position1);
        assertEquals(2, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1, person3)));
    }

    @Test
    public void testHasRoleGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position4), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "");
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position5), new ArrayList<>());

        RALExpression expression = new RALExprHasRole(_resourceModel, _role1, _unit1);
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1)));
    }

    @Test
    public void testHasRoleGetEligibleResourcesWithoutUnits() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position4), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "");
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position5), new ArrayList<>());

        RALExpression expression = new RALExprHasRole(_resourceModel, _role1);
        assertEquals(2, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1, person3)));
    }

    @Test
    public void testHasUnitGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position1), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "");

        RALExpression expression = new RALExprHasUnit(_resourceModel, _unit1);
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1)));
    }

    @Test
    public void testOrGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position1), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "");
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());

        RALExpression expression1 = new RALExprHasPosition(_resourceModel, _position1);
        RALExpression expression2 = new RALExprHasPosition(_resourceModel, _position2);
        RALExpression expression = new RALExprOr(_resourceModel, expression1, expression2);

        assertEquals(2, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1, person3)));
    }

    @Test
    public void testAndGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position1), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "");
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position1), new ArrayList<>());

        RALExpression expression1 = new RALExprHasPosition(_resourceModel, _position1);
        RALExpression expression2 = new RALExprHasPosition(_resourceModel, _position2);
        RALExpression expression = new RALExprAnd(_resourceModel, expression1, expression2);

        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person3)));
    }

    @Test
    public void testNotGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position1), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "");
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position1), new ArrayList<>());

        RALExpression expression1 = new RALExprHasPosition(_resourceModel, _position1);
        RALExpression expression = new RALExprNot(_resourceModel, expression1);
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2)));
    }

    @Test
    public void testReportsToPersonPositionDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position4), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());

        RALExpression expression = new RALExprReportsToPersonPosition(
                _resourceModel,
                new RALExprIsPerson(_resourceModel, person2),
                true);
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person3)));
    }

    @Test
    public void testReportsToPersonPositionNotDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position1), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position4), new ArrayList<>());

        RALExpression expression = new RALExprReportsToPersonPosition(
                _resourceModel,
                new RALExprIsPerson(_resourceModel, person1),
                false);
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2,person3, person4)));
    }

    @Test
    public void testReportsToPositionDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position1), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position4), new ArrayList<>());

        RALExpression expression = new RALExprReportsToPosition(
                _resourceModel,
                _position5,
                true);

        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person4)));
    }

    @Test
    public void testReportsToPositionNotDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position1), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position4), new ArrayList<>());

        RALExpression expression = new RALExprReportsToPosition(
                _resourceModel,
                _position5,
                false);
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2,person3, person4)));
    }

    @Test
    public void testReportedByPersonPositionDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position1), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position4), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position5), new ArrayList<>());

        RALExpression expression = new RALExprReportedByPersonPosition(
                _resourceModel,
                new RALExprIsPerson(_resourceModel, person3),
                true);
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person4)));
    }

    @Test
    public void testReportedByPersonPositionNotDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position1), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position4), new ArrayList<>());

        RALExpression expression = new RALExprReportedByPersonPosition(
                _resourceModel,
                new RALExprIsPerson(_resourceModel, person2),
                false);
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1,person3, person4)));
    }

    @Test
    public void testReportedByPositionDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position1), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position4), new ArrayList<>());

        RALExpression expression = new RALExprReportedByPosition(
                _resourceModel,
                _position1,
                true);
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person3)));
    }

    @Test
    public void testReportedByPositionNotDirectlyGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position1), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position4), new ArrayList<>());

        RALExpression expression = new RALExprReportedByPosition(
                _resourceModel,
                _position1,
                false);
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1,person3, person4)));
    }

    @Test
    public void testWorkDelegatedByPersonPositionGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position4), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprDelegatedByPersonPosition(
                _resourceModel,
                new RALExprIsPerson(_resourceModel, person2));
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1)));
    }

    @Test
    public void testWorkDelegatedByPositionGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position4), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprDelegatedByPosition(
                _resourceModel,
                _position2);
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1)));
    }

    @Test
    public void testWorkDelegatesToPersonPositionGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position4), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprDelegatesToPersonPosition(
                _resourceModel,
                new RALExprIsPerson(_resourceModel, person1));
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2, person3, person4)));
    }

    @Test
    public void testWorkDelegatesToPositionGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position4), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprDelegatesToPosition(
                _resourceModel,
                _position4);
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2, person3, person4)));
    }

    @Test
    public void testShareSomePositionGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position3), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprSharesPosition(
                _resourceModel,
                RALExprCommonality.Amount.SOME,
                new RALExprIsPerson(_resourceModel, person1));
        assertEquals(2, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2, person3)));
    }

    @Test
    public void testShareAllPositionGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2,_position3), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position3), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprSharesPosition(
                _resourceModel,
                RALExprCommonality.Amount.ALL,
                new RALExprIsPerson(_resourceModel, person3));
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1)));
    }

    @Test
    public void testShareSomeUnitGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position3), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprSharesUnit(
                _resourceModel,
                RALExprCommonality.Amount.SOME,
                new RALExprIsPerson(_resourceModel, person1));
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2, person3)));
    }

    @Test
    public void testShareAllUnitGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2,_position3), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position3), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprSharesUnit(
                _resourceModel,
                RALExprCommonality.Amount.ALL,
                new RALExprIsPerson(_resourceModel, person3));
        assertEquals(3, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person1, person2, person4)));
    }

    @Test
    public void testShareSomeRoleGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position3), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position3), new ArrayList<>());

        RALExpression expression = new RALExprSharesRole(
                _resourceModel,
                RALExprCommonality.Amount.SOME,
                new RALExprIsPerson(_resourceModel, person1));
        assertEquals(2, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person2, person3)));
    }

    @Test
    public void testShareAllRoleGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2,_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());
        Person person3 = new Person(_resourceModel, "Test3", "", Arrays.asList(_position2, _position5), new ArrayList<>());
        Person person4 = new Person(_resourceModel, "Test4", "", Arrays.asList(_position5), new ArrayList<>());

        RALExpression expression = new RALExprSharesRole(
                _resourceModel,
                RALExprCommonality.Amount.ALL,
                new RALExprIsPerson(_resourceModel, person1));
        assertEquals(1, expression.getEligibleResources(_workflowInstance).size());
        assertTrue(expression.getEligibleResources(_workflowInstance).containsAll(Arrays.asList(person3)));
    }

    @Test
    public void testIsPersonInTaskDutyInformedGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2,_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());

        Entity ent1 = designer.createEntity(new EntityDTO(spec.getSpecId(), "Entity", false));
        Attribute att1 = designer.createAttribute(new AttributeDTO(SPEC_ID, ent1.getExternalId(), null,
                "att", Attribute.AttributeType.NUMBER.toString(), false));

        spec.getConditionModel().generateConditions();
        spec.getActivityModel().generateActivities();

        designer.addResourceRule(new ResourceRuleDTO(
                spec.getSpecId(),
                "Entity.att",
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprOrDTO(new RALExprIsPersonDTO(person1.getName()),  new RALExprIsPersonDTO(person2.getName()))
        ));

        designer.generateEnrichedModels(spec.getSpecId());

        RALExpression expression = new RALExprIsPersonInTaskDuty(
                _resourceModel,
                RALExpression.TaskDutyType.INFORMED_ABOUT,
                "Entity.att"
        );

        WorkflowInstance workflow = edi.createWorkflowInstance(spec.getSpecId(), "Test");

        EntityInstance entityInstance = new EntityInstance(workflow, ent1);
        AttributeInstance attributeInstance = new AttributeInstance(entityInstance, att1, "213");

        List<Person> personList = expression.getEligibleResources(workflow);
        assertEquals(2, personList.size());
        assertTrue(expression.getEligibleResources(workflow).containsAll(Arrays.asList(person1, person2)));
    }

    @Test
    public void testIsPersonInTaskDutyResponsibleForGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2,_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());

        Entity ent1 = designer.createEntity(new EntityDTO(spec.getSpecId(), "Entity", false));
        Attribute att1 = designer.createAttribute(new AttributeDTO(SPEC_ID, ent1.getExternalId(), null,
                "att", Attribute.AttributeType.NUMBER.toString(), false));

        spec.getConditionModel().generateConditions();
        spec.getActivityModel().generateActivities();

        designer.addResourceRule(new ResourceRuleDTO(
                spec.getSpecId(),
                "Entity.att",
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprOrDTO(new RALExprIsPersonDTO(person1.getName()),  new RALExprIsPersonDTO(person2.getName()))
        ));

        designer.generateEnrichedModels(spec.getSpecId());

        RALExpression expression = new RALExprIsPersonInTaskDuty(
                _resourceModel,
                RALExpression.TaskDutyType.RESPONSIBLE_FOR,
                "Entity.att"
        );

        WorkflowInstance workflow = edi.createWorkflowInstance(spec.getSpecId(), "Test");

        Activity activityAtt = spec.getActivityModel().getActivity(att1.getFullPath());
        WorkItem workitem2 = new ActivityWorkItem(workflow, activityAtt);
        PostWorkItemArgument postWorkItemArgument = new PostWorkItemArgument(workitem2, DefAttributeCondition.getDefAttributeCondition(att1));

        EntityInstance entityInstance = new EntityInstance(workflow, ent1);
        ProductInstance productInstance = new AttributeInstance(entityInstance, att1, "123");

        productInstance.setPostWorkItemArgument(postWorkItemArgument);
        workitem2.setExecutionUser(person1.getUser());

        List<Person> personList = expression.getEligibleResources(workflow);
        assertEquals(1, personList.size());
        assertTrue(expression.getEligibleResources(workflow).containsAll(Arrays.asList(person1)));
    }

    @Test
    public void testIsPersonInTaskDutyResponsibleForGetEligibleResourcesWithMocks() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2,_position5), new ArrayList<>());
        Person person2 = new Person(_resourceModel, "Test2", "", Arrays.asList(_position2), new ArrayList<>());

        Entity ent1 = designer.createEntity(new EntityDTO(spec.getSpecId(), "Entity", false));
        Attribute att1 = designer.createAttribute(new AttributeDTO(SPEC_ID, ent1.getExternalId(), null,
                "att", Attribute.AttributeType.NUMBER.toString(), false));

        spec.getConditionModel().generateConditions();
        spec.getActivityModel().generateActivities();

        designer.addResourceRule(new ResourceRuleDTO(
                spec.getSpecId(),
                "Entity.att",
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprOrDTO(new RALExprIsPersonDTO(person1.getName()),  new RALExprIsPersonDTO(person2.getName()))
        ));

        designer.generateEnrichedModels(spec.getSpecId());

        RALExpression expression = new RALExprIsPersonInTaskDuty(
                _resourceModel,
                RALExpression.TaskDutyType.RESPONSIBLE_FOR,
                "Entity.att"
        );

        WorkflowInstance workflowInstance = mock(WorkflowInstance.class);

        WorkItem workItem = mock(WorkItem.class);
        when(workItem.getExecutionUser()).thenReturn(person1.getUser());

        ProductInstance productInstance = mock(ProductInstance.class);
        when(productInstance.getCreatorWorkItem()).thenReturn(workItem);

        EntityInstance entityInstance = mock(EntityInstance.class);
        when(entityInstance.getProductInstancesByPath("Entity.att")).thenReturn(new HashSet<>(Arrays.asList(productInstance)));

        when(workflowInstance.getEntityInstanceSet()).thenReturn(new HashSet<>(Arrays.asList(entityInstance)));

        List<Person> personList = expression.getEligibleResources(workflowInstance);
        assertEquals(1, personList.size());
        assertTrue(expression.getEligibleResources(workflowInstance).containsAll(Arrays.asList(person1)));
    }

    @Test
    public void testIsPersonInDataFieldGetEligibleResources() throws RMException {
        Person person1 = new Person(_resourceModel, "Test1", "", Arrays.asList(_position2,_position5), new ArrayList<>());

        Entity ent1 = designer.createEntity(new EntityDTO(spec.getSpecId(), "Teste1", false));
        Entity ent2 = designer.createEntity(new EntityDTO(spec.getSpecId(), "Teste2", false));
        designer.createRelation(new RelationDTO(spec.getSpecId(), "Teste1Teste2", ent1.getExternalId(), "teste1", "0..1",
                ent2.getExternalId(), "teste2", "0..1"));

        _resourceModel.addEntityIsPerson("Teste2");

        EntityInstance entityInstance = mock(EntityInstance.class);
        when(entityInstance.getProduct()).thenReturn(ent2);
        when(entityInstance.getPerson()).thenReturn(person1);
        when(entityInstance.getProductInstancesByPath("Teste1.teste2")).thenReturn(new HashSet<>(Arrays.asList(entityInstance)));

        WorkflowInstance workflowInstance = mock(WorkflowInstance.class);
        when(workflowInstance.getEntityInstanceSet()).thenReturn(new HashSet<>(Arrays.asList(entityInstance)));

        RALExpression expression = new RALExprIsPersonDataObject(
                _resourceModel,
                "Teste1.teste2"
                );
        assertEquals(1, expression.getEligibleResources(workflowInstance).size());
        assertTrue(expression.getEligibleResources(workflowInstance).containsAll(Arrays.asList(person1)));
    }
}