package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.blended.common.common.And;
import org.blended.common.common.Association;
import org.blended.common.common.Attribute;
import org.blended.common.common.AttributeDefinition;
import org.blended.common.common.AttributeGroup;
import org.blended.common.common.AttributeValue;
import org.blended.common.common.BoolConstant;
import org.blended.common.common.Constraint;
import org.blended.common.common.Div;
import org.blended.common.common.Entity;
import org.blended.common.common.Equal;
import org.blended.common.common.Expression;
import org.blended.common.common.Greater;
import org.blended.common.common.GreaterEqual;
import org.blended.common.common.IntConstant;
import org.blended.common.common.Minus;
import org.blended.common.common.Mul;
import org.blended.common.common.Not;
import org.blended.common.common.NotEqual;
import org.blended.common.common.Or;
import org.blended.common.common.Plus;
import org.blended.common.common.Smaller;
import org.blended.common.common.SmallerEqual;
import org.blended.common.common.StringConstant;
import org.blended.data.data.DataModel;
import org.eclipse.emf.ecore.EObject;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWBinaryExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWBinaryExpression.BinaryOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWNumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BWStringLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.FalseCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.NotCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.OrCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
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
            BWEntity bwEntity = null;
            if (!existingEntities.contains(eEnt.getName())) {
                bwEntity = dataModel.createEntity(eEnt.getName(),
                        eEnt.isExists());

            } else {
                existingEntities.remove(eEnt.getName());
                bwEntity = getEntity(dataModel, eEnt.getName());
            }

            // create entity dependences
            for (String eDep : eEnt.getDependsOn()) {
                bwEntity.createDependence(eDep);
            }

            // create attributes
            Set<String> existingAttributes = bwEntity.getAttributesSet()
                    .stream().map(att -> att.getName())
                    .collect(Collectors.toSet());

            // delete existing attribute groups
            for (BWAttributeGroup attGroup : bwEntity.getAttributeGroupSet()) {
                attGroup.delete();
            }

            for (EObject att : eEnt.getAttributes()) {
                if (att instanceof Attribute) {
                    Attribute eAtt = (Attribute) att;
                    BWAttribute bwAtt;
                    if (!existingAttributes.contains(eAtt.getName())) {
                        bwAtt = bwEntity.createAttribute(eAtt.getName(),
                                parseAttributeType(eAtt.getType()));
                    } else {
                        existingAttributes.remove(eAtt.getName());
                        bwAtt = bwEntity.getAttribute(eAtt.getName())
                                .orElse(null);
                        bwAtt.setType(parseAttributeType(eAtt.getType()));
                    }
                    // create attribute dependences
                    for (String eDep : eAtt.getDependsOn()) {
                        bwAtt.createDependence(eDep);
                    }
                } else if (att instanceof AttributeGroup) {
                    AttributeGroup eAttGroup = (AttributeGroup) att;
                    BWAttributeGroup attGroup = bwEntity
                            .createAttributeGroup(eAttGroup.getName());
                    for (Attribute eAtt : eAttGroup.getAttributes()) {
                        BWAttribute bwAtt = bwEntity.createAttribute(
                                eAtt.getName(),
                                parseAttributeType(eAtt.getType()));
                        attGroup.addAttribute(bwAtt);
                    }
                    // create group attribute dependences
                    for (String eDep : eAttGroup.getDependsOn()) {
                        attGroup.createDependence(eDep);
                    }
                }
            }

            // delete attributes
            for (String attName : existingAttributes) {
                bwEntity.getAttribute(attName).orElse(null).delete();
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

        // check dependences
        for (BWDependence dependence : dataModel.getDependenceSet()) {
            dependence.check();
        }

        // load constraint
        // delete rules
        for (BWRule rule : dataModel.getRuleSet()) {
            rule.delete();
        }

        // create rules
        for (Constraint constraint : eDataModel.getConstraint()) {
            Condition condition = buildCondition(dataModel,
                    constraint.getConstraint());
            dataModel.createRule(condition);
        }
    }

    private Condition buildCondition(BWDataModel dataModel,
            Expression expression) {
        if (expression instanceof And) {
            And andExpression = (And) expression;
            return new AndCondition(
                    buildCondition(dataModel, andExpression.getLeft()),
                    buildCondition(dataModel, andExpression.getRight()));
        } else if (expression instanceof Or) {
            Or andExpression = (Or) expression;
            return new OrCondition(
                    buildCondition(dataModel, andExpression.getLeft()),
                    buildCondition(dataModel, andExpression.getRight()));
        } else if (expression instanceof Not) {
            Not notExpression = (Not) expression;
            return new NotCondition(
                    buildCondition(dataModel, notExpression.getExpression()));
        } else if (expression instanceof AttributeDefinition) {
            AttributeDefinition defExpression = (AttributeDefinition) expression;
            List<String> pathLeft = Arrays
                    .stream(defExpression.getName().split("\\."))
                    .collect(Collectors.toList());
            BWEntity entity = dataModel.getEntity(pathLeft.get(0))
                    .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                            defExpression.getName() + ":" + pathLeft));
            pathLeft.remove(0);
            // TODO: remove the cast
            return new DEFAttributeCondition((BWAttribute) entity
                    .getNext(pathLeft, defExpression.getName()));
        } else if (expression instanceof Equal) {
            Equal equalExpression = (Equal) expression;
            return new Comparison(
                    buildExpression(dataModel, equalExpression.getLeft()),
                    buildExpression(dataModel, equalExpression.getRight()),
                    ComparisonOperator.EQUAL);
        } else if (expression instanceof NotEqual) {
            NotEqual notEqualExpression = (NotEqual) expression;
            return new Comparison(
                    buildExpression(dataModel, notEqualExpression.getLeft()),
                    buildExpression(dataModel, notEqualExpression.getRight()),
                    ComparisonOperator.NOT_EQUAL);
        } else if (expression instanceof Greater) {
            Greater greaterExpression = (Greater) expression;
            return new Comparison(
                    buildExpression(dataModel, greaterExpression.getLeft()),
                    buildExpression(dataModel, greaterExpression.getRight()),
                    ComparisonOperator.GREATER);
        } else if (expression instanceof GreaterEqual) {
            GreaterEqual greaterEqualExpression = (GreaterEqual) expression;
            return new Comparison(
                    buildExpression(dataModel,
                            greaterEqualExpression.getLeft()),
                    buildExpression(dataModel,
                            greaterEqualExpression.getRight()),
                    ComparisonOperator.GREATER_EQUAL);
        } else if (expression instanceof Smaller) {
            Smaller smallerExpression = (Smaller) expression;
            return new Comparison(
                    buildExpression(dataModel, smallerExpression.getLeft()),
                    buildExpression(dataModel, smallerExpression.getRight()),
                    ComparisonOperator.SMALLER);
        } else if (expression instanceof SmallerEqual) {
            SmallerEqual smallerEqualExpression = (SmallerEqual) expression;
            return new Comparison(
                    buildExpression(dataModel,
                            smallerEqualExpression.getLeft()),
                    buildExpression(dataModel,
                            smallerEqualExpression.getRight()),
                    ComparisonOperator.SMALLER_EQUAL);
        } else if (expression instanceof BoolConstant) {
            BoolConstant castedExpression = (BoolConstant) expression;
            String value = castedExpression.getName();
            if (value.toLowerCase().equals("true"))
                return new TrueCondition();
            if (value.toLowerCase().equals("false"))
                return new FalseCondition();
        }
        assert false;
        return null;
    }

    private BWExpression buildExpression(BWDataModel dataModel,
            Expression expression) {
        if (expression instanceof Plus) {
            Plus castedExpression = (Plus) expression;
            return new BWBinaryExpression(
                    buildExpression(dataModel, castedExpression.getLeft()),
                    buildExpression(dataModel, castedExpression.getRight()),
                    BinaryOperator.PLUS);
        } else if (expression instanceof Minus) {
            Minus castedExpression = (Minus) expression;
            return new BWBinaryExpression(
                    buildExpression(dataModel, castedExpression.getLeft()),
                    buildExpression(dataModel, castedExpression.getRight()),
                    BinaryOperator.MINUS);
        } else if (expression instanceof Mul) {
            Mul castedExpression = (Mul) expression;
            return new BWBinaryExpression(
                    buildExpression(dataModel, castedExpression.getLeft()),
                    buildExpression(dataModel, castedExpression.getRight()),
                    BinaryOperator.MUL);
        } else if (expression instanceof Div) {
            Div castedExpression = (Div) expression;
            return new BWBinaryExpression(
                    buildExpression(dataModel, castedExpression.getLeft()),
                    buildExpression(dataModel, castedExpression.getRight()),
                    BinaryOperator.DIV);
        } else if (expression instanceof AttributeValue) {
            AttributeValue attValue = (AttributeValue) expression;
            List<String> pathLeft = Arrays
                    .stream(attValue.getName().split("\\."))
                    .collect(Collectors.toList());
            BWEntity entity = dataModel.getEntity(pathLeft.get(0))
                    .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                            attValue.getName() + ":" + pathLeft));
            pathLeft.remove(0);
            // TODO: remove cast
            return new BWAttributeValueExpression(
                    (BWAttribute) entity.getNext(pathLeft, attValue.getName()));
        } else if (expression instanceof StringConstant) {
            StringConstant castedExpression = (StringConstant) expression;
            return new BWStringLiteral(castedExpression.getName());
        } else if (expression instanceof IntConstant) {
            IntConstant castedExpression = (IntConstant) expression;
            return new BWNumberLiteral(castedExpression.getName());
        }
        assert false;
        return null;
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
