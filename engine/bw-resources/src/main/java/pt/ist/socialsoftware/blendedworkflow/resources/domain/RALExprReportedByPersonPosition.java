package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.ArrayList;
import java.util.List;

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
    public List<Person> getEligibleResources(List<WorkItem> history) {
        List<Position> positions = new ArrayList();
        getPersonExpr().getEligibleResources(history).stream()
                .forEach(person -> {
                    person.getPositionSet().stream().forEach(position -> {
                        if (getDirectly()) {
                            positions.add(position.getReportsTo());
                        } else {
                            positions.addAll(position.getAllPositionsReportsTo());
                        }
                    });
                });

        //FIXME: return getPersons
    }
    
}
