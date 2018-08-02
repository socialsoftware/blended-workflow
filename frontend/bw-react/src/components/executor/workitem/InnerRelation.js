import React from 'react'
import { SelectEntityInstances } from './SelectEntityInstances'

export class InnerRelation extends React.Component {
    constructor(props) {
        super(props);

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(selected) {
        const result = this.props.innerRelation.entityInstanceSet.filter(ei => selected.includes(ei));
        this.props.onSelection(this.props.innerRelation.mulCondition, result);
    }

    render() {
        return (
            <div>
                <span>{this.props.innerRelation.targetEntity.name}({this.props.innerRelation.mulCondition.cardinality}): </span> 
                <SelectEntityInstances entityInstances={this.props.innerRelation.entityInstanceSet} mulCondition={this.props.innerRelation.mulCondition} onSubmit={this.handleSubmit}/>
            </div>
        )
    }
}