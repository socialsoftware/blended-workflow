import React from 'react'

export class OpenWorkItem extends React.Component {
    constructor(props) {
        super(props);
        
        this.handleClick = this.handleClick.bind(this);
    }
    
    handleClick() {
        this.props.onClick(this.props.workItem);
    }

    render() {
        return ( 
            <div> Work Item: {this.props.workItem.name} <button onClick={this.handleClick}>Open</button> </div>
        )
    }
}