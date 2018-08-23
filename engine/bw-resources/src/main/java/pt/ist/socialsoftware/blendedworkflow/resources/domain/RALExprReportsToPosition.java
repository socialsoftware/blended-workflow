package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        List<Position> positions = getDirectly() ? Arrays.asList(getPosition().getReportsTo()) : getPosition().getAllPositionsReportsTo();

        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> positions.contains(position)))
                .collect(Collectors.toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        List<Position> positions = getDirectly() ? Arrays.asList(getPosition().getReportsTo()) : getPosition().getAllPositionsReportsTo();

        return new SetOfRequiredResources().addPositions(
                positions
                        .stream()
                        .filter(Objects::nonNull)
                        .map(Position::getDTO)
                        .collect(Collectors.toList()));
    }

    @Override
    public void isMergable(RALExpression expression) {

    }
}
