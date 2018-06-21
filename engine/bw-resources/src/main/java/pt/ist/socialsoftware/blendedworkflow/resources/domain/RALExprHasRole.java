package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprHasRole extends RALExprHasRole_Base implements RALExprDeniable {
    
    public RALExprHasRole(ResourceModel resourceModel, Role role) {
        setResourceModel(resourceModel);
        setRole(role);
        setUnit(null);
    }

    public RALExprHasRole(ResourceModel resourceModel, Role role, Unit unit) {
        this(resourceModel, role);
        setUnit(unit);
    }

    @Override
    public void delete() {
        setUnit(null);
        setRole(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return getPersonSet().stream().filter(person -> {
            List<Role> roles = new ArrayList();
            person.getPositionSet().stream().forEach(position -> {
                if ((getUnit() != null && position.getUnit().getName().equals(getUnit().getName())) || getUnit() == null) {
                    roles.addAll(position.getRoleSet());
                }
            });

            return roles.contains(getRole());
        }).collect(Collectors.toList());
    }

    @Override
    public void isMergable(RALExpression expression) {

    }

}
