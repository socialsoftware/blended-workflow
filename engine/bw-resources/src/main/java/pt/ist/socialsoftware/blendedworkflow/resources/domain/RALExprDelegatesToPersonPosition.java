package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprDelegatesToPersonPosition extends RALExprDelegatesToPersonPosition_Base {

    public RALExprDelegatesToPersonPosition(ResourceModel resourceModel, RALPersonExpression personExpression) {
        setPersonExpr(personExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPersonExpr(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        List<Position> positions = new ArrayList();
        getPersonExpr().getEligibleResources(history).stream()
                .forEach(person -> person.getPositionSet().stream().forEach(position -> {
                    positions.addAll(position.getCanDelegateWorkToSet());
                }));

        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> positions.contains(position)))
                .collect(Collectors.toList());

    }
}
