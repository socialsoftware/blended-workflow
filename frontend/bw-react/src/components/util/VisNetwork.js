import {
    DataSet,
    Network
} from 'vis';
import React, {
    Component,
    createRef
} from "react";

export class VisNetwork extends Component {
    constructor(props) {
        super(props);
        this.network = {};
        this.appRef = createRef();
    }

    componentDidMount() {
        this.network = new Network(this.appRef.current, this.props.graph, options);
    }

    render() {
        if (this.props.graph.nodes) {
            this.network = new Network(this.appRef.current, this.props.graph, options);
        }
        return ( 
            <div ref = {this.appRef}/>
        );
    }
}