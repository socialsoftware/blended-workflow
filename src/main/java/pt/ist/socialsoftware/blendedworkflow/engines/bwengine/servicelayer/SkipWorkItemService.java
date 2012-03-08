package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import jvstm.Atomic;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

public class SkipWorkItemService {

	private String xml;
	private BlendedWorkflow blendedWorkflow;
	private BWInstance bwInstance;
	private WorkItem workItem;

	public SkipWorkItemService (String xml) {
		this.xml = xml;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		inputDataConverter();

		this.workItem.notifyWorkItemSkipped();
	}

	private void inputDataConverter() throws BlendedWorkflowException {
		this.blendedWorkflow = BlendedWorkflow.getInstance();
		Document doc = StringUtils.stringToDoc(this.xml);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();
		Element caseInfo = root.getChild("CaseInfo", bwNamespace);

		this.bwInstance = blendedWorkflow.getBWInstance(caseInfo.getChildText("instanceID", bwNamespace));
		this.workItem = this.bwInstance.getWorkItem(caseInfo.getChildText("workitemID", bwNamespace));
	}
}