import React from 'react'

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
                Definition group
            </div>
        )
    }
}