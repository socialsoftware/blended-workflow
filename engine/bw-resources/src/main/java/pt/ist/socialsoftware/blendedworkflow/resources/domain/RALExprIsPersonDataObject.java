package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.ProductInstance;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RALExprIsPersonDataObject extends RALExprIsPersonDataObject_Base {
    private static Logger logger = LoggerFactory.getLogger(RALExprIsPersonDataObject.class);

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
        return history.getEntityInstanceSet().stream()
                .flatMap(entityInstance -> entityInstance.getProductInstancesByPath(getPath()).stream())
                .filter(productInstance -> productInstance instanceof EntityInstance && getResourceModel().checkEntityIsPerson(productInstance.getProduct()))
                .map(productInstance -> ((EntityInstance) productInstance).getPerson())
                .collect(toList());
    }

}
