import { Network } from 'vis';
import React, { Component, createRef } from "react";

export class VisNetwork extends Component {
    constructor(props) {
        super(props);
        this.network = {};
        this.appRef = createRef();

        this.handleHoverNode = this.handleHoverNode.bind(this);
    }

    handleHoverNode(event) {
        alert(this.props.graph.nodes.filter(n => n.id === event.node)[0].title);
    }

    render() {
        const nodes = this.props.graph.nodes;
        const network = this.network;
        if (this.props.graph.nodes) {
            this.network = new Network(this.appRef.current, this.props.graph, this.props.options);
            this.network.on("hoverNode", this.handleHoverNode);
            this.network.on("blurNode", function(){
                // functionality for popup to hide on mouseout
            });        
        }
        return ( 
            <div ref = {this.appRef}/>
        );
    }
}