import React from 'react';
import { AttributeInstance } from './AttributeInstance';
import { MulLink } from './MulLink';

export class EntityInstance extends React.Component {
    render() {
        var textColor = "";

        if (this.props.entityInstance.state === "SKIPPED")
            textColor = "#eb8318";     

        return (
            <div>
                <span style={{color: textColor}}>{this.props.entityInstance.entity.name}[{this.props.entityInstance.id}] - {this.props.entityInstance.state}</span><br/>
                {this.props.entityInstance.attributeInstances.map(att => <AttributeInstance key={att.attribute.name} attributeInstance={att} />)}
                {this.props.entityInstance.links.map(link => <MulLink key={link.mulCondition.externalId} link={link} />)}
            </div>
        )
    }
}
