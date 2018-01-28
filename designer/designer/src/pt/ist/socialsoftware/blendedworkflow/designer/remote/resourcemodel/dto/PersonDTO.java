package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.designer.remote.datamodel.dto.ProductDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO extends ProductDTO {
	private String name;
	private List<String> capabilities;
	private List<String> positions;

	public PersonDTO() {
	}

	public PersonDTO(String specId, String name) {
		setSpecId(specId);
		this.name = name;
	}

	public PersonDTO(String specId, String name, List<String> capabilities, List<String> positions) {
		setSpecId(specId);
		this.name = name;
		this.capabilities = capabilities;
		this.positions = positions;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}

	public List<String> getPositions() {
		return positions;
	}

	public void setPositions(List<String> positions) {
		this.positions = positions;
	}
	
	
}
