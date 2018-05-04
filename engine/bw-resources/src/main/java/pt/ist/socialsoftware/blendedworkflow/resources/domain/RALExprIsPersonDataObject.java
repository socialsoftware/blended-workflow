package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.List;

public class RALExprIsPersonDataObject extends RALExprIsPersonDataObject_Base {
    
    public RALExprIsPersonDataObject(ResourceModel resourceModel, Entity entity) throws RMException {
        setResourceModel(resourceModel);

        if (!resourceModel.checkEntityIsPerson(entity)) {
            throw new RMException(RMErrorType.ENTITY_IN_EXPRESSION_IS_NOT_PERSON, "The entity " + entity.getName() + " is not a person");
        }

        setDataField(entity);
    }

    @Override
    public void delete() {
        setDataField(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }

}
