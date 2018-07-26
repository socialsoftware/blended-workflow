import React from 'react'
import { DefEntity } from './DefEntity'
import { DefAttribute } from './DefAttribute'
import { EntityContext } from './EntityContext'
import { DefinitionGroupInstance } from './DefinitionGroupInstance'
import { InnerRelation } from './InnerRelation'

export class DefinitionGroup extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            definitionGroup: props.definitionGroup
        }
    }
    
 
    render() {
        return ( 
            <div>
                Definition Group: 
                {this.state.definitionGroup.defProductConditionSet.defEnts.map(de => <DefEntity key={de.extId} defEntity={de} />)}
                {this.state.definitionGroup.defProductConditionSet.defAtts.map(da => <DefAttribute key={da.attributeExtId} defAttribute={da} />)}
                {this.state.definitionGroup.entityContextSet.map(ec => <EntityContext key={ec.index} entityContext={ec} />)}
                {this.state.definitionGroup.definitionGroupInstanceSet.map(dgi => <DefinitionGroupInstance definitionGroupInstance={dgi} />)}
                {this.state.definitionGroup.innerRelationSet.map(ir => <InnerRelation key={ir.mulCondition.externalId} innerRelation={ir} />)}
            </div>
        )
    }
}