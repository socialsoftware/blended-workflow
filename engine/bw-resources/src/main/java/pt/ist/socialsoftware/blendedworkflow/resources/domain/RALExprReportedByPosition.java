package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprReportedByPosition extends RALExprReportedByPosition_Base {

    public RALExprReportedByPosition(ResourceModel resourceModel, Position position, boolean isDirectly) {
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
