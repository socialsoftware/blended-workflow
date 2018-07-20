package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.PositionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RALExprSharesPosition extends RALExprSharesPosition_Base implements RALExprDeniable {

    public RALExprSharesPosition(ResourceModel resourceModel, Amount amount, RALPersonExpression personExpression) {
        setAmount(amount);
        setPersonExpr(personExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        List<Position> positions = new ArrayList();
        List<Person> persons = getPersonExpr().getEligibleResources(history, defProducts);
        persons.forEach(person -> positions.addAll(person.getPositionSet()));

        return getPersonSet().stream().filter(person -> {
            if (persons.contains(person)) {
                return false;
            }

            switch (getAmount()) {
                case ALL:
                    return person.getPositionSet().containsAll(positions);
                case SOME:
                    return person.getPositionSet().stream().anyMatch(position -> positions.contains(position));
            }
            return false;
        }).collect(toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        List<PositionDTO> positions = null;
        if (getPersonExpr() instanceof RALExprIsPerson) {
            Person person = ((RALExprIsPerson) getPersonExpr()).getPerson();

            positions = person.getPositionSet().stream()
                    .map(Position::getDTO)
                    .collect(Collectors.toList());
        }

        return getPersonExpr().getSetOfRequiredResources().addPositions(positions);
    }

    @Override
    public void isMergable(RALExpression expression) {
        getPersonExpr().isMergable(expression);
    }

}
