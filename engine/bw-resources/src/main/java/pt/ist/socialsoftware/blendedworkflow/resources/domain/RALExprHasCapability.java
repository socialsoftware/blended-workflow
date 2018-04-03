package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprHasCapability extends RALExprHasCapability_Base implements RALExprDeniable {

    public RALExprHasCapability(ResourceModel resourceModel, Capability capability) {
        setResourceModel(resourceModel);
        setCapability(capability);
    }

    @Override
    public void delete() {
        setCapability(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }
    
}
