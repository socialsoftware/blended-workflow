package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class MulConditionDto {
	private String externalId;
	private String rolename;
	private String rolePath;
	private String cardinality;
	private int min;
	private int max;
	private int sourceMin;
	private int sourceMax;
	private EntityDto sourceEntity;
	private EntityDto targetEntity;

	public MulConditionDto() {
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolePath() {
		return this.rolePath;
	}

	public void setRolePath(String rolePath) {
		this.rolePath = rolePath;
	}

	public String getCardinality() {
		return this.cardinality;
	}

	public void setCardinality(String cardinality) {
		this.cardinality = cardinality;
	}

	public int getMin() {
		return this.min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return this.max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getSourceMin() {
		return this.sourceMin;
	}

	public void setSourceMin(int sourceMin) {
		this.sourceMin = sourceMin;
	}

	public int getSourceMax() {
		return this.sourceMax;
	}

	public void setSourceMax(int sourceMax) {
		this.sourceMax = sourceMax;
	}

	public EntityDto getSourceEntity() {
		return this.sourceEntity;
	}

	public void setSourceEntity(EntityDto sourceEntity) {
		this.sourceEntity = sourceEntity;
	}

	public EntityDto getTargetEntity() {
		return this.targetEntity;
	}

	public void setTargetEntity(EntityDto targetEntity) {
		this.targetEntity = targetEntity;
	}

}
