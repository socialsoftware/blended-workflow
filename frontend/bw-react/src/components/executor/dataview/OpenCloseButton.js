import React from 'react';
import Button from '@material-ui/core/Button';

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
            <Button variant="contained" color="primary" onClick={this.handleOnClick}>{!this.props.open ? 'Open' : 'Close'}</Button>
        );
    }
}