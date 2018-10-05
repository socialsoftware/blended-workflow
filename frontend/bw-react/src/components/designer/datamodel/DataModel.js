import React from 'react';
import { connect } from 'react-redux';
import { RepositoryService } from '../../../services/RepositoryService';
import { Entity } from './Entity';
import { Association } from './Association';
import { DataModelDiagram } from './DataModelDiagram';

const mapStateToProps = state => {
    return {
        spec: state.spec,
        user: state.user,
    };
};  

class ConnectedDataModel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            dataModel: {}
        };
    }

    componentDidMount() {
        const service = new RepositoryService(this.props.user);

        service.getDataModel(this.props.spec.specId).then(response => {
            this.setState({ dataModel: response.data }
            )
        });
    }

    renderEntities() {
        if (this.state.dataModel.entities) {
            return this.state.dataModel.entities.map(entity => <Entity key={entity.extId} entity={entity} />);
        } else {
            return "";
        }
    }

    renderAssociations() {
        if (this.state.dataModel.associations) {
            return this.state.dataModel.associations.map(a => <Association key={a.name} association={a} />);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div>
                <b>{this.state.dataModel.specName}: Data Model Diagram</b><br /><br />
                <DataModelDiagram dataModel={this.state.dataModel} />
                <br /><br />
                <b>{this.state.dataModel.specName}: Data Model Specification</b><br />
               {this.renderEntities()}
                {this.renderAssociations()}
            </div>
        )
    }
}

const DataModel = connect(mapStateToProps)(ConnectedDataModel);

export default DataModel;