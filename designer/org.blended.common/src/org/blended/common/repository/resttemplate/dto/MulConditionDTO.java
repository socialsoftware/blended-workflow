package org.blended.common.repository.resttemplate.dto;

import org.blended.common.common.CommonFactory;
import org.blended.common.common.EntityInvariantCondition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MulConditionDTO {
	private String specId;
	private String rolePath;
	private String sourceCardinality;
	private int sourceMin;
	private int sourceMax;
	private String targetCardinality;
	private int targetMin;
	private int targetMax;

	public MulConditionDTO() {
	}

	public MulConditionDTO(String specId, String rolePath, String cardinality) {
		this.specId = specId;
		this.rolePath = rolePath;
		this.targetCardinality = cardinality;
	}

	public EntityInvariantCondition createEntityInvariantCondition(CommonFactory factory) {
		EntityInvariantCondition entityInvariantCondition = factory.createEntityInvariantCondition();
		entityInvariantCondition.setName(getRolePath());
		entityInvariantCondition.setCardinality(getTargetCardinality());
		return entityInvariantCondition;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
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
