import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';
import { OpenWorkItem } from './workitem/OpenWorkItem';
import { ExecuteWorkItem } from './workitem/ExecuteWorkItem';

export class GoalExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            openWorkItem: {}
        };

        this.openWorkItem = this.openWorkItem.bind(this);
        this.closeWorkItem = this.closeWorkItem.bind(this);
        this.executeWorkItem = this.executeWorkItem.bind(this);
        this.getWorkItems = this.getWorkItems.bind(this);
    }

    componentDidMount() {
        this.getNextGoalWorkItems();
    }

    getNextGoalWorkItems() {
        const service = new RepositoryService();

        service.getNextGoalWorkItems(this.props.match.params.specId, this.props.match.params.name).then(response => {
            this.setState({ workItems: response.data }
            )
        });
    
    }

    openWorkItem(workItem) {
        this.setState({
            openWorkItem: workItem
        });
    }

    closeWorkItem() {
        this.setState({
            openWorkItem: {}
        }, function() {
            this.getNextGoalWorkItems();
        });
    }

    executeWorkItem(workItem) {
       const service = new RepositoryService();

        service.executeWorkItem(this.props.match.params.specId, 
            this.props.match.params.name, 
            workItem.name, workItem)
        .then(() => {
            this.closeWorkItem();
        }).catch((err) => {
            alert(err.message)
        });
    }

    getWorkItems() {
        if (this.state.openWorkItem.name) {
            return <ExecuteWorkItem workItem={this.state.openWorkItem} onClose={this.closeWorkItem} onExecute={this.executeWorkItem}/>;
        } else if (this.state.workItems) {
            return this.state.workItems.sort((w1,w2) => w1.name.localeCompare(w2.name)).map(wi => <OpenWorkItem key={wi.name} workItem={wi} onClick={this.openWorkItem} />);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div> 
               <h5>Goal executor of instance {this.props.match.params.name} of {this.props.match.params.specId} </h5>
                {this.getWorkItems()}
            </div>
        )
    }
} 