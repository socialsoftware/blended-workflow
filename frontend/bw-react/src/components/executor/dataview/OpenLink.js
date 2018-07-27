import React from 'react'

export class OpenLink extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: true
        }

        this.handleOnClick = this.handleOnClick.bind(this);
    }

    handleOnClick() {
        this.setState({
            open: !this.state.open
        })
        this.props.onClick()
    }

    render() {
        return (
            <button onClick={this.handleOnClick}>{this.state.open ? 'Open' : 'Close'}</button>
        )
    }
}