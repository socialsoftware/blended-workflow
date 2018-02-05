package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;

public class ResourceModel extends ResourceModel_Base {
	private static Logger logger = LoggerFactory.getLogger(ResourceModel.class);

	public ResourceModel() {
		super();
	}

    public void clean() {
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


}
