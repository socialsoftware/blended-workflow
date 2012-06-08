package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.BWSpecificationFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class LoadBWSpecificationService implements Callable<String> {

	private static Logger log = Logger.getLogger("LoadBWSpecificationService");
	private String bwXML;

	public LoadBWSpecificationService(String bwXML) {
		this.bwXML = bwXML;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		try {
		BWSpecificationFactory.createBWSpecification(this.bwXML);
		} catch (BlendedWorkflowException bwe) {
			BlendedWorkflow.getInstance().getBwManager().notifyException(bwe.getError());
		}
		Transaction.commit();
		log.info("END");
		return "LoadBWSpecificationService:Sucess";
	}
}