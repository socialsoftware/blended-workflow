package pt.ist.socialsoftware.blendedworkflow.service.design;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWService;

public class CreateSpecificationService extends BWService {
    private final String name;

    public CreateSpecificationService(String name) {
        this.name = name;
    }

    @Override
    protected void dispatch() throws BWException {
        getBlendedWorkflow().createSpecification(this.name);
    }

}
