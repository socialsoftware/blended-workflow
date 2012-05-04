package pt.ist.socialsoftware.blendedworkflow.bwmanager;

import org.apache.log4j.Logger;

import com.vaadin.ui.Window.Notification;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
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

}
