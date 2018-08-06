import React from 'react'
import { Tab } from '../../util/Tab';
import { DependenceInstance } from './DependenceInstance'

export class AttributeInstance extends React.Component {
    render() {
        const dependencies = this.props.attributeInstance.attribute.dependencies == null ? "" : this.props.attributeInstance.attribute.dependencies.map(d => <DependenceInstance key={d.extId} entityInstance={this.props.entityInstance} dependence={d}/>);
        return (
            <div>
                <Tab />{this.props.attributeInstance.attribute.name}: {this.props.attributeInstance.attribute.type}[{this.props.attributeInstance.value}] 
                {dependencies}
            </div>
        )
    }
}
