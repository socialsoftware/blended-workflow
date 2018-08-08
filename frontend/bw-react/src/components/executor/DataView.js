import React from 'react';
import { connect } from 'react-redux';
import { getEntityInstancesAction } from '../../actions/get-entity-instances';
import { RepositoryService } from '../../services/RepositoryService';
import { EntityInstance } from './dataview/EntityInstance';

const mapStateToProps = state => {
    return {
        specId: state.specId,
        name: state.name,
        entityInstances: state.entityInstances };
};

const mapDispatchToProps = dispatch => {
    return {
      getEntityInstancesAction: entityInstances => dispatch(getEntityInstancesAction(entityInstances))
    };
  };
  

class ConnectedDataView extends React.Component {
    constructor(props) {
        super(props);

        this.renderMandatoryEntityInstances = this.renderMandatoryEntityInstances.bind(this);
     }

     componentDidMount() {
        const service = new RepositoryService();
        service.getEntityInstances(this.props.specId, this.props.name).then(response => {
            this.props.getEntityInstancesAction(response.data);
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
                <h5>Data Model of instance {this.props.name} of specification {this.props.specId} </h5>
                {this.renderMandatoryEntityInstances()}
            </div>
        )
    }
} 

const DataView = connect(mapStateToProps, mapDispatchToProps)(ConnectedDataView);

export default DataView;