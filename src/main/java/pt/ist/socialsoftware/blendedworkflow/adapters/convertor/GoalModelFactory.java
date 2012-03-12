package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class GoalModelFactory {

	public void parseXMLGoalModel(DataModel dataModel, GoalModel goalModel, String specificationXML) throws BlendedWorkflowException {
		Document doc = StringUtils.stringToDoc(specificationXML);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();

		Element goalModelXML = root.getChild("GoalModel", bwNamespace);

		// Root Goal
		Element rootGoal = goalModelXML.getChild("RootGoal", bwNamespace);
		String rootGoalName = rootGoal.getChildText("Name", bwNamespace);
		String rootGoalConditionString = rootGoal.getChildText("Condition", bwNamespace);
		Condition rootGoalCondition = ConditionFactory.createCondition(dataModel, rootGoalConditionString);
		new Goal(goalModel, rootGoalName, rootGoalCondition);

		List<?> goals = goalModelXML.getChildren("Goal", bwNamespace);
		for (Object goal : goals) {
			Element goalXML = (Element) goal;

			String goalName = goalXML.getChildText("Name", bwNamespace);
			String goalConditionString = goalXML.getChildText("Condition", bwNamespace);
			Condition goalCondition = ConditionFactory.createCondition(dataModel, goalConditionString);
			Goal parentGoal = goalModel.getGoal(goalXML.getChildText("ParentName", bwNamespace)); 
			new Goal(goalModel, parentGoal, goalName, goalCondition);
		}
	}
}