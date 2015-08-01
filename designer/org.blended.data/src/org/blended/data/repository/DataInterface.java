package org.blended.data.repository;

import org.blended.data.data.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class DataInterface {
    final static String SERVER_ADDRESS = "http://localhost:8080/";

    private static Logger log = LoggerFactory.getLogger(DataInterface.class);

    private static DataInterface instance = null;

    public static DataInterface getInstance() {
        if (instance == null) {
            instance = new DataInterface();
        }
        return instance;
    }

    // to be invoked by tests only
    public void deleteSpecification(String specId) {
        log.debug("deleteSpecification {}", specId);
        // TODO
        // adi.deleteSpecification(specDTO);
    }

    // private AtomicDesignInterface adi = null;
    //
    // private DataInterface() {
    // adi = AtomicDesignInterface.getInstance();
    // }

    // public BWNotification createSpecification(SpecificationDTO specDTO) {
    // BWNotification notification = new BWNotification();
    // try {
    // adi.createSpecification(specDTO);
    // } catch (BWException bwe) {
    // notification
    // .addError(new BWError(bwe.getError(), bwe.getMessage()));
    // }
    //
    // return notification;
    // }
    //
    // public BWNotification createEntity(EntityDTO entDTO) {
    // BWNotification notification = new BWNotification();
    //
    // try {
    // adi.createEntity(entDTO);
    // } catch (BWException bwe) {
    // notification
    // .addError(new BWError(bwe.getError(), bwe.getMessage()));
    // }
    //
    // return notification;
    // }
    //
    // public BWNotification createAttribute(AttributeDTO attDTO) {
    // BWNotification notification = new BWNotification();
    //
    // try {
    // adi.createAttribute(attDTO);
    // } catch (BWException bwe) {
    // notification
    // .addError(new BWError(bwe.getError(), bwe.getMessage()));
    // }
    // return notification;
    // }
    //
    // public BWNotification createRelation(RelationDTO relDTO) {
    // BWNotification notification = new BWNotification();
    //
    // try {
    // adi.createRelation(relDTO);
    // } catch (BWException bwe) {
    // notification
    // .addError(new BWError(bwe.getError(), bwe.getMessage()));
    // }
    //
    // return notification;
    //
    // }

    public String loadDataModel(String specId, DataModel eDataModel) {
        log.debug("loadDataModel: {}", specId);

        // BWNotification notification = new BWNotification();

        RestTemplate restTemplate = new RestTemplate();
        String result = null;
        try {
            result = restTemplate.getForObject(SERVER_ADDRESS, String.class);
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(result);

        return result;

        // try {
        // adi.loadDataSpecification(new SpecificationDTO(specId,
        // eDataModel.getSpecification().getName()));
        // } catch (BWException bwe) {
        // notification
        // .addError(new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // }
        //
        // for (Entity eEnt : eDataModel.getEntities()) {
        // try {
        // adi.createEntity(
        // new EntityDTO(specId, eEnt.getName(), eEnt.isExists()));
        //
        // // create entity dependences
        // for (String eDep : eEnt.getDependsOn()) {
        // adi.createDependence(
        // new DependenceDTO(specId, eEnt.getName(), eDep));
        // }
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // continue;
        // }
        //
        // for (EObject eObj : eEnt.getAttributes()) {
        // if (eObj instanceof Attribute) {
        // Attribute eAtt = (Attribute) eObj;
        // try {
        // adi.createAttribute(new AttributeDTO(specId,
        // eEnt.getName(), eAtt.getName(), eAtt.getType(),
        // eAtt.isMandatory()));
        //
        // // create attribute dependences
        // for (String eDep : eAtt.getDependsOn()) {
        // adi.createDependence(new DependenceDTO(specId,
        // eEnt.getName(), ProductType.ATTRIBUTE,
        // eAtt.getName(), eDep));
        // }
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(),
        // bwe.getMessage());
        // }
        // } else if (eObj instanceof AttributeGroup) {
        // AttributeGroup eAttGroup = (AttributeGroup) eObj;
        // try {
        // adi.createAttributeGroup(new AttributeGroupDTO(specId,
        // eEnt.getName(), eAttGroup.getName(),
        // eAttGroup.isMandatory()));
        //
        // // create group attribute dependences
        // for (String eDep : eAttGroup.getDependsOn()) {
        // adi.createDependence(new DependenceDTO(specId,
        // eEnt.getName(), ProductType.ATTRIBUTE_GROUP,
        // eAttGroup.getName(), eDep));
        // }
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(),
        // bwe.getMessage());
        // continue;
        // }
        //
        // for (Attribute eAtt : eAttGroup.getAttributes()) {
        // // create groupAttribute's attributes
        // adi.createAttribute(
        // new AttributeDTO(specId, eEnt.getName(),
        // eAttGroup.getName(), eAtt.getName(),
        // eAtt.getType(), eAtt.isMandatory()));
        // }
        // }
        // }
        // }
        //
        // for (Association assoc : eDataModel.getAssociations()) {
        // try {
        // adi.createRelation(new RelationDTO(specId, assoc.getName(),
        // assoc.getEntity1().getName(), assoc.getName1(),
        // assoc.getCardinality1(), assoc.getEntity2().getName(),
        // assoc.getName2(), assoc.getCardinality2()));
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // }
        // }
        //
        // List<String> deps = adi.getDependencies(new
        // SpecificationDTO(specId));
        // for (String dep : deps) {
        // try {
        // adi.checkDependence(dep);
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // }
        // }
        //
        // for (Constraint constraint : eDataModel.getConstraint()) {
        // ExpressionDTO expression = buildExpressionDTO(specId,
        // constraint.getConstraint());
        // try {
        // adi.createRule(
        // new RuleDTO(specId, constraint.getName(), expression));
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // }
        // }
        //
        // return notification;
    }

    // private ExpressionDTO buildExpressionDTO(String specId,
    // Expression expression) {
    // if (expression instanceof And) {
    // And andExpression = (And) expression;
    // return new ExpressionDTO(specId, Type.AND,
    // buildExpressionDTO(specId, andExpression.getLeft()),
    // buildExpressionDTO(specId, andExpression.getRight()));
    // } else if (expression instanceof Or) {
    // Or orExpression = (Or) expression;
    // return new ExpressionDTO(specId, Type.OR,
    // buildExpressionDTO(specId, orExpression.getLeft()),
    // buildExpressionDTO(specId, orExpression.getRight()));
    // } else if (expression instanceof Not) {
    // Not notExpression = (Not) expression;
    // return new ExpressionDTO(specId, Type.NOT,
    // buildExpressionDTO(specId, notExpression.getExpression()));
    // } else if (expression instanceof AttributeDefinition) {
    // AttributeDefinition defExpression = (AttributeDefinition) expression;
    // return new ExpressionDTO(specId, Type.ATT_DEF,
    // defExpression.getName());
    // } else if (expression instanceof Equal) {
    // Equal equalExpression = (Equal) expression;
    // return new ExpressionDTO(specId, Type.EQUAL,
    // buildExpressionDTO(specId, equalExpression.getLeft()),
    // buildExpressionDTO(specId, equalExpression.getRight()));
    // } else if (expression instanceof NotEqual) {
    // NotEqual notEqualExpression = (NotEqual) expression;
    // return new ExpressionDTO(specId, Type.NOT_EQUAL,
    // buildExpressionDTO(specId, notEqualExpression.getLeft()),
    // buildExpressionDTO(specId, notEqualExpression.getRight()));
    // } else if (expression instanceof Greater) {
    // Greater greaterExpression = (Greater) expression;
    // return new ExpressionDTO(specId, Type.GREATER,
    // buildExpressionDTO(specId, greaterExpression.getLeft()),
    // buildExpressionDTO(specId, greaterExpression.getRight()));
    // } else if (expression instanceof GreaterEqual) {
    // GreaterEqual greaterEqualExpression = (GreaterEqual) expression;
    // return new ExpressionDTO(specId, Type.GREATER_EQUAL,
    // buildExpressionDTO(specId,
    // greaterEqualExpression.getLeft()),
    // buildExpressionDTO(specId,
    // greaterEqualExpression.getRight()));
    // } else if (expression instanceof Smaller) {
    // Smaller smallerExpression = (Smaller) expression;
    // return new ExpressionDTO(specId, Type.SMALLER,
    // buildExpressionDTO(specId, smallerExpression.getLeft()),
    // buildExpressionDTO(specId, smallerExpression.getRight()));
    // } else if (expression instanceof SmallerEqual) {
    // SmallerEqual smallerEqualExpression = (SmallerEqual) expression;
    // return new ExpressionDTO(specId, Type.SMALLER_EQUAL,
    // buildExpressionDTO(specId,
    // smallerEqualExpression.getLeft()),
    // buildExpressionDTO(specId,
    // smallerEqualExpression.getRight()));
    // } else if (expression instanceof Plus) {
    // Plus castedExpression = (Plus) expression;
    // return new ExpressionDTO(specId, Type.PLUS,
    // buildExpressionDTO(specId, castedExpression.getLeft()),
    // buildExpressionDTO(specId, castedExpression.getRight()));
    // } else if (expression instanceof Minus) {
    // Minus castedExpression = (Minus) expression;
    // return new ExpressionDTO(specId, Type.MINUS,
    // buildExpressionDTO(specId, castedExpression.getLeft()),
    // buildExpressionDTO(specId, castedExpression.getRight()));
    // } else if (expression instanceof Mul) {
    // Mul castedExpression = (Mul) expression;
    // return new ExpressionDTO(specId, Type.MUL,
    // buildExpressionDTO(specId, castedExpression.getLeft()),
    // buildExpressionDTO(specId, castedExpression.getRight()));
    // } else if (expression instanceof Div) {
    // Div castedExpression = (Div) expression;
    // return new ExpressionDTO(specId, Type.DIV,
    // buildExpressionDTO(specId, castedExpression.getLeft()),
    // buildExpressionDTO(specId, castedExpression.getRight()));
    // } else if (expression instanceof AttributeValue) {
    // AttributeValue attValue = (AttributeValue) expression;
    // return new ExpressionDTO(specId, Type.ATT_VALUE,
    // attValue.getName());
    // } else if (expression instanceof StringConstant) {
    // StringConstant castedExpression = (StringConstant) expression;
    // return new ExpressionDTO(specId, Type.STRING,
    // castedExpression.getName());
    // } else if (expression instanceof IntConstant) {
    // IntConstant castedExpression = (IntConstant) expression;
    // return new ExpressionDTO(specId, Type.INT,
    // String.valueOf(castedExpression.getName()));
    // } else if (expression instanceof BoolConstant) {
    // BoolConstant castedExpression = (BoolConstant) expression;
    // return new ExpressionDTO(specId, Type.BOOL,
    // castedExpression.getName());
    // }
    // assert(false);
    // return null;
    // }

}
