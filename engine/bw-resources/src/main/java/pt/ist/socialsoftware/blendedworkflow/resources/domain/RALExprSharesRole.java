package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

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
    public List<Person> getEligibleResources() {
        return null;
    }

}
