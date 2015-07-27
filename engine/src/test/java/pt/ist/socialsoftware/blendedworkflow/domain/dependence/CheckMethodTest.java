package pt.ist.socialsoftware.blendedworkflow.domain.dependence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckMethodTest extends BWDomainAndServiceTest {

    private static final String SPEC_ID = "SpecId";
    private static final String ENT_ONE_NAME = "EntOne";
    private static final String ENT_TWO_NAME = "EntTwo";
    private static final String ENT_THREE_NAME = "EntThree";
    private static final String GROUP_ONE_NAME = "GroupOne";
    private static final String ATT_ONE_NAME = "AttOne";
    private static final String ATT_TWO_NAME = "AttTwo";
    private static final String ATT_THREE_NAME = "AttThree";
    private static final String ROLENAME_ENT_ONE = "entOne";
    private static final String ROLENAME_ENT_TWO = "entTwo";
    private static final String ROLENAME_ENT_THREE = "entThree";

    BWDataModel dataModel;
    BWEntity entOne;
    BWEntity entTwo;
    BWEntity entThree;
    BWAttribute attThree;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        dataModel = spec.getDataModel();

        entOne = new BWEntity(dataModel, ENT_ONE_NAME, false);
        entTwo = new BWEntity(dataModel, ENT_TWO_NAME, false);
        entThree = new BWEntity(dataModel, ENT_THREE_NAME, false);

        BWAttributeGroup attGroupOne = new BWAttributeGroup(dataModel, entOne,
                GROUP_ONE_NAME, true);

        BWAttribute attOne = new BWAttribute(dataModel, entOne, null,
                ATT_ONE_NAME, AttributeType.BOOLEAN, true, false, false);
        attGroupOne.addAttribute(attOne);
        BWAttribute attTwo = new BWAttribute(dataModel, entOne, null,
                ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

        attThree = new BWAttribute(dataModel, entTwo, null, ATT_THREE_NAME,
                AttributeType.STRING, true, false, false);

        BWRelation relOneThree = new BWRelation(dataModel, "relOne", entOne,
                ROLENAME_ENT_ONE, Cardinality.ONE, false, entThree,
                ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

        BWRelation relThreeTwo = new BWRelation(dataModel, "relThree", entThree,
                ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
                ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

    }

    @Test
    public void successEntitytoExternalAttribute() throws BWException {
        BWDependence dep = new BWDependence(dataModel, entOne,
                ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO
                        + "." + ATT_THREE_NAME);

        dep.check();
    }

    @Test
    public void failPrefixMIsing() throws BWException {
        try {
            BWDependence dep = new BWDependence(dataModel, entOne,
                    ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO + "."
                            + ATT_THREE_NAME);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
        }
    }

    @Test
    public void successEntitytoExternalGroupAttributeAttribute()
            throws BWException {
        BWDependence dep = new BWDependence(dataModel, entTwo,
                ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE
                        + "." + GROUP_ONE_NAME + "." + ATT_ONE_NAME);

        dep.check();
    }

    @Test
    public void successEntitytoExternalGroupAttribute() throws BWException {
        BWDependence dep = new BWDependence(dataModel, entTwo,
                ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE
                        + "." + GROUP_ONE_NAME);

        dep.check();
    }

    @Test
    public void successEntitytoExternalEntity() throws BWException {
        BWDependence dep = new BWDependence(dataModel, entTwo, ENT_TWO_NAME
                + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE);

        dep.check();
    }

    @Test
    public void successEntitytoAttributeGroupAttribute() throws BWException {
        BWDependence dep = new BWDependence(dataModel, entOne,
                ENT_ONE_NAME + "." + GROUP_ONE_NAME + "." + ATT_ONE_NAME);

        dep.check();
    }

    @Test
    public void successAttributetoExternalGroupAttribute() throws BWException {
        BWDependence dep = new BWDependence(dataModel, attThree,
                ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE
                        + "." + GROUP_ONE_NAME + "." + ATT_ONE_NAME);

        dep.check();
    }

    @Test
    public void missingAttribute() throws BWException {
        BWDependence dep = new BWDependence(dataModel, entOne,
                ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO
                        + "." + "notHere");

        try {
            dep.check();
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_PATH, bwe.getError());
            assertEquals(ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "."
                    + ROLENAME_ENT_TWO + "." + "notHere" + ":" + "[notHere]",
                    bwe.getMessage());
        }
    }

}
