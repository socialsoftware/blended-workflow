package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.List;

public class RALExprIsPerson extends RALExprIsPerson_Base {
    
    public RALExprIsPerson(ResourceModel resourceModel, Person person) {
        setResourceModel(resourceModel);
        setPerson(person);
    }

    @Override
    public void delete() {
        setPerson(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }

}
