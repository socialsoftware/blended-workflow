package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.apache.log4j.Logger;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.BWSpecificationFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class LoadBWSpecificationService {

	private static Logger log = Logger.getLogger("LoadBWSpecificationService");
	private String bwXML;
	private String yawlXML;

	public LoadBWSpecificationService(String bwXML, String yawlXML) {
		this.bwXML = bwXML;
		this.yawlXML = yawlXML;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		log.info("New BWSpecification loaded");
		
		BWSpecificationFactory.createBWSpecification(this.bwXML, this.yawlXML);
	}
}