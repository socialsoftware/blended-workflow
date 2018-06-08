package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.EntityIsPerson;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceRule;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceRules;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.DataModelInterface;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprDelegatedByPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprDelegatesToPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprIsPersonDataObjectDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprIsPersonInTaskDutyDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprReportedByPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprReportsToPersonPositionExprDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprSharesPositionDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprSharesRoleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExprSharesUnitDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RALExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRelationDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRuleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRuleDTO.ResourceRuleTypeDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWError;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;

public class WriteResourceRulesService {
private Logger logger = LoggerFactory.getLogger(WriteResourceRulesService.class);
	
	private ResourceModelInterface repository = null;
	private DataModelInterface dmRepository = null;
	
	public WriteResourceRulesService(DataModelInterface dmRepository, ResourceModelInterface repository) {
		this.repository = repository;
		this.dmRepository = dmRepository;
	}
	
	public void writeResourceRules(ResourceRules rules, String specId, BWNotification notification) {
		System.out.println("[WriteRR] Begin writing resource rules");
		
		writeRelations(rules.getRelations(), specId, notification);
		
		writeRules(rules.getRules(), specId, notification);
		
		System.out.println("[WriteRR] Finish writing resource rules");
	}
	
	private void writeRules(List<ResourceRule> rules, String specId, BWNotification notification) {
		rules.stream().forEach(rule -> {
			RALExpressionDTO expr = RALExpressionDTO.buildRALExpressionDTO(specId, rule.getExpression());
			ResourceRuleTypeDTO type = ResourceRuleTypeDTO.fromString(rule.getTaskDuty());
			ResourceRuleDTO ruleDTO = new ResourceRuleDTO(specId, rule.getDatafield(), type, expr);
			boolean success = repository.createResourceRule(ruleDTO, notification);
			if (success) {
				addWorkflowDependece(ruleDTO, notification);
			}
		});
	}

	private void addWorkflowDependece(ResourceRuleDTO ruleDTO, BWNotification notification) {
		RALExpressionDTO expression = ruleDTO.getExpression();
		String specId = ruleDTO.getSpecId();
		String dataField = ruleDTO.getDataField();
		
		if (expression instanceof RALExprDelegatedByPersonPositionExprDTO) {
			writeDependece(((RALExprDelegatedByPersonPositionExprDTO) expression).getPersonExpr(), specId, dataField);	
		} else if (expression instanceof RALExprDelegatesToPersonPositionExprDTO) {
			writeDependece(((RALExprDelegatesToPersonPositionExprDTO) expression).getPersonExpr(), specId, dataField);	
		} else if (expression instanceof RALExprReportedByPersonPositionExprDTO) {
			writeDependece(((RALExprReportedByPersonPositionExprDTO) expression).getPersonExpr(), specId, dataField);	
		} else if (expression instanceof RALExprReportsToPersonPositionExprDTO) {
			writeDependece(((RALExprReportsToPersonPositionExprDTO) expression).getPersonExpr(), specId, dataField);	
		} else if (expression instanceof RALExprSharesPositionDTO) {
			writeDependece(((RALExprSharesPositionDTO) expression).getPersonExpr(), specId, dataField);	
		} else if (expression instanceof RALExprSharesRoleDTO) {
			writeDependece(((RALExprSharesRoleDTO) expression).getPersonExpr(), specId, dataField);	
		} else if (expression instanceof RALExprSharesUnitDTO) {
			writeDependece(((RALExprSharesUnitDTO) expression).getPersonExpr(), specId, dataField);	
		} else {
			writeDependece(expression, specId, dataField);			
		}
	}

	private void writeDependece(RALExpressionDTO expression, String specId, String dataField) {
		if (expression instanceof RALExprIsPersonInTaskDutyDTO) {
			this.dmRepository.createDependence(new DependenceDTO(specId, dataField,
					((RALExprIsPersonInTaskDutyDTO) expression).getDataField()
					));
		} else if (expression instanceof RALExprIsPersonDataObjectDTO) {
			this.dmRepository.createDependence(new DependenceDTO(specId, dataField,
					((RALExprIsPersonDataObjectDTO) expression).getDataField()
					));
		}
	}

	private void writeRelations(List<EntityIsPerson> relations, String specId, BWNotification notification) {
		relations.stream().forEach(r -> {
			repository.createEntityIsPersonRelation(new ResourceRelationDTO(specId, r.getEntity().getName()), notification);
		});
	}
}
