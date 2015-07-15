package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWAttributeGroup extends BWAttributeGroup_Base {

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public BWAttributeGroup(BWDataModel dataModel, BWEntity entity,
            String name) {
        setDataModel(dataModel);
        setEntity(entity);
        setName(name);
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

    public void delete() {
        setDataModel(null);
        setEntity(null);
        getAttributeSet().stream().forEach(att -> att.delete());
    }

    public BWAttribute getAttribute(String name) {
        return getAttributeSet().stream()
                .filter(att -> att.getName().equals(name)).findFirst()
                .orElse(null);
    }

}
