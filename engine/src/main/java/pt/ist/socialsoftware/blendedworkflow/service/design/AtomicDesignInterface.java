package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.blended.data.data.Association;
import org.blended.data.data.Attribute;
import org.blended.data.data.AttributeGroup;
import org.blended.data.data.DataModel;
import org.blended.data.data.Entity;
import org.eclipse.emf.ecore.EObject;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
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
    public void createEntity(String specId, String entityName, Boolean exists) {
        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));
        BWDataModel dataModel = spec.getDataModel();

        dataModel.createEntity(entityName, exists);
    }

    @Atomic
    public void createAttribute(String specId, String entityName,
            String attributeName, String attributeType) {
        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));

        BWEntity ent = getEntity(spec.getDataModel(), entityName);

        ent.createAttribute(attributeName, parseAttributeType(attributeType));
    }

    @Atomic
    public void createRelation(String specId, String entityOneName,
            String roleNameOne, String cardinalityOne, String entityTwoName,
            String roleNameTwo, String cardinalityTwo) {

        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));

        BWEntity entityOne = getEntity(spec.getDataModel(), entityOneName);

        BWEntity entityTwo = getEntity(spec.getDataModel(), entityTwoName);

        entityOne.createRelation(roleNameOne, parseCardinality(cardinalityOne),
                entityTwo, roleNameTwo, parseCardinality(cardinalityTwo));
    }

    @Atomic
    public void loadDataModel(String specId, DataModel eDataModel) {
        BlendedWorkflow bw = getBlendedWorkflow();

        BWSpecification spec = bw.getSpecById(specId)
                .orElseGet(() -> bw.createSpecification(specId,
                        eDataModel.getSpecification().getName()));
        BWDataModel dataModel = spec.getDataModel();

        // create entities
        Set<String> existingEntities = dataModel.getEntitiesSet().stream()
                .map(ent -> ent.getName()).collect(Collectors.toSet());

        for (Entity eEnt : eDataModel.getEntities()) {
            BWEntity entity = null;
            if (!existingEntities.contains(eEnt.getName())) {
                entity = dataModel.createEntity(eEnt.getName(),
                        eEnt.isExists());

            } else {
                existingEntities.remove(eEnt.getName());
                entity = getEntity(dataModel, eEnt.getName());
            }

            // create attributes
            Set<String> existingAttributes = entity.getAttributesSet().stream()
                    .map(att -> att.getName()).collect(Collectors.toSet());

            // delete existing attribute groups
            for (BWAttributeGroup attGroup : entity.getAttributeGroupSet()) {
                attGroup.delete();
            }

            for (EObject att : eEnt.getAttributes()) {
                if (att instanceof Attribute) {
                    Attribute eAtt = (Attribute) att;
                    if (!existingAttributes.contains(eAtt.getName())) {
                        entity.createAttribute(eAtt.getName(),
                                parseAttributeType(eAtt.getType()));
                    } else {
                        existingAttributes.remove(eAtt.getName());
                        BWAttribute existAtt = entity
                                .getAttribute(eAtt.getName());
                        existAtt.setType(parseAttributeType(eAtt.getType()));
                    }
                } else if (att instanceof AttributeGroup) {
                    AttributeGroup eAttGroup = (AttributeGroup) att;
                    BWAttributeGroup attGroup = entity
                            .createAttributeGroup("TODEFINE");
                    for (Attribute eAtt : eAttGroup.getAttributes()) {
                        BWAttribute bwAtt = entity.createAttribute(
                                eAtt.getName(),
                                parseAttributeType(eAtt.getType()));
                        attGroup.addAttribute(bwAtt);
                    }
                }
            }

            // delete attributes
            for (String attName : existingAttributes) {
                entity.getAttribute(attName).delete();
            }
        }

        // delete entities
        for (String entityName : existingEntities) {
            dataModel.getEntity(entityName).get().delete();
        }

        // create relations
        // first delete all existing relation because they do not have a name
        for (BWRelation rel : dataModel.getRelationsSet()) {
            rel.delete();
        }

        for (Association assoc : eDataModel.getAssociations()) {
            BWEntity entityOne = getEntity(dataModel,
                    assoc.getEntity1().getName());
            BWEntity entityTwo = getEntity(dataModel,
                    assoc.getEntity2().getName());

            entityOne.createRelation(assoc.getName1(),
                    parseCardinality(assoc.getCardinality1()), entityTwo,
                    assoc.getName2(),
                    parseCardinality(assoc.getCardinality2()));
        }

    }

    final static String STRING = "String";
    final static String NUMBER = "Number";
    final static String BOOLEAN = "Boolean";
    final static String DATE = "Date";
    final static String ATTRIBUTE_TYPE = "(" + STRING + "|" + NUMBER + "|"
            + BOOLEAN + "|" + DATE + ")";

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
        case DATE:
            res = BWAttribute.AttributeType.DATE;
            break;
        default:
            res = null;
            assert(false);
        }

        return res;
    }

    final static String ONE = "1";
    final static String ZERO_MANY = "\\*";
    final static String ZERO_OR_ONE = "0..1";
    final static String ONE_MANY = "1..\\*";
    final static String CARDINALITY = "(" + ONE + "|" + ZERO_OR_ONE + "|"
            + ZERO_MANY + "|" + ONE_MANY + ")";

    private Cardinality parseCardinality(String cardinality) {
        if (!Pattern.matches(CARDINALITY, cardinality))
            throw new BWException(BWErrorType.INVALID_CARDINALITY, cardinality);

        Cardinality res;

        switch (cardinality) {
        case ONE:
            res = Cardinality.ONE;
            break;
        case ZERO_OR_ONE:
            res = Cardinality.ZERO_OR_ONE;
            break;
        case "*":
            res = Cardinality.ZERO_MANY;
            break;
        case "1..*":
            res = Cardinality.ONE_MANY;
            break;
        default:
            res = null;
            assert(false);
        }

        return res;
    }

    private BWEntity getEntity(BWDataModel dataModel, String name) {
        return dataModel.getEntity(name).orElseThrow(
                () -> new BWException(BWErrorType.INVALID_ENTITY_NAME, name));
    }

}
