import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';

export class DataModel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            specId: props.match.params.specId.substring(1),
            entities: []
        };
    }

    componentDidMount() {
        const service = new RepositoryService();
        service.getEntities(this.state.specId).then(response => {
            this.setState({ entities: response.data }
            )
        });
    }

    render() {
        const entities = this.state.entities.map(entity =>  <div><br />Entity{entity.name}</div>);

        return (
            <div>
                <b>Data Model Specification {this.state.specId}</b>
                {entities}
            </div>
        )
    }
}
