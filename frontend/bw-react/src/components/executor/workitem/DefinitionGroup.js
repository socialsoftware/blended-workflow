import React from 'react'
import { EntityContext } from './EntityContext'
import { DefineEntity } from './DefineEntity'
import { DefineAttribute } from './DefineAttribute'
import { InnerRelation } from './InnerRelation'

export class DefinitionGroup extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            definitionGroupInstanceMap: new Map()
        }

        if (this.props.definitionGroup.defEnt != null) {
            const definitionGroupInstanceMap = this.state.definitionGroupInstanceMap;
            const definitionGroupInstance = {
                        entityInstanceContextSet : [],
                        productInstanceSet: [{
                            product: { productType: 'ENTITY' },
                            externalId: this.props.id,
                            id: this.props.id,
                            path: this.props.definitionGroup.defEnt.path,
                            value: ''
                        }],
                        innerRelationInstanceSet: []
                    };
            definitionGroupInstanceMap.set(this.props.definitionGroup.defEnt.path, definitionGroupInstance);

            this.state = {
                definitionGroupInstanceMap: definitionGroupInstanceMap
            }
        };

        this.props.updateInstance(this.props.id,Array.from(this.state.definitionGroupInstanceMap.values()));
    }


    defineAttribute() {

    }
 
    render() {
        return ( 
            <div>
                {this.props.definitionGroup.entityContextSet && this.props.definitionGroup.entityContextSet.map(ec => <EntityContext key={ec.index} entityContext={ec} />)}
                {this.props.definitionGroup.defEnt && <DefineEntity key={this.props.definitionGroup.defEnt.index} id={this.props.id} defEntity={this.props.definitionGroup.defEnt} />}
                {this.props.definitionGroup.defAtts && this.props.definitionGroup.defAtts.map(a => <DefineAttribute key={a.index} defAttribute={a} />)}
                {this.props.definitionGroup.innerRelationSet && this.props.definitionGroup.innerRelationSet.map(ir => <InnerRelation key={ir.mulCondition.externalId} innerRelation={ir} />)}
            </div>
        )
    }
}