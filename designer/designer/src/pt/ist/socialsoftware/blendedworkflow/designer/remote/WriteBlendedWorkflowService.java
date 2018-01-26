package pt.ist.socialsoftware.blendedworkflow.designer.remote;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Association;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Attribute;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Constraint;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Entity;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Person;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Resource;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.DataModelInterface;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.WriteDataModelService;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ProductDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.SpecDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.ResourceModelInterface;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.WriteResourceModelService;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PersonDTO;
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
		this.rmService = new WriteResourceModelService(this.rmRepository);
	}

	public BWNotification write(BWSpecification eBWSpecification) {
		String specId = eBWSpecification.getSpecification().getName().replaceAll("\\s+", "");
		// logger.debug("eBWSpecification: {}", specId);

		BWNotification notification = new BWNotification();

		try {
			this.repository.getSpecBySpecId(specId);
			this.repository.cleanBlendedWorkflowModel(specId);
		} catch (RepositoryException re) {
			// logger.debug("getSpec: {}", re.getMessage());
			try {
				this.repository.createSpec(new SpecDTO(specId, eBWSpecification.getSpecification().getName()));
			} catch (RepositoryException ree) {
				notification.addError(ree.getError());
				// logger.debug("createSpec: {}", re.getMessage());
				return notification;
			}
		}

		this.dmService.writeDataModel(eBWSpecification, specId, notification);
		
		this.rmService.writeResourceModel(specId, eBWSpecification.getResourceSpecification(), notification);

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
		}

		return notification;
	}
}
