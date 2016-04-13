package pt.ist.socialsoftware.blendedworkflow.domain;

import java.text.DateFormat;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.BWExecutorService;

public class BlendedWorkflow extends BlendedWorkflow_Base {
	private static Logger log = LoggerFactory.getLogger(BlendedWorkflow.class);

	DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
	private YAWLAdapter yawlAdapter = null;
	private WorkletAdapter workletAdapter = null;
	private String today = dateFormatter.format(new java.util.Date());

	private BWExecutorService bwExecutorService = null;

	public static BlendedWorkflow getInstance() {
		if (FenixFramework.getDomainRoot().getBlendedWorkflow() == null) {
			new BlendedWorkflow();
			log.debug("BlendedWorkflow instance created");
		}

		return FenixFramework.getDomainRoot().getBlendedWorkflow();
	}

	public BlendedWorkflow() {
		FenixFramework.getDomainRoot().setBlendedWorkflow(this);
	}

	public Specification createSpecification(String specId, String name) throws BWException {
		String author = "Author";
		String description = "Description";
		String version = "Version";
		String UID = "UID";
		return new Specification(specId, name, author, description, version, UID);
	}

	public Set<Specification> getSpecByName(String name) {
		return getSpecificationSet().stream().filter(spec -> spec.getName().equals(name)).collect(Collectors.toSet());
	}

	public Optional<Specification> getSpecById(String specId) {
		return getSpecificationSet().stream().filter(spec -> spec.getSpecId().equals(specId)).findFirst();
	}

	public OldBWInstance getBWInstance(String ID) throws BWException {
		for (Specification specificationpecification : getSpecificationSet()) {
			for (OldBWInstance bwInstance : specificationpecification.getOldBwInstancesSet()) {
				if (bwInstance.getID().equals(ID))
					return bwInstance;
			}
		}
		throw new BWException(BWErrorType.NON_EXISTENT_CASE_ID, ID);
	}

	public OldBWInstance getBWInstanceFromYAWLCaseID(String yawlCaseID) throws BWException {
		for (Specification specification : getSpecificationSet()) {
			for (OldBWInstance bwInstance : specification.getOldBwInstancesSet()) {
				if (bwInstance.getYawlCaseID().equals(yawlCaseID))
					return bwInstance;
			}
		}
		throw new BWException(BWErrorType.NON_EXISTENT_CASE_ID, yawlCaseID);
	}

	public YAWLAdapter getYawlAdapter() throws BWException {
		if (yawlAdapter == null) {
			yawlAdapter = new YAWLAdapter();
		}
		return yawlAdapter;
	}

	public void setYawlAdapter(YAWLAdapter yawlAdapter) {
		this.yawlAdapter = yawlAdapter;
	}

	public WorkletAdapter getWorkletAdapter() {
		if (workletAdapter == null) {
			workletAdapter = new WorkletAdapter();
		}
		return workletAdapter;
	}

	public void setWorkletAdapter(WorkletAdapter workletAdapter) {
		this.workletAdapter = workletAdapter;
	}

	public BWExecutorService getBWExecutorService() {
		if (bwExecutorService == null) {
			bwExecutorService = new BWExecutorService();
		}
		return bwExecutorService;
	}

	public void setBWExecutorService(BWExecutorService bwExecutorService) {
		this.bwExecutorService = bwExecutorService;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	// TODO: a in depth deletion of objects
	@Atomic(mode = TxMode.WRITE)
	public void delete() {
		getSpecificationSet().stream().forEach(spec -> spec.delete());

		setRoot(null);

		deleteDomainObject();
	}

}