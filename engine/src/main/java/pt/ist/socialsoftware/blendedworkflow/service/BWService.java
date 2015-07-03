package pt.ist.socialsoftware.blendedworkflow.service;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;

public abstract class BWService {

    @Atomic
    public final void execute() throws BWException {
        dispatch();
    }

    protected abstract void dispatch() throws BWException;

    protected BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
    }

}
