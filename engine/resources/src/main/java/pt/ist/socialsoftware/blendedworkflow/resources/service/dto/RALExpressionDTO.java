package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.blendedWorkflow.RALExpression;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExpressionDTO {
	public static RALExpressionDTO buildRALExpressionDTO(String specId, RALExpression expression) {
		return null;
	}
}
