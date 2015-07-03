package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Callable;

import jvstm.Transaction;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.ConditionFactory;
import pt.ist.socialsoftware.blendedworkflow.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateNewGoalService implements Callable<String> {

	private static Logger log = Logger.getLogger("CreateGoalService");
	private final BWInstance bwInstance;
	private final AchieveGoal parentGoal;
	private final String name;
	private final String description;
	private final String condition;
	private final ArrayList<String> activateConditions;
	private final Entity entityContext;
	private final String userID;

	public CreateNewGoalService(String bwInstanceOID, String name,
			String description, String parentGoalOID, String condition,
			ArrayList<String> activateConditions, String entityOID,
			String userID) {
		this.bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
		this.parentGoal = FenixFramework.getDomainObject(parentGoalOID);
		this.name = name;
		this.description = description;
		this.condition = condition;
		this.activateConditions = activateConditions;
		this.entityContext = FenixFramework.getDomainObject(entityOID);
		this.userID = userID;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		try {
			DataModelInstance dataModelInstance = bwInstance
					.getDataModelInstance();
			GoalModelInstance goalModelInstance = bwInstance
					.getGoalModelInstance();

			// Create Condition
			Condition goalCondition = ConditionFactory.createCondition(
					dataModelInstance, condition);
			log.info("goalCondition:" + goalCondition);

			// Create Goal
			AchieveGoal newGoal = new AchieveGoal(goalModelInstance,
					parentGoal, name, description, goalCondition, entityContext);
			User defaultUser = BlendedWorkflow.getInstance()
					.getOrganizationalModel().getUser("BlendedWorkflow");
			Role defaultRole = BlendedWorkflow.getInstance()
					.getOrganizationalModel().getRole("Admin");
			newGoal.setUser(defaultUser);
			newGoal.setRole(defaultRole);

			// Add activate conditions
			for (String activateConditionString : this.activateConditions) {
				Condition activateCondition = ConditionFactory.createCondition(
						dataModelInstance, activateConditionString);
				newGoal.addActivateConditions(activateCondition);
			}

			BlendedWorkflow.getInstance().getWorkListManager()
					.notifyNewGoalCreated(newGoal);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String date = dateFormat.format(Calendar.getInstance().getTime());
			bwInstance.getLog().addLogRecords(
					new LogRecord(date, "Goal Created", "[GOAL] " + name,
							userID));

		} catch (BWException bwe) {
			log.error(bwe.getError());
			BlendedWorkflow.getInstance().getWorkListManager()
					.notifyException(bwe.getError());
		}
		Transaction.commit();
		log.info("END");
		return "CreateGoalService:Sucess";
	}
}