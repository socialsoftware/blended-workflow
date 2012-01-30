package pt.ist.socialsoftware.blendedworkflow.blendedworkflowengine.servicelayer;

import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.*;
import pt.ist.socialsoftware.blendedworkflow.exception.*;

public class RemoveBWSpecificationService {

	private String id;
	private String name;

	public RemoveBWSpecificationService(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public final void call() throws BWSpecificationException {
		boolean committed = false;

		try {
			Transaction.begin();
			BlendedWorkflow bw = BlendedWorkflow.getInstance();
			bw.removeBwSpecification (new BWSpecification (this.id, this.name));
			Transaction.commit();
			committed = true;
		} finally {
			if (!committed)
				Transaction.abort();
		}
	}
}
