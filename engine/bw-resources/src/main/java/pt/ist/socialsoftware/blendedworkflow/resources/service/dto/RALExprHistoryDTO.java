package pt.ist.socialsoftware.blendedworkflow.resources.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryDTO extends RALExpressionDTO {
	public enum QuantifierDTO {
		LEAST("LEAST"), 
		MOST("MOST");
		
		private final String code;

		private QuantifierDTO(String code) {
			this.code = code;
		}
		
		public String toString() {
			return code;
		}
	
		public static QuantifierDTO fromString(String code) throws IllegalArgumentException {
			switch (code) {
				case "LEAST":
					return QuantifierDTO.LEAST;
				case "MOST":
					return QuantifierDTO.MOST;
				default:
					throw new IllegalArgumentException();
			}
		}
	}

	public RALExprHistoryDTO() {

	}
}
