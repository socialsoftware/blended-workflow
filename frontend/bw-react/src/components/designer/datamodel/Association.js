import React from 'react'
import { Tab } from '../../util/Tab'
export const Association = (props) => (
    <div>
        <br />
        Association {props.association.name} {'{'}<br />
        <Tab />{props.association.entOneName} with {props.association.rolenameOne} {'('}{props.association.cardinalityOne}{')'}
        <br />
        <Tab />{props.association.entTwoName} with {props.association.rolenameTwo} {'('}{props.association.cardinalityTwo}{')'}
        <br />{'}'}
    </div> 
)