package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class RelationDTO {
	private String specId;
	private String extId;
	private String name;
	private String entOneExtId;
	private String entOneName;
	private String rolenameOne;
	private String cardinalityOne;
	private String entTwoExtId;
	private String entTwoName;
	private String rolenameTwo;
	private String cardinalityTwo;

	public RelationDTO() {
	}

	public RelationDTO(String specId, String name, String entOneExtId, String rolenameOne, String cardinalityOne,
			String entTwoExtId, String rolenameTwo, String cardinalityTwo) {
		this.specId = specId;
		this.name = name;
		this.entOneExtId = entOneExtId;
		this.rolenameOne = rolenameOne;
		this.cardinalityOne = cardinalityOne;
		this.entTwoExtId = entTwoExtId;
		this.rolenameTwo = rolenameTwo;
		this.cardinalityTwo = cardinalityTwo;
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

	public String getEntOneExtId() {
		return this.entOneExtId;
	}

	public void setEntOneExtId(String entOneExtId) {
		this.entOneExtId = entOneExtId;
	}

	public String getEntOneName() {
		return this.entOneName;
	}

	public void setEntOneName(String entOneName) {
		this.entOneName = entOneName;
	}

	public String getRolenameOne() {
		return this.rolenameOne;
	}

	public void setRolenameOne(String rolenameOne) {
		this.rolenameOne = rolenameOne;
	}

	public String getCardinalityOne() {
		return this.cardinalityOne;
	}

	public void setCardinalityOne(String cardinalityOne) {
		this.cardinalityOne = cardinalityOne;
	}

	public String getEntTwoExtId() {
		return this.entTwoExtId;
	}

	public void setEntTwoExtId(String entTwoExtId) {
		this.entTwoExtId = entTwoExtId;
	}

	public String getEntTwoName() {
		return this.entTwoName;
	}

	public void setEntTwoName(String entTwoName) {
		this.entTwoName = entTwoName;
	}

	public String getRolenameTwo() {
		return this.rolenameTwo;
	}

	public void setRolenameTwo(String rolenameTwo) {
		this.rolenameTwo = rolenameTwo;
	}

	public String getCardinalityTwo() {
		return this.cardinalityTwo;
	}

	public void setCardinalityTwo(String cardinalityTwo) {
		this.cardinalityTwo = cardinalityTwo;
	}

}
