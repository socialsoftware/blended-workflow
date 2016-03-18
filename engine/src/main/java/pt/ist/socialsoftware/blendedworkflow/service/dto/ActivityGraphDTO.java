package pt.ist.socialsoftware.blendedworkflow.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityGraphDTO {
	private NodeDTO[] nodes;
	private EdgeDTO[] edges;

	public ActivityGraphDTO() {

	}

	public NodeDTO[] getNodes() {
		return nodes;
	}

	public void setNodes(NodeDTO[] nodes) {
		this.nodes = nodes;
	}

	public EdgeDTO[] getEdges() {
		return edges;
	}

	public void setEdges(EdgeDTO[] edges) {
		this.edges = edges;
	}

}
