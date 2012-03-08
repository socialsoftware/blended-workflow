package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import jvstm.Atomic;


import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class CreateBWInstanceService {

	private String xml;
	private BlendedWorkflow blendedWorkflow;
	private BWSpecification bwSpecification;

	public CreateBWInstanceService(String xml) {
		this.xml = xml;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		// Get BWSpecification and clone it
		inputDataConverter();
		BWInstance bwInstance = new BWInstance(this.bwSpecification);
		
		// Launch case on YAWL
		BWSpecification bwSpecification = bwInstance.getBwSpecification();
		String yawlSpecificationID = bwSpecification.getYawlSpecficationID();
		String yawlCaseID = BlendedWorkflow.getInstance().getYawlAdapter().launchCase(yawlSpecificationID);
		bwInstance.setYawlCaseID(yawlCaseID);
		
		// Get Enabled Workitems;
		bwInstance.getGoalModelInstance().getEnabledWorkItems();
	}

	private void inputDataConverter() throws BlendedWorkflowException {
		this.blendedWorkflow = BlendedWorkflow.getInstance();
		Document doc = StringUtils.stringToDoc(this.xml);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();
		Element caseInfo = root.getChild("CaseInfo", bwNamespace);

		this.bwSpecification = blendedWorkflow.getBWSpecification(caseInfo.getChildText("specificationName", bwNamespace));
	}
}