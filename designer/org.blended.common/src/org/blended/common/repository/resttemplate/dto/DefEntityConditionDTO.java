package org.blended.common.repository.resttemplate.dto;

import org.blended.common.common.CommonFactory;
import org.blended.common.common.EntityAchieveCondition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefEntityConditionDTO {
	private String specId;
	private String extId;
	private String entityName;
	private boolean exists;

	public DefEntityConditionDTO() {
	}

	public DefEntityConditionDTO(String specId, String entityName, boolean exists) {
		this.specId = specId;
		this.entityName = entityName;
		this.exists = exists;
	}

	public DefEntityConditionDTO(String specId, String entityName) {
		this.specId = specId;
		this.entityName = entityName;
	}

	public EntityAchieveCondition createEntityAchieveCondition(CommonFactory factory) {
		EntityAchieveCondition entityAchieveCondition = factory.createEntityAchieveCondition();
		entityAchieveCondition.setName(getEntityName());
		return entityAchieveCondition;
	}

	public String getSpecId() {
		return specId;
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

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

}
