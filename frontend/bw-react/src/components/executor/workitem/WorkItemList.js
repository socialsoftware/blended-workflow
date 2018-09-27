import React from 'react';
import { connect } from 'react-redux';
import { getEntityInstances } from '../../../actions/getEntityInstances';
import { RepositoryService } from '../../../services/RepositoryService';
import { OpenWorkItem } from './OpenWorkItem';
import ExecuteWorkItem from './ExecuteWorkItem';
import { ModalMessage } from '../../util/ModalMessage';

const mapDispatchToProps = dispatch => {
    return {
      getEntityInstances: entityInstances => dispatch(getEntityInstances(entityInstances))
    };
};

class ConnectedWorkItemList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            error: false,
            errorMessage: '',
            open: false,
            openWorkItem: {}
        };

        this.openWorkItem = this.openWorkItem.bind(this);
        this.closeWorkItem = this.closeWorkItem.bind(this);
        this.closeErrorMessageModal = this.closeErrorMessageModal.bind(this);
        this.executeWorkItem = this.executeWorkItem.bind(this);
        this.getNextWorkItems = this.getNextWorkItems.bind(this);
        this.renderWorkItems = this.renderWorkItems.bind(this);
    }

    componentDidMount() {
        this.getNextWorkItems();
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

    closeErrorMessageModal() {
        this.setState({
            error: false,
            errorMessage: ''
        });
    }

    executeWorkItem(workItem) {
        this.props.executeWorkItem(workItem)
        .then(() => {
            this.closeWorkItem();
        }).catch((err) => {
            this.setState({
                error: true,
                errorMessage: 'ERROR: '+ err.response.data.type + ' - ' + err.response.data.value
            });
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
                <h4>Work items</h4>
                {this.state.error && <ModalMessage title='Error Message' message={this.state.errorMessage} onClose={this.closeErrorMessageModal} />}
                {this.renderWorkItems()}
            </div>
        )
    }
} 

const WorkItemList = connect(null, mapDispatchToProps)(ConnectedWorkItemList);

export default WorkItemList;