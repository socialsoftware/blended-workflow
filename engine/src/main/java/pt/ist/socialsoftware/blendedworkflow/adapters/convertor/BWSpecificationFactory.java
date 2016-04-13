package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.SpecUtils;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class BWSpecificationFactory {

	public static void createBWSpecification(String bwXML) throws BWException {
		// BWSpecification Name
		Document doc = StringUtils.stringToDoc(bwXML);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();
		Element caseInfo = root.getChild("CaseInfo", bwNamespace);

		String name = caseInfo.getChildText("name", bwNamespace);
		String author = caseInfo.getChildText("author", bwNamespace);
		String description = caseInfo.getChildText("description", bwNamespace);
		String version = caseInfo.getChildText("version", bwNamespace);
		String identifier = caseInfo.getChildText("identifier", bwNamespace);

		// TODO. We will need to generate unique specIds
		Specification bwSpecification = new Specification("specID", name, author, description, version, identifier);

		// Data Model
		DataModel dataModel = bwSpecification.getDataModel();
		new DataModelFactory().parseXMLDataModel(dataModel, bwXML);
		new DataModelInstanceFactory().parseXMLDataModel(dataModel, bwXML);

		// Task Model
		ActivityModel taskModel = bwSpecification.getActivityModel();
		new TaskModelFactory().parseXMLTaskModel(dataModel, taskModel, bwXML);

		// Goal Model
		GoalModel goalModel = bwSpecification.getGoalModel();
		new GoalModelFactory().parseXMLGoalModel(dataModel, goalModel, bwXML);

		// Create YAWL Specification
		String yawlXML = new YAWLSpecificationFactory().parseYAWLSpecificationFactory(bwSpecification);

		// Load YAWL Specification on the engine
		BlendedWorkflow.getInstance().getYawlAdapter().loadSpecification(yawlXML);

		String yawlSpecficationID = SpecUtils.getYAWLSpecificationIDFromSpec(yawlXML).getIdentifier();
		bwSpecification.setYawlSpecficationID(yawlSpecficationID);

		// Create Worklet Rules
		BlendedWorkflow.getInstance().getWorkletAdapter().loadRdrSet(bwSpecification);

		// Notify BWManager
		// BlendedWorkflow.getInstance().getBwManager()
		// .notifyLoadedBWSpecification(bwSpecification);
	}

}