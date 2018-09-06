import React from 'react';
import { Link } from 'react-router-dom';
import { RepositoryService } from '../../services/RepositoryService';
import { connect } from 'react-redux';
import { selectInstance } from '../../actions/selectInstance';
import { MenuItem } from 'react-bootstrap';

const mapStateToProps = state => {
    return { spec: state.spec };
};  

const mapDispatchToProps = dispatch => {
    return {
        selectInstance: name => dispatch(selectInstance(name))
    };
  };

class ConnectedManageInstancesLinks extends React.Component {
    constructor(props) {
        super(props);

        this.state = {};
    }

   componentDidMount() {
        const service = new RepositoryService();
        service.getWorkflowInstances(this.props.spec.specId).then(response => {
            this.setState({ instances: response.data }
            )
        });
    }

    renderInstances() {
        if (this.state.instances) {
            return this.state.instances.map(i => <MenuItem eventKey={i.name}><Link onClick={() => {this.props.selectInstance(i.name)}} to={`/specifications/executor/instances/${i.name}`}>{i.name}</Link></MenuItem>);
        } else {
            return "";
        }
    }

    render() {
        return (
            <span> 
                    {this.props.spec.specId && <MenuItem eventKey={4.1}><Link to='/specifications/executor/instances'>Manage Instances</Link></MenuItem>}
                    <MenuItem divider />
                    {this.renderInstances()}
            </span>
        )
    }
} 

const ManageInstancesLinks = connect(mapStateToProps, mapDispatchToProps)(ConnectedManageInstancesLinks);

export default ManageInstancesLinks;