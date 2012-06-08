package pt.ist.socialsoftware.blendedworkflow.engines.domain.test;

import static org.junit.Assert.*;
import jvstm.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.CompareAttributeToValueCondition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.ExistsOneCondition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class ExistsOneConditionTest {
	
	private static String ENTITY_1_NAME = "Person";
	private static String ENTITY_2_NAME = "Dog";
	
	private static String ENTITY_1_ATT_1_NAME = "Name";
	private static String ENTITY_2_ATT_1_NAME = "Age";
	private static Boolean ENTITY_1_ATT_1_KEY = true;
	private static Boolean ENTITY_2_ATT_1_KEY = true;
	private static AttributeType ENTITY_1_ATT_1_TYPE = AttributeType.STRING;
	private static AttributeType ENTITY_2_ATT_1_TYPE = AttributeType.STRING;
	
	private static String RELATION_1_NAME = "Person has Dog";
	private static Cardinality ENTITY_1_CARDINALITY = Cardinality.ONE;
	private static Boolean ENTITY_1_KEY = true;
	private static Cardinality ENTITY_2_CARDINALITY = Cardinality.ONE;
	private static Boolean ENTITY_2_KEY = false;
	
	private static String CONDITION_OPERATOR = "=";
	private static String CONDITION_VALUE_TRUE = "rex";
	private static String CONDITION_VALUE_FALSE = "bobby";
	
	private DataModelInstance dataModelInstance;
	private Entity entity1;
	private Attribute entity1Att1;
	private Entity entity2;
	private Attribute entity2Att1;
	private Relation relation;
	
	private EntityInstance entityInstance1_1;
	private AttributeInstance entityInstance1_1Att1;
	private EntityInstance entityInstance1_2;
	private AttributeInstance entityInstance1_2Att1;

	private EntityInstance entityInstance2_1;
	private AttributeInstance entityInstance2_1Att1;
	private EntityInstance entityInstance2_2;
	private AttributeInstance entityInstance2_2Att1;
	
	private CompareAttributeToValueCondition compareAttributeToValueCondition;
	private ExistsOneCondition existsOneCondition;

	@Before
	public void setUp() throws BlendedWorkflowException {
		Bootstrap.initTestDB();
		Transaction.begin();
		dataModelInstance = new DataModelInstance();
		
		//Entity1
		entity1 = new Entity(dataModelInstance, ENTITY_1_NAME);
		entity1Att1 = new Attribute(dataModelInstance, ENTITY_1_ATT_1_NAME, entity1, ENTITY_1_ATT_1_TYPE, ENTITY_1_ATT_1_KEY, false);
		
		entityInstance1_1 = new EntityInstance(dataModelInstance, entity1);
		entityInstance1_1Att1 = new AttributeInstance(entity1Att1, entityInstance1_1);
		entityInstance1_2 = new EntityInstance(dataModelInstance, entity1);
		entityInstance1_2Att1 = new AttributeInstance(entity1Att1, entityInstance1_2);
		
		entityInstance1_1Att1.setValue(" ");
		entityInstance1_2Att1.setValue(" ");
		
		//Entity2
		entity2 = new Entity(dataModelInstance, ENTITY_2_NAME);
		entity2Att1 = new Attribute(dataModelInstance, ENTITY_2_ATT_1_NAME, entity2, ENTITY_2_ATT_1_TYPE, ENTITY_2_ATT_1_KEY, false);
		
		entityInstance2_1 = new EntityInstance(dataModelInstance, entity2);
		entityInstance2_1Att1 = new AttributeInstance(entity2Att1, entityInstance2_1);
		entityInstance2_2 = new EntityInstance(dataModelInstance, entity2);
		entityInstance2_2Att1 = new AttributeInstance(entity2Att1, entityInstance2_2);

		//Relation
		relation = new Relation(dataModelInstance, RELATION_1_NAME, entity1, entity2, ENTITY_1_CARDINALITY, ENTITY_2_CARDINALITY, ENTITY_1_KEY, ENTITY_2_KEY);
		
		new RelationInstance(relation, entityInstance1_1, entityInstance2_1, entityInstance1_1.getNewRelationInstanceID());
		new RelationInstance(relation, entityInstance1_2, entityInstance2_2, entityInstance1_2.getNewRelationInstanceID());

		//Condition
		compareAttributeToValueCondition = new CompareAttributeToValueCondition(entity2Att1,CONDITION_OPERATOR, CONDITION_VALUE_TRUE);
		existsOneCondition = new ExistsOneCondition(relation, entity2, compareAttributeToValueCondition);
		
		Transaction.commit();
	}
	
	@After
	public void tearDown() {
		Bootstrap.clean();
	}

	@Test
	public void evaluateToFalse() throws BlendedWorkflowException {
		Transaction.begin();
		entityInstance2_1Att1.setState(DataState.UNDEFINED);
		entityInstance2_2Att1.setState(DataState.UNDEFINED);
		assertEquals(TripleStateBool.FALSE, existsOneCondition.evaluateWithDataModel(null, null, null));
		
		entityInstance2_1Att1.setValue(CONDITION_VALUE_FALSE);
		entityInstance2_1Att1.setState(DataState.DEFINED);
		entityInstance2_2Att1.setValue(CONDITION_VALUE_FALSE);
		entityInstance2_2Att1.setState(DataState.DEFINED);
		assertEquals(TripleStateBool.FALSE, existsOneCondition.evaluateWithDataModel(null, null, null));
		Transaction.commit();
	}
	
	@Test
	public void evaluateToTrue() throws BlendedWorkflowException {
		Transaction.begin();
		entityInstance2_1Att1.setValue(CONDITION_VALUE_TRUE);
		entityInstance2_1Att1.setState(DataState.DEFINED);
		entityInstance2_2Att1.setValue(CONDITION_VALUE_FALSE);
		entityInstance2_2Att1.setState(DataState.DEFINED);
		assertEquals(TripleStateBool.TRUE, existsOneCondition.evaluateWithDataModel(null, null, null));
		
		entityInstance2_1Att1.setValue(CONDITION_VALUE_FALSE);
		entityInstance2_1Att1.setState(DataState.DEFINED);
		entityInstance2_2Att1.setValue(CONDITION_VALUE_TRUE);
		entityInstance2_2Att1.setState(DataState.DEFINED);
		assertEquals(TripleStateBool.TRUE, existsOneCondition.evaluateWithDataModel(null, null, null));
		
		entityInstance2_1Att1.setState(DataState.SKIPPED);
		entityInstance2_2Att1.setValue(CONDITION_VALUE_TRUE);
		entityInstance2_2Att1.setState(DataState.DEFINED);
		assertEquals(TripleStateBool.TRUE, existsOneCondition.evaluateWithDataModel(null, null, null));
		Transaction.commit();
	}
}
