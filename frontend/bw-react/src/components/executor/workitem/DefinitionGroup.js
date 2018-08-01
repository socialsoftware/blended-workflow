import React from 'react'
import { EntityContext } from './EntityContext'
import { DefineEntity } from './DefineEntity'
import { DefineAttribute } from './DefineAttribute'
import { InnerRelation } from './InnerRelation'

export class DefinitionGroup extends React.Component {
    constructor(props) {
        super(props);

        if (this.props.definitionGroup.defEnt != null) {
            const entityInstance = {
                product: { productType: 'ENTITY' },
                externalId: this.props.id,
                id: this.props.id,
                path: this.props.definitionGroup.defEnt.path,
                value: ''
            };

            const productInstanceMap = new Map();
            productInstanceMap.set(this.props.definitionGroup.defEnt.path, entityInstance);

            this.state = {
                entityInstancesContext: [],
                productInstanceMap: productInstanceMap
            }

            this.updateInstance();
        } else {
            this.state = {
                entityInstancesContext: [],
                productInstanceMap: new Map()
            }
        }

        this.defineEntityContext = this.defineEntityContext.bind(this);
        this.defineAttribute = this.defineAttribute.bind(this);
        this.updateInstance = this.updateInstance.bind(this);
    }


    defineAttribute(path, value) {
        const attributeInstance = {
            product: { productType: 'ATTRIBUTE' },
            externalId: this.props.id,
            id: this.props.id,
            path: path,
            value: value
        }

        const productInstanceMap = this.state.productInstanceMap;
        productInstanceMap.set(path, attributeInstance);

        this.setState({
            productInstanceMap: productInstanceMap
        }, function() {
            this.updateInstance();
        });
    }

    defineEntityContext(entityInstancesContext) {
        alert(entityInstancesContext);
        this.setState({
            entityInstancesContext: entityInstancesContext
        }, function() {
            this.updateInstance();
        })
    }

    updateInstance() {
        this.props.updateInstance(this.props.id, [{ 
            entityInstanceContextSet: this.state.entityInstancesContext, 
            productInstanceSet: Array.from(this.state.productInstanceMap.values()), 
            innerRelationInstanceSet: [] }]);
    }
 
    render() {
        return ( 
            <div>
                {this.props.definitionGroup.entityContextSet && this.props.definitionGroup.entityContextSet.map(ec => <EntityContext key={ec.index} entityContext={ec} onSelection={this.defineEntityContext} />)}
                {this.props.definitionGroup.defEnt && <DefineEntity key={this.props.definitionGroup.defEnt.index} id={this.props.id} defEntity={this.props.definitionGroup.defEnt} />}
                {this.props.definitionGroup.defAtts && this.props.definitionGroup.defAtts.map(a => <DefineAttribute key={a.index} onChange={this.defineAttribute} defAttribute={a} />)}
                {this.props.definitionGroup.innerRelationSet && this.props.definitionGroup.innerRelationSet.map(ir => <InnerRelation key={ir.mulCondition.externalId} innerRelation={ir} />)}
            </div>
        )
    }
}