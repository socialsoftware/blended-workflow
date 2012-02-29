package pt.ist.socialsoftware.blendedworkflow.adapters.convertor;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;


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

		// TODO Conditions Model - Worklet
	}

}