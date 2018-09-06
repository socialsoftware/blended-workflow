import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';
import { connect } from 'react-redux';
import CreateInstance from './CreateInstance';
import { DeleteInstance } from './DeleteInstance';
import { MenuItem } from 'react-bootstrap';

const mapStateToProps = state => {
    return { spec: state.spec };
};  

class ConnectedManageInstances extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            instances: []
        };

        this.createInstance = this.createInstance.bind(this);
        this.deleteInstance = this.deleteInstance.bind(this);
    }

   componentDidMount() {
        const service = new RepositoryService();
        service.getWorkflowInstances(this.props.spec.specId).then(response => {
            this.setState({ instances: response.data }
            )
        });
    }

    createInstance(name, unitOfWork) {
        const service = new RepositoryService();

        service.createWorkflowInstance(this.props.spec.specId, name, unitOfWork)
        .then(() => {
            service.getWorkflowInstances(this.props.spec.specId).then(response => {
                this.setState({ instances: response.data }
                );
            });
        }).catch((err) => {
            alert('ERROR: '+ err.response.data.type + ' - ' + err.response.data.value)
        });
    }

    deleteInstance(name) {
        const service = new RepositoryService();

        service.deleteWorkflowInstance(this.props.spec.specId, name).then(response => {
            service.getWorkflowInstances(this.props.spec.specId).then(response => {
                this.setState({ instances: response.data }
                )
            });
        });
    }

    renderInstances() {
        if (this.state.instances) {
            return this.state.instances.map(i => <DeleteInstance name={i.name} onClick={this.deleteInstance} />);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div> 
                {this.props.spec.specId && <MenuItem eventKey={4.1}><CreateInstance specId={this.props.spec.specId} onSubmit={this.createInstance} /></MenuItem>}
                {this.renderInstances()}
            </div>
        )
    }
} 

const ManageInstances = connect(mapStateToProps)(ConnectedManageInstances);

export default ManageInstances;