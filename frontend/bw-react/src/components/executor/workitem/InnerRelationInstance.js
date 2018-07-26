import React from 'react'
import { ProductInstance } from './ProductInstance'
import { MulCondition } from './MulCondition'

export class InnerRelationInstance extends React.Component {
    render() {
        return (
            <div>
                Inner Relation Instance:
                <MulCondition mulCondition={this.props.innerRelationInstance.mulConditionDTO} />
                {this.props.innerRelationInstance.productInstanceSet.map(pi => <ProductInstance key={pi.id} productInstance={pi} />)}
            </div>
        )
    }
}