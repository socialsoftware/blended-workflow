import React from 'react'

export class EntityInstance extends React.Component {
    render() {
        return (
            <div>
                {this.props.entityInstance.entity.name}
            </div>
        )
    }
}
