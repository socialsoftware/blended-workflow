package pt.ist.socialsoftware.blendedworkflow.domain.relationinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateRelationInstanceTest extends TeardownRollbackTest {
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
		relation = new RelationBW(spec.getDataModel(), NAME, entityOne, "entOne", Cardinality.ONE_MANY, false,
				entityTwo, "entTwo", Cardinality.ONE, false);

		entityInstanceOne = new EntityInstance(workflowInstance, entityOne);
		entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo);
	}

	@Test
	public void success() {
		RelationInstance relationInstance = new RelationInstance(entityInstanceOne, entityInstanceTwo, relation);

		assertEquals(entityOne, relationInstance.getEntityInstanceOne().getEntity());
		assertEquals(entityTwo, relationInstance.getEntityInstanceTwo().getEntity());
		assertEquals(relation, relationInstance.getRelationType());
	}

	@Test
	public void failWorkflowInstance() {
		EntityInstance entityInstanceOther = new EntityInstance(new WorkflowInstance(spec, "other"), entityOne);

		try {
			new RelationInstance(entityInstanceOther, entityInstanceTwo, relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CREATE_RELATION_INSTANCE, bwe.getError());
			assertEquals(entityInstanceOne.getEntity().getName() + ":" + entityInstanceTwo.getEntity().getName(),
					bwe.getMessage());
		}
	}

	@Test
	public void failEntityOne() {
		try {
			new RelationInstance(entityInstanceTwo, entityInstanceTwo, relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CREATE_RELATION_INSTANCE, bwe.getError());
			assertEquals(entityInstanceTwo.getEntity().getName() + ":" + relation.getEntityOne().getName(),
					bwe.getMessage());
		}
	}

	@Test
	public void failEntityTwo() {
		try {
			new RelationInstance(entityInstanceOne, entityInstanceOne, relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.CREATE_RELATION_INSTANCE, bwe.getError());
			assertEquals(entityInstanceOne.getEntity().getName() + ":" + relation.getEntityTwo().getName(),
					bwe.getMessage());
		}
	}

}
