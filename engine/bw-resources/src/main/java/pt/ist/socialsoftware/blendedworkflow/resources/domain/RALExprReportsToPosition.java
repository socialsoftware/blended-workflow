package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprReportsToPosition extends RALExprReportsToPosition_Base {

    public RALExprReportsToPosition(ResourceModel resourceModel, Position position, boolean isDirectly) {
        setPosition(position);
        setDirectly(isDirectly);
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
