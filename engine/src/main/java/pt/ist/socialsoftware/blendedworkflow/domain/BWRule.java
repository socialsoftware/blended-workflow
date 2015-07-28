package pt.ist.socialsoftware.blendedworkflow.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWRule extends BWRule_Base {
    private static Logger log = LoggerFactory.getLogger(BWRule.class);

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public BWRule(BWDataModel dataModel, String name, Condition condition) {
        setDataModel(dataModel);
        setName(name);
        setCondition(condition);
    }

    private void checkName(String name) {
        if ((name == null) || (name.equals("")))
            throw new BWException(BWErrorType.INVALID_RULE_NAME, name);

        checkUniqueName(name);
    }

    private void checkUniqueName(String name) throws BWException {
        boolean exists = getDataModel().getRuleSet().stream()
                .anyMatch(rule -> (rule.getName() != null)
                        && (rule.getName().equals(name)));
        if (exists)
            throw new BWException(BWErrorType.INVALID_RULE_NAME, name);
    }

    public void delete() {
        setDataModel(null);
        getCondition().delete();

        deleteDomainObject();
    }

}