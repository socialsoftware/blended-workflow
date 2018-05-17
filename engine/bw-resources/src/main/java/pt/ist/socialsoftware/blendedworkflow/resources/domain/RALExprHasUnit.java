package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.List;
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
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> position.getUnit().getName().equals(getUnit().getName())))
                .collect(Collectors.toList());
    }

}
