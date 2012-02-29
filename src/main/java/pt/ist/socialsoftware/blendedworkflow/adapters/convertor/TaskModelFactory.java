package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class TaskModelFactory {
	
	public void parseXMLTaskModel(DataModel dataModel, TaskModel taskModel, String specificationXML) throws BlendedWorkflowException {
		Document doc = StringUtils.stringToDoc(specificationXML);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();

		Element taskModelXML = root.getChild("TaskModel", bwNamespace);

		List<?> tasks = taskModelXML.getChildren("Task", bwNamespace);
		for (Object task : tasks) {
			Element taskXML = (Element) task;

			String taskName = taskXML.getChildText("Name", bwNamespace);
			String taskPreConditionString = taskXML.getChildText("PreCondition", bwNamespace);
			Condition taskPreCondition = ConditionFactory.createCondition(dataModel, taskPreConditionString);
			String taskPostConditionString = taskXML.getChildText("PostCondition", bwNamespace);
			Condition taskPostCondition = ConditionFactory.createCondition(dataModel, taskPostConditionString);
			
			new Task(taskModel, taskName, taskPreCondition, taskPostCondition);
		}
	}

}
