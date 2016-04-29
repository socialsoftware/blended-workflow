package pt.ist.socialsoftware.blendedworkflow.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
		entityOne = new Entity(spec.getDataModel(), "Entity one name", false);
		entityTwo = new Entity(spec.getDataModel(), "Entity two name", false);
		new RelationBW(spec.getDataModel(), "name", entityOne, EXISTS_ROLE_NAME, Cardinality.ONE, false, entityTwo,
				EXISTS_ROLE_NAME, Cardinality.ZERO_MANY, false);
	}

	@Test
	public void success() {
		RelationBW relation = entityOne.createRelation(RELATION_NAME, ROLE_NAME_ONE, Cardinality.ZERO_MANY, entityTwo,
				ROLE_NAME_TWO, Cardinality.ONE);

		assertNotNull(relation);
		assertEquals(entityOne, relation.getEntityOne());
		assertEquals(entityTwo, relation.getEntityTwo());
		assertEquals(ROLE_NAME_ONE, relation.getRoleNameOne());
		assertEquals(ROLE_NAME_TWO, relation.getRoleNameTwo());
		assertEquals(Cardinality.ZERO_MANY, relation.getCardinalityOne().getExp());
		assertEquals(Cardinality.ONE, relation.getCardinalityTwo().getExp());
	}

	@Test
	public void duplicatedRoleName1() {
		try {
			entityOne.createRelation(RELATION_NAME, EXISTS_ROLE_NAME, Cardinality.ZERO_MANY, entityTwo, ROLE_NAME_TWO,
					Cardinality.ONE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
			assertEquals(2, entityOne.getRelationSet().size());
		}
	}

	@Test
	public void duplicatedRoleName2() {
		try {
			entityOne.createRelation(RELATION_NAME, ROLE_NAME_ONE, Cardinality.ZERO_OR_ONE, entityTwo, EXISTS_ROLE_NAME,
					Cardinality.ONE);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.DUPLICATE_NAME, bwe.getError());
			assertEquals(2, entityOne.getRelationSet().size());
		}
	}

}
