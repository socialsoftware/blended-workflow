package org.blended.activity.repository;

import java.util.List;
import java.util.Set;

import org.blended.activity.activity.Activity;
import org.blended.activity.activity.ActivityFactory;
import org.blended.activity.activity.ActivityModel;
import org.blended.common.common.CommonFactory;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.dto.ActivityDTO;
import org.blended.common.repository.resttemplate.dto.DefAttributeConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefEntityConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefProductConditionSetDTO;
import org.blended.common.repository.resttemplate.dto.ExpressionDTO;
import org.blended.common.repository.resttemplate.dto.MulConditionDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadActivityModelService {
	private static Logger logger = LoggerFactory.getLogger(ReadActivityModelService.class);

	private static ReadActivityModelService instance = null;

	public static ReadActivityModelService getInstance() {
		logger.debug("getInstance");
		if (instance == null) {
			instance = new ReadActivityModelService();
		}
		return instance;
	}

	private CommonInterface ci = null;
	private CommonFactory factory = null;

	private ReadActivityModelService() {
		ci = CommonInterface.getInstance();
		factory = CommonFactory.eINSTANCE;
	}

	public void read(String specId, ActivityModel model) {
		Set<ActivityDTO> activities = ci.getActivitySet(specId);

		for (ActivityDTO activityDTO : activities) {
			Activity activity = createActivity(model, activityDTO);

			Set<ExpressionDTO> expressionsDTO = ci.getActivityPreConditionSet(specId, activityDTO.getName());

			for (ExpressionDTO expressionDTO : expressionsDTO) {
				activity.getPre().add(expressionDTO.buildExpression());
			}

			DefProductConditionSetDTO defProductConditionSetDTO = ci.getActivityPostConditionSet(specId,
					activityDTO.getName());
			for (DefEntityConditionDTO defEntityConditionDTO : defProductConditionSetDTO.getDefEnts()) {
				activity.getPost().add(defEntityConditionDTO.createEntityAchieveCondition(factory));
			}
			for (DefAttributeConditionDTO defAttributeConditionDTO : defProductConditionSetDTO.getDefAtts()) {
				activity.getPost().add(defAttributeConditionDTO.createAttributeAchieveCondition(factory));
			}

			List<MulConditionDTO> mulConditions = ci.getActivityMulConditions(specId, activityDTO.getName());
			for (MulConditionDTO mulConditionDTO : mulConditions) {
				activity.getPost().add(mulConditionDTO.createEntityInvariantCondition(factory));
			}

			List<RuleDTO> rules = ci.getActivityRuleConditions(specId, activityDTO.getName());
			for (RuleDTO rule : rules) {
				activity.getPost().add(rule.createAttributeInvariantCondition(factory));
				// logger.debug("RUL-CONDITIONS name:{}", rule.getName());
			}
		}

	}

	private Activity createActivity(ActivityModel model, ActivityDTO activityDTO) {
		ActivityFactory factory = ActivityFactory.eINSTANCE;
		Activity newActivity = factory.createActivity();
		newActivity.setName(activityDTO.getName());
		newActivity.setDescription(activityDTO.getDescription());
		model.getActivities().add(newActivity);

		return newActivity;
	}

}
