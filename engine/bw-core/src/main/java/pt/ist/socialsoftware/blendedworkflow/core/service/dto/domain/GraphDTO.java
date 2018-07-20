package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphDTO {
	private NodeDto[] nodes;
	private EdgeDTO[] edges;

	public GraphDTO() {

	}

	public NodeDto[] getNodes() {
		return nodes;
	}

	public void setNodes(NodeDto[] nodes) {
		this.nodes = nodes;
	}

	public EdgeDTO[] getEdges() {
		return edges;
	}

	public void setEdges(EdgeDTO[] edges) {
		this.edges = edges;
	}

}
