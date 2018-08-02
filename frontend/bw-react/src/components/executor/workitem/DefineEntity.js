import React from 'react'

export class DefineEntity extends React.Component {
    render() {
        return ( 
            <div>
                {this.props.defEntity.entityName}[{this.props.id}]
            </div>
        )
    }
}