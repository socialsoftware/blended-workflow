package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.blended.data.data.Association;
import org.blended.data.data.Attribute;
import org.blended.data.data.DataModel;
import org.blended.data.data.Entity;
import org.eclipse.emf.ecore.EObject;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;

public class DesignInterface {
    private static DesignInterface instance = null;

    public static DesignInterface getInstance() {
        if (instance == null) {
            instance = new DesignInterface();
        }
        return instance;
    }

    private AtomicDesignInterface adi = null;

    private DesignInterface() {
        adi = AtomicDesignInterface.getInstance();
    }

    public BWNotification createSpecification(String specId, String name) {
        BWNotification notification = new BWNotification();
        try {
            adi.createSpecification(specId, name);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;
    }

    public BWNotification createEntity(String specId, String entityName) {
        BWNotification notification = new BWNotification();

        try {
            adi.createEntity(specId, entityName);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;
    }

    public BWNotification createAttribute(String specId, String entityName,
            String attributeName, String attributeType) {
        BWNotification notification = new BWNotification();

        try {
            adi.createAttribute(specId, entityName, attributeName,
                    attributeType);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }
        return notification;
    }

    public BWNotification createRelation(String specId, String entityOneName,
            String roleNameOne, String cardinalityOne, String entityTwoName,
            String roleNameTwo, String cardinalityTwo) {
        BWNotification notification = new BWNotification();

        try {
            adi.createRelation(specId, entityOneName, roleNameOne,
                    cardinalityOne, entityTwoName, roleNameTwo, cardinalityTwo);
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
        }

        return notification;

    }

    @Atomic
    public BWNotification loadDataModel(String specId, DataModel eDataModel) {
        BWNotification notification = new BWNotification();

        BlendedWorkflow bw = getBlendedWorkflow();

        BWSpecification spec = bw.getSpecById(specId)
                .orElseGet(() -> bw.createSpecification(specId,
                        eDataModel.getSpecification().getName()));
        BWDataModel dataModel = spec.getDataModel();

        // create entities
        Set<String> existingEntities = dataModel.getEntitiesSet().stream()
                .map(ent -> ent.getName()).collect(Collectors.toSet());

        for (Entity eEnt : eDataModel.getEntities()) {
            BWEntity newEnt = null;
            if (!existingEntities.contains(eEnt.getName())) {
                try {
                    newEnt = dataModel.createEntity(eEnt.getName());
                } catch (BWException bwe) {
                    notification.addError(
                            new BWError(bwe.getError(), eEnt.getName()));
                }
            } else {
                existingEntities.remove(eEnt.getName());
                newEnt = dataModel.getEntity(eEnt.getName()).get();
            }

            // create attributes
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
                                    eEnt.getName()));
                        }
                    } else {
                        existingAttributes.remove(eAtt.getName());
                        BWAttribute existAtt = newEnt
                                .getAttribute(eAtt.getName());
                        existAtt.setType(parseAttributeType(eAtt.getType()));
                    }
                }
            }

            // delete attributes
            for (String attName : existingAttributes) {
                newEnt.getAttribute(attName).delete();
            }
        }

        // delete entities
        for (String entityName : existingEntities) {
            dataModel.getEntity(entityName).get().delete();
        }

        // create relations
        for (Association assoc : eDataModel.getAssociations()) {
            if ((assoc.getEntity1() == null) || (assoc.getEntity2() == null))
                System.out.println("null entity");

            System.out.println(assoc.getEntity1().getName() + ","
                    + assoc.getEntity2().getName());
        }

        return notification;
    }

    private BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
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

}
