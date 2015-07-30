package pt.ist.socialsoftware.blendedworkflow.domain.mulcondition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.MULCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetMulConditionMethodTest extends TeardownRollbackTest {
    private static String ROLENAME_ONE = "Role name one";
    private static String ROLENAME_TWO = "Role name two";

    BWRelation relation;

    @Override
    public void populate4Test() throws BWException {
        BWSpecification spec = new BWSpecification("SpecId", "My spec",
                "author", "description", "version", "UID");

        BWEntity entityOne = new BWEntity(spec.getDataModel(),
                "Entity one name", false);
        BWEntity entityTwo = new BWEntity(spec.getDataModel(),
                "Entity two name", false);
        relation = new BWRelation(spec.getDataModel(), "name", entityOne,
                ROLENAME_ONE, Cardinality.ONE, false, entityTwo, ROLENAME_TWO,
                Cardinality.ZERO_MANY, false);
    }

    @Test
    public void successOneCreation() {
        MULCondition mulCondition = MULCondition.getMulCondition(relation,
                ROLENAME_ONE);

        assertNotNull(mulCondition);
        assertEquals(1, relation.getMulConditionSet().size());
        assertEquals(ROLENAME_ONE, mulCondition.getRolename());
        assertEquals(relation.getCardinalityByRolename(ROLENAME_ONE),
                mulCondition.getCardinality());
    }

    @Test
    public void successTwoCreation() {
        MULCondition.getMulCondition(relation, ROLENAME_ONE);
        MULCondition mulCondition = MULCondition.getMulCondition(relation,
                ROLENAME_TWO);

        assertNotNull(mulCondition);
        assertEquals(2, relation.getMulConditionSet().size());
        assertEquals(ROLENAME_TWO, mulCondition.getRolename());
        assertEquals(relation.getCardinalityByRolename(ROLENAME_TWO),
                mulCondition.getCardinality());
    }

    @Test
    public void successThreeCreation() {
        MULCondition.getMulCondition(relation, ROLENAME_ONE);
        MULCondition.getMulCondition(relation, ROLENAME_TWO);
        MULCondition mulCondition = MULCondition.getMulCondition(relation,
                ROLENAME_ONE);

        assertNotNull(mulCondition);
        assertEquals(2, relation.getMulConditionSet().size());
        assertEquals(ROLENAME_ONE, mulCondition.getRolename());
        assertEquals(relation.getCardinalityByRolename(ROLENAME_ONE),
                mulCondition.getCardinality());
    }

}
