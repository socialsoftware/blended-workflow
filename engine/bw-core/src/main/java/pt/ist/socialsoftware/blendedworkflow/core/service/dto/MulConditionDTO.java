package pt.ist.socialsoftware.blendedworkflow.core.service.dto;

public class MulConditionDTO {
	private String externalId;
	private String rolePath;
	private String cardinality;
	private int min;
	private int max;
	private int sourceMin;
	private int sourceMax;

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

	public String getCardinality() {
		return cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
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

}
