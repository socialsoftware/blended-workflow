package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;

public class Attribute extends Attribute_Base {
    private static Logger log = LoggerFactory.getLogger(Attribute.class);

    final static String ATTRIBUTE_TYPE = "(" + AttributeType.STRING + "|"
            + AttributeType.NUMBER + "|" + AttributeType.BOOLEAN + "|"
            + AttributeType.DATE + ")";

    public static enum AttributeType {
        BOOLEAN("Boolean"), NUMBER("Number"), STRING("String"), DATE("Date");
        private String name;

        AttributeType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static Attribute.AttributeType parseAttributeType(String type) {
            if (!Pattern.matches(ATTRIBUTE_TYPE, type))
                throw new BWException(BWErrorType.INVALID_ATTRIBUTE_TYPE);

            Attribute.AttributeType res = null;

            if (type.equals(AttributeType.STRING.toString()))
                return AttributeType.STRING;

            if (type.equals(AttributeType.NUMBER.toString()))
                return AttributeType.NUMBER;

            if (type.equals(AttributeType.BOOLEAN.toString()))
                return AttributeType.BOOLEAN;

            if (type.equals(AttributeType.DATE.toString()))
                return AttributeType.DATE;

            assert(false);

            return res;
        }

    };

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public Attribute(DataModel dataModel, Entity entity, AttributeGroup group,
            String name, AttributeType type, boolean isMandatory,
            boolean isKeyAttribute, boolean isSystem) {
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

        for (Attribute attribute : getEntity().getAttributesSet()) {
            if ((attribute != this) && attribute.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_ATTRIBUTE_NAME, name);
            }
        }
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ATTRIBUTE;
    }

    public void cloneAttribute(DataModelInstance dataModelInstance,
            Entity entity) throws BWException {
        new Attribute(dataModelInstance, entity, getAttributeGroup(), getName(),
                getType(), getIsMandatory(), getIsKeyAttribute(),
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
        if (getDefAttributeCondition() != null)
            getDefAttributeCondition().delete();
        getAttValueExpressionSet().stream().forEach(exp -> exp.delete());
        getAttBoolConditionSet().stream().forEach(cond -> cond.delete());

        super.delete();
    }

    @Override
    public Product getNext(List<String> pathLeft, String path) {
        log.debug("getNext {}:{}", path, pathLeft);

        if (pathLeft.size() == 0)
            return this;

        throw new BWException(BWErrorType.INVALID_PATH, path + ":" + pathLeft);
    }

    @Override
    public AttributeDTO getDTO() {
        AttributeDTO attDTO = new AttributeDTO();
        attDTO.setSpecId(getDataModel().getSpecification().getSpecId());
        attDTO.setExtId(getExternalId());
        attDTO.setProductType(ProductType.ATTRIBUTE.name());
        attDTO.setEntityExtId(getEntity().getExternalId());
        attDTO.setEntityName(getEntity().getName());
        attDTO.setGroupExtId(getAttributeGroup() != null
                ? getAttributeGroup().getExternalId() : null);
        attDTO.setName(getName());
        attDTO.setType(getType().toString());
        attDTO.setIsMandatory(getIsMandatory());

        return attDTO;
    }

    @Override
    public Condition getDefCondition() {
        return DefAttributeCondition.getDefAttribute(this);
    }

}