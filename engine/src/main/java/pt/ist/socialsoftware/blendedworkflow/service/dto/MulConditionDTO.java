package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class MulConditionDTO {
	private String externalId;
	private String rolePath;
	private String sourceCardinality;
	private int sourceMin;
	private int sourceMax;
	private String targetCardinality;
	private int targetMin;
	private int targetMax;

	public MulConditionDTO() {
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getRolePath() {
		return rolePath;
	}

	public void setRolePath(String rolePath) {
		this.rolePath = rolePath;
	}

	public String getSourceCardinality() {
		return sourceCardinality;
	}

	public void setSourceCardinality(String sourceCardinality) {
		this.sourceCardinality = sourceCardinality;
	}

	public int getSourceMin() {
		return sourceMin;
	}

	public void setSourceMin(int sourceMin) {
		this.sourceMin = sourceMin;
	}

	public int getSourceMax() {
		return sourceMax;
	}

	public void setSourceMax(int sourceMax) {
		this.sourceMax = sourceMax;
	}

	public String getTargetCardinality() {
		return targetCardinality;
	}

	public void setTargetCardinality(String targetCardinality) {
		this.targetCardinality = targetCardinality;
	}

	public int getTargetMin() {
		return targetMin;
	}

	public void setTargetMin(int targetMin) {
		this.targetMin = targetMin;
	}

	public int getTargetMax() {
		return targetMax;
	}

	public void setTargetMax(int targetMax) {
		this.targetMax = targetMax;
	}

}
