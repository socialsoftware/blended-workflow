package pt.ist.socialsoftware.blendedworkflow.resources.service.execution;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.*;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.Person;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.User;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.DesignResourcesInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprIsPersonDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.RALExprOrDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.ResourceRuleDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExecutionResourcesInterfaceTest extends TeardownRollbackTest {
    public static final String USERNAME_1 = "User1";
    public static final String PASSWORD_1 = "User1";
    public static final String USERNAME_2 = "User2";
    public static final String PASSWORD_2 = "User2";
    private static final String SPEC_ID = "SpecTest";
    private static final String WORKFLOW_ID = "workflow-1";
    private static final String ENT_1 = "Ent1";
    private Logger logger = LoggerFactory.getLogger(ExecutionResourcesInterfaceTest.class);

    private WorkflowInstanceDTO workflowInstanceDTO;
    private WorkflowInstance instance;

    private Specification spec;
    private DesignResourcesInterface designer;
    private ExecutionResourcesInterface edi;
    private Person person1;
    private Person person2;

    @Override
    public void populate4Test() {
        designer = DesignResourcesInterface.getInstance();
        edi = ExecutionResourcesInterface.getInstance();
        spec = designer.createSpecification(new SpecDTO(SPEC_ID, SPEC_ID));

        Entity entity = designer.createEntity(new EntityDTO(SPEC_ID, ENT_1, false, true));
        designer.createResourceModel(SPEC_ID);
        person1 = designer.createPerson(new PersonDTO(SPEC_ID, USERNAME_1, "", new ArrayList<>(), new ArrayList<>()));
        person2 = designer.createPerson(new PersonDTO(SPEC_ID, USERNAME_2, "", new ArrayList<>(), new ArrayList<>()));
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

        instance = new WorkflowInstance(spec,WORKFLOW_ID);
    }

    @Test
    public void executeActivityWorkItem() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                USERNAME_1,
                PASSWORD_1,
                new ArrayList<>()));

        Set<ActivityWorkItemDTO> workItemDTOList = edi.getPendingActivityWorkItemSet(SPEC_ID, WORKFLOW_ID);

        ActivityWorkItemDTO workItemDTO = workItemDTOList.stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.NO_WORKITEMS_AVAILABLE));

        logger.debug("WORKITEM: {}", workItemDTO.print());

        DefinitionGroupDTO definitionGroup = workItemDTO.getDefinitionGroupSet().stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.INVALID_WORKITEM));

        ProductInstanceDTO productInstanceDTO = new ProductInstanceDTO();
        ProductDTO product = new ProductDTO();
        product.setProductType("ENTITY");
        productInstanceDTO.setProduct(product);
        productInstanceDTO.setPath(definitionGroup.getDefProductConditionSet().getDefEnts().stream().findFirst().orElseThrow(() -> new RMException(RMErrorType.INVALID_WORKITEM)).getPath());
        productInstanceDTO.setExternalId("-1");
        productInstanceDTO.setValue("");

        DefinitionGroupInstanceDTO definitionGroupInstanceDTO = new DefinitionGroupInstanceDTO();
        definitionGroupInstanceDTO.setProductInstanceSet(new HashSet<>());
        definitionGroupInstanceDTO.setEntityInstanceContextSet(new HashSet<>());
        definitionGroupInstanceDTO.setInnerRelationInstanceSet(new HashSet<>());
        definitionGroupInstanceDTO.getProductInstanceSet().add(productInstanceDTO);

        definitionGroup.getDefinitionGroupInstanceSet().add(definitionGroupInstanceDTO);

        ActivityWorkItem activityWorkItem = edi.executeActivityWorkItem(workItemDTO);

        assertTrue(person1.getUser().getWorkItemSet().size() > 0);
        assertTrue(person1.getUser().getWorkItemSet().contains(activityWorkItem));
    }

}