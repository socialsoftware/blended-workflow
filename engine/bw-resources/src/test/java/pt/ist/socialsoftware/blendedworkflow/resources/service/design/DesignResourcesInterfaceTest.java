package pt.ist.socialsoftware.blendedworkflow.resources.service.design;

import org.junit.Test;
import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.*;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.*;

import java.util.Arrays;

import static org.junit.Assert.*;


public class DesignResourcesInterfaceTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String SPEC_NAME = "Spec Name";

    private DesignResourcesInterface designer;

    private Specification spec;
    private ResourceModel _resourceModel;

    private Capability _capability1;

    private Role _role1;
    private Role _role2;

    private Unit _unit1;

    private Position _position1;

    private Person _person1;

    private RALExpressionDTO _ralExprDTO1;

    @Override
    public void populate4Test() throws BWException {
        designer = DesignResourcesInterface.getInstance();
        designer.createSpecification(new SpecDTO(SPEC_ID, SPEC_NAME));

        spec = getBlendedWorkflow().getSpecById(SPEC_ID).orElse(null);
        _resourceModel = designer.createResourceModel(spec.getSpecId());

        _capability1 = new Capability(_resourceModel, "Cap1_Pop", null);

        _role1 = new Role(_resourceModel, "Role1_Pop", null);
        _role2 = new Role(_resourceModel, "Role2_Pop", null);

        _unit1 = new Unit(_resourceModel, "Unit1_Pop", null);

        _position1 = new Position(_resourceModel, "Pos1_Pop", "test", _unit1);

        _person1 = new Person(_resourceModel, "Person_1", "test", Arrays.asList(_position1), Arrays.asList(_capability1));

        _ralExprDTO1 = new RALExprAnyoneDTO();
    }

    @Test(expected = RMException.class)
    public void testInvalidRALExpression() {
        designer.createRALExpression(SPEC_ID, new RALExpressionDTO() {});
    }

    @Test
    public void testCreateRALExpressionAnd() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID, new RALExprAndDTO(_ralExprDTO1, _ralExprDTO1));
        assertTrue(ralExpression.getClass().equals(RALExprAnd.class));
    }

    @Test
    public void testCreateRALExpressionAnyone() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID, new RALExprAnyoneDTO());
        assertTrue(ralExpression.getClass().equals(RALExprAnyone.class));
    }

    @Test
    public void testCreateRALExpressionDelegatedByPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
            new RALExprDelegatedByPersonPositionExprDTO(
                new RALExprIsPersonDTO(_person1.getName())
        ));
        assertTrue(ralExpression.getClass().equals(RALExprDelegatedByPersonPosition.class));
    }

    @Test
    public void testCreateRALExpressionDelegatedByPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprDelegatedByPositionExprDTO(_position1.getName()));
        assertTrue(ralExpression.getClass().equals(RALExprDelegatedByPosition.class));
    }

    @Test
    public void testCreateRALExpressionDelegatesToPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprDelegatesToPersonPositionExprDTO(
                        new RALExprIsPersonDTO(_person1.getName())
                ));
        assertTrue(ralExpression.getClass().equals(RALExprDelegatesToPersonPosition.class));
    }

    @Test
    public void testCreateRALExpressionDelegatesToPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprDelegatesToPositionExprDTO(_position1.getName()));
        assertTrue(ralExpression.getClass().equals(RALExprDelegatesToPosition.class));
    }

    @Test
    public void testCreateRALExpressionHasCapability() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasCapabilityDTO("Cap1_Pop"));
        assertTrue(ralExpression.getClass().equals(RALExprHasCapability.class));
    }

    @Test
    public void testCreateRALExpressionHasPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasPositionDTO(_position1.getName()));
        assertTrue(ralExpression.getClass().equals(RALExprHasPosition.class));
    }

    @Test
    public void testCreateRALExpressionHasRole() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasRoleDTO("Role1_Pop"));
        assertTrue(ralExpression.getClass().equals(RALExprHasRole.class));
    }

    @Test
    public void testCreateRALExpressionHasUnit() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHasUnitDTO("Unit1_Pop"));
        assertTrue(ralExpression.getClass().equals(RALExprHasUnit.class));
    }

    @Test
    public void testCreateRALExpressionHistoryExecuting() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryExecutingDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        "test"
                ));
        assertTrue(ralExpression.getClass().equals(RALExprHistoryExecuting.class));
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionHistoryExecutingWithNullProduct() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryExecutingDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        null
                ));
        assertTrue(ralExpression.getClass().equals(RALExprHistoryExecuting.class));
    }

    @Test(expected = BWException.class)
    public void testCreateRALExpressionHistoryExecutingWithInvalidProduct() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryExecutingDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        "invalid"
                ));
        assertTrue(ralExpression.getClass().equals(RALExprHistoryExecuting.class));
    }

    @Test
    public void testCreateRALExpressionHistoryInformed() {
        Product product = spec.getDataModel().createEntity("test", true, true);

        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprHistoryInformedDTO(
                        RALExprHistoryDTO.QuantifierDTO.MOST,
                        "test"
                ));

        assertTrue(ralExpression.getClass().equals(RALExprHistoryInformed.class));
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
                    _person1.getName()
                ));

        assertTrue(ralExpression.getClass().equals(RALExprIsPerson.class));
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

        assertTrue(ralExpression.getClass().equals(RALExprIsPersonDataObject.class));
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

        assertTrue(ralExpression.getClass().equals(RALExprIsPersonInTaskDuty.class));
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

        assertTrue(ralExpression.getClass().equals(RALExprIsPersonInTaskDuty.class));
    }

    @Test
    public void testCreateRALExpressionNot() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprNotDTO(new RALExprHasPositionDTO(_position1.getName())));
        assertTrue(ralExpression.getClass().equals(RALExprNot.class));
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
        assertTrue(ralExpression.getClass().equals(RALExprOr.class));
    }

    @Test
    public void testCreateRALExpressionReportedByPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportedByPersonPositionExprDTO(
                    new RALExprIsPersonDTO(_person1.getName()),
                    true
                ));
        assertTrue(ralExpression.getClass().equals(RALExprReportedByPersonPosition.class));
    }

    @Test
    public void testCreateRALExpressionReportedByPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportedByPositionExprDTO(_position1.getName(),true));
        assertTrue(ralExpression.getClass().equals(RALExprReportedByPosition.class));
    }

    @Test
    public void testCreateRALExpressionReportsToPersonPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportsToPersonPositionExprDTO(
                        new RALExprIsPersonDTO(_person1.getName()),
                        true
                ));
        assertTrue(ralExpression.getClass().equals(RALExprReportsToPersonPosition.class));
    }

    @Test
    public void testCreateRALExpressionReportsToPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprReportsToPositionExprDTO(_position1.getName(),true));
        assertTrue(ralExpression.getClass().equals(RALExprReportsToPosition.class));
    }

    @Test
    public void testCreateRALExpressionSharesPosition() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesPositionDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExprIsPersonDTO(_person1.getName())
                ));
        assertTrue(ralExpression.getClass().equals(RALExprSharesPosition.class));
    }

    @Test
    public void testCreateRALExpressionSharesRole() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesRoleDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExprIsPersonDTO(_person1.getName())
                ));
        assertTrue(ralExpression.getClass().equals(RALExprSharesRole.class));
    }

    @Test
    public void testCreateRALExpressionSharesUnit() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesUnitDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExprIsPersonDTO(_person1.getName())
                ));
        assertTrue(ralExpression.getClass().equals(RALExprSharesUnit.class));
    }

    @Test(expected = RMException.class)
    public void testCreateRALExpressionCommonalityWithoutPersonDTO() {
        RALExpression ralExpression = designer.createRALExpression(SPEC_ID,
                new RALExprSharesPositionDTO(
                        RALExprCommonalityDTO.AmountDTO.SOME,
                        new RALExpressionDTO() {}
                ));
        assertTrue(ralExpression.getClass().equals(RALExprSharesPosition.class));
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