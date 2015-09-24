package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetDependencePathsTest extends TeardownRollbackTest {
    private static final String ROLE_TWO = "role2";
    private static final String ROLE_ONE = "role1";
    private static final String SPEC_ID = "ID0";
    private static final String SPEC_NAME = "Old Doctor Appointment Specification";
    private static final String ENTITY_NAME_TWO = "Entity Name";
    private static final String ENTITY_NAME_ONE = "Exists Entity Name";
    private static final String ATTRIBUTE_NAME_ONE = "Exists Attribute Name";
    private static final String ATTRIBUTE_NAME_TWO = "Exists Attribute Name String";
    private static final String ATTRIBUTE_GROUP_NAME = "Attribute Group Name";
    private static final String DEPENDENCE_ONE = ENTITY_NAME_ONE + "."
            + "Dependence One";
    private static final String DEPENDENCE_TWO = ENTITY_NAME_ONE + "."
            + "Dependence Two";

    DesignInterface designInterface;

    Specification spec;

    @Override
    public void populate4Test() throws BWException {
        designInterface = DesignInterface.getInstance();

        spec = new Specification(SPEC_ID, SPEC_NAME, "author", "description",
                "version", "UID");

        Entity entityOne = new Entity(spec.getDataModel(), ENTITY_NAME_ONE,
                false);
        Entity entityTwo = new Entity(spec.getDataModel(), ENTITY_NAME_TWO,
                false);
        Attribute attributeOne = new Attribute(spec.getDataModel(),
                entityOne, null, ATTRIBUTE_NAME_ONE, AttributeType.NUMBER, true,
                false, false);
        Attribute attributeTwo = new Attribute(spec.getDataModel(),
                entityOne, null, ATTRIBUTE_NAME_TWO, AttributeType.STRING,
                false, false, false);

        new RelationBW(spec.getDataModel(), "relation", entityOne, ROLE_ONE,
                Cardinality.ZERO_OR_ONE, false, entityTwo, ROLE_TWO,
                Cardinality.ONE, false);

        new Dependence(spec.getDataModel(), entityOne, DEPENDENCE_ONE);
        new Dependence(spec.getDataModel(), attributeTwo, DEPENDENCE_TWO);

    }

    @Test
    public void success() throws BWException {
        Set<String> conditions = new HashSet<String>();
        conditions.add(ENTITY_NAME_ONE);
        conditions.add(ENTITY_NAME_ONE + "." + ATTRIBUTE_NAME_TWO);

        Set<String> dependences = designInterface.getDependencePaths(SPEC_ID,
                conditions);

        assertEquals(2, dependences.size());
        assertTrue(dependences.contains(DEPENDENCE_ONE));
        assertTrue(dependences.contains(DEPENDENCE_TWO));
    }

    @Test
    public void successAttribute() throws BWException {
        Set<String> conditions = new HashSet<String>();
        conditions.add(ENTITY_NAME_ONE + "." + ATTRIBUTE_NAME_TWO);

        Set<String> dependences = designInterface.getDependencePaths(SPEC_ID,
                conditions);

        assertEquals(1, dependences.size());
        assertTrue(dependences.contains(DEPENDENCE_TWO));
    }

}
