import React from 'react';

export class ActivityExecutor extends React.Component {
    render() {
        return (
            <div> 
               <h5>Activity executor of {this.props.match.params.name}</h5>
            </div>
        )
    }
} 