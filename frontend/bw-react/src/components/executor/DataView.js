import React from 'react';
import { connect } from 'react-redux';
import { getEntityInstances } from '../../actions/getEntityInstances';
import { RepositoryService } from '../../services/RepositoryService';
import { EntityInstance } from './dataview/EntityInstance';

const mapStateToProps = state => {
    return {
        spec: state.spec,
        name: state.name,
        entityInstances: state.entityInstances,
        user: state.user,
    };
};

const mapDispatchToProps = dispatch => {
    return {
      getEntityInstances: entityInstances => dispatch(getEntityInstances(entityInstances))
    };
};

class ConnectedDataView extends React.Component {
    constructor(props) {
        super(props);

        this.renderMandatoryEntityInstances = this.renderMandatoryEntityInstances.bind(this);
     }

     componentDidMount() {
        const service = new RepositoryService(this.props.user);
        service.getEntityInstances(this.props.spec.specId, this.props.name).then(response => {
            this.props.getEntityInstances(response.data);
        });
     }

    renderMandatoryEntityInstances() {
        if (this.props.entityInstances) {
            return  this.props.entityInstances.map(ei => <EntityInstance key={ei.id} entityInstance={ei} />);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div> 
                <h5>Data Model of instance {this.props.name} of specification {this.props.spec.specId} </h5>
                {this.renderMandatoryEntityInstances()}
            </div>
        )
    }
} 

const DataView = connect(mapStateToProps, mapDispatchToProps)(ConnectedDataView);

export default DataView;