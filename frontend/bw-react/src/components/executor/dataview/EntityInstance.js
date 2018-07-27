import React from 'react'
import { AttributeInstance } from './AttributeInstance'
import { Link } from './Link'

export class EntityInstance extends React.Component {
    render() {
        return (
            <div>
                {this.props.entityInstance.entity.name} [{this.props.entityInstance.externalId == null ? 'UNDEF' : this.props.entityInstance.id}] <br/>
                {this.props.entityInstance.attributes.map(att => <AttributeInstance key={att.attribute.name} attributeInstance={att} />)}
                {this.props.entityInstance.links.map(link => <Link key={link.mulCondition.externalId} link={link} />)}
            </div>
        )
    }
}
