package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.EntityIsPerson;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceRules;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.ResourceRelationDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;

public class WriteResourceRulesService {
private Logger logger = LoggerFactory.getLogger(WriteResourceRulesService.class);
	
	private ResourceModelInterface repository = null;
	
	public WriteResourceRulesService(ResourceModelInterface repository) {
		this.repository = repository;
	}
	
	public void writeResourceRules(ResourceRules rules, String specId, BWNotification notification) {
		System.out.println("[WriteRR] Begin writing resource rules");
		
		writeRelations(rules.getRelations(), specId, notification);
		
		System.out.println("[WriteRR] Finish writing resource rules");
	}
	
	private void writeRelations(List<EntityIsPerson> relations, String specId, BWNotification notification) {
		relations.stream().forEach(r -> {
			repository.createEntityIsPersonRelation(new ResourceRelationDTO(specId, r.getEntity().getName()), notification);
		});
	}
}
