package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWAttribute extends BWAttribute_Base {

    public enum AttributeType {
        BOOLEAN, NUMBER, STRING
    };

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public BWAttribute(BWDataModel dataModel, String name, BWEntity entity,
            AttributeType type, boolean isKeyAttribute, boolean isSystem) {
        setDataModel(dataModel);
        setEntity(entity);
        setName(name);
        setType(type);
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
        new BWAttribute(dataModelInstance, getName(), entity, getType(),
                getIsKeyAttribute(), getIsSystem());
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

    public void delete() {
        setDataModel(null);
        setEntity(null);
        deleteDomainObject();
    }

}