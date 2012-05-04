package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class BWInstance extends BWInstance_Base {

	private Logger log = Logger.getLogger("BWInstance");
	
	/**
	 * Create a new BWInstance, and clones the Data, Task and Goal models from its parent BWSpecification
	 */
	public BWInstance(BWSpecification bwSpecification, String name, User user) throws BlendedWorkflowException {
		setBwSpecification(bwSpecification);
		setID(getBwSpecification().getName() + "." + getBwSpecification().getNewBWInstanceId()); //Id: BWSpecificationName.#
		setUser(user);
		
		setWorkItemCounter(0);
		setDataModelInstance(bwSpecification.getDataModel().cloneDataModel());

		GoalModelInstance goalModelInstance = new GoalModelInstance();
		setGoalModelInstance(goalModelInstance);
		bwSpecification.getGoalModel().cloneGoalModel(goalModelInstance);

		TaskModelInstance taskModelInstance = new TaskModelInstance();
		setTaskModelInstance(taskModelInstance);
		bwSpecification.getTaskModel().cloneTaskModel(taskModelInstance);

		setName(name);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		setCreationDate(dateFormat.format(Calendar.getInstance().getTime()));
		setLog(new Log());
		
		log.info("BWInstance: created BWInstance with id: " + getID());
	}

	public Integer getNewWorkItemID() {
		setWorkItemCounter(getWorkItemCounter()+1);
		return getWorkItemCounter();
	}

	public WorkItem getWorkItem(String id) throws BlendedWorkflowException {
		for (WorkItem workItem : getWorkItems())
			if (workItem.getID().equals(id))
				return workItem;
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_WORKITEM_ID, id);
	}

}