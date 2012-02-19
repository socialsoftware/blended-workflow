package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import jvstm.Atomic;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.GoalModelFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private String xml;
	private BlendedWorkflow blendedWorkflow;
	private BWInstance bwInstance;
	private GoalModelInstance goalModelInstance;
	private DataModelInstance dataModelInstance;

	public CreateGoalService (String xml) {
		this.xml = xml;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		inputDataConverter();

		new GoalModelFactory().parseXMLNewGoal(dataModelInstance, goalModelInstance, this.xml);
	}

	private void inputDataConverter () throws BlendedWorkflowException {
		this.blendedWorkflow = BlendedWorkflow.getInstance();
		Document doc = StringUtils.stringToDoc(this.xml);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();
		Element caseInfo = root.getChild("CaseInfo", bwNamespace);

		this.bwInstance = blendedWorkflow.getBWInstance(caseInfo.getChildText("instanceID", bwNamespace));
		this.dataModelInstance = bwInstance.getDataModelInstance();
		this.goalModelInstance = bwInstance.getGoalModelInstance();
	}

}