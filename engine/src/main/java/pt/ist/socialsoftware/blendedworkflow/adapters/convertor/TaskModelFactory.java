package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class TaskModelFactory {

	public void parseXMLTaskModel(DataModel dataModel, ActivityModel taskModel, String specificationXML)
			throws BWException {
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
			// taskPreConditionString =
			// ConditionFactory.getRelationDependencies(dataModel,
			// taskPreConditionString);

			Set<DefPathCondition> taskPreCondition = new HashSet<DefPathCondition>();
			taskPreCondition
					.add((DefPathCondition) ConditionFactory.createCondition(dataModel, taskPreConditionString));
			String taskPostConditionString = taskXML.getChildText("PostCondition", bwNamespace);
			// taskPostConditionString =
			// ConditionFactory.getRelationDependencies(dataModel,
			// taskPostConditionString);
			Set<DefProductCondition> taskPostCondition = new HashSet<DefProductCondition>();
			taskPostCondition
					.add((DefProductCondition) ConditionFactory.createCondition(dataModel, taskPostConditionString));

			String flowType = taskXML.getChildText("FlowType", bwNamespace);
			String joinCode = taskXML.getChildText("JoinCode", bwNamespace);
			String splitCode = taskXML.getChildText("SplitCode", bwNamespace);

			String previousTask = "";
			if (flowType.equals("none")) {
				previousTask = taskXML.getChildText("PreviousTaskName", bwNamespace);
			} else if (flowType.equals("root")) {
				previousTask = "";
			} else {
				String previousTask1 = taskXML.getChildText("PreviousTaskName1", bwNamespace);
				String previousTask2 = taskXML.getChildText("PreviousTaskName2", bwNamespace);
				previousTask = previousTask1 + "," + previousTask2;
			}
			Activity newTask = new Activity(taskModel, taskName, taskDescription, taskPreCondition, taskPostCondition,
					previousTask, joinCode, splitCode);
			newTask.setUser(defaultUser);
			newTask.setRole(defaultRole);
		}

		// Add nextTasks
		for (Object task : tasks) {
			Element taskXML = (Element) task;
			int nextTaskCount = Integer.parseInt(taskXML.getChildText("NextTaskCount", bwNamespace));
			String currentTaskName = taskXML.getChildText("Name", bwNamespace);
			Activity currentTask = taskModel.getActivity(currentTaskName);
			for (int i = 0; i < nextTaskCount; i++) {
				String nextTaskNameXML = "NextTaskName" + (i + 1);
				String nextTaskName = taskXML.getChildText(nextTaskNameXML, bwNamespace);
				Activity nextTask = taskModel.getActivity(nextTaskName);
				currentTask.addNextActivity(nextTask);
			}
		}
	}

}
