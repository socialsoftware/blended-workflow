package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RALExprSharesRole extends RALExprSharesRole_Base implements RALExprDeniable {

    public RALExprSharesRole(ResourceModel resourceModel, Amount amount, RALPersonExpression personExpression) {
        setResourceModel(resourceModel);
        setPersonExpr(personExpression);
        setAmount(amount);
        setUnit(null);
    }

    public RALExprSharesRole(ResourceModel resourceModel, Amount amount, RALPersonExpression personExpression, Unit unit) {
        this(resourceModel, amount, personExpression);
        setUnit(unit);
    }

    @Override
    public void delete() {
        setUnit(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        List<Role> roles = new ArrayList();
        List<Person> persons = getPersonExpr().getEligibleResources(history);
        persons.forEach(person -> roles.addAll(person.getPositionSet().stream()
                .map(position -> position.getRoleSet())
                .flatMap(Collection::stream)
                .collect(Collectors.toList())));

        return getPersonSet().stream().filter(person -> {
            if (persons.contains(person)) {
                return false;
            }

            switch (getAmount()) {
                case ALL:
                    return person.getPositionSet().stream()
                            .map(position -> position.getRoleSet())
                            .flatMap(Collection::stream)
                            .collect(toList())
                            .containsAll(roles);
                case SOME:
                    return person.getPositionSet().stream()
                            .map(position -> position.getRoleSet())
                            .flatMap(Collection::stream)
                            .anyMatch(role -> roles.contains(role));
            }
            return false;
        }).collect(toList());
    }

    @Override
    public void isMergable(RALExpression expression) {
        getPersonExpr().isMergable(expression);
    }

}
