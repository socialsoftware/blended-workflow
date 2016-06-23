package pt.ist.socialsoftware.blendedworkflow.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityWorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefinitionGroupDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefinitionGroupInstanceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityInstanceContextDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductInstanceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.WorkItemDTO;
import pt.ist.socialsoftware.blendedworkflow.service.execution.ExecutionInterface;

@RestController
@RequestMapping(value = "/specs/{specId}/instances/{instanceName}/activityworkitem")
public class ActivityWorkItemController {
	private static Logger logger = LoggerFactory.getLogger(ActivityWorkItemController.class);

	@RequestMapping(value = "/next", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ActivityWorkItemDTO[]> getNextActivityWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getNextActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		ActivityWorkItemDTO[] instances = edi.getPendingActivityWorkItemSet(specId, instanceName).stream()
				.toArray(size -> new ActivityWorkItemDTO[size]);

		return new ResponseEntity<ActivityWorkItemDTO[]>(instances, HttpStatus.OK);
	}

	@RequestMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ActivityWorkItemDTO[]> getLogActivityWorkItems(@PathVariable String specId,
			@PathVariable String instanceName) {
		logger.debug("getLogActivityWorkItems specId:{}, instanceName:{}", specId, instanceName);
		ExecutionInterface edi = ExecutionInterface.getInstance();

		ActivityWorkItemDTO[] instances = edi.getLogActivityWorkItemSet(specId, instanceName).stream()
				.map(awi -> awi.getDTO()).toArray(size -> new ActivityWorkItemDTO[size]);

		return new ResponseEntity<ActivityWorkItemDTO[]>(instances, HttpStatus.OK);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Boolean> executeActivityWorkItem(@PathVariable String specId,
			@RequestBody ActivityWorkItemDTO activityWorkItemDTO) {
		logger.debug("executeActivityActivityWorkItem specId:{}, instanceName:{}, activityName:{}", specId,
				activityWorkItemDTO.getWorkflowInstanceName(), activityWorkItemDTO.getActivityName());
		logger.debug("executeActivityWorkItem activityWorkItemDTO:{}", printWorkItemDTO(activityWorkItemDTO));
		ExecutionInterface edi = ExecutionInterface.getInstance();

		edi.executeActivityWorkItem(activityWorkItemDTO);

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	private String printWorkItemDTO(WorkItemDTO workItemDTO) {
		String result = "\r\n";
		for (DefinitionGroupDTO definitionGroupDTO : workItemDTO.getDefinitionGroupSet()) {
			result = result + "DEF ENTITY: " + definitionGroupDTO.getDefProductConditionSet().getDefEnts().stream()
					.map(d -> d.getPath()).collect(Collectors.joining(",")) + "\r\n";
			result = result + "DEF ATTRIBUTE: " + definitionGroupDTO.getDefProductConditionSet().getDefAtts().stream()
					.map(d -> d.getPath()).collect(Collectors.joining(",")) + "\r\n";
			result = result + "ENTITY CONTEXT: "
					+ definitionGroupDTO.getEntityContextSet().stream()
							.map(ec -> ec.getEntity().getName() + ", " + ec.getMulCondition().getRolePath()
									+ " ENTITY INSTANCE CONTEXT: "
									+ printEntityInstanceContext(ec.getEntityInstanceContextSet()))
							.collect(Collectors.joining(";"))
					+ "\r\n";

			result = result + "INNER RELATION: " + definitionGroupDTO.getInnerRelationSet().stream()
					.map(ir -> " Source " + ir.getSourceEntity().getName() + ", Target "
							+ ir.getTargetEntity().getName() + ", Rolename " + ir.getMulCondition().getRolePath()
							+ ", Product " + printProductInstance(ir.getProductInstanceSet()))
					.collect(Collectors.joining(",")) + "\r\n";

			result = result + "ENTITY INSTANCE CONTEXT: "
					+ definitionGroupDTO.getDefinitionGroupInstanceSet().stream()
							.map(dgi -> printEntityInstanceContext(dgi.getEntityInstanceContextSet()))
							.collect(Collectors.joining(","))
					+ "\r\n";

			result = result + "PRODUCT INSTANCE: "
					+ definitionGroupDTO.getDefinitionGroupInstanceSet().stream()
							.map(dgi -> printProductInstance(dgi.getProductInstanceSet()))
							.collect(Collectors.joining(";"))
					+ "\r\n";

			result = result + "INNER RELATION INSTANCE: "
					+ definitionGroupDTO.getDefinitionGroupInstanceSet().stream()
							.map(dgi -> printInnerRelationInstance(dgi)).collect(Collectors.joining(";"))
					+ "\r\n" + "\r\n";
		}
		return result;
	}

	private String printInnerRelationInstance(DefinitionGroupInstanceDTO dgi) {
		return dgi
				.getInnerRelationInstanceSet().stream().map(iri -> iri.getMulConditionDTO().getRolePath()
						+ ", Products " + printProductInstance(iri.getProductInstanceSet()))
				.collect(Collectors.joining(";"));
	}

	private String printEntityInstanceContext(Set<EntityInstanceContextDTO> eicSet) {
		return eicSet.stream().map(eic -> eic.getPathValues()).collect(Collectors.joining(";"));
	}

	private String printProductInstance(Set<ProductInstanceDTO> piSet) {
		return piSet.stream().map(pi -> pi.getPath() + ":" + pi.getExternalId() + "," + pi.getValue())
				.collect(Collectors.joining(";"));
	}

}
