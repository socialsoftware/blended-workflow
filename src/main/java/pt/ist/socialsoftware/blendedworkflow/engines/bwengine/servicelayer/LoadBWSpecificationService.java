package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.BWSpecificationFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class LoadBWSpecificationService {

	private String bwXML;
	private String yawlXML;

	public LoadBWSpecificationService(String bwXML, String yawlXML) {
		this.bwXML = bwXML;
		this.yawlXML = yawlXML;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BWSpecificationFactory.createBWSpecification(this.bwXML, this.yawlXML);
	}
}