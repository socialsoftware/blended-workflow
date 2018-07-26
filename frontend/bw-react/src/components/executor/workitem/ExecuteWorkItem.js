import React from 'react'
import { DefinitionGroup } from './DefinitionGroup'

export class ExecuteWorkItem extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            workitem: props.workitem
        }
        
        this.handleClose = this.handleClose.bind(this);
        this.handleExecute = this.handleExecute.bind(this);
    }
    
    handleClose() {
        this.props.onClose();
    }

    handleExecute() {
        this.props.onExecute(this.state.workitem);
    }

    render() {
        return ( 
            <div>
                <h5>Execute Workitem {this.props.workItem.name}</h5>
                {this.props.workItem.definitionGroupSet.map((dg,index) => <DefinitionGroup key={index} definitionGroup={dg}/>)}
                <div><button onClick={this.handleClose}>Close</button></div>
                <div><button onClick={this.handleExecute}>Execute</button></div>
            </div>
        )
    }
}