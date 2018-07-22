import React from 'react';
import { Attribute } from './Attribute'

export const Entity = (props) => (
    <div>
        <br />
        Entity {props.entity.name} {props.entity.mandatory ? "mandatory" : ""} {props.entity.exists ? "exists" : ""} 
        <br /> {'{'}
        {props.entity.attributes.map(a => <Attribute key={a.extId} attribute={a} />)}
        {'}'}
    </div >
)

