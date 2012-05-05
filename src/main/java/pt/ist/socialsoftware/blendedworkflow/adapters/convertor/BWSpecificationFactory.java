package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.SpecUtils;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class BWSpecificationFactory {

	// FIXME: Remove YAWL argument
	public static void createBWSpecification(String bwXML, String yawlXML2) throws BlendedWorkflowException {
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
		
		BWSpecification bwSpecification = new BWSpecification(name, author, description, version, identifier);

		// Data Model
		DataModel dataModel = bwSpecification.getDataModel();
		new DataModelFactory().parseXMLDataModel(dataModel, bwXML);

		// Goal Model
		GoalModel goalModel = bwSpecification.getGoalModel();
		new GoalModelFactory().parseXMLGoalModel(dataModel, goalModel, bwXML);

		// Task Model
		TaskModel taskModel = bwSpecification.getTaskModel();
		new TaskModelFactory().parseXMLTaskModel(dataModel, taskModel, bwXML);
		
		// Create YAWL Specification
		String yawlXML = new YAWLSpecificationFactory().parseYAWLSpecificationFactory(bwSpecification);
		
		// Load YAWL Specification on the engine
		BlendedWorkflow.getInstance().getYawlAdapter().loadSpecification(yawlXML);
		
		String yawlSpecficationID = SpecUtils.getYAWLSpecificationIDFromSpec(yawlXML).getIdentifier();
		bwSpecification.setYawlSpecficationID(yawlSpecficationID);

		// Create Worklet Rules
//		BlendedWorkflow.getInstance().getWorkletAdapter(); // Test proposes only.
		BlendedWorkflow.getInstance().getWorkletAdapter().loadRdrSet(bwSpecification); //FIXME: Disabled to run tests.
		
		// Notify BWManager
		BlendedWorkflow.getInstance().getBwManager().notifyLoadedBWSpecification(bwSpecification);
	}

}