package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.Arrays;
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
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return Arrays.asList(getPerson());
    }

    @Override
    public void isMergable(RALExpression expression) {

    }


}
