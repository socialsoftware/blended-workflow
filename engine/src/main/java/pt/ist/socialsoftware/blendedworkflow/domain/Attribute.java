package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class Attribute extends Attribute_Base {

    public enum AttributeType {
        BOOLEAN, NUMBER, STRING
    };

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public Attribute(DataModel dataModel, String name, Entity entity,
            AttributeType type, boolean isKeyAttribute, boolean isSystem)
                    throws BWException {
        setDataModel(dataModel);
        setEntity(entity);
        setName(name);
        setType(type);
        setIsKeyAttribute(isKeyAttribute);
        setIsSystem(isSystem);
    }

    private void checkName(String name) {
        if ((name == null) || name.equals(""))
            throw new BWException(BlendedWorkflowError.INVALID_ATTRIBUTE_NAME,
                    name);

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

        for (Attribute attribute : getEntity().getAttributesSet()) {
            if ((attribute != this) && attribute.getName().equals(name)) {
                throw new BWException(
                        BlendedWorkflowError.INVALID_ATTRIBUTE_NAME, name);
            }
        }
    }

    public void cloneAttribute(DataModelInstance dataModelInstance,
            Entity entity) throws BWException {
        new Attribute(dataModelInstance, getName(), entity, getType(),
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