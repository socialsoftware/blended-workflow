package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class TaskModelFactory {
	
	public void parseXMLTaskModel(DataModel dataModel, TaskModel taskModel, String specificationXML) throws BWException {
		User defaultUser = BlendedWorkflow.getInstance().getOrganizationalModel().getUser("BlendedWorkflow");
		Role defaultRole = BlendedWorkflow.getInstance().getOrganizationalModel().getRole("Admin");
		
		Document doc = StringUtils.stringToDoc(specificationXML);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();

		Element taskModelXML = root.getChild("TaskModel", bwNamespace);

		List<?> tasks = taskModelXML.getChildren("Task", bwNamespace);
		for (Object task : tasks) {
			Element taskXML = (Element) task;

			String taskName = taskXML.getChildText("Name", bwNamespace);
			String taskDescription = taskXML.getChildText("description", bwNamespace);
			String taskPreConditionString = taskXML.getChildText("PreCondition", bwNamespace);
//			taskPreConditionString = ConditionFactory.getRelationDependencies(dataModel, taskPreConditionString);
			
			Condition taskPreCondition = ConditionFactory.createCondition(dataModel, taskPreConditionString);
			String taskPostConditionString = taskXML.getChildText("PostCondition", bwNamespace);
//			taskPostConditionString = ConditionFactory.getRelationDependencies(dataModel, taskPostConditionString);
			Condition taskPostCondition = ConditionFactory.createCondition(dataModel, taskPostConditionString);
			
			String flowType = taskXML.getChildText("FlowType", bwNamespace);
			String joinCode = taskXML.getChildText("JoinCode", bwNamespace);
			String splitCode = taskXML.getChildText("SplitCode", bwNamespace);
			
			String previousTask ="";
			if (flowType.equals("none")) {
				previousTask = taskXML.getChildText("PreviousTaskName", bwNamespace);
			} else if (flowType.equals("root")) {
				previousTask ="";
			}
			else {
				String previousTask1 = taskXML.getChildText("PreviousTaskName1", bwNamespace);
				String previousTask2 = taskXML.getChildText("PreviousTaskName2", bwNamespace);
				previousTask = previousTask1 + "," + previousTask2;
			}
			Task newTask = new Task(taskModel, taskName, taskDescription, taskPreCondition, taskPostCondition, previousTask, joinCode, splitCode);
			newTask.setUser(defaultUser);
			newTask.setRole(defaultRole);
		}
		
		// Add nextTasks
		for (Object task : tasks) {
			Element taskXML = (Element) task;
			int nextTaskCount = Integer.parseInt(taskXML.getChildText("NextTaskCount", bwNamespace));
			String currentTaskName = taskXML.getChildText("Name", bwNamespace);
			Task currentTask = taskModel.getTask(currentTaskName);
			for (int i = 0; i < nextTaskCount ; i++) {
				String nextTaskNameXML = "NextTaskName" + (i+1);
				String nextTaskName = taskXML.getChildText(nextTaskNameXML, bwNamespace);
				Task nextTask = taskModel.getTask(nextTaskName);
				currentTask.addNextTasks(nextTask);
			}
		}
	}

}
