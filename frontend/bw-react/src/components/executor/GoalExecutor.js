import React from 'react';

export class GoalExecutor extends React.Component {
    render() {
        return (
            <div> 
               <h5>Goal executor of {this.props.match.params.name}</h5>
            </div>
        )
    }
} 