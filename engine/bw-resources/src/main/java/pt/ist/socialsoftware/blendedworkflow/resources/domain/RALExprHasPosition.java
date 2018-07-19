package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().contains(getPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources().addPositions(new HashSet<>(Arrays.asList(getPosition().getDTO())));
    }

    @Override
    public void isMergable(RALExpression expression) {

    }

}
