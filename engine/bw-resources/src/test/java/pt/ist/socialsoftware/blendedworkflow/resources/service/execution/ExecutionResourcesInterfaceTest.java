package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExecutionResourcesInterfaceTest extends TeardownRollbackTest {
    public static final String USERNAME_1 = "User1";
    public static final String PASSWORD_1 = "User1";
    public static final String USERNAME_2 = "User2";
    public static final String PASSWORD_2 = "User2";
    public static final String USERNAME_3 = "User3";
    public static final String PASSWORD_3 = "User3";
    private static final String SPEC_ID = "SpecTest";
    private static final String WORKFLOW_ID = "workflow-1";
    private static final String ENT_1 = "Ent1";
    private Logger logger = LoggerFactory.getLogger(ExecutionResourcesInterfaceTest.class);

    private WorkflowInstanceDto _workflowInstanceDto;
    private WorkflowInstance instance;

    private Specification spec;
    private DesignResourcesInterface designer;
    private ExecutionResourcesInterface edi;
    private Person person1;
    private Person person2;
    private Person person3;

    @Override
    public void populate4Test() {
        designer = DesignResourcesInterface.getInstance();
        edi = ExecutionResourcesInterface.getInstance();
        spec = designer.createSpecification(new SpecDto(SPEC_ID, SPEC_ID));

        Entity entity = designer.createEntity(new EntityDto(SPEC_ID, ENT_1, false, true));
        designer.createResourceModel(SPEC_ID);
        person1 = designer.createPerson(new PersonDto(SPEC_ID, USERNAME_1, "", new ArrayList<>(), new ArrayList<>()));
        person2 = designer.createPerson(new PersonDto(SPEC_ID, USERNAME_2, "", new ArrayList<>(), new ArrayList<>()));
        person3 = designer.createPerson(new PersonDto(SPEC_ID, USERNAME_3, "", new ArrayList<>(), new ArrayList<>()));

        designer.relationEntityIsPerson(new ResourceRelationDto(
                SPEC_ID,
                ENT_1
        ));
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

        designer.generateConditionModel(SPEC_ID);
        designer.generateActivityModel(SPEC_ID);
        designer.generateGoalModel(SPEC_ID);
        designer.generateEnrichedModels(SPEC_ID);

        instance = new WorkflowInstance(spec,WORKFLOW_ID);
    }

    private void fillWorkItem(WorkItemDto workItemDTO) {
        DefinitionGroupDto definitionGroup = workItemDTO.getDefinitionGroupSet().stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.INVALID_WORKITEM));

        ProductInstanceDto productInstanceDto = new ProductInstanceDto();
        ProductDto product = new ProductDto();
        product.setProductType("ENTITY");
        productInstanceDto.setProduct(product);
        productInstanceDto.setPath(definitionGroup.getDefEnt().getPath());
        productInstanceDto.setExternalId("-1");
        productInstanceDto.setValue("");

        DefinitionGroupInstanceDto definitionGroupInstanceDto = new DefinitionGroupInstanceDto();
        definitionGroupInstanceDto.setProductInstanceSet(new HashSet<>());
        definitionGroupInstanceDto.setEntityInstanceContextSet(new HashSet<>());
        definitionGroupInstanceDto.setInnerRelationInstanceSet(new HashSet<>());
        definitionGroupInstanceDto.getProductInstanceSet().add(productInstanceDto);

        definitionGroup.getDefinitionGroupInstanceSet().add(definitionGroupInstanceDto);
    }

    private void fakeLogin(String username, String password) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
            username,
            password,
            new ArrayList<>()));
    }

    @Test
    public void executeActivityWorkItem() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<ActivityWorkItemDto> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ActivityWorkItemDto workItemDTO = workItemDTOList.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        assertTrue(person1.getUser().getWorkItemSet().size() > 0);
        assertTrue(person1.getUser().getWorkItemSet().contains(activityWorkItem));
    }

    @Test
    public void executeGoalWorkItem() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<GoalWorkItemDto> workItemDTOList = edi.getPendingGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        GoalWorkItemDto workItemDTO = workItemDTOList.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        GoalWorkItem goalWorkItem = edi.executeGoalWorkItem(workItemDTO);

        assertTrue(person1.getUser().getWorkItemSet().size() > 0);
        assertTrue(person1.getUser().getWorkItemSet().contains(goalWorkItem));
    }

    @Test
    public void getLogActivityWorkItem() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<ActivityWorkItemDto> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ActivityWorkItemDto workItemDTO = workItemDTOList.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                USERNAME_2,
                PASSWORD_2,
                new ArrayList<>()));

        List<ActivityWorkItem> workItems = edi.getLogActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        assertEquals(1, workItems.size());
        assertEquals(activityWorkItem, workItems.get(0));
    }

    @Test
    public void getLogActivityWorkItemWithInvalidUser() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<ActivityWorkItemDto> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ActivityWorkItemDto workItemDTO = workItemDTOList.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        fakeLogin(USERNAME_1, USERNAME_1);

        List<ActivityWorkItem> workItems = edi.getLogActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        assertEquals(0, workItems.size());
    }

    @Test
    public void getLogGoalWorkItem() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<GoalWorkItemDto> workItemDTOList = edi.getPendingGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        GoalWorkItemDto workItemDTO= workItemDTOList.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        GoalWorkItem goalWorkItem = edi.executeGoalWorkItem(workItemDTO);

        fakeLogin(USERNAME_2, USERNAME_2);

        List<GoalWorkItem> workItems = edi.getLogGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        assertEquals(1, workItems.size());
        assertEquals(goalWorkItem, workItems.get(0));
    }

    @Test
    public void getLogGoalWorkItemWithInvalidUser() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<GoalWorkItemDto> workItemDTOList = edi.getPendingGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        GoalWorkItemDto workItemDTO= workItemDTOList.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        GoalWorkItem goalWorkItem = edi.executeGoalWorkItem(workItemDTO);

        fakeLogin(USERNAME_3, USERNAME_3);

        List<GoalWorkItem> workItems = edi.getLogGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        assertEquals(0, workItems.size());
    }

    @Test
    public void executeActivityWorkItemEntityIsPerson() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<ActivityWorkItemDto> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ActivityWorkItemDto workItemDTO = workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        assertTrue(person1.getUser().getWorkItemSet().size() > 0);
        assertTrue(person1.getUser().getWorkItemSet().contains(activityWorkItem));
        assertTrue(spec.getResourceModel().getPersonSet().size() > 3);
        assertTrue(activityWorkItem.getPostConditionSet().stream()
                .map(PostWorkItemArgument_Base::getProductInstanceSet)
                .flatMap(Collection::stream)
                .filter(productInstance -> productInstance instanceof EntityInstance)
                .allMatch(productInstance -> ((EntityInstance) productInstance).getPerson() != null));
        assertTrue(spec.getResourceModel().getPersonSet().stream()
                .anyMatch(person -> !person.getName().equals(person1.getName()) &&
                        !person.getName().equals(person2.getName()) &&
                        !person.getName().equals(person3.getName())));
    }

    @Test
    public void executeGoalWorkItemEntityIsPerson() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<GoalWorkItemDto> workItemDTOList = edi.getPendingGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        GoalWorkItemDto workItemDTO = workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        GoalWorkItem goalWorkItem = edi.executeGoalWorkItem(workItemDTO);

        assertTrue(person1.getUser().getWorkItemSet().size() > 0);
        assertTrue(person1.getUser().getWorkItemSet().contains(goalWorkItem));

        assertTrue(spec.getResourceModel().getPersonSet().size() > 3);
        assertTrue(goalWorkItem.getPostConditionSet().stream()
                .map(PostWorkItemArgument_Base::getProductInstanceSet)
                .flatMap(Collection::stream)
                .filter(productInstance -> productInstance instanceof EntityInstance)
                .allMatch(productInstance -> ((EntityInstance) productInstance).getPerson() != null));

        assertTrue(spec.getResourceModel().getPersonSet().stream()
                .anyMatch(person -> !person.getName().equals(person1.getName()) &&
                        !person.getName().equals(person2.getName()) &&
                        !person.getName().equals(person3.getName())));
    }

    @Test
    public void executeActivityWorkItemEntityIsPersonWithPersonChosen() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<ActivityWorkItemDto> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ResourceActivityWorkItemDto workItemDTO = (ResourceActivityWorkItemDto) workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        fillWorkItem(workItemDTO);

        EntityIsPersonDto entityIsPersonDto = workItemDTO.getEntityIsPersonDTOSet().stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        PersonDto personChosen = entityIsPersonDto.getPersonContext().stream().findFirst().get();

        entityIsPersonDto.setPersonChosen(personChosen);

        logger.debug("WORKITEM: {}", workItemDTO.print());

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        assertTrue(person1.getUser().getWorkItemSet().size() > 0);
        assertTrue(person1.getUser().getWorkItemSet().contains(activityWorkItem));
        assertEquals(3, spec.getResourceModel().getPersonSet().size());
        assertTrue(activityWorkItem.getPostConditionSet().stream()
                .map(PostWorkItemArgument_Base::getProductInstanceSet)
                .flatMap(Collection::stream)
                .filter(productInstance -> productInstance instanceof EntityInstance)
                .map(EntityInstance.class::cast)
                .allMatch(entityInstance -> entityInstance.getPerson() != null && entityInstance.getPerson().getName().equals(personChosen.getName())));
    }

    @Test
    public void executeGoalWorkItemEntityIsPersonWithPersonChosen() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<GoalWorkItemDto> workItemDTOList = edi.getPendingGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ResourceGoalWorkItemDto workItemDTO = (ResourceGoalWorkItemDto) workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));


        fillWorkItem(workItemDTO);

        EntityIsPersonDto entityIsPersonDto = workItemDTO.getEntityIsPersonDTOSet().stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        PersonDto personChosen = entityIsPersonDto.getPersonContext().stream().findFirst().get();

        entityIsPersonDto.setPersonChosen(personChosen);

        logger.debug("WORKITEM: {}", workItemDTO.print());

        GoalWorkItem goalWorkItem = edi.executeGoalWorkItem(workItemDTO);

        assertTrue(person1.getUser().getWorkItemSet().size() > 0);
        assertTrue(person1.getUser().getWorkItemSet().contains(goalWorkItem));
        assertEquals(3, spec.getResourceModel().getPersonSet().size());
        assertTrue(goalWorkItem.getPostConditionSet().stream()
                .map(PostWorkItemArgument_Base::getProductInstanceSet)
                .flatMap(Collection::stream)
                .filter(productInstance -> productInstance instanceof EntityInstance)
                .map(EntityInstance.class::cast)
                .allMatch(entityInstance -> entityInstance.getPerson() != null && entityInstance.getPerson().getName().equals(personChosen.getName())));
    }

    @Test
    public void getActivityWorkItemLogWithPersonChosen() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<ActivityWorkItemDto> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ResourceActivityWorkItemDto workItemDTO = (ResourceActivityWorkItemDto) workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        fillWorkItem(workItemDTO);

        EntityIsPersonDto entityIsPersonDto = workItemDTO.getEntityIsPersonDTOSet().stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        PersonDto personChosen = entityIsPersonDto.getPersonContext().stream().findFirst().get();

        entityIsPersonDto.setPersonChosen(personChosen);

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        fakeLogin(USERNAME_2, USERNAME_2);

        List<ActivityWorkItemDto> logWorkItems = edi.getLogActivityWorkItemDtoSet(SPEC_ID, WORKFLOW_ID);

        ResourceActivityWorkItemDto workItemToTest = (ResourceActivityWorkItemDto) logWorkItems.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        assertEquals(personChosen.getName(), workItemToTest.getEntityIsPersonDTOSet().stream().findFirst().get().getPersonChosen().getName());
    }

    @Test
    public void getGoalWorkItemLogWithPersonChosen() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<GoalWorkItemDto> workItemDTOList = edi.getPendingGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ResourceGoalWorkItemDto workItemDTO = (ResourceGoalWorkItemDto) workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        fillWorkItem(workItemDTO);

        EntityIsPersonDto entityIsPersonDto = workItemDTO.getEntityIsPersonDTOSet().stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        PersonDto personChosen = entityIsPersonDto.getPersonContext().stream().findFirst().get();

        entityIsPersonDto.setPersonChosen(personChosen);

        GoalWorkItem activityWorkItem = edi.executeGoalWorkItem(workItemDTO);

        fakeLogin(USERNAME_2, USERNAME_2);

        List<GoalWorkItemDto> logWorkItems = edi.getLogGoalWorkItemDTOSet(SPEC_ID, WORKFLOW_ID);

        ResourceGoalWorkItemDto workItemToTest = (ResourceGoalWorkItemDto) logWorkItems.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        assertEquals(personChosen.getName(), workItemToTest.getEntityIsPersonDTOSet().stream().findFirst().get().getPersonChosen().getName());
    }

    @Test
    public void getActivityWorkItemLogContainsExecutionUser() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<ActivityWorkItemDto> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ResourceActivityWorkItemDto workItemDTO = (ResourceActivityWorkItemDto) workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        fillWorkItem(workItemDTO);

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        fakeLogin(USERNAME_2, USERNAME_2);

        List<ActivityWorkItemDto> logWorkItems = edi.getLogActivityWorkItemDtoSet(SPEC_ID, WORKFLOW_ID);

        ResourceActivityWorkItemDto workItemToTest = (ResourceActivityWorkItemDto) logWorkItems.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        assertEquals(USERNAME_1, workItemToTest.getExecutionUser().getUsername());
    }

    @Test
    public void getGoalWorkItemLogContainsExecutionUser() throws Exception {
        fakeLogin(USERNAME_1, USERNAME_1);

        Set<GoalWorkItemDto> workItemDTOList = edi.getPendingGoalWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ResourceGoalWorkItemDto workItemDTO = (ResourceGoalWorkItemDto) workItemDTOList.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        fillWorkItem(workItemDTO);

        GoalWorkItem goalWorkItem = edi.executeGoalWorkItem(workItemDTO);

        fakeLogin(USERNAME_2, USERNAME_2);

        List<GoalWorkItemDto> logWorkItems = edi.getLogGoalWorkItemDTOSet(SPEC_ID, WORKFLOW_ID);

        ResourceGoalWorkItemDto workItemToTest = (ResourceGoalWorkItemDto) logWorkItems.stream()
                .findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        assertEquals(USERNAME_1, workItemToTest.getExecutionUser().getUsername());
    }

}
