import React from 'react'
import { RepositoryService } from '../../services/RepositoryService'
import { EntityInstance } from './dataview/EntityInstance'

export class DataView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            mandatoryEntityInstance: {}
        };

        this.renderMandatoryEntityInstance = this.renderMandatoryEntityInstance.bind(this);
     }

    componentDidMount() {
        const service = new RepositoryService();

        service.getMandatoryEntityInstance(this.props.match.params.specId, this.props.match.params.name).then(response => {
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
                <h5>Data Model of instance {this.props.match.params.name} of specification {this.props.match.params.specId} </h5>
                {this.renderMandatoryEntityInstance()}
            </div>
        )
    }
} 