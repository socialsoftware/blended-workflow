package org.blended.common.repository.resttemplate.dto;

import org.blended.common.common.AttributeAchieveCondition;
import org.blended.common.common.CommonFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefAttributeConditionDTO {
	private String specId;
	private String path;
	private boolean mandatory;
	private String attributeExtId;

	public DefAttributeConditionDTO() {
	}

	public DefAttributeConditionDTO(String specId, String path, boolean mandatory) {
		this.setSpecId(specId);
		this.path = path;
		this.mandatory = mandatory;
	}

	public DefAttributeConditionDTO(String specId, String path) {
		this.setSpecId(specId);
		this.path = path;
	}

	public AttributeAchieveCondition createAttributeAchieveCondition(CommonFactory factory) {
		AttributeAchieveCondition achieveCondition;
		if (isMandatory()) {
			achieveCondition = factory.createMandatoryAttributeAchieveCondition();
			achieveCondition.setAttribute(getPath());
		} else {
			achieveCondition = factory.createNotMandatoryAttributeAchieveCondition();
			achieveCondition.setAttribute(getPath());
		}
		return achieveCondition;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getAttributeExtId() {
		return attributeExtId;
	}

	public void setAttributeExtId(String attributeExtId) {
		this.attributeExtId = attributeExtId;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

}
