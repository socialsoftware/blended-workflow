package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphVisDto {
	private List<NodeVisDto> nodes;
	private List<EdgeVisDto> edges;

	public GraphVisDto() {

	}

	public List<NodeVisDto> getNodes() {
		return this.nodes;
	}

	public void setNodes(List<NodeVisDto> nodes) {
		this.nodes = nodes;
	}

	public List<EdgeVisDto> getEdges() {
		return this.edges;
	}

	public void setEdges(List<EdgeVisDto> edges) {
		this.edges = edges;
	}

}
