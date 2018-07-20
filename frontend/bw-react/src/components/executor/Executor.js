import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { RepositoryService } from '../../services/RepositoryService';
import { Instances } from './Instances';
import { ExecuteInstance } from './ExecuteInstance';

export class Executor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {}
    }

   componentDidMount() {
        const service = new RepositoryService();
        service.getWorkflowInstances(this.props.match.params.specId).then(response => {
            this.setState({ instances: response.data }
            )
        });
    }

    generateInstancesDom() {
        if (this.state.instances) {
            return this.state.instances.map(i => <li><Link to={`/specifications/${this.props.match.params.specId}/executor/instances/${i.name}`}>{i.name}</Link></li>);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div> 
                <ul>
                    <li><Link to={`/specifications/${this.props.match.params.specId}/executor/instances`}>Instances</Link></li>
                    {this.generateInstancesDom()}
                </ul>
                <Switch>
                    <Route exact path='/specifications/:specId/executor/instances' component={Instances} />
                    <Route path='/specifications/:specId/executor/instances/:name' component={ExecuteInstance} />
                </Switch>
            </div>
        )
    }
} 