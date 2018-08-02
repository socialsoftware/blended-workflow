import React from 'react'
import { SelectEntityInstances } from './SelectEntityInstances'

export class EntityContext extends React.Component {
    constructor(props) {
        super(props);

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(selected) {
        const result = this.props.entityContext.entityInstanceContextSet.filter(eic => selected.includes(eic.entityInstance));
        this.props.onSelection(result);
    }

    render() {
        const entityInstances = this.props.entityContext.entityInstanceContextSet.map(eic => eic.entityInstance);
        return ( 
            <div>
                <span>{this.props.entityContext.defEntityCondition.path}({this.props.entityContext.mulCondition.cardinality}) [{this.props.entityContext.mulCondition.min},{this.props.entityContext.mulCondition.max}] [{this.props.entityContext.mulCondition.sourceMin},{this.props.entityContext.mulCondition.sourceMax}]: </span>
                <SelectEntityInstances entityInstances={entityInstances} mulCondition={this.props.entityContext.mulCondition} onSubmit={this.handleSubmit}/>    
                {this.props.entityContext.defPathConditionSet.map(dp => <div key={dp.path}>{dp.path}</div>)}
            </div>
        )
    }
}