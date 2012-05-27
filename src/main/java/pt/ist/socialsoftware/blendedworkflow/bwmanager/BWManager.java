package pt.ist.socialsoftware.blendedworkflow.bwmanager;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.vaadin.ui.Window.Notification;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Relation;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.RelationInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;
import pt.ist.socialsoftware.blendedworkflow.shared.BWExecutorService;

public class BWManager {

	private Logger log = Logger.getLogger("BWManager");
	protected BWPresentation bwPresentation = null;

	public BWPresentation getBwPresentation() {
		return bwPresentation;
	}

	public void setBwPresentation(BWPresentation bwPresentation) {
		this.bwPresentation = bwPresentation;
	}

	/**
	 * Notify the BWPresentation of loaded BWSpecifications.
	 * @param bwSpecification The loaded BWSpecification.
	 */
	public void notifyLoadedBWSpecification(BWSpecification bwSpecification) {
		log.info("BWSpecification " + bwSpecification.getName() + " created.");
		getBwPresentation().addBWSpecification(bwSpecification.getOID(), bwSpecification.getName());
	}

	/**
	 * Notify the BWPresentation of created BWInstances.
	 * @param bwInstance The created BWInstance.
	 */
	public void notifyCreatedBWInstance(BWInstance bwInstance) {
		log.info("BWInstance " + bwInstance.getID() + " created.");
		getBwPresentation().addBWInstance(bwInstance.getOID(), bwInstance.getName());
	}

	/**
	 * Update the BWPresentation with all BWSpecifications and BWInstances created.
	 */
	public void updateBWPresentation() {
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance().getBwSpecifications()) {
			notifyLoadedBWSpecification(bwSpecification);
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				notifyCreatedBWInstance(bwInstance);
			}
		}
	}

	public void loadBWSpecification(String bwXML){
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		LoadBWSpecificationService service = new LoadBWSpecificationService(bwXML, "yawlXML");
		bwExecutorService.runTask(service);
	}

	public void createBWInstance(long bwSpecificationOID, String name, String userID){
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		CreateBWInstanceService service = new CreateBWInstanceService(bwSpecificationOID, name, userID);
		bwExecutorService.runTask(service);
	}

	public void notifyException(BlendedWorkflowError bwe) {
		getBwPresentation().getMainWindow().showNotification(bwe.toString(), Notification.TYPE_ERROR_MESSAGE);
	}

	//FIXME: relation order infinite loop, add only if do not exists
	public void addRelationInstance(long bwInstanceOID, long e1OID, long e2OID) {
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

		EntityInstance e1 = AbstractDomainObject.fromOID(e1OID);
		EntityInstance e2 = AbstractDomainObject.fromOID(e2OID);
		Entity entity1 = e1.getEntity();
		Entity entity2 = e2.getEntity();
		boolean exists = false;
		
		//Check if relation instance already exists
		for (Relation relation : dataModelInstance.getRelations()) {
			for (RelationInstance relationInstance : relation.getRelationInstances()) {
				EntityInstance one = relationInstance.getEntityInstanceOne();
				EntityInstance two = relationInstance.getEntityInstanceTwo();
				if (one.equals(e1) && two.equals(e2) || one.equals(e2) && two.equals(e1)) {
					exists = true;
					break;
				}
			}
		}
		
		//Do not exists create
		if (!exists) {
			for (Relation relation : dataModelInstance.getRelations()) {
				Entity one = relation.getEntityOne();
				Entity two = relation.getEntityTwo();
				if ((one.equals(entity1) && two.equals(entity2)) ) {
					new RelationInstance(relation, e1, e2, e1.getNewRelationInstanceID());
				} else if (one.equals(entity2) && two.equals(entity1)) {
					new RelationInstance(relation, e2, e1, e2.getNewRelationInstanceID());
				}
			}
		}
	}

	public void notifyNeededEntityInstances(EntityInstance entityContext, HashMap<Entity, Relation> neededEntityInstances) {
		getBwPresentation().generateAddSubGoalsContextWindow(entityContext, neededEntityInstances);
	}

}
