package pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprCommonalityDto extends RALExpressionDto {
	public enum AmountDTO {
		SOME("SOME"), 
		ALL("ALL");
		
		private final String code;
		
		private AmountDTO(String code) {
			this.code = code;
		}
		
		public String toString() {
			return code;
		}
	
		public static AmountDTO fromString(String code) throws IllegalArgumentException {
			switch (code) {
				case "SOME":
					return AmountDTO.SOME;
				case "ALL":
					return AmountDTO.ALL;
				default:
					throw new IllegalArgumentException();
			}
		}
	}
	
	private AmountDTO amountDTO;
	private RALExpressionDto personExpr;

	public RALExprCommonalityDto() {

	}

	public RALExprCommonalityDto(AmountDTO amountDTO, RALExpressionDto personExpr) {
		this.amountDTO = amountDTO;
		this.personExpr = personExpr;
	}

	public AmountDTO getAmountDTO() {
		return amountDTO;
	}

	public void setAmountDTO(AmountDTO amountDTO) {
		this.amountDTO = amountDTO;
	}

	public RALExpressionDto getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExpressionDto personExpr) {
		this.personExpr = personExpr;
	}
}
