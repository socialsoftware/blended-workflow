import React from 'react'
import { OpenCloseButton } from './OpenCloseButton'
import { EntityInstance } from './EntityInstance';

export class EntityInstanceLink extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };

        this.openCloseLink = this.openCloseLink.bind(this);
    }

    openCloseLink() {
        this.setState({
            open: !this.state.open
        })
    }

    render() {
        return (
            <span>
                {this.props.entityInstance.entity.name} [{this.props.entityInstance.id}] <OpenCloseButton open={this.state.open} onClick={this.openCloseLink} />
                {this.state.open && <EntityInstance entityInstance={this.props.entityInstance} />} 
            </span>
        )
    }
}
