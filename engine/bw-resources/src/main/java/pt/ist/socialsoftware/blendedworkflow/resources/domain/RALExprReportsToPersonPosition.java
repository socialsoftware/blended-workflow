package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprReportsToPersonPosition extends RALExprReportsToPersonPosition_Base {
    
    public RALExprReportsToPersonPosition(ResourceModel resourceModel, RALPersonExpression personExpression, boolean isDirectly) {
        setPersonExpr(personExpression);
        setDirectly(isDirectly);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPersonExpr(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        List<Position> positions = new ArrayList();
        getPersonExpr().getEligibleResources(history).stream()
                .forEach(person -> person.getPositionSet().stream().forEach(position -> {
                    if (getDirectly()) {
                        positions.add(position.getReportsTo());
                    } else {
                        positions.addAll(position.getAllPositionsReportsTo());
                    }
                }));

        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> positions.contains(position)))
                .collect(Collectors.toList());
    }
}
