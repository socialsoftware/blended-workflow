package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Capability;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Person;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Position;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.ResourceSpecification;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Role;
import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.Unit;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.WriteDataModelService;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.CapabilityDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PersonDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.PositionDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.RoleDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto.UnitDTO;
import pt.ist.socialsoftware.blendedworkflow.designer.remote.utils.BWNotification;

public class WriteResourceModelService {
private Logger logger = LoggerFactory.getLogger(WriteDataModelService.class);
	
	private ResourceModelInterface repository = null;
	
	public WriteResourceModelService(ResourceModelInterface repository) {
		this.repository = repository;
	}
	
	public void writeResourceModel(ResourceSpecification spec, String specId, BWNotification notification) {
		System.out.println("[WriteRM] Begin writing resource model");
		
		
		System.out.println("[WriteRM] Begin writing capabilities");
		
		createCapabilities(spec, specId, notification);
		
		createRoles(spec, specId, notification);
		
		createUnits(spec, specId, notification);
		
		createPositions(spec, specId, notification);
		
		createPersons(spec, specId, notification);
		
		System.out.println("[WriteRM] Finish writing resource model");
	}
	
	private void createResourceModel(String specId, BWNotification notification) {
		repository.createResourceModel(specId, notification);
	}

	private void createCapabilities(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Capability c : spec.getCapabilities()) {
			System.out.printf("[WriteCapability] Name: %s; Description: %s\n", c.getName(), c.getDescription());
			repository.createCapability(new CapabilityDTO(specId, c.getName(), c.getDescription()), notification);
		}
	}

	private void createRoles(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Role r : spec.getRoles()) {
			System.out.printf("[WriteRole] Name: %s; Description: %s\n", r.getName(), r.getDescription());
			repository.createRole(new RoleDTO(specId, r.getName(), r.getDescription()), notification);
		}
	}
	
	private void createUnits(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Unit u : spec.getUnits()) {
			System.out.printf("[WriteUnit] Name: %s; Description: %s\n", u.getName(), u.getDescription());
			repository.createUnit(new UnitDTO(specId, u.getName(), u.getDescription()), notification);
		}
	}
	
	private void createPositions(ResourceSpecification spec, String specId, BWNotification notification) {
		// First pass: Create simple positions with names
		spec.getPositions().stream().forEach(p -> {
			PositionDTO position = new PositionDTO(specId, p.getName(), p.getUnit().getName(), p.getDescription());
			System.out.printf("[WritePosition] Name: %s; Description: %s\n", position.getName(), position.getDescription());
			repository.createPosition(position, notification);
		});
		
		// Second pass: Create associations between positions
		spec.getPositions().stream().forEach(p -> {
			PositionDTO position = new PositionDTO(specId, p.getName(), p.getUnit().getName(), p.getDescription());
			System.out.printf("[InitPosition] Name: %s; Description: %s\n", position.getName(), position.getDescription());
			if (p.getRoles() != null) {
				position.setRoles(p.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()));
			}
			
			if (p.getDelegateTo() != null) {
				position.setDelegateToRelations(p.getDelegateTo().stream().map(r -> r.getName()).collect(Collectors.toList()));
			}
			
			if (p.getReportsTo() != null) {
				position.setReportsTo(p.getReportsTo().getName());
			}
			
			repository.initPosition(position, notification);		
		});
	}
	
	private void createPersons(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Person p : spec.getPersons()) {
			PersonDTO personDTO = new PersonDTO(specId, p.getName(), p.getDescription());
			
			if (p.getCapabilities() != null) {
				personDTO.setCapabilities(p.getCapabilities().stream().map(c -> c.getName()).collect(Collectors.toList()));
			}
			
			if (p.getOccupies() != null) {
				personDTO.setPositions(p.getOccupies().stream().map(position -> position.getName()).collect(Collectors.toList()));
			}
			System.out.printf("[WritePerson] Name: %s; Description: %s\n", p.getName(), p.getDescription());
			repository.createPerson(personDTO, notification);		
		}
	}
}
