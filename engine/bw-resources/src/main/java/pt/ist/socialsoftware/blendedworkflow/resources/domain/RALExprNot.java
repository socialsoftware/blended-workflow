package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprNot extends RALExprNot_Base {

    public RALExprNot(ResourceModel resourceModel, RALExpression expression) throws RMException {
        if (!(expression instanceof RALExprDeniable)) {
            throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_TYPE, "The expression provided to the NOT expression is not deniable");
        }
        setExpression(expression);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setExpression(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return getPersonSet().stream()
                .filter(person -> !getExpression().getEligibleResources(history).contains(person))
                .collect(Collectors.toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        SetOfRequiredResources set = getExpression().getSetOfRequiredResources();

        set.addNotPositions(set.getPositions());
        set.setPositions(new HashSet<>());

        set.addNotPersons(set.getPersons());
        set.setPersons(new HashSet<>());

        set.addNotCapabilities(set.getCapabilities());
        set.setCapabilities(new HashSet<>());

        set.addNotRoles(set.getRoles());
        set.setRoles(new HashSet<>());

        set.addNotUnits(set.getUnits());
        set.setUnits(new HashSet<>());

        set.addNotDataField(set.getDataField());
        set.setDataField(new HashSet<>());

        set.addNotTaskDuty(set.getTaskDuty());
        set.setTaskDuty(new HashSet<>());

        return set;
    }

    @Override
    public void isMergable(RALExpression expression) {
        getExpression().isMergable(expression);
    }
}
