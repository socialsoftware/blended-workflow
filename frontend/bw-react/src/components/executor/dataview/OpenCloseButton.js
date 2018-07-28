import React from 'react'

export class OpenCloseButton extends React.Component {
    constructor(props) {
        super(props);

         this.handleOnClick = this.handleOnClick.bind(this);
    }

    handleOnClick() {
        this.props.onClick()
    }

    render() {
        return (
            <button onClick={this.handleOnClick}>{!this.props.open ? 'Open' : 'Close'}</button>
        )
    }
}