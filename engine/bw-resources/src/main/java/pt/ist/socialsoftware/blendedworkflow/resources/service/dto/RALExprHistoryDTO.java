package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryDTO extends RALExpressionDTO {
	public enum Quantifier {
		LEAST("LEAST"), 
		MOST("MOST");
		
		private final String code;
		
		private Quantifier(String code) {
			this.code = code;
		}
		
		public String toString() {
			return code;
		}
	
		public static Quantifier fromString(String code) throws IllegalArgumentException {
			switch (code) {
				case "LEAST":
					return Quantifier.LEAST;
				case "MOST":
					return Quantifier.MOST;
				default:
					throw new IllegalArgumentException();
			}
		}
	}

	public RALExprHistoryDTO() {

	}
}
