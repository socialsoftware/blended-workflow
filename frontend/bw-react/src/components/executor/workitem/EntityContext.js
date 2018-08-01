import React from 'react'
import { EntityInstanceLink } from '../dataview/EntityInstanceLink'
import { SelectEntityInstancesContext } from './SelectEntityInstancesContext'

export class EntityContext extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            select: false,
            selectedEntityInstances: []
        };

        this.handleOnClick = this.handleOnClick.bind(this);
        this.handleOnSubmit = this.handleOnSubmit.bind(this);
    }

    handleOnClick() {
        this.setState({
            select: true
        });
    }

    handleOnSubmit(selected) {
        this.setState({
            select: false,
            selectedEntityInstances: selected
        }, function() {
            this.props.onSelection(this.state.selectedEntityInstances)
        });
    }

    render() {
        return ( 
            <div>
                <span>{this.props.entityContext.defEntityCondition.path}({this.props.entityContext.mulCondition.cardinality}) [{this.props.entityContext.mulCondition.min},{this.props.entityContext.mulCondition.max}] [{this.props.entityContext.mulCondition.sourceMin},{this.props.entityContext.mulCondition.sourceMax}]: </span>
                {!this.state.select && this.state.selectedEntityInstances.map(ec => <EntityInstanceLink key={ec.index} entityInstance={ec.entityInstance}/>)}
                {!this.state.select && this.state.selectedEntityInstances.length === 0 && '[undef] '}
                {!this.state.select && <button onClick={this.handleOnClick} >Start Selection</button>}
                {this.state.select && <SelectEntityInstancesContext entityContext={this.props.entityContext} selectedEntityInstances={this.state.selectedEntityInstances} onSubmit={this.handleOnSubmit}/>}    
                {this.props.entityContext.defPathConditionSet.map(dp => <div key={dp.path} defPath={dp.path}/>)}
            </div>
        )
    }
}