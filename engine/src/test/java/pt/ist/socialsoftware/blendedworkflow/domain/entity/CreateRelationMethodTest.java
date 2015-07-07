package pt.ist.socialsoftware.blendedworkflow.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateRelationMethodTest extends BWDomainAndServiceTest {
    private static String ROLE_NAME_ONE = "Role name one";
    private static String ROLE_NAME_TWO = "Role name two";
    private static String EXISTS_NAME = "Role name exist";
    private static String EMPTY_NAME = "";

    private Entity entityOne = null;
    private Entity entityTwo = null;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification("My spec", "author",
                "description", "version", "UID");
        entityOne = new Entity(spec.getDataModel(), "Entity one name");
        entityTwo = new Entity(spec.getDataModel(), "Entity two name");
    }

    @Test
    public void success() {
        Relation relation = entityOne.createRelation(ROLE_NAME_ONE,
                Cardinality.MANY, entityTwo, ROLE_NAME_TWO, Cardinality.ONE);

        assertNotNull(relation);
        assertEquals(entityOne, relation.getEntityOne());
        assertEquals(entityTwo, relation.getEntityTwo());
        assertEquals(ROLE_NAME_ONE, relation.getRoleNameOne());
        assertEquals(ROLE_NAME_TWO, relation.getRoleNameTwo());
        assertEquals(Cardinality.MANY, relation.getCardinalityOne());
        assertEquals(Cardinality.ONE, relation.getCardinalityTwo());
    }

}
