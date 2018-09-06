import React from 'react';

export class DeleteInstance extends React.Component {
    constructor(props) {
        super(props);

        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        this.props.onClick(this.props.name);
    }

    render() {
       return (
           <div>
                <span>{this.props.name} </span><button onClick={this.handleClick}>delete</button>
            </div>
       );
    }
}
