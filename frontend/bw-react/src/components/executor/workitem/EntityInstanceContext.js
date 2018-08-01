import React from 'react'
import { ProductInstance } from './ProductInstance'

export class EntityInstanceContext extends React.Component {
    render() {
        return (
            <div>
                <ProductInstance productInstance={this.props.entityInstanceContext.entityInstance} />
            </div>
        )
    }
}