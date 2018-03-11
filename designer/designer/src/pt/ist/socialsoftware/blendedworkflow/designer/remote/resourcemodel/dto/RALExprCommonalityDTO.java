package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprCommonalityDTO extends RALExpressionDTO {
	public enum Amount {
		SOME("SOME"), 
		ALL("ALL");
		
		private final String code;
		
		private Amount(String code) {
			this.code = code;
		}
		
		public String toString() {
			return code;
		}
	
		public static Amount fromString(String code) throws IllegalArgumentException {
			switch (code) {
				case "SOME":
					return Amount.SOME;
				case "ALL":
					return Amount.ALL;
				default:
					throw new IllegalArgumentException();
			}
		}
	}
	
	private Amount amount;
	private RALExpressionDTO personExpr;

	public RALExprCommonalityDTO() {

	}

	public RALExprCommonalityDTO(Amount amount, RALExpressionDTO personExpr) {
		this.amount = amount;
		this.personExpr = personExpr;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public RALExpressionDTO getPersonExpr() {
		return personExpr;
	}

	public void setPersonExpr(RALExpressionDTO personExpr) {
		this.personExpr = personExpr;
	}
}
