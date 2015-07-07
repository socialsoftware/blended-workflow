package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.regex.Pattern;

import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Relation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.service.BWService;

public class CreateRelationService extends BWService {
    private final String specName;
    private final String entityOneName;
    private final String roleNameOne;
    private final Cardinality cardinalityOne;
    private final String entityTwoName;
    private final String roleNameTwo;
    private final Cardinality cardinalityTwo;

    public CreateRelationService(String specName, String entityOneName,
            String roleNameOne, String cardinalityOne, String entityTwoName,
            String roleNameTwo, String cardinalityTwo) {
        this.specName = specName;
        this.entityOneName = entityOneName;
        this.roleNameOne = roleNameOne;
        this.cardinalityOne = parseCardinality(cardinalityOne);
        this.entityTwoName = entityTwoName;
        this.roleNameTwo = roleNameTwo;
        this.cardinalityTwo = parseCardinality(cardinalityTwo);
    }

    @Override
    protected void dispatch() throws BWException {
        Specification spec = getBlendedWorkflow()
                .getSpecification(this.specName)
                .orElseThrow(() -> new BWException(
                        BlendedWorkflowError.INVALID_SPECIFICATION_NAME));

        Entity entityOne = spec.getDataModel().getEntity(this.entityOneName)
                .orElseThrow(() -> new BWException(
                        BlendedWorkflowError.INVALID_ENTITY_NAME));

        Entity entityTwo = spec.getDataModel().getEntity(this.entityTwoName)
                .orElseThrow(() -> new BWException(
                        BlendedWorkflowError.INVALID_ENTITY_NAME));

        entityOne.createRelation(roleNameOne, cardinalityOne, entityTwo,
                roleNameTwo, cardinalityTwo);
    }

    final static String ONE = "1";
    final static String MANY = "\\*";
    final static String ZERO_OR_ONE = "0..1";
    final static String CARDINALITY = "(" + ONE + "|" + ZERO_OR_ONE + "|" + MANY
            + ")";

    private Cardinality parseCardinality(String cardinality) {
        if (!Pattern.matches(CARDINALITY, cardinality))
            throw new BWException(BlendedWorkflowError.INVALID_CARDINALITY);

        Cardinality res;

        switch (cardinality) {
        case ONE:
            res = Cardinality.ONE;
            break;
        case ZERO_OR_ONE:
            res = Cardinality.ZERO_OR_ONE;
            break;
        case "*":
            res = Cardinality.MANY;
            break;
        default:
            res = null;
            assert(false);
        }

        return res;
    }

}
