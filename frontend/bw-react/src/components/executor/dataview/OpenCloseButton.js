import React from 'react';
import { Button } from 'react-bootstrap';

export class OpenCloseButton extends React.Component {
    constructor(props) {
        super(props);

        this.handleOnClick = this.handleOnClick.bind(this);
    }

    handleOnClick() {
        this.props.onClick();
    }

    render() {
        return (
            <Button bsStyle="primary" onClick={this.handleOnClick}>{!this.props.open ? 'Open' : 'Close'}</Button>
        );
    }
}