import React from 'react'
import { RepositoryService } from '../../../services/RepositoryService'
import { OpenCloseButton } from './OpenCloseButton'
import { EntityInstance } from './EntityInstance';

export class EntityInstanceLink extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };

        this.openCloseLink = this.openCloseLink.bind(this);
        this.getEntityInstanceByExternalId = this.getEntityInstanceByExternalId.bind(this)
    }

      getEntityInstanceByExternalId() {
        const service = new RepositoryService();
        service.getEntityInstanceByExternalId(this.props.entityInstance.externalId).then(response => {
            this.setState({ 
                open: true,
                entityInstance: response.data 
            });
        });
    }

    openCloseLink() {
        if (this.state.open) {
            this.setState({
                open: false
            })
        } else {
            this.getEntityInstanceByExternalId();
        }
    }

    render() {
        return (
            <span>
                {this.props.entityInstance.entity.name}[{this.props.entityInstance.id}]
                {this.props.entityInstance.externalId !== null && <span> <OpenCloseButton open={this.state.open} onClick={this.openCloseLink} /></span>}
                {this.state.open && <EntityInstance entityInstance={this.state.entityInstance} />} 
            </span>
        )
    }
}
