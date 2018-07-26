import React from 'react'

export class ProductInstance extends React.Component {
    render() {
        return (
            <div>ProductInstance - path: {this.props.productInstance.path}, value: {this.props.productInstance.value}</div>
        )
    }
}