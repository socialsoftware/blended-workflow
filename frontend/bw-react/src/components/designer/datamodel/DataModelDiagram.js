import * as SRD from "storm-react-diagrams";
import * as React from "react";
import '../../../../node_modules/storm-react-diagrams/dist/style.min.css';
//import { distributeElements } from "dagre-utils";

export class DataModelDiagram extends React.Component {
	constructor(props) {
		super(props);

		this.populateNodesAndLinks = this.populateNodesAndLinks.bind(this);
	}

	// getDistributedModel(engine, model) {
	// 	const serialized = model.serializeDiagram();
	// 	const distributedSerializedDiagram = distributeElements(serialized);
	
	// 	//deserialize the model
	// 	let deSerializedModel = new SRD.DiagramModel();
	// 	deSerializedModel.deSerializeDiagram(distributedSerializedDiagram, engine);
	// 	return deSerializedModel;
	// }

	populateNodesAndLinks() {
		var engine = new SRD.DiagramEngine();
		engine.installDefaultFactories();

		var model = new SRD.DiagramModel();

		if (this.props.dataModel.entities) {
			var map = new Map();

			this.props.dataModel.entities.forEach((e, index) => {
				let node = new SRD.DefaultNodeModel(e.name, "rgb(0,192,255)");
				node.setPosition(index * 120 + 10, 100);
				model.addNode(node);

				e.attributes.forEach(a => {
					node.addInPort(a.name);
				});

				map.set(e.name, node);
			});

			this.props.dataModel.associations.forEach(a => {
				const nodeOne = map.get(a.entOneName);
				const portOne = nodeOne.addInPort(a.rolenameTwo + a.cardinalityTwo);

				const nodeTwo = map.get(a.entTwoName);
				const portTwo = nodeTwo.addOutPort(a.rolenameOne + a.cardinalityOne);

				let link = portOne.link(portTwo);
				model.addLink(link);
			});
		}

		// let distributedModel = this.getDistributedModel(engine, model);
		// engine.setDiagramModel(distributedModel);
	//	this.forceUpdate();


		engine.setDiagramModel(model);

		return engine;
	}

	render() {
		return ( 
			<div style = {{
					width: 800 + 'px',
					height: 600 + 'px',
					display: 'flex',
					background: 'black'
				}} >
				<SRD.DiagramWidget className = "srd-demo-canvas"
					diagramEngine = {
					this.populateNodesAndLinks()
				}/>  
			</div>
		);
	};
}
