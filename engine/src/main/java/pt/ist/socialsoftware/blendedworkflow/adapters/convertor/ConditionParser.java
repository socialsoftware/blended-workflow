package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.ExistsOneCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.ForAllCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.NotCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.OrCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class ConditionParser {

    private final int STR_LENGHT;

    private static String _cond;

    private final DataModel dataModel;
    private int _token;

    public ConditionParser(DataModel dataModel, String condition)
            throws BWException {
        if (condition == null || condition.equals("")) {
            throw new BWException(BWErrorType.EMPTY_CONDITION_STRING);
        }
        this.dataModel = dataModel;
        _cond = condition;
        STR_LENGHT = condition.length();
        _token = 0;
    }

    public Condition parseCondition() throws BWException {
        Condition finalCondition = null;
        if (_cond.startsWith("existsAttribute(")
                || _cond.startsWith("existsEntity(")
                || _cond.startsWith("compareAttributeTo(")
                || _cond.startsWith("existsOne[")
                || _cond.startsWith("forAll[")) {
            finalCondition = parseConditionType();
        } else if (_cond.startsWith("true")) {
            return new TrueCondition();
        } else {
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING, _cond);
        }

        return continueParseCondition(finalCondition);
    }

    protected Condition continueParseCondition(Condition parsedCondition)
            throws BWException {
        while (_token < STR_LENGHT) {
            if (_cond.startsWith(" and ", _token)
                    || _cond.startsWith(" or ", _token)) {
                parsedCondition = parseConditionJoiner(parsedCondition);
            } else {
                throw new BWException(BWErrorType.INVALID_CONDITION_STRING,
                        _cond);
            }
        }
        return parsedCondition;
    }

    // //// PARSE RULES /////
    protected Condition parseConditionType() throws BWException {
        Condition parsedCondition;
        if (_cond.startsWith("existsAttribute(", _token)) {
            parsedCondition = parseExistsAttributeCondition();
        } else if (_cond.startsWith("existsEntity(", _token)) {
            parsedCondition = parseExistsEntityCondition();
        } else if (_cond.startsWith("compareAttributeTo(", _token)) {
            parsedCondition = parseCompareAttributeToCondition();
        } else if (_cond.startsWith("existsOne[", _token)) {
            parsedCondition = parseExistsOneCondition();
        } else if (_cond.startsWith("forAll[", _token)) {
            parsedCondition = parseForAllCondition();
        } else {
            return null;
        }

        // see if there is a .not() next
        if (_cond.startsWith(".not()", _token)) {
            parsedCondition = parseNotCondition(parsedCondition);
        }
        return parsedCondition;
    }

    protected Condition parseExistsAttributeCondition() throws BWException {
        int endOfCondition = _cond.indexOf(')', _token);
        if (endOfCondition < _token) {
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING, _cond);
        }
        String existsAttributeString = _cond.substring(_token,
                endOfCondition + 1);
        StringBuilder elementName = new StringBuilder();
        int startArgs = "existsAttribute(".length();

        Attribute attribute = parseExistsAttributeConditionArgs(
                existsAttributeString, startArgs,
                existsAttributeString.length() - 1, elementName);
        Condition existsAttributeCondition = DefAttributeCondition
                .getDefAttribute(attribute);
        _token = endOfCondition + 1;
        return existsAttributeCondition;
    }

    protected Attribute parseExistsAttributeConditionArgs(
            String existsAttributeCondition, int startArgs, int endArgs,
            StringBuilder elementName) throws BWException {
        if (startArgs > endArgs)
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING,
                    existsAttributeCondition);

        elementName
                .append(existsAttributeCondition.substring(startArgs, endArgs));

        String[] elementArr = elementName.toString().split("\\.");

        Entity entity = parseEntity(elementArr);
        return parseAttribute(elementArr, entity);

    }

    protected Condition parseExistsEntityCondition() throws BWException {
        int endOfCondition = _cond.indexOf(')', _token);
        if (endOfCondition < _token) {
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING, _cond);
        }

        String existsEntityString = _cond.substring(_token, endOfCondition + 1);
        StringBuilder elementName = new StringBuilder();
        int startArgs = "existsEntity(".length();
        Entity entity = parseExistsEntityConditionArgs(existsEntityString,
                startArgs, existsEntityString.length() - 1, elementName);

        Condition existsEntityCondition = entity.getDefEntityCondition();
        _token = endOfCondition + 1;
        return existsEntityCondition;
    }

    protected Entity parseExistsEntityConditionArgs(
            String existsEntityCondition, int startArgs, int endArgs,
            StringBuilder elementName) throws BWException {
        if (startArgs > endArgs)
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING,
                    existsEntityCondition);

        elementName.append(existsEntityCondition.substring(startArgs, endArgs));

        if (dataModel.getEntity(elementName.toString()) != null)
            return dataModel.getEntity(elementName.toString()).get();
        else
            return new Entity(dataModel, elementName.toString(), false);
    }

    protected Condition parseCompareAttributeToCondition() throws BWException {
        int endOfCondition = _cond.indexOf(')', _token);

        if (endOfCondition < _token) {
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING, _cond);
        }

        String compareAttributeToString = _cond.substring(_token,
                endOfCondition + 1);
        StringBuilder elementName = new StringBuilder();
        StringBuilder elementTo = new StringBuilder();
        int startArgs = "compareAttributeTo(".length();

        Attribute attribute;
        String operator;
        String value;

        // FIXME REFACTOR
        int endArgs = compareAttributeToString.length() - 1;
        int subToken = compareAttributeToString.indexOf(',', startArgs);

        // Parse Entity and Attribute
        elementName.append(
                compareAttributeToString.substring(startArgs, subToken));
        String[] elementArr = elementName.toString().split("\\.");
        Entity entity = parseEntity(elementArr);
        attribute = parseAttribute(elementArr, entity);

        // Parse Operator and Value
        elementTo.append(compareAttributeToString
                .substring(subToken + 1, endArgs).trim());
        String[] toArr = elementTo.toString().split("\\.");
        operator = toArr[0];
        value = toArr[1];
        // FIXME REFACTOR

        Comparison compareAttributeToCondition = new Comparison(attribute,
                operator, value);
        _token = endOfCondition + 1;

        return compareAttributeToCondition;
    }

    protected Condition parseForAllCondition() throws BWException {
        int endOfCondition = _cond.indexOf(']', _token);

        if (endOfCondition < _token) {
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING, _cond);
        }
        String forAllString = _cond.substring(_token, endOfCondition + 1);
        StringBuilder relationString = new StringBuilder();
        StringBuilder subConditionString = new StringBuilder();
        int startArgs = "forAll[".length();

        int endArgs = forAllString.length() - 1;
        int subToken = forAllString.indexOf(',', startArgs);

        // Parse Entity and Relation
        relationString.append(forAllString.substring(startArgs, subToken));
        String[] elementArr = relationString.toString().split("\\.");
        Entity entity = parseEntity(elementArr);
        RelationBW relation = parseRelation(elementArr[1]);
        // Parse condition
        subConditionString
                .append(forAllString.substring(subToken + 1, endArgs).trim());

        String subCondition = subConditionString.toString().replaceAll("]", "");
        Condition newCondition = new ConditionParser(dataModel, subCondition)
                .parseCondition();

        ForAllCondition forAllCondition = new ForAllCondition(relation, entity,
                newCondition);
        _token = endOfCondition + 1;
        return forAllCondition;
    }

    protected Condition parseExistsOneCondition() throws BWException {
        int endOfCondition = _cond.indexOf(']', _token);

        if (endOfCondition < _token) {
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING, _cond);
        }
        String exitsOneString = _cond.substring(_token, endOfCondition + 1);
        StringBuilder relationString = new StringBuilder();
        StringBuilder subConditionString = new StringBuilder();
        int startArgs = "existsOne[".length();

        int endArgs = exitsOneString.length() - 1;
        int subToken = exitsOneString.indexOf(',', startArgs);

        // Parse Entity and Relation
        relationString.append(exitsOneString.substring(startArgs, subToken));
        String[] elementArr = relationString.toString().split("\\.");
        Entity entity = parseEntity(elementArr);
        RelationBW relation = parseRelation(elementArr[1]);
        // Parse condition
        subConditionString
                .append(exitsOneString.substring(subToken + 1, endArgs).trim());

        String subCondition = subConditionString.toString().replaceAll("]", "");
        Condition newCondition = new ConditionParser(dataModel, subCondition)
                .parseCondition();

        ExistsOneCondition existOneCondition = new ExistsOneCondition(relation,
                entity, newCondition);
        _token = endOfCondition + 1;
        return existOneCondition;
    }

    protected Condition parseNotCondition(Condition typeCondition) {
        _token += ".not()".length();
        return new NotCondition(typeCondition);
    }

    protected Condition parseConditionJoiner(Condition parsedCondition)
            throws BWException {
        if (_cond.startsWith(" and ", _token)) {
            return parseAndCondition(parsedCondition);
        } else if (_cond.startsWith(" or ", _token)) {
            return parseOrCondition(parsedCondition);
        } else {
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING, _cond);
        }
    }

    protected Condition parseAndCondition(Condition parsedCondition)
            throws BWException {
        _token += " and ".length();
        return new AndCondition(parsedCondition, parseConditionType());
    }

    protected Condition parseOrCondition(Condition parsedCondition)
            throws BWException {
        _token += " or ".length();
        return new OrCondition(parsedCondition, parseConditionType());
    }

    private Entity parseEntity(String[] elementArr) throws BWException {
        Entity entity;
        if (dataModel.getEntity(elementArr[0]) != null)
            entity = dataModel.getEntity(elementArr[0]).get();
        else
            entity = new Entity(dataModel, elementArr[0], false);
        return entity;
    }

    private RelationBW parseRelation(String relationName) throws BWException {
        RelationBW relation;
        if (dataModel.getRelation(relationName) != null)
            relation = dataModel.getRelation(relationName);
        else
            throw new BWException(BWErrorType.INVALID_CONDITION_STRING);
        return relation;
    }

    private Attribute parseAttribute(String[] elementArr, Entity entity)
            throws BWException {
        // AttributeType type;
        // boolean iskeyAttribute;
        if (entity.getAttribute(elementArr[1]) != null)
            return entity.getAttribute(elementArr[1]).orElse(null);
        else {
            // Legacy Code: used to create data from Conditions
            // if (elementArr[2].equals("BOOLEAN"))
            // type = AttributeType.BOOLEAN;
            // else if (elementArr[2].equals("NUMBER"))
            // type = AttributeType.NUMBER;
            // else
            // type = AttributeType.STRING;
            //
            // if (elementArr[3].equals("true"))
            // iskeyAttribute = true;
            // else
            // iskeyAttribute = false;
            //
            // return new Attribute(dataModel, elementArr[1], entity, type,
            // iskeyAttribute, false); //FIXME: isSystem
            System.out.println(
                    "Condition Attribute does not exist in data specification.");
            return null;
        }
    }

}