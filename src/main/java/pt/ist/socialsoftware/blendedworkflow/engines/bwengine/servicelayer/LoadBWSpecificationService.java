package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.parser.BWSpecificationFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class LoadBWSpecificationService {

	private String xml;

	public LoadBWSpecificationService(String xml) {
		this.xml = xml;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BWSpecificationFactory.createBWSpecification(this.xml);
	}

}