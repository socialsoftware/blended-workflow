import React from 'react'
import { AttributeInstance } from './AttributeInstance'
import { MulLink } from './MulLink'

export class EntityInstance extends React.Component {
    render() {
        return (
            <div>
                {this.props.entityInstance.entity.name}[{this.props.entityInstance.externalId == null ? 'undef' : this.props.entityInstance.id}] <br/>
                {this.props.entityInstance.attributes.map(att => <AttributeInstance key={att.attribute.name} entityInstance={this.props.entityInstance} attributeInstance={att} />)}
                {this.props.entityInstance.links.map(link => <MulLink key={link.mulCondition.externalId} link={link} />)}
            </div>
        )
    }
}
