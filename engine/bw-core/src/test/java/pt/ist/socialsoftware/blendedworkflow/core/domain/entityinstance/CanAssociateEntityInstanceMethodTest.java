package pt.ist.socialsoftware.blendedworkflow.core.domain.entityinstance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

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
		this.spec = new Specification("SpecId", "My spec");
		DataModel dataModel = this.spec.getDataModel();
		this.workflowInstance = new WorkflowInstance(this.spec, "WorkflowInstanceName");

		this.entOne = new Entity(dataModel, ENT_ONE_NAME, false);
		this.entTwo = new Entity(dataModel, ENT_TWO_NAME, false);

		this.relationOne = new RelationBW(dataModel, "relOneTwo", this.entOne, ROLENAME_ENT_ONE, Cardinality.ONE_MANY,
				false, this.entTwo, ROLENAME_ENT_TWO, "0..2", false);
	}

	@Test
	public void entityIsNotDefined() {
		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.add(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_ONE));
		mulConditions.add(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstance = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);

		boolean result = entityInstance.canBeAssociatedWithNewEntityInstance(mulConditions);

		assertTrue(result);
	}

	@Test
	public void entityAssociationIsFull() {
		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.add(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_ONE));
		mulConditions.add(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwoOne = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwoTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoOne, ROLENAME_ENT_TWO,
				this.relationOne);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwoTwo, ROLENAME_ENT_TWO,
				this.relationOne);

		boolean result = entityInstanceOne.canBeAssociatedWithNewEntityInstance(mulConditions);

		assertFalse(result);
	}

	@Test
	public void entityAssociationIsAlmostFull() {
		Set<MulCondition> mulConditions = new HashSet<>();
		mulConditions.add(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_ONE));
		mulConditions.add(MulCondition.getMulCondition(this.relationOne, ROLENAME_ENT_TWO));

		EntityInstance entityInstanceOne = new EntityInstance(this.workflowInstance, this.entOne, ProductInstanceState.DEFINED);
		EntityInstance entityInstanceTwo = new EntityInstance(this.workflowInstance, this.entTwo, ProductInstanceState.DEFINED);
		new RelationInstance(entityInstanceOne, ROLENAME_ENT_ONE, entityInstanceTwo, ROLENAME_ENT_TWO,
				this.relationOne);

		boolean result = entityInstanceOne.canBeAssociatedWithNewEntityInstance(mulConditions);

		assertTrue(result);
	}

}
