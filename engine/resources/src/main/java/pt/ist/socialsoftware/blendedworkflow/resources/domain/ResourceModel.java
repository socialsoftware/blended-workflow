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

	public Position addPosition(String name, String unitName, List<String> roleNames,
								List<String> delegatesNames, String reportsNames) {
		Unit unit = getUnit(unitName);
		List<Role> roles = getRolesFromStringList(roleNames);
		List<Position> delegates = getPositionsFromStringList(delegatesNames);
		Position reports = null;
		if (reportsNames != null) {
			 reports = getPosition(reportsNames);
		}

		return new Position(this, name, unit, roles, delegates, reports);
	}


	private Unit getUnit(String unitName) throws RMException {
		Optional<Unit> unit = getUnitSet().stream().filter(u -> u.getName().equals(unitName))
				.findFirst();

		if (!unit.isPresent()) {
			throw new RMException(RMErrorType.INVALID_UNIT_NAME, "Unit with name '" + unitName + "' does not exist");
		}

		return unit.get();
	}

	private Position getPosition(String positionName) throws RMException {
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

	private Role getRole(String roleName) {
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
