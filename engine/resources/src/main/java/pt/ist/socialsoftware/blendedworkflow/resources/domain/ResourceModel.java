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
    }

	public void delete() {
		clean();
		setSpec(null);
		deleteDomainObject();
	}

	public Capability addCapability(String name, String description) {
		Capability capability = new Capability(this, name, description);

		addCapability(capability);

		return capability;
	}
}
