package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.Role;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class GoalModelFactory {

	public void parseXMLGoalModel(DataModel dataModel, GoalModel goalModel, String specificationXML)
			throws BWException {
		User defaultUser = BlendedWorkflow.getInstance().getOrganizationalModel().getUser("BlendedWorkflow");
		Role defaultRole = BlendedWorkflow.getInstance().getOrganizationalModel().getRole("Admin");

		Document doc = StringUtils.stringToDoc(specificationXML);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();

		Element goalModelXML = root.getChild("GoalModel", bwNamespace);

		// Root Goal
		Element rootGoalXML = goalModelXML.getChild("RootGoal", bwNamespace);
		String rootGoalName = rootGoalXML.getChildText("Name", bwNamespace);
		String rootGoalDescription = rootGoalXML.getChildText("description", bwNamespace);

		String rootGoalConditionString = rootGoalXML.getChildText("SucessCondition", bwNamespace);
		Condition rootGoalCondition = ConditionFactory.createCondition(dataModel, rootGoalConditionString);

		String entityContextName = rootGoalXML.getChildText("Context", bwNamespace);
		Entity entityContext = dataModel.getEntity(entityContextName).get();

		Goal rootGoal = new Goal(goalModel, rootGoalName, rootGoalDescription, (DefProductCondition) rootGoalCondition,
				entityContext);
		rootGoal.setUser(defaultUser);
		rootGoal.setRole(defaultRole);

		// Activate Conditions
		int activateConditionCount = Integer.parseInt(rootGoalXML.getChildText("ActivateConditionCount", bwNamespace));
		for (int i = 0; i < activateConditionCount; i++) {
			String activateConditionXML = "ActivateCondition" + (i + 1);
			String activateConditionString = rootGoalXML.getChildText(activateConditionXML, bwNamespace);
			// Condition activateCondition = ConditionFactory
			// .createCondition(dataModel, activateConditionString);
			rootGoal.addActivationCondition(
					DefPathCondition.getDefPathCondition(dataModel.getSpecification(), activateConditionString));
		}

		List<?> goals = goalModelXML.getChildren("Goal", bwNamespace);
		for (Object goal : goals) {
			Element goalXML = (Element) goal;

			String goalName = goalXML.getChildText("Name", bwNamespace);
			String goalDescription = goalXML.getChildText("description", bwNamespace);

			String goalConditionString = goalXML.getChildText("SucessCondition", bwNamespace);
			Condition goalCondition = ConditionFactory.createCondition(dataModel, goalConditionString);

			entityContextName = goalXML.getChildText("Context", bwNamespace);
			entityContext = dataModel.getEntity(entityContextName).get();

			Goal parentGoal = goalModel.getGoal(goalXML.getChildText("ParentName", bwNamespace));
			Goal newGoal = new Goal(goalModel, parentGoal, goalName, goalDescription,
					(DefProductCondition) goalCondition, entityContext);
			newGoal.setUser(defaultUser);
			newGoal.setRole(defaultRole);

			// Activate Conditions
			activateConditionCount = Integer.parseInt(goalXML.getChildText("ActivateConditionCount", bwNamespace));
			for (int i = 0; i < activateConditionCount; i++) {
				String activateConditionXML = "ActivateCondition" + (i + 1);
				String activateConditionString = goalXML.getChildText(activateConditionXML, bwNamespace);
				// Condition activateCondition =
				// ConditionFactory.createCondition(dataModel,
				// activateConditionString);
				newGoal.addActivationCondition(
						DefPathCondition.getDefPathCondition(dataModel.getSpecification(), activateConditionString));
			}

		}

		// MaintainGoals
		List<?> maintainGoals = goalModelXML.getChildren("MaintainGoal", bwNamespace);
		for (Object goal : maintainGoals) {
			Element goalXML = (Element) goal;

			String goalName = goalXML.getChildText("Name", bwNamespace);
			String goalDescription = goalXML.getChildText("description", bwNamespace);

			String goalConditionString = goalXML.getChildText("MaintainCondition", bwNamespace);
			Condition goalCondition = ConditionFactory.createCondition(dataModel, goalConditionString);

			entityContextName = goalXML.getChildText("Context", bwNamespace);
			entityContext = dataModel.getEntity(entityContextName).get();

			new MaintainGoal(goalModel, goalName, goalDescription, goalCondition, entityContext);
		}
	}
}