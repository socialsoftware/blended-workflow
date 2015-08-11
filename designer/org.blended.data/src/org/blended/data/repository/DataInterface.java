package org.blended.data.repository;

import java.util.Set;

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
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.BWNotification;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.vo.AttributeGroupVO;
import org.blended.common.repository.resttemplate.vo.AttributeVO;
import org.blended.common.repository.resttemplate.vo.DependenceVO;
import org.blended.common.repository.resttemplate.vo.EntityVO;
import org.blended.common.repository.resttemplate.vo.ExpressionVO;
import org.blended.common.repository.resttemplate.vo.RelationVO;
import org.blended.common.repository.resttemplate.vo.RuleVO;
import org.blended.common.repository.resttemplate.vo.SpecVO;
import org.blended.data.data.DataModel;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        ci.deleteSpecification(specId);
    }

    private CommonInterface ci = null;

    private DataInterface() {
        ci = CommonInterface.getInstance();
    }

    public BWNotification loadDataModel(String specId, DataModel eDataModel) {
        log.debug("loadDataModel: {}", specId);

        BWNotification notification = new BWNotification();

        try {
            ci.getSpecBySpecId(specId);
            ci.cleanDataModel(specId);
        } catch (RepositoryException re) {
            log.debug("getSpec: {}", re.getMessage());
            try {
                ci.createSpec(new SpecVO(specId,
                        eDataModel.getSpecification().getName()));
            } catch (RepositoryException ree) {
                notification.addError(ree.getError());
                log.debug("createSpec: {}", re.getMessage());
                return notification;
            }
        }

        for (Entity eEnt : eDataModel.getEntities()) {
            String entityExtId = null;
            try {
                EntityVO entVO = ci.createEntity(
                        new EntityVO(specId, eEnt.getName(), eEnt.isExists()));
                entityExtId = entVO.getExtId();
                log.debug("createdEntity: {}, {}, {}", entVO.getExtId(),
                        entVO.getName(), entVO.getExists());
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("createEntity: {}", re.getMessage());
                continue;
            }
            // create entity dependences
            for (String eDep : eEnt.getDependsOn()) {
                try {
                    ci.createDependence(
                            new DependenceVO(specId, entityExtId, eDep));
                } catch (RepositoryException re) {
                    notification.addError(re.getError());
                    log.debug("Error: {}", re.getMessage());
                }
            }

            for (EObject eObj : eEnt.getAttributes()) {
                if (eObj instanceof Attribute) {
                    String attributeExtId;
                    Attribute eAtt = (Attribute) eObj;
                    try {
                        AttributeVO attributeVO = ci
                                .createAttribute(new AttributeVO(specId,
                                        entityExtId, null, eAtt.getName(),
                                        eAtt.getType(), eAtt.isMandatory()));
                        attributeExtId = attributeVO.getExtId();
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                        continue;
                    }

                    // create attribute dependences
                    for (String eDep : eAtt.getDependsOn()) {
                        try {
                            ci.createDependence(new DependenceVO(specId,
                                    attributeExtId, eDep));
                        } catch (RepositoryException re) {
                            notification.addError(re.getError());
                            log.debug("Error: {}", re.getMessage());
                        }
                    }
                } else if (eObj instanceof AttributeGroup) {
                    String groupExtId;
                    AttributeGroup eAttGroup = (AttributeGroup) eObj;
                    try {
                        AttributeGroupVO groupVO = ci.createAttributeGroup(
                                new AttributeGroupVO(specId, entityExtId,
                                        eAttGroup.getName(),
                                        eAttGroup.isMandatory()));
                        groupExtId = groupVO.getExtId();
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                        continue;
                    }
                    // create group attribute dependences
                    for (String eDep : eAttGroup.getDependsOn()) {
                        try {
                            ci.createDependence(
                                    new DependenceVO(specId, groupExtId, eDep));
                        } catch (RepositoryException re) {
                            notification.addError(re.getError());
                            log.debug("Error: {}", re.getMessage());
                        }
                    }

                    for (Attribute eAtt : eAttGroup.getAttributes()) {
                        // create groupAttribute's attributes
                        try {
                            ci.createAttribute(new AttributeVO(specId,
                                    entityExtId, groupExtId, eAtt.getName(),
                                    eAtt.getType(), eAtt.isMandatory()));
                        } catch (RepositoryException re) {
                            notification.addError(re.getError());
                            log.debug("Error: {}", re.getMessage());
                        }
                    }
                }
            }
        }

        for (Association assoc : eDataModel.getAssociations()) {
            try {
                ci.createRelation(new RelationVO(specId, assoc.getName(),
                        assoc.getEntity1().getName(), assoc.getName1(),
                        assoc.getCardinality1(), assoc.getEntity2().getName(),
                        assoc.getName2(), assoc.getCardinality2()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        Set<DependenceVO> deps = ci.getDependencies(specId);
        for (DependenceVO dep : deps) {
            log.debug("dependence extId:{}, path:{}, productExtId:{}",
                    dep.getExtId(), dep.getPath(), dep.getProductExtId());
            try {
                ci.checkDependence(specId, dep.getExtId());
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());

                ci.deleteDependence(specId, dep.getExtId());
            }
        }

        for (Constraint constraint : eDataModel.getConstraint()) {
            ExpressionVO expression = buildExpressionVO(specId,
                    constraint.getConstraint());
            try {
                ci.createRule(
                        new RuleVO(specId, constraint.getName(), expression));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        ci.printSpecificationModels(specId);

        return notification;
    }

    private ExpressionVO buildExpressionVO(String dataModelExtId,
            Expression expression) {
        if (expression instanceof And) {
            And andExpression = (And) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.AND,
                    buildExpressionVO(dataModelExtId, andExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            andExpression.getRight()));
        } else if (expression instanceof Or) {
            Or orExpression = (Or) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.OR,
                    buildExpressionVO(dataModelExtId, orExpression.getLeft()),
                    buildExpressionVO(dataModelExtId, orExpression.getRight()));
        } else if (expression instanceof Not) {
            Not notExpression = (Not) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.NOT,
                    buildExpressionVO(dataModelExtId,
                            notExpression.getExpression()));
        } else if (expression instanceof AttributeDefinition) {
            AttributeDefinition defExpression = (AttributeDefinition) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.ATT_DEF,
                    defExpression.getName());
        } else if (expression instanceof Equal) {
            Equal equalExpression = (Equal) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.EQUAL,
                    buildExpressionVO(dataModelExtId,
                            equalExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            equalExpression.getRight()));
        } else if (expression instanceof NotEqual) {
            NotEqual notEqualExpression = (NotEqual) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.NOT_EQUAL,
                    buildExpressionVO(dataModelExtId,
                            notEqualExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            notEqualExpression.getRight()));
        } else if (expression instanceof Greater) {
            Greater greaterExpression = (Greater) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.GREATER,
                    buildExpressionVO(dataModelExtId,
                            greaterExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            greaterExpression.getRight()));
        } else if (expression instanceof GreaterEqual) {
            GreaterEqual greaterEqualExpression = (GreaterEqual) expression;
            return new ExpressionVO(dataModelExtId,
                    ExpressionVO.Type.GREATER_EQUAL,
                    buildExpressionVO(dataModelExtId,
                            greaterEqualExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            greaterEqualExpression.getRight()));
        } else if (expression instanceof Smaller) {
            Smaller smallerExpression = (Smaller) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.SMALLER,
                    buildExpressionVO(dataModelExtId,
                            smallerExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            smallerExpression.getRight()));
        } else if (expression instanceof SmallerEqual) {
            SmallerEqual smallerEqualExpression = (SmallerEqual) expression;
            return new ExpressionVO(dataModelExtId,
                    ExpressionVO.Type.SMALLER_EQUAL,
                    buildExpressionVO(dataModelExtId,
                            smallerEqualExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            smallerEqualExpression.getRight()));
        } else if (expression instanceof Plus) {
            Plus castedExpression = (Plus) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.PLUS,
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof Minus) {
            Minus castedExpression = (Minus) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.MINUS,
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof Mul) {
            Mul castedExpression = (Mul) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.MUL,
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof Div) {
            Div castedExpression = (Div) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.DIV,
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionVO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof AttributeValue) {
            AttributeValue attValue = (AttributeValue) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.ATT_VALUE,
                    attValue.getName());
        } else if (expression instanceof StringConstant) {
            StringConstant castedExpression = (StringConstant) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.STRING,
                    castedExpression.getName());
        } else if (expression instanceof IntConstant) {
            IntConstant castedExpression = (IntConstant) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.INT,
                    String.valueOf(castedExpression.getName()));
        } else if (expression instanceof BoolConstant) {
            BoolConstant castedExpression = (BoolConstant) expression;
            return new ExpressionVO(dataModelExtId, ExpressionVO.Type.BOOL,
                    castedExpression.getName());
        }
        assert(false);
        return null;
    }

}
