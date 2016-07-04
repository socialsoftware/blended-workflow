package pt.ist.socialsoftware.blendedworkflow.domain.relationinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateRelationInstanceTest extends TeardownRollbackTest {
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";
	private static final String NAME = "name";
	private static final String OTHER_ENTITY_NAME = "otherEntityName";

	Specification spec = null;
	Entity entityOne = null;
	Entity entityTwo = null;
	RelationBW relation = null;
	WorkflowInstance workflowInstance = null;
	EntityInstance entityInstanceOne = null;
	EntityInstance entityInstanceTwo = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		workflowInstance = new WorkflowInstance(spec, NAME);
		entityOne = new Entity(spec.getDataModel(), "entityNameOne", false);
		entityTwo = new Entity(spec.getDataModel(), "entityNameTwo", false);
		relation = new RelationBW(spec.getDataModel(), NAME, entityOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false,
				entityTwo, "entTwo", Cardinality.ONE, false);

		entityInstanceOne = new EntityInstance(workflowInstance, entityOne);
		entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
	}

	@Test
	public void success() {
		RelationInstance relationInstance = new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo,
				ROLENAME_ENT_TWO, relation);

		assertEquals(entityOne, relationInstance.getEntityInstanceOne().getEntity());
		assertEquals(entityTwo, relationInstance.getEntityInstanceTwo().getEntity());
		assertEquals(relation, relationInstance.getRelationType());
	}

	@Test
	public void failWorkflowInstance() {
		EntityInstance entityInstanceOther = new EntityInstance(new WorkflowInstance(spec, "other"), entityOne);

		try {
			new RelationInstance(entityInstanceOther, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO, relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Different workflow instances " + entityInstanceOne.getEntity().getName() + " - "
					+ entityInstanceTwo.getEntity().getName(), bwe.getMessage());
		}
	}

	@Test
	public void failEntityOne() {
		try {
			new RelationInstance(entityInstanceTwo, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO, relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Rolename and type do not match for rolename " + ROLENAME_ENT_ONE + " of entity type "
					+ entityInstanceTwo.getEntity().getName(), bwe.getMessage());
		}
	}

	@Test
	public void failEntityTwo() {
		try {
			new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceOne, ROLENAME_ENT_TWO, relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Rolename and type do not match for rolename " + ROLENAME_ENT_TWO + " of entity type "
					+ entityInstanceOne.getEntity().getName(), bwe.getMessage());
		}
	}

	@Test
	public void failRelationMaxCardinality() {
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO, relation);
		try {
			new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, new EntityInstance(workflowInstance, entityTwo),
					ROLENAME_ENT_TWO, relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Number of instances 2 > 1", bwe.getMessage());
		}
	}

}
