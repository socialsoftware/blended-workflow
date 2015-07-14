package pt.ist.socialsoftware.blendedworkflow.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateRelationMethodTest extends BWDomainAndServiceTest {
    private static String ROLE_NAME_ONE = "Role name one";
    private static String ROLE_NAME_TWO = "Role name two";
    private static String EXISTS_ROLE_NAME = "Role name exist";
    private static String EMPTY_NAME = "";

    private BWEntity entityOne = null;
    private BWEntity entityTwo = null;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification("SpecId", "My spec",
                "author", "description", "version", "UID");
        entityOne = new BWEntity(spec.getDataModel(), "Entity one name", false);
        entityTwo = new BWEntity(spec.getDataModel(), "Entity two name", false);
        new BWRelation(spec.getDataModel(), "name", entityOne, EXISTS_ROLE_NAME,
                Cardinality.ONE, false, entityTwo, EXISTS_ROLE_NAME,
                Cardinality.ZERO_MANY, false);
    }

    @Test
    public void success() {
        BWRelation relation = entityOne.createRelation(ROLE_NAME_ONE,
                Cardinality.ZERO_MANY, entityTwo, ROLE_NAME_TWO,
                Cardinality.ONE);

        assertNotNull(relation);
        assertEquals(entityOne, relation.getEntityOne());
        assertEquals(entityTwo, relation.getEntityTwo());
        assertEquals(ROLE_NAME_ONE, relation.getRoleNameOne());
        assertEquals(ROLE_NAME_TWO, relation.getRoleNameTwo());
        assertEquals(Cardinality.ZERO_MANY, relation.getCardinalityOne());
        assertEquals(Cardinality.ONE, relation.getCardinalityTwo());
    }

    @Test
    public void duplicatedRoleName1() {
        try {
            entityOne.createRelation(EXISTS_ROLE_NAME, Cardinality.ZERO_MANY,
                    entityTwo, ROLE_NAME_TWO, Cardinality.ONE);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ROLE_NAME, bwe.getError());
            assertEquals(2, entityOne.getRelationsSet().size());
        }
    }

    @Test
    public void duplicatedRoleName2() {
        try {
            entityOne.createRelation(ROLE_NAME_ONE, Cardinality.ZERO_OR_ONE,
                    entityTwo, EXISTS_ROLE_NAME, Cardinality.ONE);
            fail();
        } catch (BWException bwe) {
            assertEquals(BWErrorType.INVALID_ROLE_NAME, bwe.getError());
            assertEquals(2, entityOne.getRelationsSet().size());
        }
    }

}
