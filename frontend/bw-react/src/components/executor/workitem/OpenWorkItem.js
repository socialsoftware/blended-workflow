import React from 'react';
import { Button } from 'react-bootstrap';

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
            <div> {this.props.workItem.name} <Button bsStyle="primary" onClick={this.handleClick}>Open</Button> </div>
        )
    }
}