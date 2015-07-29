package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetDependencePathsTest extends BWDomainAndServiceTest {
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

    AtomicDesignInterface designInterface;

    BWSpecification spec;

    @Override
    public void populate4Test() throws BWException {
        designInterface = AtomicDesignInterface.getInstance();

        spec = new BWSpecification(SPEC_ID, SPEC_NAME, "author", "description",
                "version", "UID");

        BWEntity entityOne = new BWEntity(spec.getDataModel(), ENTITY_NAME_ONE,
                false);
        BWEntity entityTwo = new BWEntity(spec.getDataModel(), ENTITY_NAME_TWO,
                false);
        BWAttribute attributeOne = new BWAttribute(spec.getDataModel(),
                entityOne, null, ATTRIBUTE_NAME_ONE,
                BWAttribute.AttributeType.NUMBER, true, false, false);
        BWAttribute attributeTwo = new BWAttribute(spec.getDataModel(),
                entityOne, null, ATTRIBUTE_NAME_TWO,
                BWAttribute.AttributeType.STRING, false, false, false);

        new BWRelation(spec.getDataModel(), "relation", entityOne, ROLE_ONE,
                Cardinality.ZERO_OR_ONE, false, entityTwo, ROLE_TWO,
                Cardinality.ONE, false);

        new BWDependence(spec.getDataModel(), entityOne, DEPENDENCE_ONE);
        new BWDependence(spec.getDataModel(), attributeTwo, DEPENDENCE_TWO);

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
