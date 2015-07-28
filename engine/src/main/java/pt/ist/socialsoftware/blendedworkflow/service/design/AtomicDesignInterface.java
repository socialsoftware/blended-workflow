package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeBoolCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWBinaryExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWBinaryExpression.BinaryOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.BWConditionModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWNumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BWStringLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BoolComparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.FalseCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.MULCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.NotCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.OrCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeGroupDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityAchieveConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulInvariantDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecificationDTO;

public class AtomicDesignInterface {
    private static Logger log = LoggerFactory
            .getLogger(AtomicDesignInterface.class);

    private static AtomicDesignInterface instance;

    public static AtomicDesignInterface getInstance() {
        if (instance == null) {
            instance = new AtomicDesignInterface();
        }
        return instance;
    }

    private AtomicDesignInterface() {
    }

    private BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
    }

    // to be invoked by tests only
    @Atomic
    public void deleteSpecification(SpecificationDTO specDTO) {
        getBlendedWorkflow().getSpecById(specDTO.specId).get().delete();
    }

    @Atomic
    public void createSpecification(SpecificationDTO specDTO) {
        getBlendedWorkflow().createSpecification(specDTO.specId, specDTO.name);
    }

    @Atomic
    public void loadDataSpecification(SpecificationDTO specDTO) {
        BlendedWorkflow bw = getBlendedWorkflow();

        BWSpecification spec = bw.getSpecById(specDTO.specId).orElseGet(
                () -> bw.createSpecification(specDTO.specId, specDTO.name));

        BWDataModel dataModel = spec.getDataModel();

        dataModel.delete();

        spec.setDataModel(new BWDataModel());
    }

    @Atomic
    public void loadConditionSpecification(SpecificationDTO specDTO) {
        BlendedWorkflow bw = getBlendedWorkflow();

        BWSpecification spec = bw.getSpecById(specDTO.specId).orElseGet(
                () -> bw.createSpecification(specDTO.specId, specDTO.name));

        BWConditionModel conditionalModel = spec.getConditionModel();

        conditionalModel.delete();

        spec.setConditionModel(new BWConditionModel());
    }

    @Atomic
    public void createEntity(EntityDTO entDTO) {
        BWSpecification spec = getSpecification(entDTO.specDTO.specId);
        BWDataModel dataModel = spec.getDataModel();

        dataModel.createEntity(entDTO.name, entDTO.exists);
    }

    @Atomic
    public void createAttribute(AttributeDTO attDTO) {
        BWSpecification spec = getSpecification(attDTO.entDTO.specDTO.specId);

        BWEntity ent = getEntity(spec.getDataModel(), attDTO.entDTO.name);

        BWAttributeGroup attGroup = ent.getAttributeGroup(attDTO.groupDTO.name)
                .orElse(null);

        ent.createAttribute(attGroup, attDTO.name,
                parseAttributeType(attDTO.type), attDTO.isMandatory);
    }

    @Atomic
    public void createRelation(RelationDTO relDTO) {

        BWSpecification spec = getSpecification(
                relDTO.entOneDTO.specDTO.specId);

        BWEntity entityOne = getEntity(spec.getDataModel(),
                relDTO.entOneDTO.name);

        BWEntity entityTwo = getEntity(spec.getDataModel(),
                relDTO.entTwoDTO.name);

        entityOne.createRelation(relDTO.name, relDTO.rolenameOne,
                parseCardinality(relDTO.cardinalityOne), entityTwo,
                relDTO.rolenameTwo, parseCardinality(relDTO.cardinalityTwo));
    }

    @Atomic
    public void createAttributeGroup(AttributeGroupDTO attGroupDTO) {
        BWSpecification spec = getSpecification(
                attGroupDTO.entDTO.specDTO.specId);

        BWEntity entity = getEntity(spec.getDataModel(),
                attGroupDTO.entDTO.name);

        entity.createAttributeGroup(attGroupDTO.name, attGroupDTO.isMandatory);
    }

    @Atomic
    public void createDependence(DependenceDTO productDTO) {
        BWSpecification spec = getSpecification(productDTO.specDTO.specId);

        BWProduct product = null;
        BWEntity entity = null;
        switch (productDTO.type) {
        case ENTITY:
            product = getEntity(spec.getDataModel(), productDTO.name);
            break;
        case ATTRIBUTE_GROUP:
            entity = getEntity(spec.getDataModel(), productDTO.entDTO.name);
            product = getAttributeGroup(entity, productDTO.name);
            break;
        case ATTRIBUTE:
            entity = getEntity(spec.getDataModel(), productDTO.entDTO.name);
            product = getAttribute(entity, productDTO.name);
            break;
        default:
            assert false;
        }

        product.createDependence(productDTO.value);
    }

    @Atomic
    public List<String> getDependencies(SpecificationDTO specDTO) {
        List<String> deps = new ArrayList<String>();

        BWSpecification spec = getSpecification(specDTO.specId);

        for (BWDependence dependence : spec.getDataModel().getDependenceSet()) {
            deps.add(dependence.getExternalId());
        }

        return deps;
    }

    @Atomic
    public void checkDependence(String externalId) {
        BWDependence dependence = FenixFramework.getDomainObject(externalId);
        dependence.check();
    }

    @Atomic
    public void checkDependencies(SpecificationDTO specDTO) {
        BWSpecification spec = getSpecification(specDTO.specId);

        for (BWDependence dependence : spec.getDataModel().getDependenceSet()) {
            dependence.check();
        }
    }

    @Atomic
    public void createRule(RuleDTO ruleDTO) {
        BWSpecification spec = getSpecification(ruleDTO.specDTO.specId);

        spec.getDataModel().createRule(ruleDTO.name,
                buildCondition(spec.getDataModel(), ruleDTO.expDTO));
    }

    @Atomic
    public void createEntityAchieveCondition(EntityAchieveConditionDTO eacDTO) {
        log.debug("createEntityAchieveCondition Entity:{}, Value:{}",
                eacDTO.entity, eacDTO.exists);
        BWSpecification spec = getSpecification(eacDTO.specId);

        BWEntity entity = getEntity(spec.getDataModel(), eacDTO.entity);

        if (entity.getExists() != eacDTO.exists) {
            throw new BWException(BWErrorType.INVALID_ENTITY,
                    eacDTO.entity + " exists=" + entity.getExists());
        }

        spec.getConditionModel().addEntityAchieveCondition(
                DEFEntityCondition.getDEFEntity(spec, entity));
    }

    @Atomic
    public void createEntityDependenceCondition(DependenceDTO edcDTO) {
        log.debug("createEntityDependenceCondition Entity:{}, Value:{}",
                edcDTO.entDTO.name, edcDTO.value);
        BWSpecification spec = getSpecification(edcDTO.specDTO.specId);

        BWEntity entity = getEntity(spec.getDataModel(), edcDTO.entDTO.name);

        BWDependence dependence = entity.getDependenceSet().stream()
                .filter(dep -> dep.getPath().equals(edcDTO.value)).findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.DEPENDENCE_NOT_EXISTS, edcDTO.value));

        spec.getConditionModel().addEntityDependenceCondition(dependence);
    }

    @Atomic
    public void createEntityInvariantCondition(MulInvariantDTO miDTO) {
        log.debug("createEntityInvariantCondition Entity:{}, Value:{}",
                miDTO.rolePath, miDTO.cardinality);
        BWSpecification spec = getSpecification(miDTO.specDTO.specId);

        String entityName = miDTO.rolePath.split("\\.")[0];
        String rolename = miDTO.rolePath.split("\\.")[1];

        BWRelation relation = spec.getDataModel().getRelationsSet().stream()
                .filter(rel -> (rel.getEntityOne().getName().equals(entityName)
                        && rel.getRoleNameTwo().equals(rolename))
                        || (rel.getEntityTwo().getName().equals(entityName)
                                && rel.getRoleNameOne().equals(rolename)))
                .findFirst()
                .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                        miDTO.rolePath));

        MULCondition mulCondition = MULCondition.getMulCondition(relation,
                rolename);

        if (!mulCondition.getCardinality()
                .equals(parseCardinality(miDTO.cardinality)))
            new BWException(BWErrorType.INVALID_PATH, miDTO.rolePath);
    }

    @Atomic
    public void createAttributeAchieveCondition(
            EntityAchieveConditionDTO eacDTO) {
        log.debug("createAttributeAchieveCondition Entity:{}, Value:{}",
                eacDTO.entity, eacDTO.exists);
        BWSpecification spec = getSpecification(eacDTO.specId);

        BWEntity entity = getEntity(spec.getDataModel(), eacDTO.entity);

        if (entity.getExists() != eacDTO.exists) {
            throw new BWException(BWErrorType.INVALID_ENTITY,
                    eacDTO.entity + " exists=" + entity.getExists());
        }

        spec.getConditionModel().addEntityAchieveCondition(
                DEFEntityCondition.getDEFEntity(spec, entity));
    }

    @Atomic
    public void writeLoadedConditionModel(String specId) {
        BWSpecification spec = getSpecification(specId);

        spec.getConditionModel().getEntityAchieveConditionSet().stream()
                .map(def -> def.getEntity().getName() + "-"
                        + def.getEntity().getExists())
                .forEach(System.out::println);

        // spec.getConditionModel().getEntityDependenceConditionSet().stream()
        // .map(dep -> dep.getProduct().getName() + "-" + dep.getPath())
        // .forEach(System.out::println);

        log.debug("getEntityInvariantConditionSet().size() {}", spec
                .getConditionModel().getEntityInvariantConditionSet().size());

        spec.getConditionModel().getEntityInvariantConditionSet().stream()
                .map(mul -> mul.getExpression()).forEach(System.out::println);
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

    private BWSpecification getSpecification(String specId) {
        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));
        return spec;
    }

    private BWEntity getEntity(BWDataModel dataModel, String name) {
        return dataModel.getEntity(name).orElseThrow(
                () -> new BWException(BWErrorType.INVALID_ENTITY_NAME, name));
    }

    private BWProduct getAttributeGroup(BWEntity entity, String name) {
        return entity.getAttributeGroup(name).orElseThrow(() -> new BWException(
                BWErrorType.INVALID_ATTRIBUTE_GROUP_NAME, name));
    }

    private BWProduct getAttribute(BWEntity entity, String name) {
        return entity.getAttribute(name).orElseThrow(() -> new BWException(
                BWErrorType.INVALID_ATTRIBUTE_NAME, name));
    }

    private Condition buildCondition(BWDataModel dataModel,
            ExpressionDTO expression) {
        switch (expression.type) {
        case AND:
            return new AndCondition(
                    buildCondition(dataModel, expression.leftExpDTO),
                    buildCondition(dataModel, expression.rightExpDTO));
        case OR:
            return new OrCondition(
                    buildCondition(dataModel, expression.leftExpDTO),
                    buildCondition(dataModel, expression.rightExpDTO));
        case NOT:
            return new NotCondition(
                    buildCondition(dataModel, expression.expDTO));
        case ATT_DEF:
            List<String> pathLeft = Arrays.stream(expression.value.split("\\."))
                    .collect(Collectors.toList());
            BWEntity entity = dataModel.getEntity(pathLeft.get(0))
                    .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                            expression.value + ":" + pathLeft));
            pathLeft.remove(0);
            // TODO: remove the cast
            BWProduct product = entity.getNext(pathLeft, expression.value);
            if (product instanceof BWAttribute)
                return new DEFAttributeCondition((BWAttribute) product);
            else if (product instanceof BWAttributeGroup)
                return new DEFAttributeCondition((BWAttributeGroup) product);
            assert(true);
            return null;
        case ATT_VALUE:
            pathLeft = Arrays.stream(expression.value.split("\\."))
                    .collect(Collectors.toList());
            entity = dataModel.getEntity(pathLeft.get(0))
                    .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                            expression.value + ":" + pathLeft));
            pathLeft.remove(0);
            // TODO: remove cast
            BWAttribute att = (BWAttribute) entity.getNext(pathLeft,
                    expression.value);
            // if (att.getType().equals(BOOLEAN))
            return new BWAttributeBoolCondition(att);
        case EQUAL:
            if (ExpressionDTO.isBoolExp(expression.leftExpDTO.type))
                return new BoolComparison(
                        buildCondition(dataModel, expression.leftExpDTO),
                        buildCondition(dataModel, expression.rightExpDTO),
                        ComparisonOperator.EQUAL);
            else
                return new Comparison(
                        buildExpression(dataModel, expression.leftExpDTO),
                        buildExpression(dataModel, expression.rightExpDTO),
                        ComparisonOperator.EQUAL);
        case NOT_EQUAL:
            if (ExpressionDTO.isBoolExp(expression.leftExpDTO.type))
                return new BoolComparison(
                        buildCondition(dataModel, expression.leftExpDTO),
                        buildCondition(dataModel, expression.rightExpDTO),
                        ComparisonOperator.NOT_EQUAL);
            else
                return new Comparison(
                        buildExpression(dataModel, expression.leftExpDTO),
                        buildExpression(dataModel, expression.rightExpDTO),
                        ComparisonOperator.NOT_EQUAL);
        case GREATER:
            return new Comparison(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    ComparisonOperator.GREATER);
        case GREATER_EQUAL:
            return new Comparison(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    ComparisonOperator.GREATER_EQUAL);
        case SMALLER:
            return new Comparison(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    ComparisonOperator.SMALLER);
        case SMALLER_EQUAL:
            return new Comparison(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    ComparisonOperator.SMALLER_EQUAL);
        case BOOL:
            if (expression.value.toLowerCase().equals("true"))
                return new TrueCondition();
            if (expression.value.toLowerCase().equals("false"))
                return new FalseCondition();
            assert(false);
            return null;
        default:
            assert(false);
            return null;
        }
    }

    private BWExpression buildExpression(BWDataModel dataModel,
            ExpressionDTO expression) {
        switch (expression.type) {
        case PLUS:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    BinaryOperator.PLUS);
        case MINUS:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    BinaryOperator.MINUS);
        case MUL:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    BinaryOperator.MUL);
        case DIV:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.leftExpDTO),
                    buildExpression(dataModel, expression.rightExpDTO),
                    BinaryOperator.DIV);
        case ATT_VALUE:
            List<String> pathLeft = Arrays.stream(expression.value.split("\\."))
                    .collect(Collectors.toList());
            BWEntity entity = dataModel.getEntity(pathLeft.get(0))
                    .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                            expression.value + ":" + pathLeft));
            pathLeft.remove(0);
            // TODO: remove cast
            return new BWAttributeValueExpression(
                    (BWAttribute) entity.getNext(pathLeft, expression.value));
        case INT:
            return new BWNumberLiteral(Integer.parseInt(expression.value));
        case STRING:
            return new BWStringLiteral(expression.value);
        default:
            assert(false);
            return null;
        }
    }

}
