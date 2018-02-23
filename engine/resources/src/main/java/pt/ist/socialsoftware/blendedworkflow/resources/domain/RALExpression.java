package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;

import java.util.List;

public abstract class RALExpression extends RALExpression_Base {
    private static Logger logger = LoggerFactory.getLogger(RALExpression.class);
    
    public RALExpression() {

    }

    public abstract List<Person> getEligibleResources();
}
