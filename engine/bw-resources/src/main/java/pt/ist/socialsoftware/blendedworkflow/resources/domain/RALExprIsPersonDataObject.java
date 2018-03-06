package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute;

import java.util.List;

public class RALExprIsPersonDataObject extends RALExprIsPersonDataObject_Base {
    
    public RALExprIsPersonDataObject(ResourceModel resourceModel, Attribute attribute) {
        setDataField(attribute);
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
