package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;


public class BWSpecificationFactory {

	public static boolean isBWSpecificationValid(String rawSpecification) {
		//TODO blended workflow specification checker
		return true;
	}

	public static void createBWSpecification(String specificationXML) throws BlendedWorkflowException {
		// BWSpecification Name
		Document doc = StringUtils.stringToDoc(specificationXML);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();
		Element specificationName = root.getChild("CaseInfo", bwNamespace);

		BWSpecification bwSpecification = new BWSpecification(specificationName.getChildText("specificationName", bwNamespace));

		// Data Model
		DataModel dataModel = bwSpecification.getDataModel();
		new DataModelFactory().parseXMLDataModel(dataModel, specificationXML);

		// Goal Model
		GoalModel goalModel = bwSpecification.getGoalModel();
		new GoalModelFactory().parseXMLGoalModel(dataModel, goalModel, specificationXML);

		// Task Model
		TaskModel taskModel = bwSpecification.getTaskModel();
		new TaskModelFactory().parseXMLTaskModel(dataModel, taskModel, specificationXML);
		
		BlendedWorkflow.getInstance().getYawlAdapter().loadSpecification("test");

		// Create YAWL Specification and Load on the engine. FIXME: Create/Parse YAWL specification from taskModel.
//		try {
//		String yawlSpecificationFileName = specificationName.getChildText("yawlSpecificationFILENAME", bwNamespace);
//		String yawlSpecification = StringUtils.fileToString(yawlSpecificationFileName);
//		String yawlSpecficationID = SpecUtils.getYAWLSpecificationIDFromSpec(yawlSpecification).getIdentifier();
//		taskModel.setYawlSpecficationID(yawlSpecficationID);
//		}
//		catch (BlendedWorkflowException bwe) {
//			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
//		}
		
		// Create Worklet Rules
//		WorkletAdapter.getInstance().loadRDRTrees();
	}

}