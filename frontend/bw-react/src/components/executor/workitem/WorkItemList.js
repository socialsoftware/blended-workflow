import React from 'react';
import { connect } from 'react-redux';
import { getEntityInstances } from '../../../actions/getEntityInstances';
import { setEntityInstancesToDefine } from '../../../actions/setEntityInstancesToDefine';
import { clearEntityInstancesToDefine } from '../../../actions/clearEntityInstancesToDefine';
import { RepositoryService } from '../../../services/RepositoryService';
import { OpenWorkItem } from './OpenWorkItem';
import ExecuteWorkItem from './ExecuteWorkItem';

const mapDispatchToProps = dispatch => {
    return {
      getEntityInstances: entityInstances => dispatch(getEntityInstances(entityInstances)),
      setEntityInstancesToDefine: entityInstancesToDefine => dispatch(setEntityInstancesToDefine(entityInstancesToDefine)),
      clearEntityInstancesToDefine: () => dispatch(clearEntityInstancesToDefine())
    };
};

class ConnectedWorkItemList extends React.Component {
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
        // const service = new RepositoryService();
        // service.getEntityInstances(this.props.specId, this.props.name).then(response => {
        //     this.props.getEntityInstances(response.data);
        this.getNextWorkItems();
        //});  
    }

    getNextWorkItems() {
        const service = new RepositoryService();
        service.getEntityInstances(this.props.specId, this.props.name).then(response => {
            this.props.getEntityInstances(response.data);
            this.props.getNextWorkItems().then(result => {
                this.setState({
                    workItems: result.data
                });
            });
        });
    }

    openWorkItem(workItem) {
        this.props.setEntityInstancesToDefine(workItem.entityInstancesToDefine);
        this.setState({
            open: true,
            openWorkItem: workItem
        });
    }

    closeWorkItem() {
        this.props.clearEntityInstancesToDefine();
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

const WorkItemList = connect(null, mapDispatchToProps)(ConnectedWorkItemList);

export default WorkItemList;