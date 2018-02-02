package pt.ist.socialsoftware.blendedworkflow.core.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class CreateRelationMethodTest extends TeardownRollbackTest {
	private static String RELATION_NAME = "Relation name";
	private static String ROLE_NAME_ONE = "Role name one";
	private static String ROLE_NAME_TWO = "Role name two";
	private static String EXISTS_ROLE_NAME = "Role name exist";
	private static String EMPTY_NAME = "";

	private Entity entityOne = null;
	private Entity entityTwo = null;

	@Override
	public void populate4Test() throws BWException {
		Specification spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		this.entityOne = new Entity(spec.getDataModel(), "Entity one name", false);
		this.entityTwo = new Entity(spec.getDataModel(), "Entity two name", false);
		new RelationBW(spec.getDataModel(), "name", this.entityOne, EXISTS_ROLE_NAME, Cardinality.ONE, false,
				this.entityTwo, EXISTS_ROLE_NAME, Cardinality.ZERO_MANY, false);
	}

	@Test
	public void success() {
		RelationBW relation = this.entityOne.createRelation(RELATION_NAME, ROLE_NAME_ONE, Cardinality.ZERO_MANY,
				this.entityTwo, ROLE_NAME_TWO, Cardinality.ONE);

		assertNotNull(relation);
		assertEquals(this.entityOne, relation.getEntityOne());
		assertEquals(this.entityTwo, relation.getEntityTwo());
		assertEquals(ROLE_NAME_ONE, relation.getRolenameOne());
		assertEquals(ROLE_NAME_TWO, relation.getRolenameTwo());
		assertEquals(Cardinality.ZERO_MANY, relation.getCardinalityOne().getExp());
		assertEquals(Cardinality.ONE, relation.getCardinalityTwo().getExp());
	}

	@Test
	public void duplicatedRoleName1() {
		try {
			this.entityOne.createRelation(RELATION_NAME, EXISTS_ROLE_NAME, Cardinality.ZERO_MANY, this.entityTwo,
					ROLE_NAME_TWO, Cardinality.ONE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
			assertEquals(2, this.entityOne.getRelationSet().size());
		}
	}

	@Test
	public void duplicatedRoleName2() {
		try {
			this.entityOne.createRelation(RELATION_NAME, ROLE_NAME_ONE, Cardinality.ZERO_OR_ONE, this.entityTwo,
					EXISTS_ROLE_NAME, Cardinality.ONE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
			assertEquals(2, this.entityOne.getRelationSet().size());
		}
	}

}
