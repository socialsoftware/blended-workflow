package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeGroupDTO;

public class BWAttributeGroup extends BWAttributeGroup_Base {
    private static Logger log = LoggerFactory.getLogger(BWAttributeGroup.class);

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public BWAttributeGroup(BWDataModel dataModel, BWEntity entity, String name,
            boolean isMandatory) {
        setDataModel(dataModel);
        setEntity(entity);
        setName(name);
        setIsMandatory(isMandatory);
    }

    public BWAttributeGroup() {
        // TODO Auto-generated constructor stub
    }

    private void checkName(String name) {
        if ((name == null) || name.equals("")) {
            throw new BWException(BWErrorType.INVALID_ATTRIBUTE_GROUP_NAME,
                    name);
        }

        if (getEntity().getAttributeGroupSet().stream().anyMatch(
                attG -> (attG != this) && attG.getName().equals(name)))
            throw new BWException(BWErrorType.INVALID_ATTRIBUTE_GROUP_NAME,
                    name);
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ATTRIBUTE_GROUP;
    }

    public Optional<BWAttribute> getAttribute(String name) {
        return getAttributeSet().stream()
                .filter(att -> att.getName().equals(name)).findFirst();
    }

    @Override
    public void delete() {
        setDataModel(null);
        setEntity(null);
        getAttributeSet().stream().forEach(att -> att.delete());

        super.delete();
    }

    @Override
    public BWProduct getNext(List<String> pathLeft, String path) {
        log.debug("getNext {}:{}", path, pathLeft);

        if (pathLeft.isEmpty())
            return this;

        BWAttribute att = getAttribute(pathLeft.get(0))
                .orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH,
                        path + ":" + pathLeft));

        pathLeft.remove(0);
        return att.getNext(pathLeft, path);
    }

    @Override
    public AttributeGroupDTO getDTO() {
        AttributeGroupDTO group = new AttributeGroupDTO();
        group.setExtId(getExternalId());
        group.setDataModelExtId(getDataModel().getExternalId());
        group.setProductType(getProductType().name());
        group.setEntityExtId(getExternalId());
        group.setName(getName());
        group.setMandatory(getIsMandatory());

        return group;
    }

}
