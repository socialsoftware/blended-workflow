package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.blended.data.data.Attribute;
import org.blended.data.data.DataModel;
import org.blended.data.data.Entity;
import org.eclipse.emf.ecore.EObject;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;

public class DesignInterface {
    public static DesignInterface instance = null;

    public static DesignInterface getInstance() {
        if (instance == null) {
            instance = new DesignInterface();
        }
        return instance;
    }

    private DesignInterface() {
        // Bootstrap.init();
    }

    @Atomic
    public BWNotification loadDataModel(String specId, DataModel eDataModel) {
        // String specId = EcoreUtil2.getNormalizedURI(eDataModel.eResource())
        // .lastSegment().split("\\.")[0];

        BWNotification notification = new BWNotification();

        BlendedWorkflow bw = getBlendedWorkflow();

        BWSpecification spec = bw.getSpecById(specId)
                .orElseGet(() -> bw.createSpecification(specId,
                        eDataModel.getSpecification().getName()));
        BWDataModel dataModel = spec.getDataModel();

        // create/delete entities
        Set<String> existingEntities = dataModel.getEntitiesSet().stream()
                .map(ent -> ent.getName()).collect(Collectors.toSet());

        for (Entity eEnt : eDataModel.getEntities()) {
            BWEntity newEnt = null;
            if (!existingEntities.contains(eEnt.getName())) {
                try {
                    newEnt = dataModel.createEntity(eEnt.getName());
                } catch (BWException bwe) {
                    notification.addError(
                            new BWError(bwe.getError(), eEnt.getName(), ""));
                }
            } else {
                existingEntities.remove(eEnt.getName());
                newEnt = dataModel.getEntity(eEnt.getName()).get();
            }

            // create/delete attributes
            Set<String> existingAttributes = newEnt.getAttributesSet().stream()
                    .map(att -> att.getName()).collect(Collectors.toSet());

            for (EObject att : eEnt.getAttributes()) {
                if (att instanceof Attribute) {
                    Attribute eAtt = (Attribute) att;
                    if (!existingAttributes.contains(eAtt.getName())) {
                        try {
                            newEnt.createAttribute(eAtt.getName(),
                                    parseAttributeType(eAtt.getType()));
                        } catch (BWException bwe) {
                            notification.addError(new BWError(bwe.getError(),
                                    eEnt.getName(), ""));
                        }
                    } else {
                        existingAttributes.remove(eAtt.getName());
                        BWAttribute existAtt = newEnt
                                .getAttribute(eAtt.getName());
                        existAtt.setType(parseAttributeType(eAtt.getType()));
                    }
                }
            }

            for (String attName : existingAttributes) {
                newEnt.getAttribute(attName).delete();
            }
        }

        for (String entityName : existingEntities) {
            dataModel.getEntity(entityName).get().delete();
        }

        return notification;
    }

    @Atomic
    public BWNotification createSpecification(String specId, String name) {
        BWNotification notification = new BWNotification();
        try {
            getBlendedWorkflow().createSpecification(specId, name);
        } catch (BWException bwe) {
            switch (bwe.getError()) {
            case INVALID_SPECIFICATION_ID:
                notification.addError(new BWError(bwe.getError(), specId, ""));
                break;
            case INVALID_SPECIFICATION_NAME:
                notification.addError(new BWError(bwe.getError(), name, ""));
                break;
            default:
                assert false;
            }
        }

        return notification;
    }

    @Atomic
    public BWNotification createEntity(String specId, String entityName) {
        BWNotification notification = new BWNotification();

        BWSpecification spec = getSpecification(specId, notification);
        if (notification.hasErrors())
            return notification;
        BWDataModel dataModel = spec.getDataModel();

        try {
            dataModel.createEntity(entityName);
        } catch (BWException bwe) {
            notification.addError(new BWError(bwe.getError(), entityName, ""));
        }

        return notification;
    }

    @Atomic
    public BWNotification createAttribute(String specId, String entityName,
            String attributeName, String attributeType) {
        BWNotification notification = new BWNotification();

        BWSpecification spec = getSpecification(specId, notification);
        if (notification.hasErrors())
            return notification;

        BWEntity ent = getEntity(entityName, notification, spec);
        if (notification.hasErrors())
            return notification;

        try {
            ent.createAttribute(attributeName,
                    parseAttributeType(attributeType));
        } catch (BWException bwe) {
            switch (bwe.getError()) {
            case INVALID_ATTRIBUTE_NAME:
                notification.addError(new BWError(
                        BWErrorType.INVALID_ATTRIBUTE_NAME, attributeName, ""));
                return notification;
            case INVALID_ATTRIBUTE_TYPE:
                notification.addError(new BWError(
                        BWErrorType.INVALID_ATTRIBUTE_TYPE, attributeType, ""));
                return notification;
            default:
                assert(false);
            }
        }

        return notification;
    }

    private BWEntity getEntity(String entityName, BWNotification notification,
            BWSpecification spec) {
        BWEntity ent = spec.getDataModel().getEntity(entityName).orElse(null);
        if (ent == null) {
            notification.addError(new BWError(BWErrorType.INVALID_ENTITY_NAME,
                    entityName, ""));
        }
        return ent;
    }

    @Atomic
    public BWNotification createRelation(String specId, String entityOneName,
            String roleNameOne, String cardinalityOne, String entityTwoName,
            String roleNameTwo, String cardinalityTwo) {
        BWNotification notification = new BWNotification();

        BWSpecification spec = getSpecification(specId, notification);
        if (notification.hasErrors())
            return notification;

        BWEntity entityOne = getEntity(entityOneName, notification, spec);
        if (notification.hasErrors())
            return notification;

        BWEntity entityTwo = getEntity(entityTwoName, notification, spec);
        if (notification.hasErrors())
            return notification;

        try {
            entityOne.createRelation(roleNameOne,
                    parseCardinality(cardinalityOne), entityTwo, roleNameTwo,
                    parseCardinality(cardinalityTwo));
        } catch (BWException bwe) {
            switch (bwe.getError()) {
            case INVALID_ROLE:
                notification.addError(new BWError(bwe.getError(),
                        roleNameOne + "-" + roleNameTwo, ""));
                break;
            case INVALID_CARDINALITY:
                notification.addError(new BWError(bwe.getError(),
                        cardinalityOne + "-" + cardinalityTwo, ""));
                break;
            default:
                assert false;
            }
        }

        return notification;

    }

    private BWSpecification getSpecification(String specId,
            BWNotification notification) {
        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElse(null);
        if (spec == null) {
            notification.addError(new BWError(
                    BWErrorType.INVALID_SPECIFICATION_ID, specId, ""));
        }
        return spec;
    }

    private BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
    }

    final static String STRING = "String";
    final static String NUMBER = "Number";
    final static String BOOLEAN = "Boolean";
    final static String ATTRIBUTE_TYPE = "(" + STRING + "|" + NUMBER + "|"
            + BOOLEAN + ")";

    public static BWAttribute.AttributeType parseAttributeType(String type) {
        if (!Pattern.matches(ATTRIBUTE_TYPE, type))
            throw new BWException(BWErrorType.INVALID_CARDINALITY);

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
