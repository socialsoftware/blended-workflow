import React from 'react';
import { connect } from 'react-redux';
import { RepositoryService } from '../../services/RepositoryService';
import WorkItemList from './workitem/WorkItemList';
import { ExecutionLog } from './ExecutionLog';

const mapStateToProps = state => {
    return { 
        spec: state.spec,
        name: state.name,
        user: state.user,
    };
};  

class ConnectedGoalExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            log: []
        };

        this.getLog = this.getLog.bind(this);
        this.getNextGoalWorkItems = this.getNextGoalWorkItems.bind(this);
        this.executeGoalWorkItem = this.executeGoalWorkItem.bind(this);
    }

    componentDidMount() {
        this.getLog();
    }

    getLog() {
        const service = new RepositoryService(this.props.user);
        service.getWorkflowInstanceLog(this.props.spec.specId, this.props.name).then(response => {
            this.setState({
                log: response.data
            });
        });
    }

    getNextGoalWorkItems() {
        this.getLog();

        const service = new RepositoryService(this.props.user);
        return service.getNextGoalWorkItems(this.props.spec.specId, this.props.name);
    }

    executeGoalWorkItem(workItem) {
       const service = new RepositoryService(this.props.user);

       return service.executeGoalWorkItem(this.props.spec.specId, this.props.name, workItem.name, workItem);
    }

    render() {
        return (
            <div> 
               <h3>Goal executor of instance {this.props.name} of {this.props.spec.specId} </h3>
                <WorkItemList 
                    specId={this.props.spec.specId} 
                    name={this.props.name} 
                    getNextWorkItems={this.getNextGoalWorkItems} 
                    executeWorkItem={this.executeGoalWorkItem} />
                <br /><br />
                <ExecutionLog 
                    log={this.state.log} />
            </div>
        )
    }
}

const GoalExecutor = connect(mapStateToProps)(ConnectedGoalExecutor);

export default GoalExecutor;