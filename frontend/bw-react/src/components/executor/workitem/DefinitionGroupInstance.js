import React from 'react'
import { EntityInstanceContext } from './EntityInstanceContext';
import { ProductInstance } from './ProductInstance';
import { InnerRelationInstance } from './InnerRelationInstance';

export class DefinitionGroupInstance extends React.Component {
    render() {
        alert("definition group instance");
        return (
            <div>
                Definition Group Instance
                {this.props.definitionGroupInstance.entityInstanceContextSet.map(eic => <EntityInstanceContext key={eic.index} entityInstanceContext={eic} />)}
                {this.props.definitionGroupInstance.productInstanceSet.map(pi => <ProductInstance key={pi.index} productInstance={pi} />)}
                {this.props.definitionGroupInstance.innerRelationInstanceSet.map(iri => <InnerRelationInstance key={iri.index} innerRelationInstance={iri} />)}
            </div>
        )
    }
}