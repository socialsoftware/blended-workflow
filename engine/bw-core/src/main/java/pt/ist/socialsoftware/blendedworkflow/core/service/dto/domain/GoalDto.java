package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Goal;

public class GoalDto {
	private String specId;
	private String extId;
	private String type;
	private String name;
	private PositionDto position;

	public GoalDto() {}

	public GoalDto(String specId, String extId, String type, String name) {
		this.specId = specId;
		this.extId = extId;
		this.type = type;
		this.name = name;
	}

	public GoalDto(Goal goal) {
		setSpecId(goal.getGoalModel().getSpecification().getSpecId());
		setExtId(goal.getExternalId());
		setType(goal.getClass().getSimpleName());
		setName(goal.getName());
		if (goal.getView() != null) {
			position = new PositionDto(goal.getView().getPosition());
		}
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getExtId() {
		return this.extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PositionDto getPosition() {
		return position;
	}

	public void setPosition(PositionDto position) {
		this.position = position;
	}
}
