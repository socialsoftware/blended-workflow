import React from 'react';
import { OpenWorkItem } from './OpenWorkItem';
import { ExecuteWorkItem } from './ExecuteWorkItem';

export class WorkItemList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
            openWorkItem: {}
        };

        this.openWorkItem = this.openWorkItem.bind(this);
        this.closeWorkItem = this.closeWorkItem.bind(this);
        this.executeWorkItem = this.executeWorkItem.bind(this);
        this.getNextWorkItems = this.getNextWorkItems.bind(this);
        this.renderWorkItems = this.renderWorkItems.bind(this);
    }

    componentDidMount() {
        this.getNextWorkItems();
    }

    getNextWorkItems() {
        this.props.getNextWorkItems().then(result => {
            this.setState({
                workItems: result.data
            })
        });
    }

    openWorkItem(workItem) {
        this.setState({
            open: true,
            openWorkItem: workItem
        });
    }

    closeWorkItem() {
        this.setState({
            open: false,
            openWorkItem: {}
        }, function() {
            this.getNextWorkItems();
        });
    }

    executeWorkItem(workItem) {
        this.props.executeWorkItem(workItem)
        .then(() => {
            this.closeWorkItem();
        }).catch((err) => {
            alert('ERROR: '+ err.response.data.type + ' - ' + err.response.data.value)
        });
    }

    renderWorkItems() {
        if (this.state.open) {
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
                {this.renderWorkItems()}
            </div>
        )
    }
} 