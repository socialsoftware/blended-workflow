import React from 'react'
import { connect } from 'react-redux';
import { RepositoryService } from '../../services/RepositoryService'
import { WorkItemList } from './workitem/WorkItemList'

const mapStateToProps = state => {
    return { 
        specId: state.specId,
        name: state.name };
};  

class ConnectedActivityExecutor extends React.Component {
    constructor(props) {
        super(props);

        this.getNextActivityWorkItems = this.getNextActivityWorkItems.bind(this);
        this.executeActivityWorkItem = this.executeActivityWorkItem.bind(this);
    }

    getNextActivityWorkItems() {
        const service = new RepositoryService();

        return service.getNextActivityWorkItems(this.props.specId, this.props.name);
    }

    executeActivityWorkItem(workItem) {
       const service = new RepositoryService();

       return service.executeActivityWorkItem(this.props.specId, this.props.name, workItem.name, workItem);
    }

    render() {
        return (
            <div> 
               <h5>Activity executor of instance {this.props.name} of {this.props.specId} </h5>
                <WorkItemList specId={this.props.specId} name={this.props.name} getNextWorkItems={this.getNextActivityWorkItems} executeWorkItem={this.executeActivityWorkItem}/>
            </div>
        )
    }
} 

const ActivityExecutor = connect(mapStateToProps)(ConnectedActivityExecutor);

export default ActivityExecutor;