import React from 'react'
import { RepositoryService } from '../../services/RepositoryService'
import { WorkItemList } from './workitem/WorkItemList'

export class GoalExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            specId: this.props.match.params.specId,
            name: this.props.match.params.name
        };

        this.getNextGoalWorkItems = this.getNextGoalWorkItems.bind(this);
    }

    getNextGoalWorkItems() {
        const service = new RepositoryService();

        return service.getNextGoalWorkItems(this.state.specId, this.state.name);
    }

    render() {
        return (
            <div> 
               <h5>Goal executor of instance {this.state.name} of {this.state.specId} </h5>
                <WorkItemList specId={this.state.specId} name={this.state.name} getNextWorkItems={this.getNextGoalWorkItems} />
            </div>
        )
    }
}