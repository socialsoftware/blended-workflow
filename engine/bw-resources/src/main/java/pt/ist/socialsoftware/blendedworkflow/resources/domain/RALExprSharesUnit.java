package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.UnitDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RALExprSharesUnit extends RALExprSharesUnit_Base implements RALExprDeniable {

    public RALExprSharesUnit(ResourceModel resourceModel, Amount amount, RALPersonExpression personExpression) {
        setAmount(amount);
        setPersonExpr(personExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        List<Unit> units = new ArrayList();
        List<Person> persons = getPersonExpr().getEligibleResources(history, defProducts);
        persons.forEach(person -> units.addAll(person.getPositionSet().stream()
                .map(pos -> pos.getUnit())
                .collect(Collectors.toList())));

        return getPersonSet().stream().filter(person -> {
            if (persons.contains(person)) {
                return false;
            }

            switch (getAmount()) {
                case ALL:
                    return person.getPositionSet().stream()
                            .map(pos -> pos.getUnit())
                            .collect(toList())
                            .containsAll(units);
                case SOME:
                    return person.getPositionSet().stream()
                            .map(pos -> pos.getUnit())
                            .anyMatch(position -> units.contains(position));
            }
            return false;
        }).collect(toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        List<UnitDTO> units = null;
        if (getPersonExpr() instanceof RALExprIsPerson) {
            Person person = ((RALExprIsPerson) getPersonExpr()).getPerson();

            units = person.getPositionSet().stream()
                    .map(Position::getUnit)
                    .map(Unit::getDTO)
                    .collect(Collectors.toList());
        }

        return getPersonExpr().getSetOfRequiredResources().addUnits(units);
    }

    @Override
    public void isMergable(RALExpression expression) {
        getPersonExpr().isMergable(expression);
    }
}
