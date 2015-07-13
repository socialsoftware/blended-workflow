package pt.ist.socialsoftware.blendedworkflow.service;

import pt.ist.fenixframework.Atomic;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;

public abstract class BWService {
    protected BWNotification notification;

    @Atomic
    public final BWNotification execute() throws BWException {
        notification = new BWNotification();
        return dispatch();
    }

    protected abstract BWNotification dispatch();

    protected BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
    }

}
