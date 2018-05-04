package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.List;

public class RALExprHasPosition extends RALExprHasPosition_Base implements RALExprDeniable {
    
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
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }

}
