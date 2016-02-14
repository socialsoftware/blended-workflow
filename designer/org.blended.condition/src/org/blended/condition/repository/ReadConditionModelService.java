package org.blended.condition.repository;

import org.blended.common.common.AttributeDependenceCondition;
import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.CommonFactory;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityAchieveConditionExist;
import org.blended.common.common.EntityDependenceCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.MandatoryAttributeAchieveCondition;
import org.blended.common.common.NotMandatoryAttributeAchieveCondition;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.dto.DefAttributeConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefEntityConditionDTO;
import org.blended.common.repository.resttemplate.dto.DependenceDTO;
import org.blended.common.repository.resttemplate.dto.MulConditionDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.condition.condition.ConditionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadConditionModelService {
	private static Logger logger = LoggerFactory.getLogger(ReadConditionModelService.class);

	private static ReadConditionModelService instance = null;

	public static ReadConditionModelService getInstance() {
		logger.debug("getInstance");
		if (instance == null) {
			instance = new ReadConditionModelService();
		}
		return instance;
	}

	private CommonInterface ci = null;
	private CommonFactory factory = null;

	private ReadConditionModelService() {
		ci = CommonInterface.getInstance();
		factory = CommonFactory.eINSTANCE;
	}

	public void read(String specId, ConditionModel model) {

		ci.getEntityAchieveConditionSet(specId).stream().forEach(eac -> createEntityAchieveCondition(eac, model));

		ci.getEntityDependenceConditionSet(specId).stream().forEach(edc -> createEntityDependenceCondition(edc, model));

		ci.getEntityInvariantConditionSet(specId).stream().forEach(eic -> createEntityInvariantCondition(eic, model));

		ci.getAttributeAchieveConditionSet(specId).stream().forEach(aac -> createAttributeAchieveCondition(aac, model));

		ci.getAttributeDependenceConditionSet(specId).stream()
				.forEach(adc -> createAttributeDependenceCondition(adc, model));

		ci.getAttributeInvariantConditionSet(specId).stream()
				.forEach(aic -> createAttributeInvariantCondition(aic, model));
	}

	private void createEntityAchieveCondition(DefEntityConditionDTO eacDTO, ConditionModel model) {
		if (!eacDTO.isExists()) {
			EntityAchieveCondition eac = factory.createEntityAchieveCondition();
			eac.setName(eacDTO.getEntityName());
			model.getEntityAchieveConditions().add(eac);
		} else {
			EntityAchieveConditionExist eac = factory.createEntityAchieveConditionExist();
			eac.setName(eacDTO.getEntityName());
			model.getEntityAchieveConditions().add(eac);
		}
	}

	private void createEntityDependenceCondition(DependenceDTO edc, ConditionModel model) {
		EntityDependenceCondition dep = factory.createEntityDependenceCondition();
		dep.setEntity1(edc.getProduct());
		dep.setEntity2(edc.getPath());
		model.getEntityDependenceConditions().add(dep);
	}

	private void createEntityInvariantCondition(MulConditionDTO mulConditionDTO, ConditionModel model) {
		EntityInvariantCondition mul = factory.createEntityInvariantCondition();
		mul.setName(mulConditionDTO.getRolePath());
		mul.setCardinality(mulConditionDTO.getCardinality());
		model.getEntityInvariantConditions().add(mul);
	}

	private void createAttributeAchieveCondition(DefAttributeConditionDTO defAttributeConditionDTO,
			ConditionModel model) {
		if (defAttributeConditionDTO.isMandatory()) {
			MandatoryAttributeAchieveCondition maac = factory.createMandatoryAttributeAchieveCondition();
			maac.setAttribute(defAttributeConditionDTO.getPath());
			model.getAttributeAchieveConditions().add(maac);
		} else {
			NotMandatoryAttributeAchieveCondition nmaac = factory.createNotMandatoryAttributeAchieveCondition();
			nmaac.setAttribute(defAttributeConditionDTO.getPath());
			model.getAttributeAchieveConditions().add(nmaac);
		}
	}

	private void createAttributeDependenceCondition(DependenceDTO dependenceDTO, ConditionModel model) {
		AttributeDependenceCondition dep = factory.createAttributeDependenceCondition();
		dep.setAttribute1(dependenceDTO.getProduct());
		dep.setAttribute2(dependenceDTO.getPath());
		model.getAttributeDependenceConditions().add(dep);
	}

	private void createAttributeInvariantCondition(RuleDTO ruleDTO, ConditionModel model) {
		AttributeInvariantCondition inv = factory.createAttributeInvariantCondition();
		inv.setName(ruleDTO.getName());
		inv.setContext(ruleDTO.getEntityName());
		inv.setExpression(ruleDTO.getExpression().buildExpression());
		model.getAttributeInvariantConditions().add(inv);
	}

}
