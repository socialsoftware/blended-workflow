package pt.ist.socialsoftware.blendedworkflow.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BWRule extends BWRule_Base {
    private static Logger log = LoggerFactory.getLogger(BWRule.class);

    public BWRule(BWDataModel dataModel, Condition condition) {
        setDataModel(dataModel);
        setCondition(condition);
    }

    public void delete() {
        setDataModel(null);
        getCondition().delete();

        deleteDomainObject();
    }

}
