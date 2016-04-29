package pt.ist.socialsoftware.blendedworkflow.domain.mulcondition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GetMulConditionMethodTest extends TeardownRollbackTest {
	private static String ROLENAME_ONE = "Role name one";
	private static String ROLENAME_TWO = "Role name two";

	RelationBW relation;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		Entity entityOne = new Entity(spec.getDataModel(), "Entity one name", false);
		Entity entityTwo = new Entity(spec.getDataModel(), "Entity two name", false);
		relation = new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);
	}

	@Test
	public void successOneCreation() {
		MulCondition mulCondition = MulCondition.getMulCondition(relation, ROLENAME_ONE);

		assertNotNull(mulCondition);
		assertEquals(1, relation.getMulConditionSet().size());
		assertEquals(ROLENAME_ONE, mulCondition.getTargetRolename());
		assertEquals(relation.getCardinalityByRolename(ROLENAME_ONE), mulCondition.getTargetCardinality());
	}

	@Test
	public void successTwoCreation() {
		MulCondition.getMulCondition(relation, ROLENAME_ONE);
		MulCondition mulCondition = MulCondition.getMulCondition(relation, ROLENAME_TWO);

		assertNotNull(mulCondition);
		assertEquals(2, relation.getMulConditionSet().size());
		assertEquals(ROLENAME_TWO, mulCondition.getTargetRolename());
		assertEquals(relation.getCardinalityByRolename(ROLENAME_TWO), mulCondition.getTargetCardinality());
	}

	@Test
	public void successThreeCreation() {
		MulCondition.getMulCondition(relation, ROLENAME_ONE);
		MulCondition.getMulCondition(relation, ROLENAME_TWO);
		MulCondition mulCondition = MulCondition.getMulCondition(relation, ROLENAME_ONE);

		assertNotNull(mulCondition);
		assertEquals(2, relation.getMulConditionSet().size());
		assertEquals(ROLENAME_ONE, mulCondition.getTargetRolename());
		assertEquals(relation.getCardinalityByRolename(ROLENAME_ONE), mulCondition.getTargetCardinality());
	}

}
