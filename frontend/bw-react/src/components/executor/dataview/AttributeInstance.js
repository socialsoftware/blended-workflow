import React from 'react'

export class AttributeInstance extends React.Component {
    render() {
        return (
            <div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{this.props.attributeInstance.attribute.name} is {this.props.attributeInstance.externalId == null ? 'UNDEF' : this.props.attributeInstance.value} of type {this.props.attributeInstance.attribute.type} <br/>
            </div>
        )
    }
}
