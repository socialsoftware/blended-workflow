package pt.ist.socialsoftware.blendedworkflow.core.service.dto.req;

public class MergeOperationDto {
	private String specId;
	private String newName;
	private String nameOne;
	private String nameTwo;

	public MergeOperationDto() {
	}

	public MergeOperationDto(String specId, String newName, String nameOne, String nameTwo) {
		this.specId = specId;
		this.newName = newName;
		this.nameOne = nameOne;
		this.nameTwo = nameTwo;
	}

	public String getSpecId() {
		return this.specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getNewName() {
		return this.newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNameOne() {
		return this.nameOne;
	}

	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}

	public String getNameTwo() {
		return this.nameTwo;
	}

	public void setNameTwo(String nameTwo) {
		this.nameTwo = nameTwo;
	}
}
