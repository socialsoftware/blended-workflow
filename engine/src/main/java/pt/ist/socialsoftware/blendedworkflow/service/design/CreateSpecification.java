package pt.ist.socialsoftware.blendedworkflow.service.design;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWService;

public class CreateSpecification extends BWService {
    private final String name;

    public CreateSpecification(String name) {
        this.name = name;
    }

    @Override
    protected void dispatch() throws BWException {
        getBlendedWorkflow().createSpecification(this.name);
    }

}
