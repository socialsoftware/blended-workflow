package pt.ist.socialsoftware.blendedworkflow.engines.domain.test;

import static org.junit.Assert.*;
import jvstm.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

public class DataModelTest {
	
	private static String ENTITY_1_NAME = "Person";
	private static String ENTITY_1_ATT_1_NAME = "Name";
	private DataModelInstance dataModelInstance;
	private Entity entity1;
	private Attribute entity1Att1;
	private static Boolean ENTITY_1_ATT_1_KEY = true;
	private static AttributeType ENTITY_1_ATT_1_TYPE = AttributeType.STRING;

	private EntityInstance entityInstance1_1;
	private EntityInstance entityInstance1_2;
	private EntityInstance entityInstance1_3;

	@Before
	public void setUp() throws BlendedWorkflowException {
		Bootstrap.initTestDB();
		Transaction.begin();
		dataModelInstance = new DataModelInstance();
		
		//Entity1
		entity1 = new Entity(dataModelInstance, ENTITY_1_NAME);
		entity1Att1 = new Attribute(dataModelInstance, ENTITY_1_ATT_1_NAME, entity1, ENTITY_1_ATT_1_TYPE, ENTITY_1_ATT_1_KEY);
		
		entityInstance1_1 = new EntityInstance(dataModelInstance, entity1);
		new AttributeInstance(entity1Att1, entityInstance1_1);
		entityInstance1_2 = new EntityInstance(dataModelInstance, entity1);
		new AttributeInstance(entity1Att1, entityInstance1_2);
		entityInstance1_3 = new EntityInstance(dataModelInstance, entity1);
		new AttributeInstance(entity1Att1, entityInstance1_3);
		
		Transaction.commit();
	}
	
	@After
	public void tearDown() {
		Bootstrap.clean();
	}

	@Test
	public void test() throws BlendedWorkflowException {
		Transaction.begin();

		assertEquals(3, entity1.getEntityInstancesCount());
		
		Transaction.commit();
	}
}
