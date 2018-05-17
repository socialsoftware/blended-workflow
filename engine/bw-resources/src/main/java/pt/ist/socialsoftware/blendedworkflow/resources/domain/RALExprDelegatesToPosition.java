package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprDelegatesToPosition extends RALExprDelegatesToPosition_Base {

    public RALExprDelegatesToPosition(ResourceModel resourceModel, Position position) {
        setPosition(position);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPosition(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        List<Position> positions = new ArrayList(getPosition().getCanDelegateWorkToSet());

        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> positions.contains(position)))
                .collect(Collectors.toList());
    }
    
}
