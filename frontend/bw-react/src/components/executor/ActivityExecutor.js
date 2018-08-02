import React from 'react'
import { RepositoryService } from '../../services/RepositoryService'
import { WorkItemList } from './workitem/WorkItemList'

export class ActivityExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            specId: this.props.match.params.specId,
            name: this.props.match.params.name
        };

        this.getNextActivityWorkItems = this.getNextActivityWorkItems.bind(this);
        this.executeActivityWorkItem = this.executeActivityWorkItem.bind(this);
    }

    getNextActivityWorkItems() {
        const service = new RepositoryService();

        return service.getNextActivityWorkItems(this.state.specId, this.state.name);
    }

    executeActivityWorkItem(workItem) {
       const service = new RepositoryService();

       return service.executeActivityWorkItem(this.state.specId, this.state.name, workItem.name, workItem);
    }

    render() {
        return (
            <div> 
               <h5>Activity executor of instance {this.state.name} of {this.state.specId} </h5>
                <WorkItemList specId={this.state.specId} name={this.state.name} getNextWorkItems={this.getNextActivityWorkItems} executeWorkItem={this.executeActivityWorkItem}/>
            </div>
        )
    }
} 