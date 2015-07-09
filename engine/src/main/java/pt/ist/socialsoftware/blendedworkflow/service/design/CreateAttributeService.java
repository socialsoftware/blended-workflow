package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.regex.Pattern;

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
            String attributeName, String attributeType) {
        this.specName = specName;
        this.entityName = entityName;
        this.attributeName = attributeName;
        this.type = parseAttributeType(attributeType);
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

    final static String STRING = "String";
    final static String NUMBER = "Number";
    final static String BOOLEAN = "Boolean";
    final static String ATTRIBUTE_TYPE = "(" + STRING + "|" + NUMBER + "|"
            + BOOLEAN + ")";

    public static AttributeType parseAttributeType(String type) {
        if (!Pattern.matches(ATTRIBUTE_TYPE, type))
            throw new BWException(BlendedWorkflowError.INVALID_CARDINALITY);

        AttributeType res;

        switch (type) {
        case STRING:
            res = AttributeType.STRING;
            break;
        case NUMBER:
            res = AttributeType.NUMBER;
            break;
        case BOOLEAN:
            res = AttributeType.BOOLEAN;
            break;
        default:
            res = null;
            assert(false);
        }

        return res;
    }

}
