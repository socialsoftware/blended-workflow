package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprIsPerson extends RALExprIsPerson_Base {
    
    public RALExprIsPerson(ResourceModel resourceModel, Person person) {
        setResourceModel(resourceModel);
        setPerson(person);
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }

}
