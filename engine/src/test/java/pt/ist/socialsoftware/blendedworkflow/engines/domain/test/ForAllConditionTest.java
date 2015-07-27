package pt.ist.socialsoftware.blendedworkflow.engines.domain.test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.ForAllCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class ForAllConditionTest extends AbstractDomainTest {

    private static String ENTITY_1_NAME = "Person";
    private static String ENTITY_2_NAME = "Dog";

    private static String ENTITY_1_ATT_1_NAME = "Name";
    private static String ENTITY_2_ATT_1_NAME = "Nickname";
    private static Boolean ENTITY_1_ATT_1_KEY = true;
    private static Boolean ENTITY_2_ATT_1_KEY = true;
    private static AttributeType ENTITY_1_ATT_1_TYPE = AttributeType.STRING;
    private static AttributeType ENTITY_2_ATT_1_TYPE = AttributeType.STRING;

    private static String RELATION_1_NAME = "Person has Dog";
    private static Cardinality ENTITY_1_CARDINALITY = Cardinality.ONE;
    private static Boolean ENTITY_1_KEY = true;
    private static Cardinality ENTITY_2_CARDINALITY = Cardinality.ONE;
    private static Boolean ENTITY_2_KEY = false;

    private DataModelInstance dataModelInstance;
    private BWEntity entity1;
    private BWAttribute entity1Att1;
    private BWEntity entity2;
    private BWAttribute entity2Att1;
    private BWRelation relation;

    private EntityInstance entityInstance1_1;
    private AttributeInstance entityInstance1_1Att1;
    private EntityInstance entityInstance1_2;
    private AttributeInstance entityInstance1_2Att1;

    private EntityInstance entityInstance2_1;
    private AttributeInstance entityInstance2_1Att1;
    private EntityInstance entityInstance2_2;
    private AttributeInstance entityInstance2_2Att1;

    private DEFEntityCondition existsEntityCondition;
    private ForAllCondition forAllCondition;

    @Override
    protected void populate4DomainTest() throws BWException {
        dataModelInstance = new DataModelInstance();

        // Entity1
        entity1 = new BWEntity(dataModelInstance, ENTITY_1_NAME, false);
        entity1Att1 = new BWAttribute(dataModelInstance, entity1, null,
                ENTITY_1_ATT_1_NAME, ENTITY_1_ATT_1_TYPE, false,
                ENTITY_1_ATT_1_KEY, false);

        entityInstance1_1 = new EntityInstance(entity1);
        entityInstance1_1Att1 = new AttributeInstance(entity1Att1,
                entityInstance1_1);
        entityInstance1_2 = new EntityInstance(entity1);
        entityInstance1_2Att1 = new AttributeInstance(entity1Att1,
                entityInstance1_2);

        entityInstance1_1Att1.setValue(" ");
        entityInstance1_2Att1.setValue(" ");

        // Entity2
        entity2 = new BWEntity(dataModelInstance, ENTITY_2_NAME, false);
        entity2Att1 = new BWAttribute(dataModelInstance, entity2, null,
                ENTITY_2_ATT_1_NAME, ENTITY_2_ATT_1_TYPE, false,
                ENTITY_2_ATT_1_KEY, false);

        entityInstance2_1 = new EntityInstance(entity2);
        entityInstance2_1Att1 = new AttributeInstance(entity2Att1,
                entityInstance2_1);
        entityInstance2_2 = new EntityInstance(entity2);
        entityInstance2_2Att1 = new AttributeInstance(entity2Att1,
                entityInstance2_2);

        // Relation
        relation = new BWRelation(dataModelInstance, RELATION_1_NAME, entity1,
                entity1.getClass().getName(), ENTITY_1_CARDINALITY,
                ENTITY_1_KEY, entity2, entity2.getClass().getName(),
                ENTITY_2_CARDINALITY, ENTITY_2_KEY);

        new RelationInstance(relation, entityInstance1_1, entityInstance2_1,
                entityInstance1_1.getNewRelationInstanceID());
        new RelationInstance(relation, entityInstance1_2, entityInstance2_2,
                entityInstance1_2.getNewRelationInstanceID());

        // Condition
        existsEntityCondition = entity2.getDefEntityCondition();
        forAllCondition = new ForAllCondition(relation, entity2,
                existsEntityCondition);
    }

    /*
     * @Test public void evaluateToFalse() throws BlendedWorkflowException {
     * Transaction.begin(); //False and False
     * entityInstance2_1Att1.setState(DataState.UNDEFINED);
     * entityInstance2_2Att1.setState(DataState.UNDEFINED);
     * assertEquals(TripleStateBool.FALSE,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * //TRUE and FALSE entityInstance2_1Att1.setState(DataState.DEFINED);
     * entityInstance2_2Att1.setState(DataState.UNDEFINED);
     * assertEquals(TripleStateBool.FALSE,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * //FALSE and TRUE entityInstance2_1Att1.setState(DataState.UNDEFINED);
     * entityInstance2_2Att1.setState(DataState.DEFINED);
     * assertEquals(TripleStateBool.FALSE,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * Transaction.commit(); }
     * 
     * @Test public void evaluateToSkipped() throws BlendedWorkflowException {
     * Transaction.begin(); //SKIPPED and SKIPPED
     * entityInstance2_1Att1.setState(DataState.SKIPPED);
     * entityInstance2_2Att1.setState(DataState.SKIPPED);
     * assertEquals(TripleStateBool.SKIPPED,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * //TRUE and SKIPPED entityInstance2_1Att1.setState(DataState.DEFINED);
     * entityInstance2_2Att1.setState(DataState.SKIPPED);
     * assertEquals(TripleStateBool.SKIPPED,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * //SKIPPED and TRUE entityInstance2_1Att1.setState(DataState.SKIPPED);
     * entityInstance2_2Att1.setState(DataState.DEFINED);
     * assertEquals(TripleStateBool.SKIPPED,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * //FALSE and SKIPPED entityInstance2_1Att1.setState(DataState.UNDEFINED);
     * entityInstance2_2Att1.setState(DataState.SKIPPED);
     * assertEquals(TripleStateBool.SKIPPED,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * //SKIPPED and FALSE entityInstance2_1Att1.setState(DataState.SKIPPED);
     * entityInstance2_2Att1.setState(DataState.UNDEFINED);
     * assertEquals(TripleStateBool.SKIPPED,
     * forAllCondition.evaluateWithDataModel(null));
     * 
     * Transaction.commit(); }
     */

    @Ignore
    @Test
    public void evaluateToTrue() throws BWException {
        // TRUE and TRUE
        entityInstance2_1Att1.setState(DataState.DEFINED);
        entityInstance2_2Att1.setState(DataState.DEFINED);
        assertEquals(TripleStateBool.TRUE,
                forAllCondition.evaluateWithDataModel(null, null, null));
    }

}
