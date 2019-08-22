package pt.ist.socialsoftware.blendedworkflow.core.domain.relationinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec");
		this.workflowInstance = new WorkflowInstance(this.spec, NAME);
		this.entityOne = new Entity(this.spec.getDataModel(), "entityNameOne", false);
		this.entityTwo = new Entity(this.spec.getDataModel(), "entityNameTwo", false);
		this.relation = new RelationBW(this.spec.getDataModel(), NAME, this.entityOne, ROLENAME_ENT_ONE,
				Cardinality.ONE_MANY, false, this.entityTwo, "entTwo", Cardinality.ONE, false);

		this.entityInstanceOne = new EntityInstance(this.workflowInstance, this.entityOne, ProductInstanceState.DEFINED);
		this.entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entityTwo, ProductInstanceState.DEFINED);
	}

	@Test
	public void success() {
		RelationInstance relationInstance = new RelationInstance(this.entityInstanceOne, ROLENAME_ENT_ONE,
				this.entityInstanceTwo, ROLENAME_ENT_TWO, this.relation);

		assertEquals(this.entityOne, relationInstance.getEntityInstanceOne().getEntity());
		assertEquals(this.entityTwo, relationInstance.getEntityInstanceTwo().getEntity());
		assertEquals(this.relation, relationInstance.getRelationType());
	}

	@Test
	public void failWorkflowInstance() {
		EntityInstance entityInstanceOther = new EntityInstance(new WorkflowInstance(this.spec, "other"),
				this.entityOne, ProductInstanceState.DEFINED);

		try {
			new RelationInstance(entityInstanceOther, ROLENAME_ENT_ONE, this.entityInstanceTwo, ROLENAME_ENT_TWO,
					this.relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Different workflow instances " + this.entityInstanceOne.getEntity().getName() + " - "
					+ this.entityInstanceTwo.getEntity().getName(), bwe.getMessage());
		}
	}

	@Test
	public void failEntityOne() {
		try {
			new RelationInstance(this.entityInstanceTwo, ROLENAME_ENT_ONE, this.entityInstanceTwo, ROLENAME_ENT_TWO,
					this.relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Rolename and type do not match for rolename " + ROLENAME_ENT_ONE + " of entity type "
					+ this.entityInstanceTwo.getEntity().getName(), bwe.getMessage());
		}
	}

	@Test
	public void failEntityTwo() {
		try {
			new RelationInstance(this.entityInstanceOne, ROLENAME_ENT_ONE, this.entityInstanceOne, ROLENAME_ENT_TWO,
					this.relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Rolename and type do not match for rolename " + ROLENAME_ENT_TWO + " of entity type "
					+ this.entityInstanceOne.getEntity().getName(), bwe.getMessage());
		}
	}

	@Test
	public void failRelationMaxCardinality() {
		new RelationInstance(this.entityInstanceOne, ROLENAME_ENT_ONE, this.entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relation);
		try {
			new RelationInstance(this.entityInstanceOne, ROLENAME_ENT_ONE,
					new EntityInstance(this.workflowInstance, this.entityTwo, ProductInstanceState.DEFINED), 
					ROLENAME_ENT_TWO, this.relation);
			fail();
		} catch (BWException bwe) {
			assertEquals(BWErrorType.RELATIONINSTANCE_CONSISTENCY, bwe.getError());
			assertEquals("Number of instances 2 > 1", bwe.getMessage());
		}
	}

}
