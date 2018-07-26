import React from 'react'
import { ProductInstance } from './ProductInstance'
import { MulCondition } from './MulCondition'

export class InnerRelation extends React.Component {
    render() {
        return (
            <div>
                Inner Relation
                SourceEntity: {this.props.innerRelation.sourceEntity.name}
                TargetEntity: {this.props.innerRelation.targetEntity.name}
                <MulCondition mulCondition={this.props.innerRelation.mulCondition.rolePath} />
                {this.props.innerRelation.productInstanceSet.map(pi => <ProductInstance key={pi.id} productInstance={pi} />)}
            </div>
        )
    }
}