package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphDto {
	private NodeDto[] nodes;
	private EdgeDto[] edges;

	public GraphDto() {

	}

	public NodeDto[] getNodes() {
		return nodes;
	}

	public void setNodes(NodeDto[] nodes) {
		this.nodes = nodes;
	}

	public EdgeDto[] getEdges() {
		return edges;
	}

	public void setEdges(EdgeDto[] edges) {
		this.edges = edges;
	}

}
