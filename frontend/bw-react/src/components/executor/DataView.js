import React from 'react';
import { connect } from 'react-redux';
import { RepositoryService } from '../../services/RepositoryService';
import { EntityInstance } from './dataview/EntityInstance';

const mapStateToProps = state => {
    return { 
        specId: state.specId,
        name: state.name };
};  

class ConnectedDataView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            mandatoryEntityInstance: {}
        };

        this.renderMandatoryEntityInstance = this.renderMandatoryEntityInstance.bind(this);
     }

    componentDidMount() {
        const service = new RepositoryService();

        service.getMandatoryEntityInstance(this.props.specId, this.props.name).then(response => {
            this.setState({ mandatoryEntityInstance: response.data }
            )
        });
    }

    renderMandatoryEntityInstance() {
        if (this.state.mandatoryEntityInstance.entity) {
            return <EntityInstance entityInstance={this.state.mandatoryEntityInstance} /> ;
        } else {
            return "";
        }
    }

    render() {
        return (
            <div> 
                <h5>Data Model of instance {this.props.name} of specification {this.props.specId} </h5>
                {this.renderMandatoryEntityInstance()}
            </div>
        )
    }
} 

const DataView = connect(mapStateToProps)(ConnectedDataView);

export default DataView;