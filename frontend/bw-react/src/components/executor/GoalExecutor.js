import React from 'react'
import { connect } from 'react-redux';
import { RepositoryService } from '../../services/RepositoryService'
import { WorkItemList } from './workitem/WorkItemList'

const mapStateToProps = state => {
    return { 
        specId: state.specId,
        name: state.name };
};  

class ConnectedGoalExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.getNextGoalWorkItems = this.getNextGoalWorkItems.bind(this);
        this.executeGoalWorkItem = this.executeGoalWorkItem.bind(this);
    }

    getNextGoalWorkItems() {
        const service = new RepositoryService();

        return service.getNextGoalWorkItems(this.props.specId, this.props.name);
    }

    executeGoalWorkItem(workItem) {
       const service = new RepositoryService();

       return service.executeGoalWorkItem(this.props.specId, this.props.name, workItem.name, workItem);
    }

    render() {
        return (
            <div> 
               <h5>Goal executor of instance {this.props.name} of {this.props.specId} </h5>
                <WorkItemList specId={this.props.specId} name={this.props.name} getNextWorkItems={this.getNextGoalWorkItems} executeWorkItem={this.executeGoalWorkItem} />
            </div>
        )
    }
}

const GoalExecutor = connect(mapStateToProps)(ConnectedGoalExecutor);

export default GoalExecutor;