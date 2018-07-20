import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';
import { Entity } from './Entity';

export class DataModel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            dataModel: {}
        };
    }

    componentDidMount() {
        const service = new RepositoryService();

        service.getDataModel(this.props.match.params.specId).then(response => {
            this.setState({ dataModel: response.data }
            )
        });
    }

    generateEntitiesDom() {
        if (this.state.dataModel.entities) {
            return this.state.dataModel.entities.map(entity => <Entity entity={entity} />);
        } else {
            return "";
        }
    }

    render() {
        return (
            <div>
                <b>Data Model Specification {this.state.dataModel.specName}</b>
                {this.generateEntitiesDom()}
            </div>
        )
    }
}
