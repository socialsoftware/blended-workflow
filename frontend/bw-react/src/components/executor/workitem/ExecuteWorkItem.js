import React from 'react'
import { DefinitionGroup } from './DefinitionGroup'

export class ExecuteWorkItem extends React.Component {
    constructor(props) {
        super(props);

        const defGroups = this.props.workItem.definitionGroupSet.map((dg,index) => { return { key: -index, value: dg} });
        const defGroupMap = new Map(defGroups.map(dg => [dg.key, dg.value]));

        this.state = {
            workItem: this.props.workItem,
            defGroupMap: defGroupMap,
        }
        
        this.handleClose = this.handleClose.bind(this);
        this.handleExecute = this.handleExecute.bind(this);
        this.updateInstance = this.updateInstance.bind(this);
    }

    handleClose() {
        this.props.onClose();
    }

    handleExecute() {
        const workItem = this.state.workItem;
        workItem.definitionGroupSet = Array.from(this.state.defGroupMap.values());

        this.props.onExecute(workItem);
    }

    updateInstance(key, definitionGroupInstance) {
        const definitionGroup = this.state.defGroupMap.get(key);
        definitionGroup.definitionGroupInstanceSet = definitionGroupInstance;
        this.setState({
            defGroupMap: this.state.defGroupMap.set(key, definitionGroup)
        })
    }

    render() {
        return ( 
            <div>
                <h5>Execute Workitem {this.props.workItem.name}</h5>
                {Array.from(this.state.defGroupMap).map(dg => <DefinitionGroup key={dg[0]} id={dg[0]} updateInstance={this.updateInstance} definitionGroup={dg[1]}/>)}
                <div><button onClick={this.handleClose}>Close</button> <button onClick={this.handleExecute}>Execute</button></div>
            </div>
        )
    }
}