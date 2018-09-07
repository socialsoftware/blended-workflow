import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';
import { connect } from 'react-redux';
import { setInstances } from '../../actions/setInstances';
import CreateInstance from './CreateInstance';
import { DeleteInstance } from './DeleteInstance';

const mapStateToProps = state => {
    return { spec: state.spec };
};  

const mapDispatchToProps = dispatch => {
    return {
        setInstances: (instances) => dispatch(setInstances(instances))
    };
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
            );
        });
    }

    createInstance(name, unitOfWork) {
        const service = new RepositoryService();

        service.createWorkflowInstance(this.props.spec.specId, name, unitOfWork)
        .then(() => {
            service.getWorkflowInstances(this.props.spec.specId).then(response => {
                this.setState({ instances: response.data });
                this.props.setInstances(response.data);

            });
        }).catch((err) => {
            alert('ERROR: '+ err.response.data.type + ' - ' + err.response.data.value)
        });
    }

    deleteInstance(name) {
        const service = new RepositoryService();

        service.deleteWorkflowInstance(this.props.spec.specId, name).then(response => {
            service.getWorkflowInstances(this.props.spec.specId).then(response => {
                this.setState({ instances: response.data });
                this.props.setInstances(response.data);
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
                {this.props.spec.specId && <CreateInstance specId={this.props.spec.specId} onSubmit={this.createInstance} />}
                {this.renderInstances()}
            </div>
        )
    }
} 

const ManageInstances = connect(mapStateToProps, mapDispatchToProps)(ConnectedManageInstances);

export default ManageInstances;