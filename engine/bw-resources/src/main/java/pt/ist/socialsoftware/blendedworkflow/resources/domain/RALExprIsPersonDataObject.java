package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;

import java.util.List;

public class RALExprIsPersonDataObject extends RALExprIsPersonDataObject_Base {
    
    public RALExprIsPersonDataObject(ResourceModel resourceModel, Entity entity) {
        setDataField(entity);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setDataField(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }

}
