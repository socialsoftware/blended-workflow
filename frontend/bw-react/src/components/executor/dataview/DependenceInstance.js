import React from 'react'
import { Tab } from '../../util/Tab'
import { RepositoryService } from '../../../services/RepositoryService'
import { EntityInstanceLink } from './EntityInstanceLink'

export class DependenceInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            entityInstances: []
        };
    }

    componentDidMount() {
        const service = new RepositoryService();
        service.getEntityInstancesForDependence(this.props.entityInstance.externalId,this.props.dependence.extId).then(response => {
            this.setState({ 
                entityInstances: response.data 
            });
        });
    }

    render() {
    const dependenceEntityInstances = this.state.entityInstances.length === 0 ? 'NONE' : this.state.entityInstances.map(ei => <EntityInstanceLink key={ei.id} entityInstance={ei} />);
        return (
            <div>
                <Tab /><Tab />{this.props.dependence.path}: {dependenceEntityInstances}
            </div>
        )
    }
}