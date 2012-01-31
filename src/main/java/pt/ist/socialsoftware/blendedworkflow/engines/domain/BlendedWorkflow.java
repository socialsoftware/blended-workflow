package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;

public class BlendedWorkflow extends BlendedWorkflow_Base {

	public BlendedWorkflow() {
		super();
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
