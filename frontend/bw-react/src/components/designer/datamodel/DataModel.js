import React from 'react'
import { connect } from 'react-redux'
import { RepositoryService } from '../../../services/RepositoryService'
import { Entity } from './Entity'
import { Association } from './Association'

const mapStateToProps = state => {
    return { specId: state.specId };
};  

class ConnectedDataModel extends React.Component {
    constructor(props) {
        super(props);

        alert(this.props.specId);



        this.state = {
            dataModel: {}
        };
    }

    componentDidMount() {
        const service = new RepositoryService();

        service.getDataModel(this.props.specId).then(response => {
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
                <b>Data Model Specification {this.state.dataModel.specName}</b>
                {this.renderEntities()}
                {this.renderAssociations()}
            </div>
        )
    }
}

const DataModel = connect(mapStateToProps)(ConnectedDataModel);
export default DataModel;