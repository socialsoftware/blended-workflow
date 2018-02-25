package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import javafx.geometry.Pos;
import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResourceModel extends ResourceModel_Base {
	private static Logger logger = LoggerFactory.getLogger(ResourceModel.class);

	public ResourceModel() {
		super();
	}

    public void clean() {
		getPersonSet().stream().forEach(p -> p.delete());
		getPositionSet().stream().forEach(p -> p.delete());
		getCapabilitySet().stream().forEach(c -> c.delete());
		getRoleSet().stream().forEach(r -> r.delete());
		getUnitSet().stream().forEach(u -> u.delete());
    }

	public void delete() {
		clean();
		setSpec(null);
		deleteDomainObject();
	}

	public Capability addCapability(String name, String description) {
		Capability capability = new Capability(this, name, description);

		return capability;
	}

	public Role addRole(String name, String description) {
		Role role = new Role(this,name,description);

		return role;
	}

	public Unit addUnit(String name, String description) {
		Unit unit = new Unit(this,name,description);

		return unit;
	}

	public Position addPosition(String name, String description, String unitName) {
		Unit unit = getUnit(unitName);
		return new Position(this, name, description, unit);
	}

	public Position initPosition(String name, List<String> roleNames,
								 List<String> delegatesNames, String reportsNames) throws RMException {
		// Fetch position
		Position position = getPosition(name);

		// Fetch attributes from names
		List<Role> roles = getRolesFromStringList(roleNames);
		List<Position> delegates = getPositionsFromStringList(delegatesNames);
		Position reports = null;
		if (reportsNames != null) {
			reports = getPosition(reportsNames);
		}

		// Update position
		roles.stream().forEach(r -> position.addRole(r));
		delegates.stream().forEach(p -> position.addCanDelegateWorkTo(p));
		position.setReportsTo(reports);


		return position;
	}

	public Person addPerson(String name, String description, List<String> positionsNames, List<String> capabilitiesNames) {
		List<Position> positions = getPositionsFromStringList(positionsNames);
		List<Capability> capabilities = getCapabilitiesFromStringList(capabilitiesNames);

		return new Person(this, name, description, positions, capabilities);
	}

	public Unit getUnit(String unitName) throws RMException {
		Optional<Unit> unit = getUnitSet().stream().filter(u -> u.getName().equals(unitName))
				.findFirst();

		if (!unit.isPresent()) {
			throw new RMException(RMErrorType.INVALID_UNIT_NAME, "Unit with name '" + unitName + "' does not exist");
		}

		return unit.get();
	}

	public Person getPerson(String personName) throws RMException {
		Optional<Person> person = getPersonSet().stream().filter(p -> p.getName().equals(personName))
				.findFirst();

		if (!person.isPresent()) {
			throw new RMException(RMErrorType.INVALID_PERSON_NAME, "Person with name '" + personName + "' does not exist");
		}

		return person.get();
	}

	public Position getPosition(String positionName) throws RMException {
		Optional<Position> position = getPositionSet().stream().filter(p -> p.getName().equals(positionName))
				.findFirst();

		if (!position.isPresent()) {
			throw new RMException(RMErrorType.INVALID_POSITION_NAME, "Position with name '" + positionName + "' does not exist");
		}

		return position.get();
	}

	private List<Position> getPositionsFromStringList(List<String> delegatesNames) throws RMException {
		return delegatesNames.stream().map(d -> getPosition(d)).collect(Collectors.toList());
	}

	public Capability getCapability(String capabilityName) throws RMException {
		Optional<Capability> capability = getCapabilitySet().stream().filter(c -> c.getName().equals(capabilityName))
				.findFirst();

		if (!capability.isPresent()) {
			throw new RMException(RMErrorType.INVALID_CAPABILITY_NAME, "Capability with name '" + capabilityName + "' does not exist");
		}

		return capability.get();
	}

	private List<Capability> getCapabilitiesFromStringList(List<String> capabilitiesNames) throws RMException {
		return capabilitiesNames.stream().map(c -> getCapability(c)).collect(Collectors.toList());
	}

	public Role getRole(String roleName) {
		Optional<Role> role = getRoleSet().stream().filter(r -> r.getName().equals(roleName))
				.findFirst();

		if (!role.isPresent()) {
			throw new RMException(RMErrorType.INVALID_ROLE_NAME, "Role with name '" + roleName + "' does not exist");
		}

		return role.get();
	}

	private List<Role> getRolesFromStringList(List<String> roleNames) throws RMException {
		return roleNames.stream().map(d -> getRole(d)).collect(Collectors.toList());
	}

}
