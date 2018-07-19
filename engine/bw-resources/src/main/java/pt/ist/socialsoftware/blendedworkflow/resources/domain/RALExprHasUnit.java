package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RALExprHasUnit extends RALExprHasUnit_Base {
    
    public RALExprHasUnit(ResourceModel resourceModel, Unit unit) {
        setResourceModel(resourceModel);
        setUnit(unit);
    }

    @Override
    public void delete() {
        setUnit(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> position.getUnit().getName().equals(getUnit().getName())))
                .collect(Collectors.toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources().addUnits(new HashSet<>(Arrays.asList(getUnit().getDTO())));
    }

    @Override
    public void isMergable(RALExpression expression) {

    }

}
