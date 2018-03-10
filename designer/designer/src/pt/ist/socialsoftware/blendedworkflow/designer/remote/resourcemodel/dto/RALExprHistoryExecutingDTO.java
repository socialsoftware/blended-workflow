package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprHistoryExecutingDTO extends RALExpressionDTO {
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

	private Quantifier quantifier;
	private String dataField;

	public RALExprHistoryExecutingDTO() {
		
	}

	public RALExprHistoryExecutingDTO(Quantifier quantifier, String dataField) {
		super();
		this.quantifier = quantifier;
		this.dataField = dataField;
	}

	public Quantifier getQuantifier() {
		return quantifier;
	}

	public void setQuantifier(Quantifier quantifier) {
		this.quantifier = quantifier;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}
}