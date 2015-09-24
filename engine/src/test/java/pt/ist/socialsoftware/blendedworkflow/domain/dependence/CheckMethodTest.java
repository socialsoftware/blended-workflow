package pt.ist.socialsoftware.blendedworkflow.domain.dependence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CheckMethodTest extends TeardownRollbackTest {

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

    DataModel dataModel;
    Entity entOne;
    Entity entTwo;
    Entity entThree;
    Attribute attThree;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        dataModel = spec.getDataModel();

        entOne = new Entity(dataModel, ENT_ONE_NAME, false);
        entTwo = new Entity(dataModel, ENT_TWO_NAME, false);
        entThree = new Entity(dataModel, ENT_THREE_NAME, false);

        AttributeGroup attGroupOne = new AttributeGroup(dataModel, entOne,
                GROUP_ONE_NAME, true);

        Attribute attOne = new Attribute(dataModel, entOne, null,
                ATT_ONE_NAME, AttributeType.BOOLEAN, true, false, false);
        attGroupOne.addAttribute(attOne);
        Attribute attTwo = new Attribute(dataModel, entOne, null,
                ATT_TWO_NAME, AttributeType.NUMBER, false, false, false);

        attThree = new Attribute(dataModel, entTwo, null, ATT_THREE_NAME,
                AttributeType.STRING, true, false, false);

        RelationBW relOneThree = new RelationBW(dataModel, "relOne", entOne,
                ROLENAME_ENT_ONE, Cardinality.ONE, false, entThree,
                ROLENAME_ENT_THREE, Cardinality.ONE_MANY, false);

        RelationBW relThreeTwo = new RelationBW(dataModel, "relThree", entThree,
                ROLENAME_ENT_THREE, Cardinality.ZERO_MANY, false, entTwo,
                ROLENAME_ENT_TWO, Cardinality.ONE_MANY, false);

    }

    @Test
    public void successEntitytoExternalAttribute() throws BWException {
        Dependence dep = new Dependence(dataModel, entOne,
                ENT_ONE_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_TWO
                        + "." + ATT_THREE_NAME);

        dep.check();
    }

    @Test
    public void failPrefixMIsing() throws BWException {
        try {
            Dependence dep = new Dependence(dataModel, entOne,
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
        Dependence dep = new Dependence(dataModel, entTwo,
                ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE
                        + "." + GROUP_ONE_NAME + "." + ATT_ONE_NAME);

        dep.check();
    }

    @Test
    public void successEntitytoExternalGroupAttribute() throws BWException {
        Dependence dep = new Dependence(dataModel, entTwo,
                ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE
                        + "." + GROUP_ONE_NAME);

        dep.check();
    }

    @Test
    public void successEntitytoExternalEntity() throws BWException {
        Dependence dep = new Dependence(dataModel, entTwo, ENT_TWO_NAME
                + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE);

        dep.check();
    }

    @Test
    public void successEntitytoAttributeGroupAttribute() throws BWException {
        Dependence dep = new Dependence(dataModel, entOne,
                ENT_ONE_NAME + "." + GROUP_ONE_NAME + "." + ATT_ONE_NAME);

        dep.check();
    }

    @Test
    public void successAttributetoExternalGroupAttribute() throws BWException {
        Dependence dep = new Dependence(dataModel, attThree,
                ENT_TWO_NAME + "." + ROLENAME_ENT_THREE + "." + ROLENAME_ENT_ONE
                        + "." + GROUP_ONE_NAME + "." + ATT_ONE_NAME);

        dep.check();
    }

    @Test
    public void missingAttribute() throws BWException {
        Dependence dep = new Dependence(dataModel, entOne,
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
