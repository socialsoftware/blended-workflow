import React from 'react'
import { Tab } from '../../util/Tab';

export class AttributeInstance extends React.Component {
    render() {
        return (
            <div>
                <Tab />{this.props.attributeInstance.attribute.name}: {this.props.attributeInstance.attribute.type} [{this.props.attributeInstance.externalId == null ? 'UNDEF' : this.props.attributeInstance.value}] <br/>
            </div>
        )
    }
}
