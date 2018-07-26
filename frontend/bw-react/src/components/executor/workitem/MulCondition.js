import React from 'react'

export class MulCondition extends React.Component {
    render() {
        return (
           <span>Mul({this.props.mulCondition.rolePath}, {this.props.mulCondition.cardinality})</span>
        )
    }
}