import React from 'react';
import { Tab } from '../../util/Tab';
import { DependenceInstance } from './DependenceInstance';

export class AttributeInstance extends React.Component {
    render() {
        const dependencies = this.props.attributeInstance.dependenceInstances == null ? "" : this.props.attributeInstance.dependenceInstances.map(di => <DependenceInstance key={di.dependence.extId} dependence={di}/>);
        return (
            <div>
                <Tab />{this.props.attributeInstance.attribute.name}: {this.props.attributeInstance.attribute.type}[{this.props.attributeInstance.value}] 
                {dependencies}
            </div>
        )
    }
}
