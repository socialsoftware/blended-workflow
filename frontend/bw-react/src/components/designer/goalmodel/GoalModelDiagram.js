import React from 'react';
import {
    RepositoryService
} from '../../../services/RepositoryService';
import {
    VisNetwork
} from '../../util/VisNetwork';

export class GoalModelDiagram extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            graph: {}
        };

    }

    componentDidMount() {
        const service = new RepositoryService();

        service.getGoalModelGraphVis(this.props.specId).then(response => {
            this.setState({
                graph: response.data
            });
        });
    }

    render() {
        if (this.state.graph.nodes) {
        this.state.graph.nodes.forEach(n => alert(n.id));
        }
        return ( 
            <VisNetwork graph = {this.state.graph}/>
        );
    }
}