import React from 'react'
import { DefPath } from './DefPath'
import { EntityInstanceContext } from './EntityInstanceContext'
import { MulCondition } from './MulCondition'

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
                Entity Context: <span>Entity {this.props.entityContext.defEntityCondition.path}</span>
                <MulCondition mulCondition={this.props.entityContext.mulCondition} />
                {this.props.entityContext.defPathConditionSet.map(dp => <DefPath key={dp.path} defPath={dp}/>)}
                {this.renderEntityInstanceContext()}
            </div>
        )
    }
}