package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RALExprDelegatedByPosition extends RALExprDelegatedByPosition_Base {

    public RALExprDelegatedByPosition(ResourceModel resourceModel, Position position) {
        setPosition(position);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPosition(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        List<Position> positions = new ArrayList(getPosition().getWorkDelegatedBySet());

        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> positions.contains(position)))
                .collect(Collectors.toList());

    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources().addPositions(
                getPosition().getWorkDelegatedBySet().stream()
                        .map(Position::getDTO)
                        .collect(Collectors.toList()));
    }

    @Override
    public void isMergable(RALExpression expression) {

    }

}
