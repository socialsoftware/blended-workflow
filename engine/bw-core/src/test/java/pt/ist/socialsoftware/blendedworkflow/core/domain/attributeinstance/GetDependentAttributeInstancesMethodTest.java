package pt.ist.socialsoftware.blendedworkflow.core.domain.attributeinstance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.core.TeardownRollbackTest;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance.ProductInstanceState;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;

public class GetDependentAttributeInstancesMethodTest extends TeardownRollbackTest {
	private static final String ENTITY_NAME_ONE = "EntityOne";
	private static final String ENTITY_NAME_TWO = "EntityTwo";
	private static final String ENTITY_ROLENAME_ONE = "entityone";
	private static final String ENTITY_ROLENAME_TWO = "entitytwo";
	private static final String ATTRIBUTE_NAME_ONE = "attributeone";
	private static final String ATTRIBUTE_NAME_TWO = "attributetwo";

	private static String DEPENDENCE = ENTITY_NAME_ONE + "." + ENTITY_ROLENAME_TWO + "." + ATTRIBUTE_NAME_TWO;

	private Specification spec = null;
	private Entity entityOne, entityTwo;
	private Attribute attributeOne, attributeTwo;
	private EntityInstance entityInstanceOne, entityInstanceTwo;
	private AttributeInstance attributeInstanceOne, attributeInstanceTwo;
	private WorkflowInstance workflowInstance = null;
	
	@Override
	public void populate4Test() throws BWException {
		spec = new Specification("SpecId", "My spec");
		workflowInstance = new WorkflowInstance(spec, "WorkflowInstanceName");
		
		entityOne = new Entity(spec.getDataModel(), ENTITY_NAME_ONE, false);
		attributeOne = new Attribute(spec.getDataModel(), entityOne, ATTRIBUTE_NAME_ONE, AttributeType.NUMBER, true);
		entityTwo = new Entity(spec.getDataModel(), ENTITY_NAME_TWO, false);
		attributeTwo = new Attribute(spec.getDataModel(), entityTwo, ATTRIBUTE_NAME_TWO, AttributeType.NUMBER, true);

		new RelationBW(spec.getDataModel(), "name", entityOne, ENTITY_ROLENAME_ONE, Cardinality.ONE, false,
				entityTwo, ENTITY_ROLENAME_TWO, Cardinality.ZERO_MANY, false);
		
		attributeOne.createDependence(DEPENDENCE);
		
		entityInstanceOne = new EntityInstance(workflowInstance, entityOne, ProductInstanceState.DEFINED);
		attributeInstanceOne = new AttributeInstance(entityInstanceOne, attributeOne, "1",  ProductInstanceState.DEFINED);
		
		entityInstanceTwo = new EntityInstance(workflowInstance, entityTwo, ProductInstanceState.DEFINED);
		attributeInstanceTwo = new AttributeInstance(entityInstanceTwo, attributeTwo, "2",  ProductInstanceState.DEFINED);
	
		// This proves the dependence is correctly created
		// spec.getDataModel().checkDependences();
	}

	@Test
	public void success() {
		assertEquals(attributeInstanceOne.getDependentAttributeInstances(workflowInstance).size(), 1);
	}
}
