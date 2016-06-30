package pt.ist.socialsoftware.blendedworkflow.domain.entityinstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CanAssociateEntityInstanceMethodTest extends TeardownRollbackTest {
	private static final String ENT_ONE_NAME = "EntOne";
	private static final String ENT_TWO_NAME = "EntTwo";
	private static final String ROLENAME_ENT_ONE = "entOne";
	private static final String ROLENAME_ENT_TWO = "entTwo";

	Specification spec = null;
	Entity entOne;
	Entity entTwo;
	RelationBW relationOne;

	WorkflowInstance workflowInstance = null;

	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec", "author", "description", "version", "UID");
		DataModel dataModel = spec.getDataModel();
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");

		entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		entTwo = new Entity(dataModel, ENT_TWO_NAME, false);

		relationOne = new RelationBW(dataModel, "relOneTwo", entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY, false,
				entTwo, ROLENAME_ENT_TWO, "0..2", false);
	}

	@Test
	public void entityIsNotDefined() {
		Set<MulCondition> mulConditions = new HashSet<MulCondition>();
		mulConditions.add(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_ONE));
		mulConditions.add(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstance = new EntityInstance(workflowInstance, entOne);

		boolean result = entityInstance.canBeAssociatedWithNewEntityInstance(mulConditions);

		assertTrue(result);
	}

	@Test
	public void entityAssociationIsFull() {
		Set<MulCondition> mulConditions = new HashSet<MulCondition>();
		mulConditions.add(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_ONE));
		mulConditions.add(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwoOne = new EntityInstance(workflowInstance, entTwo);
		EntityInstance entityInstanceTwoTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, entityInstanceTwoOne, relationOne);
		new RelationInstance(entityInstanceOne, entityInstanceTwoTwo, relationOne);

		boolean result = entityInstanceOne.canBeAssociatedWithNewEntityInstance(mulConditions);

		assertFalse(result);
	}

	@Test
	public void entityAssociationIsAlmostFull() {
		Set<MulCondition> mulConditions = new HashSet<MulCondition>();
		mulConditions.add(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_ONE));
		mulConditions.add(MulCondition.getMulCondition(relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstanceOne = new EntityInstance(workflowInstance, entOne);
		EntityInstance entityInstanceTwo = new EntityInstance(workflowInstance, entTwo);
		new RelationInstance(entityInstanceOne, entityInstanceTwo, relationOne);

		boolean result = entityInstanceOne.canBeAssociatedWithNewEntityInstance(mulConditions);

		assertTrue(result);
	}

}
