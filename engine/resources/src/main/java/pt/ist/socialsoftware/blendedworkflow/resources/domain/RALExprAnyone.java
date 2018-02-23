package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RALExprAnyone extends RALExprAnyone_Base {
    private static Logger logger = LoggerFactory.getLogger(RALExprAnyone.class);

    public RALExprAnyone(ResourceModel resourceModel) {
        setResourceModel(resourceModel);
    }

    @Override
    public List<Person> getEligibleResources() {
        return getResourceModel().getPersonSet().stream().collect(Collectors.toList());
    }
}
