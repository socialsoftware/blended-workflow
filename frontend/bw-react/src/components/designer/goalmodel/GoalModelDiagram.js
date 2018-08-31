import React from 'react';
import { RepositoryService } from '../../../services/RepositoryService';
import { VisNetwork } from '../../util/VisNetwork';

const options = {
    height: 700,
    layout: {
        hierarchical: false
    },
    edges: {
        smooth: false,
        color: '#000000',
        width: 0.5,
        arrows: {
          from: {
            enabled: true,
            scaleFactor: 0.5
          }
        }
    },
    interaction: {
        hover: true
    }
};

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
        return ( 
            <div style={{width:'1000px' , height: '700px'}}>
                <VisNetwork graph={this.state.graph} options={options}/>
            </div>
        );
    }
}