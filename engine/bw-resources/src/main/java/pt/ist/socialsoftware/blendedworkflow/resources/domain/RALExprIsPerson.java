package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Arrays;
import java.util.HashSet;
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
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources().addPersons(new HashSet<>(Arrays.asList(getPerson().getDTO())));
    }

    @Override
    public void isMergable(RALExpression expression) {

    }


}
