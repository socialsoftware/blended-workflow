import React from 'react';
import { connect } from 'react-redux';
import { RepositoryService } from '../../services/RepositoryService';
import WorkItemList from './workitem/WorkItemList';
import ExecutionLog from './ExecutionLog';

const mapStateToProps = state => {
    return { 
        spec: state.spec,
        name: state.name,
        user: state.user,
    };
};  

class ConnectedActivityExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            log: []
        };

        this.getLog = this.getLog.bind(this);
        this.getNextActivityWorkItems = this.getNextActivityWorkItems.bind(this);
        this.executeActivityWorkItem = this.executeActivityWorkItem.bind(this);
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

    getNextActivityWorkItems() {
        this.getLog();
        
        const service = new RepositoryService(this.props.user);
        return service.getNextActivityWorkItems(this.props.spec.specId, this.props.name);
    }

    executeActivityWorkItem(workItem) {
       const service = new RepositoryService(this.props.user);

       return service.executeActivityWorkItem(this.props.spec.specId, this.props.name, workItem.name, workItem);
    }

    render() {
        return (
            <div> 
               <h3>Activity executor of instance {this.props.name} of {this.props.spec.specId} </h3>
                <WorkItemList 
                    specId={this.props.spec.specId} 
                    name={this.props.name} 
                    getNextWorkItems={this.getNextActivityWorkItems} 
                    executeWorkItem={this.executeActivityWorkItem}/>
                <br /><br />
                <ExecutionLog 
                    log={this.state.log} />
            </div>
        )
    }
} 

const ActivityExecutor = connect(mapStateToProps)(ConnectedActivityExecutor);

export default ActivityExecutor;