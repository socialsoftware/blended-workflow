import React from 'react'
import { ProductInstance } from './ProductInstance'

export class InnerRelationInstance extends React.Component {
    render() {
        return (
            <div>
                Inner Relation Instance:
                {this.props.innerRelationInstance.mulConditionDTO.path} />
                {this.props.innerRelationInstance.productInstanceSet.map(pi => <ProductInstance key={pi.id} productInstance={pi} />)}
            </div>
        )
    }
}