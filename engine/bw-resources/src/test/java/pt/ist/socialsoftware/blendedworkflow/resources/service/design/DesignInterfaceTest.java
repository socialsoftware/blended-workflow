package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.*;

import java.util.Arrays;

import static org.junit.Assert.*;


public class DesignInterfaceTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;

    private Capability _capability1;

    private Role _role1;
    private Role _role2;

    private Unit _unit1;

    private Position _position1;
    private Position _position2;
    private Position _position3;
    private Position _position4;
    private Position _position5;
    private Position _position6;
    private Person _person1;
    private Person _person2;
    private Person _person3;
    private RALExpressionDTO _ralExprDTO1;

    @Override
    public void populate4Test() throws BWException {
        pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface.getInstance().createSpecification(new SpecDTO(SPEC_ID, SPEC_NAME));
        designer = DesignInterface.getInstance();

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());

        _capability1 = new Capability(_resourceModel, "Cap1_Pop", null);

        _role1 = new Role(_resourceModel, "Role1_Pop", null);
        _role2 = new Role(_resourceModel, "Role2_Pop", null);

        _unit1 = new Unit(_resourceModel, "Unit1_Pop", null);

        _position1 = new Position(_resourceModel, "Pos1_Pop", "test", _unit1);
        _position2 = new Position(_resourceModel, "Pos2_Pop", "test", _unit1);
        _position3 = new Position(_resourceModel, "Pos3_Pop", "test", _unit1);
        _position4 = new Position(_resourceModel, "Pos4_Pop", "test", _unit1);
        _position5 = new Position(_resourceModel, "Pos5_Pop", "test", _unit1, Arrays.asList(_role1, _role2), Arrays.asList(_position2, _position3), _position6);
        _position6 = new Position(_resourceModel, "Pos6_Pop", "test", _unit1, Arrays.asList(_role1, _role2), Arrays.asList(_position1, _position5), _position3);

        _person1 = new Person(_resourceModel, "Person_1", "test", Arrays.asList(_position1, _position2), Arrays.asList(_capability1));
        _person2 = new Person(_resourceModel, "Person_2", "test", Arrays.asList(_position1, _position2), Arrays.asList(_capability1));
        _person3 = new Person(_resourceModel, "Person_3", "test", Arrays.asList(_position1, _position2), Arrays.asList(_capability1));
        
        _ralExprDTO1 = new RALExprAnyoneDTO();
    }

    @Test(expected = RMException.class)
    public void testInvalidRALExpression() {
        designer.createRALExpression(SPEC_ID, new RALExpressionDTO() {});
    }

    @Test
    public void testCreateRALExpressionAnd() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID, new RALExprAndDTO(_ralExprDTO1, _ralExprDTO1));
        assertEquals(ralExpression.getClass(), RALExprAnd.class);
    }

    @Test
    public void testCreateRALExpressionAnyone() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID, new RALExprAnyoneDTO());
        assertEquals(ralExpression.getClass(), RALExprAnyone.class);
    }

    @Test
    public void testCreateRALExpressionDelegatedByPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
            new RALExprDelegatedByPersonPositionExprDTO(
                new RALExprIsPersonDTO("Person_1")
        ));
        assertEquals(ralExpression.getClass(), RALExprDelegatedByPersonPosition.class);
    }

    @Test
    public void testCreateRALExpressionDelegatedByPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprDelegatedByPositionExprDTO("Pos1_Pop"));
        assertEquals(ralExpression.getClass(), RALExprDelegatedByPosition.class);
    }

    @Test
    public void testCreateRALExpressionDelegatesToPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprDelegatesToPersonPositionExprDTO(
                        new RALExprIsPersonDTO("Person_1")
                ));
        assertEquals(ralExpression.getClass(), RALExprDelegatesToPersonPosition.class);
    }

    @Test
    public void testCreateRALExpressionDelegatesToPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprDelegatesToPositionExprDTO("Pos1_Pop"));
        assertEquals(ralExpression.getClass(), RALExprDelegatesToPosition.class);
    }

    @Test
    public void testCreateRALExpressionHasCapability() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasCapabilityDTO("Cap1_Pop"));
        assertEquals(ralExpression.getClass(), RALExprHasCapability.class);
    }

    @Test
    public void testCreateRALExpressionHasPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasPositionDTO("Pos1_Pop"));
        assertEquals(ralExpression.getClass(), RALExprHasPosition.class);
    }

    @Test
    public void testCreateRALExpressionHasRole() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasRoleDTO("Role1_Pop"));
        assertEquals(ralExpression.getClass(), RALExprHasRole.class);
    }

    @Test
    public void testCreateRALExpressionHasUnit() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasUnitDTO("Unit1_Pop"));
        assertEquals(ralExpression.getClass(), RALExprHasUnit.class);
    }

    @Test
    public void testCreateRALExpressionHistoryExecuting() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryExecutingDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        "test"
                ));
        assertEquals(ralExpression.getClass(), RALExprHistoryExecuting.class);
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionHistoryExecutingWithNullProduct() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryExecutingDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        null
                ));
        assertEquals(ralExpression.getClass(), RALExprHistoryExecuting.class);
    }

    @Test(expected = BWException.class)
    public void testCreateRALExpressionHistoryExecutingWithInvalidProduct() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryExecutingDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        "invalid"
                ));
        assertEquals(ralExpression.getClass(), RALExprHistoryExecuting.class);
    }

    @Test
    public void testCreateRALExpressionHistoryInformed() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryInformedDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        "test"
                ));

        assertEquals(ralExpression.getClass(), RALExprHistoryInformed.class);
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionHistoryInformedWithNullProduct() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryInformedDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        null
                ));
    }

    @Test(expected = BWException.class)
    public void testCreateRALExpressionHistoryInformedWithInvalidProduct() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryInformedDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        "invalid"
                ));
    }

    @Test
    public void testCreateRALExpressionIsPerson() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonDTO(
                    "Person_1"
                ));

        assertEquals(ralExpression.getClass(), RALExprIsPerson.class);
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionIsPersonWithInvalidPerson() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonDTO(
                        "invalid"
                ));

    }

    @Test
    public void testCreateRALExpressionIsPersonDataObject() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        designer.relationEntityIsPerson(new ResourceRelationDTO(
            SPEC_ID,
            "test"
        ));

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonDataObjectDTO(
                        "test"
                ));

        assertEquals(ralExpression.getClass(), RALExprIsPersonDataObject.class);
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionIsPersonDataObjectWithNull() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonDataObjectDTO(
                        null
                ));
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionIsPersonDataObjectWithNonPersonEntity() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonDataObjectDTO(
                        "test"
                ));
    }
    @Test(expected = BWException.class)
    public void testCreateRALExpressionIsPersonDataObjectWithInvalidDataField() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonDataObjectDTO(
                        "dasda"
                ));
    }

    @Test
    public void testCreateRALExpressionIsPersonInTaskDuty() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonInTaskDutyDTO(
                        ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                        "test"
                ));

        assertEquals(ralExpression.getClass(), RALExprIsPersonInTaskDuty.class);
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionIsPersonInTaskDutyWithNull() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonInTaskDutyDTO(
                        ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                        null
                ));
    }

    @Test(expected = BWException.class)
    public void testCreateRALExpressionIsPersonInTaskDutyWithInvalidDataField() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprIsPersonInTaskDutyDTO(
                        ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                        "asdasdas"
                ));

        assertEquals(ralExpression.getClass(), RALExprIsPersonInTaskDuty.class);
    }

    @Test
    public void testCreateRALExpressionNot() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprNotDTO(new RALExprHasPositionDTO("Pos1_Pop")));
        assertEquals(ralExpression.getClass(), RALExprNot.class);
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionNotWithNotDeniableExpr() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprNotDTO(new RALExprAnyoneDTO()));
    }

    @Test
    public void testCreateRALExpressionOr() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprOrDTO(_ralExprDTO1, _ralExprDTO1));
        assertEquals(ralExpression.getClass(), RALExprOr.class);
    }

    @Test
    public void testCreateRALExpressionReportedByPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportedByPersonPositionExprDTO(
                    new RALExprIsPersonDTO("Person_1"),
                    true
                ));
        assertEquals(ralExpression.getClass(), RALExprReportedByPersonPosition.class);
    }

    @Test
    public void testCreateRALExpressionReportedByPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportedByPositionExprDTO("Pos1_Pop",true));
        assertEquals(ralExpression.getClass(), RALExprReportedByPosition.class);
    }

    @Test
    public void testCreateRALExpressionReportsToPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportsToPersonPositionExprDTO(
                        new RALExprIsPersonDTO("Person_1"),
                        true
                ));
        assertEquals(ralExpression.getClass(), RALExprReportsToPersonPosition.class);
    }

    @Test
    public void testCreateRALExpressionReportsToPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportsToPositionExprDTO("Pos1_Pop",true));
        assertEquals(ralExpression.getClass(), RALExprReportsToPosition.class);
    }

    @Test
    public void testCreateRALExpressionSharesPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesPositionDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExprIsPersonDTO("Person_1")
                ));
        assertEquals(ralExpression.getClass(), RALExprSharesPosition.class);
    }

    @Test
    public void testCreateRALExpressionSharesRole() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesRoleDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExprIsPersonDTO("Person_1")
                ));
        assertEquals(ralExpression.getClass(), RALExprSharesRole.class);
    }

    @Test
    public void testCreateRALExpressionSharesUnit() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesUnitDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExprIsPersonDTO("Person_1")
                ));
        assertEquals(ralExpression.getClass(), RALExprSharesUnit.class);
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionCommonalityWithoutPersonDTO() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesPositionDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExpressionDTO() {}
                ));
        assertEquals(ralExpression.getClass(), RALExprSharesPosition.class);
    }

    @Test
    public void testeCreateResponsibleResourceRuleToEntity() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                "test",
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprAnyoneDTO()
        ));

        assertNotNull(product.getResponsibleFor());
        assertEquals(product.getResponsibleFor().getClass(), RALExprAnyone.class);
    }

    @Test
    public void testeCreateInformsResourceRuleToEntity() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                "test",
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprAnyoneDTO()
        ));

        assertNotNull(product.getInforms());
        assertEquals(product.getInforms().getClass(), RALExprAnyone.class);
    }

    @Test
    public void testeCreateResponsibleResourceRuleToAttribute() {
        Entity entity = spec.getDataModel().createEntity("test", true, true);
        Attribute attribute = entity.createAttribute("test12", Attribute.AttributeType.STRING, true);

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                "test.test12",
                ResourceRuleDTO.ResourceRuleTypeDTO.HAS_RESPONSIBLE,
                new RALExprAnyoneDTO()
        ));

        assertNotNull(attribute.getResponsibleFor());
        assertEquals(attribute.getResponsibleFor().getClass(), RALExprAnyone.class);
    }

    @Test
    public void testeCreateInformsResourceRuleToAttribute() {
        Entity entity = spec.getDataModel().createEntity("test", true, true);
        Attribute attribute = entity.createAttribute("test12", Attribute.AttributeType.STRING, true);

        designer.addResourceRule(new ResourceRuleDTO(
                SPEC_ID,
                "test.test12",
                ResourceRuleDTO.ResourceRuleTypeDTO.INFORMS,
                new RALExprAnyoneDTO()
        ));

        assertNotNull(attribute.getInforms());
        assertEquals(attribute.getInforms().getClass(), RALExprAnyone.class);
    }
}