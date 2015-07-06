package pt.ist.socialsoftware.blendedworkflow.service.design;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.service.BWService;

public class CreateAttributeService extends BWService {
    private final String specName;
    private final String entityName;
    private final String attributeName;
    private final AttributeType type;

    public CreateAttributeService(String specName, String entityName,
            String attributeName, AttributeType type) {
        this.specName = specName;
        this.entityName = entityName;
        this.attributeName = attributeName;
        this.type = type;
    }

    @Override
    protected void dispatch() throws BWException {
        Specification spec = getBlendedWorkflow()
                .getSpecification(this.specName)
                .orElseThrow(() -> new BWException(
                        BlendedWorkflowError.INVALID_SPECIFICATION_NAME));

        Entity ent = spec.getDataModel().getEntity(this.entityName)
                .orElseThrow(() -> new BWException(
                        BlendedWorkflowError.INVALID_ENTITY_NAME));

        ent.createAttribute(attributeName, type);
    }

}
