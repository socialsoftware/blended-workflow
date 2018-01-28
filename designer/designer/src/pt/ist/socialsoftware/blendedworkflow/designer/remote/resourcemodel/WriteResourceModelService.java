package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Capability;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Person;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Role;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.WriteDataModelService;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.CapabilityDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RoleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;

public class WriteResourceModelService {
private Logger logger = LoggerFactory.getLogger(WriteDataModelService.class);
	
	private ResourceModelInterface repository = null;
	
	public WriteResourceModelService(ResourceModelInterface repository) {
		this.repository = repository;
	}
	
	public void writeResourceModel(ResourceSpecification spec, String specId, BWNotification notification) {
		System.out.println("[WriteRM] Begin writing resource model");
				
		createCapabilities(spec, specId, notification);
		
		createRoles(spec, specId, notification);
		
		createPersons(spec, specId, notification);
		
		System.out.println("[WriteRM] Finish writing resource model");
	}

	private void createRoles(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Role r : spec.getRoles()) {
			System.out.printf("[WriteRole] Name: %s; Description: %s\n", r.getName(), r.getDescription());
			//FIXME: repository.createRole(new RoleDTO(specId, r.getName(), r.getDescription()), notification);
		}
	}

	private void createCapabilities(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Capability c : spec.getCapabilities()) {
			System.out.printf("[WriteCapability] Name: %s; Description: %s\n", c.getName(), c.getDescription());
			//FIXME: repository.createCapability(new CapabilityDTO(specId, c.getName(), c.getDescription()), notification);
		}
	}

	private void createPersons(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Person p : spec.getPersons()) {
			PersonDTO personDTO;
			if (p.getBody() != null) {
				personDTO = new PersonDTO(specId, p.getName(), p.getBody().getText());
			} else {
				personDTO = new PersonDTO(specId, p.getName());
			}
			
			repository.createPerson(personDTO, notification);		
		}
	}
}
