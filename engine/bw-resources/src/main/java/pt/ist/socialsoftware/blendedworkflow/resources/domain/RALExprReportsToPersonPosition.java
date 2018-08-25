package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.PositionDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.*;
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
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        List<Position> positions = new ArrayList<>();
        getPersonExpr().getEligibleResources(history, defProducts).stream()
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

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        List<PositionDto> positions = null;

        if (getPersonExpr() instanceof RALExprIsPerson) {
            Person person = ((RALExprIsPerson) getPersonExpr()).getPerson();


            /*List<Position> mapped = new ArrayList<>();
            for (Position pos : person.getPositionSet()) {
                if (getDirectly()) {
                    mapped.add(pos.getReportsTo());
                } else {
                    mapped.addAll(pos.getAllPositionsReportsTo());
                }
            }

            List<PositionDto> positionDtos = new ArrayList<>();
            for (Position pos : mapped) {
                positionDtos.add(pos.getDTO());
            }*/
            positions = person.getPositionSet().stream()
                    .map(position -> {
                        if (getDirectly()) {
                            return Arrays.asList(position.getReportsTo());
                        } else {
                            return position.getAllPositionsReportsTo();
                        }
                    })
                    .flatMap(Collection::stream)
                    .filter(Objects::nonNull)
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
