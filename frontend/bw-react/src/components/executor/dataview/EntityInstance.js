import React from 'react';
import { AttributeInstance } from './AttributeInstance';
import { MulLink } from './MulLink';

export class EntityInstance extends React.Component {
    render() {
        return (
            <div>
                {this.props.entityInstance.entity.name}[{this.props.entityInstance.id}] <br/>
                {this.props.entityInstance.attributeInstances.map(att => <AttributeInstance key={att.attribute.name} attributeInstance={att} />)}
                {this.props.entityInstance.links.map(link => <MulLink isOnModal={this.props.isOnModal} key={link.mulCondition.externalId} link={link} />)}
            </div>
        )
    }
}
