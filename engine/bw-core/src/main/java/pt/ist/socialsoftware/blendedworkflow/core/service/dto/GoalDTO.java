package pt.ist.socialsoftware.blendedworkflow.core.service.dto;

public class GoalDTO {
	private String specId;
	private String extId;
	private String name;

	public GoalDTO() {
	}

	public GoalDTO(String specId, String name) {
		this.specId = specId;
		this.extId = null;
		this.name = name;
	}

	public GoalDTO(String specId) {
		this(specId, null);
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
