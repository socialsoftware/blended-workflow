package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityDto {
	private String specId;
	private String extId;
	private String name;
	private String description;
	private PositionDto position;

	public ActivityDto(String specId, String name, String description) {
		this.specId = specId;
		this.name = name;
		this.description = description;
	}

	public ActivityDto(Activity activity) {
		setSpecId(activity.getActivityModel().getSpecification().getSpecId());
		setExtId(activity.getExternalId());
		setName(activity.getName());
		if (activity.getView() != null) {
			position = new PositionDto(activity.getView().getPosition());
		}
	}


	public ActivityDto(String specId, String name) {
		this(specId, name, "");
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PositionDto getPosition() {
		return position;
	}

	public void setPosition(PositionDto position) {
		this.position = position;
	}

}
