import React from 'react';
import { AttributeInstance } from './AttributeInstance';
import { MulLink } from './MulLink';
import { SelectEntityInstance } from './SelectEntityInstance';

export class EntityInstance extends React.Component {

    associateToExistingEntity() {
        return this.props.entityInstance.externalId === null 
            && this.props.entityInstance.entityInstancesContext
            && this.props.entityInstance.entityInstancesContext.length !== 0
    }

    handleSelection(entityInstance) {
        // change the unit of work associated with this entity instance to be defined
    }

    render() {
        return (
            <div>
                {this.props.entityInstance.entity.name}[{this.props.entityInstance.id !== null ? this.props.entityInstance.id : 'undef'}] {this.associateToExistingEntity() && <SelectEntityInstance entityInstances={this.props.entityInstance.entityInstancesContext} onSelection={this.handleSelection}/>} <br/>
                {this.props.entityInstance.attributeInstances.map(att => <AttributeInstance key={att.attribute.name} entityInstance={this.props.entityInstance} attributeInstance={att} />)}
                {this.props.entityInstance.links.map(link => <MulLink key={link.mulCondition.externalId} link={link} />)}
            </div>
        )
    }
}
