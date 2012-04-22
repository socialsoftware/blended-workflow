package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import jvstm.Atomic;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.ConditionFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.LogRecord;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private static Logger log = Logger.getLogger("CreateGoalService");
	private BWInstance bwInstance;
	private Goal parentGoal;
	private String name;
	private String description;
	private String condition;

	public CreateGoalService (long bwInstanceOID, String name, String description, long parentGoalOID,  String condition) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.parentGoal = AbstractDomainObject.fromOID(parentGoalOID);
		this.name = name;
		this.description = description;
		this.condition = condition;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		log.info("New Goal Created with name " + this.name);
		
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

		Condition goalCondition = ConditionFactory.createCondition(dataModelInstance, condition);
		Goal newGoal = new Goal(goalModelInstance, parentGoal, name, description, goalCondition);

		newGoal.updateParentGoal();
		goalModelInstance.getEnabledWorkItems();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = dateFormat.format(Calendar.getInstance().getTime());
		
		bwInstance.getLog().addLogRecords(new LogRecord(date,"Goal Created", name, "Author"));
	}
}