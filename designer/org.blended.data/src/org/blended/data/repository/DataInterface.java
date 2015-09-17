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
import org.blended.common.repository.resttemplate.dto.AttributeDTO;
import org.blended.common.repository.resttemplate.dto.AttributeGroupDTO;
import org.blended.common.repository.resttemplate.dto.DependenceDTO;
import org.blended.common.repository.resttemplate.dto.EntityDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO;
import org.blended.common.repository.resttemplate.dto.RelationDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.common.repository.resttemplate.dto.SpecDTO;
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
                ci.createSpec(new SpecDTO(specId,
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
                EntityDTO entityDTO = ci.createEntity(
                        new EntityDTO(specId, eEnt.getName(), eEnt.isExists()));
                entityExtId = entityDTO.getExtId();
                log.debug("createdEntity: {}, {}, {}", entityDTO.getExtId(),
                        entityDTO.getName(), entityDTO.getExists());
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("createEntity: {}", re.getMessage());
                continue;
            }
            // create entity dependences
            for (String eDep : eEnt.getDependsOn()) {
                try {
                    ci.createDependence(
                            new DependenceDTO(specId, entityExtId, eDep));
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
                        AttributeDTO attributeDTO = ci
                                .createAttribute(new AttributeDTO(specId,
                                        entityExtId, null, eAtt.getName(),
                                        eAtt.getType(), eAtt.isMandatory()));
                        attributeExtId = attributeDTO.getExtId();
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                        continue;
                    }

                    // create attribute dependences
                    for (String eDep : eAtt.getDependsOn()) {
                        try {
                            ci.createDependence(new DependenceDTO(specId,
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
                        AttributeGroupDTO groupVO = ci.createAttributeGroup(
                                new AttributeGroupDTO(specId, entityExtId,
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
                            ci.createDependence(new DependenceDTO(specId,
                                    groupExtId, eDep));
                        } catch (RepositoryException re) {
                            notification.addError(re.getError());
                            log.debug("Error: {}", re.getMessage());
                        }
                    }

                    for (Attribute eAtt : eAttGroup.getAttributes()) {
                        // create groupAttribute's attributes
                        try {
                            ci.createAttribute(new AttributeDTO(specId,
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
                ci.createRelation(new RelationDTO(specId, assoc.getName(),
                        assoc.getEntity1().getName(), assoc.getName1(),
                        assoc.getCardinality1(), assoc.getEntity2().getName(),
                        assoc.getName2(), assoc.getCardinality2()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        Set<DependenceDTO> deps = ci.getDependencies(specId);
        for (DependenceDTO dep : deps) {
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
            ExpressionDTO expression = buildExpressionDTO(specId,
                    constraint.getConstraint());
            try {
                ci.createRule(
                        new RuleDTO(specId, constraint.getName(), expression));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        ci.printSpecificationModels(specId);

        // System.out.println(
        // ci.getSourceOfPath(specId, "Episode.patient").getExtId());
        // System.out.println(
        // ci.getTargetOfPath(specId, "Episode.patient").getExtId());
        // Set<String> paths = new HashSet<String>();
        // paths.add("Episode.patient.episode");
        // paths.add("Prescription.description");
        // System.out.println(ci.getDependencePaths(specId, paths).stream()
        // .collect(Collectors.joining(",")));

        return notification;
    }

    private ExpressionDTO buildExpressionDTO(String dataModelExtId,
            Expression expression) {
        if (expression instanceof And) {
            And andExpression = (And) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.AND,
                    buildExpressionDTO(dataModelExtId, andExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            andExpression.getRight()));
        } else if (expression instanceof Or) {
            Or orExpression = (Or) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.OR,
                    buildExpressionDTO(dataModelExtId, orExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            orExpression.getRight()));
        } else if (expression instanceof Not) {
            Not notExpression = (Not) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.NOT,
                    buildExpressionDTO(dataModelExtId,
                            notExpression.getExpression()));
        } else if (expression instanceof AttributeDefinition) {
            AttributeDefinition defExpression = (AttributeDefinition) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.ATT_DEF,
                    defExpression.getName());
        } else if (expression instanceof Equal) {
            Equal equalExpression = (Equal) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.EQUAL,
                    buildExpressionDTO(dataModelExtId,
                            equalExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            equalExpression.getRight()));
        } else if (expression instanceof NotEqual) {
            NotEqual notEqualExpression = (NotEqual) expression;
            return new ExpressionDTO(dataModelExtId,
                    ExpressionDTO.Type.NOT_EQUAL,
                    buildExpressionDTO(dataModelExtId,
                            notEqualExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            notEqualExpression.getRight()));
        } else if (expression instanceof Greater) {
            Greater greaterExpression = (Greater) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.GREATER,
                    buildExpressionDTO(dataModelExtId,
                            greaterExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            greaterExpression.getRight()));
        } else if (expression instanceof GreaterEqual) {
            GreaterEqual greaterEqualExpression = (GreaterEqual) expression;
            return new ExpressionDTO(dataModelExtId,
                    ExpressionDTO.Type.GREATER_EQUAL,
                    buildExpressionDTO(dataModelExtId,
                            greaterEqualExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            greaterEqualExpression.getRight()));
        } else if (expression instanceof Smaller) {
            Smaller smallerExpression = (Smaller) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.SMALLER,
                    buildExpressionDTO(dataModelExtId,
                            smallerExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            smallerExpression.getRight()));
        } else if (expression instanceof SmallerEqual) {
            SmallerEqual smallerEqualExpression = (SmallerEqual) expression;
            return new ExpressionDTO(dataModelExtId,
                    ExpressionDTO.Type.SMALLER_EQUAL,
                    buildExpressionDTO(dataModelExtId,
                            smallerEqualExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            smallerEqualExpression.getRight()));
        } else if (expression instanceof Plus) {
            Plus castedExpression = (Plus) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.PLUS,
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof Minus) {
            Minus castedExpression = (Minus) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.MINUS,
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof Mul) {
            Mul castedExpression = (Mul) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.MUL,
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof Div) {
            Div castedExpression = (Div) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.DIV,
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getLeft()),
                    buildExpressionDTO(dataModelExtId,
                            castedExpression.getRight()));
        } else if (expression instanceof AttributeValue) {
            AttributeValue attValue = (AttributeValue) expression;
            return new ExpressionDTO(dataModelExtId,
                    ExpressionDTO.Type.ATT_VALUE, attValue.getName());
        } else if (expression instanceof StringConstant) {
            StringConstant castedExpression = (StringConstant) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.STRING,
                    castedExpression.getName());
        } else if (expression instanceof IntConstant) {
            IntConstant castedExpression = (IntConstant) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.INT,
                    String.valueOf(castedExpression.getName()));
        } else if (expression instanceof BoolConstant) {
            BoolConstant castedExpression = (BoolConstant) expression;
            return new ExpressionDTO(dataModelExtId, ExpressionDTO.Type.BOOL,
                    castedExpression.getName());
        }
        assert(false);
        return null;
    }

}
