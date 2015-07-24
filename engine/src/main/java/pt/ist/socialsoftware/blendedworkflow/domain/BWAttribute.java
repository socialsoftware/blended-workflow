package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWAttribute extends BWAttribute_Base {
    private static Logger log = LoggerFactory.getLogger(BWAttribute.class);

    public enum AttributeType {
        BOOLEAN, NUMBER, STRING, DATE
    };

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public BWAttribute(BWDataModel dataModel, BWEntity entity,
            BWAttributeGroup group, String name, AttributeType type,
            boolean isMandatory, boolean isKeyAttribute, boolean isSystem) {
        setDataModel(dataModel);
        setEntity(entity);
        setAttributeGroup(group);
        setName(name);
        setType(type);
        setIsMandatory(isMandatory);
        setIsKeyAttribute(isKeyAttribute);
        setIsSystem(isSystem);
    }

    private void checkName(String name) {
        if ((name == null) || name.equals(""))
            throw new BWException(BWErrorType.INVALID_ATTRIBUTE_NAME, name);

        checkUniqueAttributeName(name);
    }

    private void checkUniqueAttributeName(String name) throws BWException {
        // Optional<Attribute> res = getEntity().getAttributesSet().stream()
        // .filter(att -> ((att != this) && att.getName().equals(name)))
        // .findFirst();
        //
        // if (res.isPresent()) {
        // new BWException(BlendedWorkflowError.INVALID_ATTRIBUTE_NAME, name);
        // }

        for (BWAttribute attribute : getEntity().getAttributesSet()) {
            if ((attribute != this) && attribute.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_ATTRIBUTE_NAME, name);
            }
        }
    }

    public void cloneAttribute(DataModelInstance dataModelInstance,
            BWEntity entity) throws BWException {
        new BWAttribute(dataModelInstance, entity, getAttributeGroup(),
                getName(), getType(), getIsMandatory(), getIsKeyAttribute(),
                getIsSystem());
    }

    /**
     * FIXME: Double/Boolean
     */
    public String getYAWLAttributeType() {
        return "string";
    }

    public String getYAWLAttributeValue() {
        if (this.getType().equals(AttributeType.NUMBER)) {
            return "0";
        } else {
            return "string";
        }
    }

    @Override
    public void delete() {
        setDataModel(null);
        setEntity(null);
        setAttributeGroup(null);
        getAttValueExpressionSet().stream()
                .forEach(exp -> exp.setAttribute(null));
        getDefAttributeConditionSet().stream()
                .forEach(def -> def.setAttribute(null));
        getAttBoolConditionSet().stream()
                .forEach(cond -> cond.setAttribute(null));

        super.delete();
    }

    @Override
    public BWProduct getNext(List<String> pathLeft, String path) {
        log.debug("getNext {}:{}", path, pathLeft);

        if (pathLeft.size() == 0)
            return this;

        throw new BWException(BWErrorType.INVALID_PATH, path + ":" + pathLeft);
    }

}