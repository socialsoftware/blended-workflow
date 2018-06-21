package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.List;
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
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().contains(getPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public void isMergable(RALExpression expression) {

    }

}
