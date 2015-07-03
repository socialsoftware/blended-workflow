package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.BWSpecificationFactory;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class LoadBWSpecificationService implements Callable<String> {

	private static Logger log = Logger.getLogger("LoadBWSpecificationService");
	private final String bwXML;

	public LoadBWSpecificationService(String bwXML) {
		this.bwXML = bwXML;
	}

	@Atomic(mode = TxMode.WRITE)
	@Override
	public String call() throws Exception {
		log.info("Start");
		try {
			BWSpecificationFactory.createBWSpecification(this.bwXML);
		} catch (BWException bwe) {
			BlendedWorkflow.getInstance().getBwManager()
					.notifyException(bwe.getError());
		}
		log.info("END");
		return "LoadBWSpecificationService:Sucess";
	}
}