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
        };

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
        const nodeId = event.nodes[0];
        if (nodeId) {
        this.setState({
            showConditions: true,
            conditions: this.props.graph.nodes.filter(n => n.id === nodeId)[0].title
        });
        };
    }

    render() {
        if (this.props.graph.nodes && Object.keys(this.network).length === 0 && this.network.constructor === Object) {
            this.network = new Network(this.appRef.current, this.props.graph, this.props.options);
            this.network.on("click", this.handleHoverNode);
        }
        return ( 
            <div>
                {this.state.showConditions && <ModalMessage title='Conditions' message={this.state.conditions} onClose={this.handleCloseConditionsModal} />}
                <div ref = {this.appRef}/>
            </div>
        );
    }
}