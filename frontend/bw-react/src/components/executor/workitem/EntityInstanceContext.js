import React from 'react'
import { ProductInstance } from './ProductInstance'
import { MulCondition } from './MulCondition'

export class EntityInstanceContext extends React.Component {
    render() {
        return (
            <div>
                Entity Instance Context
                <ProductInstance productInstance={this.props.entityInstanceContext.entityInstance} />
                <MulCondition mulCondition={this.props.entityInstanceContext.mulConditionDTO} />
                <div>pathValueSet: {this.props.entityInstanceContext.pathValueSet.map(v => <ProductInstance key={v.externalId} productInstance={v} />)}</div>
                <div>pathValues: {this.props.entityInstanceContext.pathValues}</div>
            </div>
        )
    }
}