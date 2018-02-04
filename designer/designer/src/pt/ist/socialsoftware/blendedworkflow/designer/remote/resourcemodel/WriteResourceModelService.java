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
				
		createCapabilities(spec, specId, notification);
		
		createRoles(spec, specId, notification);
		
		createUnits(spec, specId, notification);
		
		createPersons(spec, specId, notification);
		
		createPositions(spec, specId, notification);
		
		System.out.println("[WriteRM] Finish writing resource model");
	}
	
	private void createResourceModel(String specId, BWNotification notification) {
		repository.createResourceModel(specId, notification);
	}

	private void createUnits(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Unit u : spec.getUnits()) {
			System.out.printf("[WriteUnit] Name: %s; Description: %s\n", u.getName(), u.getDescription());
			//FIXME: repository.createUnit(new UnitDTO(specId, u.getName(), u.getDescription()), notification);
		}
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
			PersonDTO personDTO = new PersonDTO(specId, p.getName());
			
			if (p.getCapabilities() != null) {
				personDTO.setCapabilities(p.getCapabilities().stream().map(c -> c.getName()).collect(Collectors.toList()));
			}
			
			if (p.getOccupies() != null) {
				personDTO.setPositions(p.getOccupies().stream().map(position -> position.getName()).collect(Collectors.toList()));
			}
			
			repository.createPerson(personDTO, notification);		
		}
	}
	
	private void createPositions(ResourceSpecification spec, String specId, BWNotification notification) {
		for (Position p : spec.getPositions()) {
			PositionDTO position = new PositionDTO(specId, p.getName(), p.getUnit().getName());
			
			if (p.getRoles() != null) {
				position.setRoles(p.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()));
			}
			
			if (p.getDelegateTo() != null) {
				position.setDelegateToRelations(p.getDelegateTo().stream().map(r -> r.getName()).collect(Collectors.toList()));
			}
			
			if (p.getReportsTo() != null) {
				position.setReportsToRelations(p.getReportsTo().stream().map(r -> r.getName()).collect(Collectors.toList()));
			}
			
			repository.createPosition(position, notification);		
		}
	}
}
