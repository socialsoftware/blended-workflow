package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprReportsToPosition extends RALExprReportsToPosition_Base {

    public RALExprReportsToPosition(ResourceModel resourceModel, Position position, boolean isDirectly) {
        setPosition(position);
        setDirectly(isDirectly);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPosition(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        List<Position> positions = getDirectly() ? Arrays.asList(getPosition().getReportsTo()) : getPosition().getAllPositionsReportsTo();

        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> positions.contains(position)))
                .collect(Collectors.toList());
    }

    @Override
    public void isMergable(RALExpression expression) {

    }
}
