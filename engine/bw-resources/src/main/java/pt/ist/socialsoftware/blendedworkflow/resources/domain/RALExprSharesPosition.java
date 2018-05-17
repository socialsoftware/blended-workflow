package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RALExprSharesPosition extends RALExprSharesPosition_Base implements RALExprDeniable {

    public RALExprSharesPosition(ResourceModel resourceModel, Amount amount, RALPersonExpression personExpression) {
        setAmount(amount);
        setPersonExpr(personExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        List<Position> positions = new ArrayList();
        List<Person> persons = getPersonExpr().getEligibleResources(history);
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

}
