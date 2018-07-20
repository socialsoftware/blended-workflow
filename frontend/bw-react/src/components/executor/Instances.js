import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';
import { CreateInstance } from './CreateInstance';
import { ShowInstance } from './ShowInstance';
import { DeleteInstance } from './DeleteInstance';

export class Instances extends React.Component {
    constructor(props) {
        super(props);

        this.state = {};

        this.createInstance = this.createInstance.bind(this);
        this.deleteInstance = this.deleteInstance.bind(this);
    }

    componentDidMount() {
        const service = new RepositoryService();
        service.getWorkflowInstances(this.props.match.params.specId).then(response => {
            this.setState({ instances: response.data }
            )
        });
    }

    createInstance(name) {
        const service = new RepositoryService();

        service.createWorkflowInstance(this.props.match.params.specId, name).then(response => {
            service.getWorkflowInstances(this.props.match.params.specId).then(response => {
                this.setState({ instances: response.data }
                )
            });
        });

    }

   deleteInstance(name) {
        const service = new RepositoryService();

        service.deleteWorkflowInstance(this.props.match.params.specId, name).then(response => {
            service.getWorkflowInstances(this.props.match.params.specId).then(response => {
                this.setState({ instances: response.data }
                )
            });
        });

    }

    generateInstancesDom() {
        if (this.state.instances) {
            return this.state.instances.map(i => <div> <ShowInstance name={i.name} /> <DeleteInstance name={i.name} onClick={this.deleteInstance} /> </div>);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div>
                <CreateInstance onSubmit={this.createInstance} />
                {this.generateInstancesDom()}
            </div>
        )
    }
} 