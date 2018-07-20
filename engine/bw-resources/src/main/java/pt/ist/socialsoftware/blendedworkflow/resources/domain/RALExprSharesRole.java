package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.RoleDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.*;
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
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        List<Role> roles = new ArrayList();
        List<Person> persons = getPersonExpr().getEligibleResources(history, defProducts);
        persons.forEach(person -> roles.addAll(person.getPositionSet().stream()
                .filter(position -> {
                    if (getUnit() != null) {
                        return position.getUnit().equals(getUnit());
                    } else {
                        return true;
                    }
                })
                .map(Position_Base::getRoleSet)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())));

        return getPersonSet().stream().filter(person -> {
            if (persons.contains(person)) {
                return false;
            }

            switch (getAmount()) {
                case ALL:
                    return person.getPositionSet().stream()
                            .map(Position::getRoleSet)
                            .flatMap(Collection::stream)
                            .collect(toList())
                            .containsAll(roles);
                case SOME:
                    return person.getPositionSet().stream()
                            .map(Position::getRoleSet)
                            .flatMap(Collection::stream)
                            .anyMatch(role -> roles.contains(role));
            }
            return false;
        }).collect(toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        SetOfRequiredResources set = getPersonExpr().getSetOfRequiredResources();
        List<RoleDto> roles = null;
        if (getPersonExpr() instanceof RALExprIsPerson) {
            Person person = ((RALExprIsPerson) getPersonExpr()).getPerson();

            roles = person.getPositionSet().stream()
                    .filter(position -> {
                        if (getUnit() != null) {
                            return position.getUnit().equals(getUnit());
                        } else {
                            return true;
                        }
                    })
                    .map(Position::getRoleSet)
                    .flatMap(Collection::stream)
                    .map(Role::getDTO)
                    .collect(Collectors.toList());
        }

        if (getUnit() != null) {
            set.addUnits(new HashSet<>(Arrays.asList(getUnit().getDTO())));
        }

        return set.addRoles(roles);
    }

    @Override
    public void isMergable(RALExpression expression) {
        getPersonExpr().isMergable(expression);
    }

}
