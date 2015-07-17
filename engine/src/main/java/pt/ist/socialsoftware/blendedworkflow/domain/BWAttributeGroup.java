package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.List;
import java.util.Optional;

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
        if (pathLeft.isEmpty())
            return this;

        BWAttribute att = getAttribute(pathLeft.get(0)).orElseThrow(
                () -> new BWException(BWErrorType.INVALID_DEPENDENCE,
                        path + ":" + pathLeft));

        pathLeft.remove(0);
        return att.getNext(pathLeft, path);
    }

}
