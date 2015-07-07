package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.BWDomainAndServiceTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateRelationServiceTest extends BWDomainAndServiceTest {
    private static final String SPEC_NAME = "Spec Name";
    private static final String ENTITY_ONE_NAME = "Entity Name One";
    private static final String ENTITY_TWO_NAME = "Entity Name Two";
    private static final String ROLENAME_ONE = "Rolename Name One";
    private static final String ROLENAME_TWO = "Rolename Name Two";
    private static final String ONE = "1";
    private static final String MANY = "*";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    Entity entityOne;
    Entity entityTwo;

    @Override
    public void populate4Test() throws BWException {
        Specification spec = new Specification(SPEC_NAME, "author",
                "description", "version", "UID");
        entityOne = new Entity(spec.getDataModel(), ENTITY_ONE_NAME);
        entityTwo = new Entity(spec.getDataModel(), ENTITY_TWO_NAME);
    }

    @Test
    public void success() throws BWException {
        CreateRelationService service = new CreateRelationService(SPEC_NAME,
                ENTITY_ONE_NAME, ROLENAME_ONE, ONE, ENTITY_TWO_NAME,
                ROLENAME_TWO, MANY);
        service.execute();

        Relation relation = entityOne.getRelationsOneSet().stream()
                .filter(rel -> rel.getRoleNameOne().equals(ROLENAME_ONE))
                .findFirst().orElse(null);

        assertNotNull(relation);
        assertEquals(ENTITY_ONE_NAME, relation.getEntityOne().getName());
        assertEquals(ROLENAME_ONE, relation.getRoleNameOne());
        assertEquals(Cardinality.ONE, relation.getCardinalityOne());
        assertEquals(ENTITY_TWO_NAME, relation.getEntityTwo().getName());
        assertEquals(ROLENAME_TWO, relation.getRoleNameTwo());
        assertEquals(Cardinality.MANY, relation.getCardinalityTwo());
    }

}
