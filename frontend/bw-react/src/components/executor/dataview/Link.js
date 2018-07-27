import React from 'react'
import { RepositoryService } from '../../../services/RepositoryService'
import { Tab } from '../../util/Tab'
import { OpenLink } from './OpenLink'
import { EntityInstance } from './EntityInstance';

export class Link extends React.Component {
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
        service.getEntityInstanceByExternalId(this.props.link.entityInstance.externalId).then(response => {
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
            <div>
                <Tab />{this.props.link.mulCondition.rolename}: {this.props.link.mulCondition.targetEntity.name} [{this.props.link.entityInstance == null ? 'UNDEF' : this.props.link.entityInstance.id}]
                {this.props.link.entityInstance && <OpenLink onClick={this.openCloseLink} />}
                <span>{this.state.open && <EntityInstance entityInstance={this.state.entityInstance} />}</span>
            </div>
        )
    }
}
