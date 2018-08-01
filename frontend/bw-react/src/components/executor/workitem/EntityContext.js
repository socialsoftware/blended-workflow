import React from 'react'
import { DefPath } from './DefPath'
import { EntityInstanceContext } from './EntityInstanceContext'

export class EntityContext extends React.Component {

    renderEntityInstanceContext() {
       if (this.props.entityContext.entityInstanceContextSet) {
         return this.props.entityContext.entityInstanceContextSet.map(eci => <EntityInstanceContext key={eci.index} entityInstanceContext={eci} />)
        } else {
            return "";
        }
    }

    render() {
        return ( 
            <div>
                <span>{this.props.entityContext.defEntityCondition.path} ({this.props.entityContext.mulCondition.cardinality})</span>
                {this.props.entityContext.defPathConditionSet.map(dp => <DefPath key={dp.path} defPath={dp}/>)}
                {this.renderEntityInstanceContext()}
            </div>
        )
    }
}