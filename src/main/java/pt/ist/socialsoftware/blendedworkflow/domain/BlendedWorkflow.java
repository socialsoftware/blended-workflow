package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.exception.*;

public class BlendedWorkflow extends BlendedWorkflow_Base {

	public BlendedWorkflow() {
		super();
	}

	@Override
	public void addBwSpecification(BWSpecification bwSpecification) throws BWSpecificationException {
		if (hasBwSpecification(bwSpecification))
			throw new BWSpecificationException(bwSpecification.getId());

		super.addBwSpecification(bwSpecification);
	}

	public static BlendedWorkflow getInstance() {
		return FenixFramework.getRoot();
	}
/*
	public BWInstance getBWInstance(String bwInstanceID){
		for (BWSpecification bwSpecification : getBwSpecification()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstance()) {
				if (bwInstance.getId().equals(bwInstanceID))
					return bwInstance;
			}
		}
		return null;
	}*/



}
