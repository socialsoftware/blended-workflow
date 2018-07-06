package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.PositionDTO;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RALExprReportedByPersonPosition extends pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprReportedByPersonPosition_Base {

    public RALExprReportedByPersonPosition(ResourceModel resourceModel, RALPersonExpression personExpression, boolean isDirectly) {
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
                        positions.addAll(position.getIsReportedBySet());
                    } else {
                        positions.addAll(position.getAllPositionsReportedBy());
                    }
                }));

        return getPersonSet().stream()
                .filter(person -> person.getPositionSet().stream().anyMatch(position -> positions.contains(position)))
                .collect(Collectors.toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        List<PositionDTO> positions = null;
        if (getPersonExpr() instanceof RALExprIsPerson) {
            Person person = ((RALExprIsPerson) getPersonExpr()).getPerson();

            positions = person.getPositionSet().stream()
                    .map(position -> {
                        if (getDirectly()) {
                            return position.getIsReportedBySet();
                        } else {
                            return position.getAllPositionsReportedBy();
                        }
                    })
                    .flatMap(Collection::stream)
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
