package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

import jvstm.Atomic;

public class CheckInWorkItemService {

	private String xml;
	private BlendedWorkflow blendedWorkflow;
	private BWInstance bwInstance;
	private WorkItem workItem;
	private HashMap<String, String> values;

	public CheckInWorkItemService (String xml) {
		this.xml = xml;
		this.values = new HashMap<String, String>();
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		inputDataConverter();

		this.workItem.notifyWorkItemCheckedIn(this.values);
	}

	private void inputDataConverter() throws BlendedWorkflowException {
		this.blendedWorkflow = BlendedWorkflow.getInstance();
		Document doc = StringUtils.stringToDoc(this.xml);

		Element root = doc.getRootElement();
		Namespace bwNamespace = root.getNamespace();
		Element caseInfo = root.getChild("CaseInfo", bwNamespace);

		this.bwInstance = blendedWorkflow.getBWInstance(caseInfo.getChildText("instanceID", bwNamespace));
		this.workItem = this.bwInstance.getWorkItem(caseInfo.getChildText("workitemID", bwNamespace));

		// Put attributes in HashMap
		Element attributesInstancesXML = root.getChild("AttributeInstances", bwNamespace);

		List<?> attributesInstances = attributesInstancesXML.getChildren("AttributeInstance", bwNamespace);
		for (Object attributesInstance : attributesInstances) {
			Element attributesInstanceXML = (Element) attributesInstance;

			String attributeInstanceID = attributesInstanceXML.getChildText("ID", bwNamespace);
			String attributeInstanceValue = attributesInstanceXML.getChildText("value", bwNamespace);

			this.values.put(attributeInstanceID, attributeInstanceValue);
		}
	}

}