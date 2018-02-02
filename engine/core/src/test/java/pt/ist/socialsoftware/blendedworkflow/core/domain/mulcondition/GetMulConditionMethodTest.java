package pt.ist.socialsoftware.blendedworkflow.core.domain.mulcondition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetMulConditionMethodTest extends TeardownRollbackTest {
	private static String ROLENAME_ONE = "Role name one";
	private static String ROLENAME_TWO = "Role name two";

	RelationBW relation;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");

		Entity entityOne = new Entity(spec.getDataModel(), "Entity one name", false);
		Entity entityTwo = new Entity(spec.getDataModel(), "Entity two name", false);
		this.relation = new RelationBW(spec.getDataModel(), "name", entityOne, ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ROLENAME_TWO, Cardinality.ZERO_MANY, false);
	}

	@Test
	public void successOneCreation() {
		MulCondition mulCondition = MulCondition.getMulCondition(this.relation, ROLENAME_ONE);

		assertNotNull(mulCondition);
		assertEquals(1, this.relation.getMulConditionSet().size());
		assertEquals(ROLENAME_ONE, mulCondition.getRolename());
		assertEquals(this.relation.getCardinalityByRolename(ROLENAME_ONE), mulCondition.getCardinality());
	}

	@Test
	public void successTwoCreation() {
		MulCondition.getMulCondition(this.relation, ROLENAME_ONE);
		MulCondition mulCondition = MulCondition.getMulCondition(this.relation, ROLENAME_TWO);

		assertNotNull(mulCondition);
		assertEquals(2, this.relation.getMulConditionSet().size());
		assertEquals(ROLENAME_TWO, mulCondition.getRolename());
		assertEquals(this.relation.getCardinalityByRolename(ROLENAME_TWO), mulCondition.getCardinality());
	}

	@Test
	public void successThreeCreation() {
		MulCondition.getMulCondition(this.relation, ROLENAME_ONE);
		MulCondition.getMulCondition(this.relation, ROLENAME_TWO);
		MulCondition mulCondition = MulCondition.getMulCondition(this.relation, ROLENAME_ONE);

		assertNotNull(mulCondition);
		assertEquals(2, this.relation.getMulConditionSet().size());
		assertEquals(ROLENAME_ONE, mulCondition.getRolename());
		assertEquals(this.relation.getCardinalityByRolename(ROLENAME_ONE), mulCondition.getCardinality());
	}

}
