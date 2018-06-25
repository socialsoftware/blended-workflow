package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RuleDTO;

public class Rule extends Rule_Base {
	private static Logger logger = LoggerFactory.getLogger(Rule.class);

	@Override
	public void setName(String name) {
		checkName(name);
		super.setName(name);
	}

	@Override
	public void setCondition(Condition condition) {
		if (condition != null) {
			condition.getPathSet().forEach(p -> checkPathPrefix(p));
		}

		super.setCondition(condition);
	}

	public Rule(Entity entity, String name, Condition condition) {
		entity.checkUniqueElementName(name);

		setEntity(entity);
		setName(name);
		setCondition(condition);
	}

	private void checkName(String name) {
		if ((name == null) || (name.equals(""))) {
			throw new BWException(BWErrorType.INVALID_RULE_NAME, name);
		}

		getEntity().checkUniqueElementName(name);
	}

	public void delete() {
		setEntity(null);
		setConditionModel(null);
		setInvariantConditionGoal(null);
		getActivityWithRuleSet().stream().forEach(t -> removeActivityWithRule(t));
		if (getCondition() != null) {
			getCondition().delete();
		}

		deleteDomainObject();
	}

	public RuleDTO getDTO() {
		RuleDTO ruleDTO = new RuleDTO();
		ruleDTO.setSpecId(getEntity().getDataModel().getSpecification().getSpecId());
		ruleDTO.setEntityName(getEntity().getName());
		ruleDTO.setExtId(getExternalId());
		ruleDTO.setName(getName());
		ruleDTO.setExpression(
				getCondition().getExpressionDTO(getEntity().getDataModel().getSpecification().getSpecId()));
		ruleDTO.setToStringExpression(ruleDTO.getExpression().toString());

		return ruleDTO;
	}

	public Set<Attribute> getAttributeSet() {
		Set<Path> paths = getCondition().getPathSet();
		return paths.stream().map(p -> p.getTarget()).map(Attribute.class::cast).collect(Collectors.toSet());
	}

	public Set<Path> getPathSet() {
		return getCondition().getPathSet();
	}

	public boolean checkPaths() {
		if (getCondition() != null) {
			getCondition().getPathSet().stream().forEach(p -> checkPath(p));
		}

		return true;
	}

	private boolean checkPath(Path path) {
		checkPathPrefix(path);
		return path.check();
	}

	private void checkPathPrefix(Path path) {
		List<String> elements = Arrays.stream(path.getValue().split("\\.")).collect(Collectors.toList());
		if (elements.size() == 1) {
			throw new BWException(BWErrorType.INVALID_PATH, path.getValue());
		}

		if (!elements.get(0).equals(getEntity().getName())) {
			throw new BWException(BWErrorType.INVALID_PATH,
					path.getValue() + " requires to have the Entity name as prefix: " + getEntity().getName());
		}
	}

}
