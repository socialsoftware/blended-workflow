package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Person;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Resource;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.WriteDataModelService;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWError;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;

public class WriteResourceModelService {
private Logger logger = LoggerFactory.getLogger(WriteDataModelService.class);
	
	private ResourceModelInterface repository = null;
	
	public WriteResourceModelService(ResourceModelInterface repository) {
		this.repository = repository;
	}
	
	public void writeResourceModel(String specId, ResourceSpecification spec, BWNotification notification) {
		System.out.println("[WriteRM] Begin writing resource model");
		
		List<Resource> resources = spec.getResources();
		
		for (Resource resource : resources) {
			String resourceType = resource.eClass().getName();
			
			switch (resourceType) {
				case "Person":
					System.out.println("[WriteRM] Writing a person");
					Person p = (Person) resource;
					PersonDTO person;
					if (p.getBody() != null) {
						person = new PersonDTO(specId, p.getName(), p.getBody().getText());
					} else {
						person = new PersonDTO(specId, p.getName());
					}
					repository.createPerson(person, notification);
					break;
				default:
					notification.addError(new BWError("Invalid resource type", resourceType + " is not a valid resource type"));
					
			}
		}
		
		System.out.println("[WriteRM] Finish writing resource model");
	}
}
