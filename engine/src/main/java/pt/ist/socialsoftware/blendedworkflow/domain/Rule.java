package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

public class Rule extends Rule_Base {
    private static Logger log = LoggerFactory.getLogger(Rule.class);

    @Override
    public void setName(String name) {
        checkName(name);
        super.setName(name);
    }

    public Rule(DataModel dataModel, String name, Condition condition) {
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
        setConditionModel(null);
        setInvariantConditionGoal(null);
        setTaskWithRule(null);
        getCondition().delete();

        deleteDomainObject();
    }

    public RuleDTO getDTO() {
        RuleDTO ruleDTO = new RuleDTO();
        ruleDTO.setSpecId(getDataModel().getSpecification().getSpecId());
        ruleDTO.setExtId(getExternalId());
        ruleDTO.setName(getName());
        ruleDTO.setExpression(getCondition()
                .getDTO(getDataModel().getSpecification().getSpecId()));

        return ruleDTO;
    }

    public Set<AttributeBasic> getAttributeBasicSet() {
        return getCondition().getAttributeBasicSet();
    }

    public Set<Attribute> getAttributeSet() {
        return getCondition().getAttributeBasicSet()
                .stream().map(a -> a.getAttributeGroup() != null
                        ? a.getAttributeGroup() : a)
                .collect(Collectors.toSet());
    }

}
