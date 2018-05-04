package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

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
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }
    
}
