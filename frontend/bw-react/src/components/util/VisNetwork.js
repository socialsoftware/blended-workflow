import { Network } from 'vis';
import React, { Component, createRef } from 'react';
import { ModalMessage } from './ModalMessage';

export class VisNetwork extends Component {
    constructor(props) {
        super(props);
        this.network = {};
        this.appRef = createRef();

        this.state = {
            showConditions: false,
            conditions: ''
        }

        this.handleCloseConditionsModal = this.handleCloseConditionsModal.bind(this);
        this.handleHoverNode = this.handleHoverNode.bind(this);
    }

    handleCloseConditionsModal() {
        this.setState({
            showConditions: false,
            conditions: ''
        });
    }

    handleHoverNode(event) {
        this.setState({
            showConditions: true,
            conditions: this.props.graph.nodes.filter(n => n.id === event.node)[0].title
        });
    }

    render() {
        const nodes = this.props.graph.nodes;
        const network = this.network;
        if (this.props.graph.nodes) {
            this.network = new Network(this.appRef.current, this.props.graph, this.props.options);
            this.network.on("hoverNode", this.handleHoverNode);
            this.network.on("blurNode", this.handleCloseConditionsModal);        
        }
        return ( 
            <div>
                {this.state.showConditions && <ModalMessage title='Conditions' message={this.state.conditions} onClose={this.handleCloseConditionsModal} />}
                <div ref = {this.appRef}/>
            </div>
        );
    }
}