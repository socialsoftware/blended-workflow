package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.ConditionFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateNewGoalService implements Callable<String> {

	private static Logger log = Logger.getLogger("CreateGoalService");
	private BWInstance bwInstance;
	private AchieveGoal parentGoal;
	private String name;
	private String description;
	private String condition;
	private String userID;

	public CreateNewGoalService (long bwInstanceOID, String name, String description, long parentGoalOID, String condition,  String userID) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.parentGoal = AbstractDomainObject.fromOID(parentGoalOID);
		this.name = name;
		this.description = description;
		this.condition = condition;
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
			String goalConditionDependencies = ConditionFactory.getRelationDependencies(dataModelInstance, condition);
			Condition goalCondition = ConditionFactory.createCondition(dataModelInstance, goalConditionDependencies);
			
			// Create Goal
			AchieveGoal newGoal = new AchieveGoal(goalModelInstance, parentGoal, name, description, goalCondition);
			User defaultUser = BlendedWorkflow.getInstance().getOrganizationalModel().getUser("BlendedWorkflow");
			Role defaultRole = BlendedWorkflow.getInstance().getOrganizationalModel().getRole("Admin");
			newGoal.setUser(defaultUser);
			newGoal.setRole(defaultRole);
			
			// Add  the Goal to BWSpecification the WorkletService RdrSet
			BlendedWorkflow.getInstance().getWorkletAdapter().addGoal(bwInstance, newGoal);
			
			// Evaluate WorkItems State
//			newGoal.updateParentGoal();
//			goalModelInstance.getEnabledWorkItems();

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String date = dateFormat.format(Calendar.getInstance().getTime());
			bwInstance.getLog().addLogRecords(new LogRecord(date, "Goal Created", "[GOAL] " + name, userID));		
			
		} catch (BlendedWorkflowException bwe) {
			BlendedWorkflow.getInstance().getWorkListManager().notifyException(bwe.getError());
		}
		Transaction.commit();
		log.info("END");
		return "CreateGoalService:Sucess";
	}
}