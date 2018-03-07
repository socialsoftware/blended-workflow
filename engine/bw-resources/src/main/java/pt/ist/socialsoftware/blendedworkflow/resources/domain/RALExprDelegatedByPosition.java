package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprDelegatedByPosition extends RALExprDelegatedByPosition_Base {

    public RALExprDelegatedByPosition(ResourceModel resourceModel, Position position) {
        setPosition(position);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPosition(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }
    
}
