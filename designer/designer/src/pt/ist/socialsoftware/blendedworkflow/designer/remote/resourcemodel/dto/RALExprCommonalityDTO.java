package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprCommonalityDTO extends RALExpressionDTO {
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
	private RALExpressionDTO personExpr;

	public RALExprCommonalityDTO() {

	}

	public RALExprCommonalityDTO(AmountDTO amount, RALExpressionDTO personExpr) {
		this.amountDTO = amount;
		this.personExpr = personExpr;
	}

	public AmountDTO getAmountDTO() {
		return amountDTO;
	}

	public void setAmountDTO(AmountDTO amount) {
		this.amountDTO = amount;
	}

	public RALExpressionDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExpressionDTO personExpr) {
		this.personExpr = personExpr;
	}
}
