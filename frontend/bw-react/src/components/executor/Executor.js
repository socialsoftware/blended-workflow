import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { RepositoryService } from '../../services/RepositoryService';
import { connect } from 'react-redux';
import { selectInstanceAction } from '../../actions/select-instance-action';
import { CreateInstance } from './CreateInstance';
import { DeleteInstance } from './DeleteInstance';
import { ExecuteInstance } from './ExecuteInstance';

const mapStateToProps = state => {
    return { specId: state.specId };
};  

const mapDispatchToProps = dispatch => {
    return {
        selectInstanceAction: name => dispatch(selectInstanceAction(name))
    };
  };

class ConnectedExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {};

        this.createInstance = this.createInstance.bind(this);
        this.deleteInstance = this.deleteInstance.bind(this);
    }

   componentDidMount() {
        const service = new RepositoryService();
        service.getWorkflowInstances(this.props.specId).then(response => {
            this.setState({ instances: response.data }
            )
        });
    }

    createInstance(name) {
        const service = new RepositoryService();

        service.createWorkflowInstance(this.props.specId, name).then(response => {
            service.getWorkflowInstances(this.props.specId).then(response => {
                this.setState({ instances: response.data }
                )
            });
        });
    }

    deleteInstance(name) {
        const service = new RepositoryService();

        service.deleteWorkflowInstance(this.props.specId, name).then(response => {
            service.getWorkflowInstances(this.props.specId).then(response => {
                this.setState({ instances: response.data }
                )
            });
        });
    }

    renderInstances() {
        if (this.state.instances) {
            return this.state.instances.map(i => <li key={i.name}><Link onClick={() => {this.props.selectInstanceAction(i.name)}} to={`/specifications/executor/instances/${i.name}`}>{i.name}</Link> <DeleteInstance name={i.name} onClick={this.deleteInstance} /></li>);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div> 
                <ul>
                    {this.props.specId && <li><CreateInstance onSubmit={this.createInstance} /></li>}
                    {this.renderInstances()}
                </ul>
                <Switch>
                    <Route path='/specifications/executor/instances/:name' component={ExecuteInstance} />
                </Switch>
            </div>
        )
    }
} 

const Executor = connect(mapStateToProps, mapDispatchToProps)(ConnectedExecutor);

export default Executor;