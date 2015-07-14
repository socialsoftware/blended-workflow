package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.regex.Pattern;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class AtomicDesignInterface {
    private static AtomicDesignInterface instance;

    public static AtomicDesignInterface getInstance() {
        if (instance == null) {
            instance = new AtomicDesignInterface();
        }
        return instance;
    }

    private AtomicDesignInterface() {
    }

    @Atomic
    public void createSpecification(String specId, String name) {
        getBlendedWorkflow().createSpecification(specId, name);
    }

    private BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
    }

    @Atomic
    public void createEntity(String specId, String entityName) {
        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));
        BWDataModel dataModel = spec.getDataModel();

        dataModel.createEntity(entityName);
    }

    @Atomic
    public void createAttribute(String specId, String entityName,
            String attributeName, String attributeType) {
        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));

        BWEntity ent = spec.getDataModel().getEntity(entityName).orElseThrow(
                () -> new BWException(BWErrorType.INVALID_ENTITY_NAME,
                        entityName));

        ent.createAttribute(attributeName, parseAttributeType(attributeType));
    }

    @Atomic
    public void createRelation(String specId, String entityOneName,
            String roleNameOne, String cardinalityOne, String entityTwoName,
            String roleNameTwo, String cardinalityTwo) {

        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));

        BWEntity entityOne = spec.getDataModel().getEntity(entityOneName)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_ENTITY_NAME, entityOneName));

        BWEntity entityTwo = spec.getDataModel().getEntity(entityTwoName)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_ENTITY_NAME, entityTwoName));

        entityOne.createRelation(roleNameOne, parseCardinality(cardinalityOne),
                entityTwo, roleNameTwo, parseCardinality(cardinalityTwo));
    }

    final static String STRING = "String";
    final static String NUMBER = "Number";
    final static String BOOLEAN = "Boolean";
    final static String ATTRIBUTE_TYPE = "(" + STRING + "|" + NUMBER + "|"
            + BOOLEAN + ")";

    private BWAttribute.AttributeType parseAttributeType(String type) {
        if (!Pattern.matches(ATTRIBUTE_TYPE, type))
            throw new BWException(BWErrorType.INVALID_ATTRIBUTE_TYPE);

        BWAttribute.AttributeType res;

        switch (type) {
        case STRING:
            res = BWAttribute.AttributeType.STRING;
            break;
        case NUMBER:
            res = BWAttribute.AttributeType.NUMBER;
            break;
        case BOOLEAN:
            res = BWAttribute.AttributeType.BOOLEAN;
            break;
        default:
            res = null;
            assert(false);
        }

        return res;
    }

    final static String ONE = "1";
    final static String MANY = "\\*";
    final static String ZERO_OR_ONE = "0..1";
    final static String CARDINALITY = "(" + ONE + "|" + ZERO_OR_ONE + "|" + MANY
            + ")";

    private Cardinality parseCardinality(String cardinality) {
        if (!Pattern.matches(CARDINALITY, cardinality))
            throw new BWException(BWErrorType.INVALID_CARDINALITY);

        Cardinality res;

        switch (cardinality) {
        case ONE:
            res = Cardinality.ONE;
            break;
        case ZERO_OR_ONE:
            res = Cardinality.ZERO_OR_ONE;
            break;
        case "*":
            res = Cardinality.MANY;
            break;
        default:
            res = null;
            assert(false);
        }

        return res;
    }

}
