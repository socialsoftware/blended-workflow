package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pt.ist.socialsoftware.blendedworkflow.core.service.design.DesignInterface;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHasRole;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExpression;
import pt.ist.socialsoftware.blendedworkflow.resources.domain.ResourceModel;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHasRoleDto extends RALExpressionDto {
	private String role;
	private String unit;

	public RALExprHasRoleDto() {
		
	}
	
	public RALExprHasRoleDto(String role) {
		super();
		this.role = role;
	}

	public RALExprHasRoleDto(String role, String unit) {
		this.role = role;
		this.unit = unit;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
    public RALExpression getRALExpresion(ResourceModel resourceModel, DesignInterface designer, String specId) throws RMException {
		// log.debug("RALExpression Type: HAS ROLE");

		RALExprHasRole ralExprHasRole = new RALExprHasRole(resourceModel,
				resourceModel.getRole(getRole()));

		if (getUnit() != null) {
			ralExprHasRole.setUnit(resourceModel.getUnit(getUnit()));
		}

		return ralExprHasRole;
	}
}