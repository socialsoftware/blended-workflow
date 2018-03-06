package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprHasPosition extends RALExprHasPosition_Base {
    
    public RALExprHasPosition(ResourceModel resourceModel, Position position) {
        setResourceModel(resourceModel);
        setPosition(position);
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
