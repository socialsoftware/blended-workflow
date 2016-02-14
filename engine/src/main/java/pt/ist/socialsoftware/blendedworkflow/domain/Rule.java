package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;

public class Rule extends Rule_Base {
	private static Logger logger = LoggerFactory.getLogger(Rule.class);

	@Override
	public void setName(String name) {
		checkName(name);
		super.setName(name);
	}

	@Override
	public void setCondition(Condition condition) {
		if (condition != null && !condition.getPathSet().stream().map(path -> path.split("\\.")[0])
				.allMatch(en -> en.equals(getEntity().getName())))
			throw new BWException(BWErrorType.INVALID_PATH, condition.toString());

		super.setCondition(condition);
	}

	public Rule(Entity entity, String name, Condition condition) {
		setEntity(entity);
		setName(name);
		setCondition(condition);
	}

	private void checkName(String name) {
		if ((name == null) || (name.equals("")))
			throw new BWException(BWErrorType.INVALID_RULE_NAME, name);

		checkUniqueName(name);
	}

	private void checkUniqueName(String name) throws BWException {
		boolean exists = getEntity().getRuleSet().stream()
				.anyMatch(rule -> (rule.getName() != null) && (rule.getName().equals(name)));
		if (exists)
			throw new BWException(BWErrorType.INVALID_RULE_NAME, name);
	}

	public void delete() {
		setEntity(null);
		setConditionModel(null);
		setInvariantConditionGoal(null);
		setTaskWithRule(null);
		getCondition().delete();

		deleteDomainObject();
	}

	public RuleDTO getDTO() {
		RuleDTO ruleDTO = new RuleDTO();
		ruleDTO.setSpecId(getEntity().getDataModel().getSpecification().getSpecId());
		ruleDTO.setEntityName(getEntity().getName());
		ruleDTO.setExtId(getExternalId());
		ruleDTO.setName(getName());
		ruleDTO.setExpression(getCondition().getDTO(getEntity().getDataModel().getSpecification().getSpecId()));

		return ruleDTO;
	}

	public Set<AttributeBasic> getAttributeBasicSet() {
		return getAttributeSet().stream().flatMap(a -> a.getAttributeBasicSet().stream()).collect(Collectors.toSet());
	}

	public Set<Attribute> getAttributeSet() {
		Set<String> paths = getPathSet();
		return paths.stream().map(p -> getEntity().getDataModel().getTargetOfPath(p))
				.filter(Attribute.class::isInstance).map(Attribute.class::cast).collect(Collectors.toSet());
	}

	public Set<String> getPathSet() {
		return getCondition().getPathSet();
	}

}
