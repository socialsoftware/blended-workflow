package pt.ist.socialsoftware.blendedworkflow.resources.domain;

public abstract class RALPersonExpression extends RALPersonExpression_Base {
    
    public RALPersonExpression() {
        super();
    }

    @Override
    public void delete() {
        getRalExprReportedByPersonPositionExprSet().forEach(expr -> removeRalExprReportedByPersonPositionExpr(expr));
        getRalExprReportsToPersonPositionExprSet().forEach(expr -> removeRalExprReportsToPersonPositionExpr(expr));
        super.delete();
    }
}
