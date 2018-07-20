package pt.ist.socialsoftware.blendedworkflow.designer.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.DataModelInterface;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.WriteDataModelService;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.ResourceModelInterface;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.WriteResourceModelService;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWError;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.RepositoryException;

public class WriteBlendedWorkflowService {
	private static Logger logger = LoggerFactory.getLogger(WriteBlendedWorkflowService.class);

	private static WriteBlendedWorkflowService instance = null;

	public static WriteBlendedWorkflowService getInstance() {
		logger.debug("getInstance");
		if (instance == null) {
			instance = new WriteBlendedWorkflowService();
		}
		return instance;
	}

	// to be invoked by tests only
	public void deleteSpecification(String specId) {
		// logger.debug("deleteSpecification {}", specId);
		this.repository.deleteSpecification(specId);
	}

	private DataModelInterface repository = null;
	private ResourceModelInterface rmRepository = null;
	private WriteDataModelService dmService = null;
	private WriteResourceModelService rmService = null;

	private WriteBlendedWorkflowService() {
		this.repository = DataModelInterface.getInstance();
		this.rmRepository = ResourceModelInterface.getInstance();
		this.dmService = new WriteDataModelService(this.repository);
		this.rmService = new WriteResourceModelService(this.repository, this.rmRepository);
	}

	public BWNotification write(BWSpecification eBWSpecification) {
		String specId = eBWSpecification.getSpecification().getName().replaceAll("\\s+", "");
		// logger.debug("eBWSpecification: {}", specId);

		BWNotification notification = new BWNotification();

		try {
			this.repository.getSpecBySpecId(specId);
			if (eBWSpecification.getResourceSpecification() != null) {				
				this.rmRepository.cleanResourceModel(specId);
			}
			this.repository.cleanBlendedWorkflowModel(specId);
		} catch (RepositoryException re) {
			// logger.debug("getSpec: {}", re.getMessage());
			try {
				this.repository.createSpec(new SpecDTO(specId, eBWSpecification.getSpecification().getName()));
				if (eBWSpecification.getResourceSpecification() != null) {					
					this.rmRepository.createResourceModel(specId, notification);
				}
			} catch (RepositoryException ree) {
				notification.addError(ree.getError());
				// logger.debug("createSpec: {}", re.getMessage());
				return notification;
			}
		}

		this.dmService.writeDataModel(eBWSpecification.getDataSpecification(), specId, notification);
		
		if (eBWSpecification.getResourceSpecification() != null) {			
			this.rmService.writeResourceModel(eBWSpecification.getResourceSpecification(), specId,  notification);
		}

		for (BWError error : notification.getError()) {
			System.out.println(error.getMessage());
		}

		try {
			this.repository.checkBlendedWorkflowModel(specId);
		} catch (RepositoryException re) {
			notification.addError(re.getError());
		}

		this.repository.printSpecificationModels(specId);

		if (!notification.hasErrors()) {
			this.repository.generateConditionModel(specId);

			this.repository.generateGoalModel(specId);

			this.repository.generateActivityModel(specId);
			
			if (eBWSpecification.getResourceSpecification() != null) {
				this.rmRepository.generateEnrichedModels(specId);
			}
		}

		return notification;
	}
}
