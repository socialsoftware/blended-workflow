package pt.ist.socialsoftware.blendedworkflow.resources.domain;

public abstract class RALPersonExpression extends RALPersonExpression_Base {
    
    public RALPersonExpression() {
        super();
    }

    @Override
    public void delete() {
        getRalExprReportedByPersonPositionSet().forEach(expr ->
            removeRalExprReportedByPersonPosition(expr)
        );
        getRalExprReportsToPersonPositionSet().forEach(expr ->
            removeRalExprReportsToPersonPosition(expr)
        );
        getRalExprDelegatedByPersonPositionSet().forEach(expr ->
            removeRalExprDelegatedByPersonPosition(expr)
        );
        getRalExprDelegatesToPersonPositionSet().forEach(expr ->
            removeRalExprDelegatesToPersonPosition(expr)
        );
        super.delete();
    }
}
