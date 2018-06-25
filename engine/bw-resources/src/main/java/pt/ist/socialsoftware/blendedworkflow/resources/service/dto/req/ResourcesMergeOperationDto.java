package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.req;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.MergeOperationDto;
import pt.ist.socialsoftware.blendedworkflow.resources.service.design.MergeResourcesPolicy;

public class ResourcesMergeOperationDto extends MergeOperationDto {
	private MergeResourcesPolicy policy;

	public ResourcesMergeOperationDto() {
		super();
	}

	public ResourcesMergeOperationDto(String specId, String newName, String nameOne, String nameTwo, MergeResourcesPolicy policy) {
		super(specId, newName, nameOne, nameTwo);
		this.policy = policy;
	}

	public MergeResourcesPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(MergeResourcesPolicy policy) {
		this.policy = policy;
	}
}
