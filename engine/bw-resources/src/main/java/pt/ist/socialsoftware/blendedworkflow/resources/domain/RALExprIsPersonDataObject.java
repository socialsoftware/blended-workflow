package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.List;

public class RALExprIsPersonDataObject extends pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprIsPersonDataObject_Base {
    
    public RALExprIsPersonDataObject(ResourceModel resourceModel, String path) throws RMException {
        setResourceModel(resourceModel);

        if (!resourceModel.checkEntityIsPerson(path)) {
            throw new RMException(RMErrorType.ENTITY_IN_EXPRESSION_IS_NOT_PERSON, "The entity in path" + path + " is not a person");
        }

        setPath(path);
    }

    @Override
    public void delete() {
        setPath(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return null;
    }

}
