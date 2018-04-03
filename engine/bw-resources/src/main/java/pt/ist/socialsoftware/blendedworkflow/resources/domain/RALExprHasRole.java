package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

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
    public List<Person> getEligibleResources() {
        return null;
    }

}
