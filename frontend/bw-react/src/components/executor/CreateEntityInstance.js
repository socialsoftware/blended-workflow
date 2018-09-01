import React from 'react';

export class CreateEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.handleOnClick = this.handleOnClick.bind(this);
    }

    handleOnClick() {
        this.props.onCreateEntityInstance(this.props.entityInstance);
    }

    render() {
        return (
            <button onClick={this.handleOnClick}>Create Other Instance</button>
        );
    } 
}