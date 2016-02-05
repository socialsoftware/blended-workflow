package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jvstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.ConditionFactory;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateNewGoalService implements Callable<String> {

	private static Logger log = LoggerFactory.getLogger("CreateGoalService");
	private final BWInstance bwInstance;
	private final Goal parentGoal;
	private final String name;
	private final String description;
	private final String condition;
	private final ArrayList<String> activateConditions;
	private final Entity entityContext;
	private final String userID;

	public CreateNewGoalService(String bwInstanceOID, String name, String description, String parentGoalOID,
			String condition, ArrayList<String> activateConditions, String entityOID, String userID) {
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
			DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
			GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

			// Create Condition
			DefProductCondition goalCondition = (DefProductCondition) ConditionFactory
					.createCondition(dataModelInstance, condition);
			log.info("goalCondition:" + goalCondition);

			// Create Goal
			Goal newGoal = new Goal(goalModelInstance, parentGoal, name, description, goalCondition, entityContext);
			User defaultUser = BlendedWorkflow.getInstance().getOrganizationalModel().getUser("BlendedWorkflow");
			Role defaultRole = BlendedWorkflow.getInstance().getOrganizationalModel().getRole("Admin");
			newGoal.setUser(defaultUser);
			newGoal.setRole(defaultRole);

			// Add activate conditions
			for (String activateConditionString : this.activateConditions) {
				// DefProductCondition activateCondition = (DefProductCondition)
				// ConditionFactory
				// .createCondition(dataModelInstance,
				// activateConditionString);
				newGoal.addActivationCondition(
						DefPathCondition.getDefPathCondition(bwInstance.getSpecification(), activateConditionString));
			}

			BlendedWorkflow.getInstance().getWorkListManager().notifyNewGoalCreated(newGoal);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String date = dateFormat.format(Calendar.getInstance().getTime());
			bwInstance.getLog().addLogRecords(new LogRecord(date, "Goal Created", "[GOAL] " + name, userID));

		} catch (BWException bwe) {
			log.error(bwe.getError().name());
			BlendedWorkflow.getInstance().getWorkListManager().notifyException(bwe.getError());
		}
		Transaction.commit();
		log.info("END");
		return "CreateGoalService:Sucess";
	}
}