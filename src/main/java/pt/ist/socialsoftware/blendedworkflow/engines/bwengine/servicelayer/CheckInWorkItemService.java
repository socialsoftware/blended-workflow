package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;

public class CheckInWorkItemService implements Callable<String> {

	private static Logger log = Logger.getLogger("XXXXXXXX");
	private WorkItem workItem;

	public CheckInWorkItemService (long workItemOID) {
		this.workItem = AbstractDomainObject.fromOID(workItemOID);
		
		//Print RelationInstances:
		for (Relation r : this.workItem.getBwInstance().getDataModelInstance().getRelations()) {
			log.info(r.getName() + ": " + r.getEntityOne().getName() + "|" + r.getEntityTwo().getName());
			for (RelationInstance ri : r.getRelationInstances()) {
				log.info(ri.getID() + ": " + ri.getEntityInstanceOne().getID() + "|" + ri.getEntityInstanceTwo().getID());
			}
		}
	}
	
	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		this.workItem.notifyCheckedIn();
		Transaction.commit();
		log.info("END");
		return "CheckInWorkItemService:Sucess";
	}

}