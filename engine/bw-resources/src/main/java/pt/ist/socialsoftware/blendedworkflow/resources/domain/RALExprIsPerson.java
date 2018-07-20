package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
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
