package pt.ist.socialsoftware.blendedworkflow.service.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;

public class CreateRelationServiceTest extends TeardownRollbackTest {
    private static final String SPEC_ID = "Spec ID";
    private static final String RELATION_NAME = "Relation name";
    private static final String ENTITY_ONE_NAME = "Entity Name One";
    private static final String ENTITY_TWO_NAME = "Entity Name Two";
    private static final String ROLENAME_ONE = "Rolename Name One";
    private static final String ROLENAME_TWO = "Rolename Name Two";
    private static final String ONE = "1";
    private static final String MANY = "*";
    private static final String NON_EXIST = "No Name";
    private static final String DUP_NAME = "Exists Name";
    private static final String EMPTY_NAME = "";

    String dataModelExtId;
    BWEntity entityOne;
    BWEntity entityTwo;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification(SPEC_ID, "name", "author",
                "description", "version", "UID");
        dataModelExtId = spec.getDataModel().getExternalId();
        entityOne = new BWEntity(spec.getDataModel(), ENTITY_ONE_NAME, false);
        entityTwo = new BWEntity(spec.getDataModel(), ENTITY_TWO_NAME, false);
    }

    @Test
    public void success() throws BWException {

        BWNotification notification = DesignInterface.getInstance()
                .createRelation(new RelationDTO(SPEC_ID, RELATION_NAME,
                        entityOne.getExternalId(), ROLENAME_ONE, ONE,
                        entityTwo.getExternalId(), ROLENAME_TWO, MANY));

        assertFalse(notification.hasErrors());

        BWRelation relation = entityOne.getRelationsOneSet().stream()
                .filter(rel -> rel.getRoleNameOne().equals(ROLENAME_ONE))
                .findFirst().orElse(null);

        assertNotNull(relation);
        assertEquals(ENTITY_ONE_NAME, relation.getEntityOne().getName());
        assertEquals(ROLENAME_ONE, relation.getRoleNameOne());
        assertEquals(Cardinality.ONE, relation.getCardinalityOne());
        assertEquals(ENTITY_TWO_NAME, relation.getEntityTwo().getName());
        assertEquals(ROLENAME_TWO, relation.getRoleNameTwo());
        assertEquals(Cardinality.ZERO_MANY, relation.getCardinalityTwo());
    }

}
