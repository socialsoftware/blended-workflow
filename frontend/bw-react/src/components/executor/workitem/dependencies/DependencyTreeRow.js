import React from 'react';
import DependencyTreeNode from './DependencyTreeNode';

export class DependencyTreeRow extends React.Component {
    constructor(props) {
        super(props);

        this.displayNodesInCurrentRow = this.displayNodesInCurrentRow.bind(this);
    }

    displayNodesInCurrentRow() {
        return this.props.attributeInstances.map(ai => {
            return <DependencyTreeNode
                        key={ai.externalId}
                        isRoot={false}
                        attributeInstance={ai}/>
        });
    }

    render() {
        const rowStyle = {
            margin: "100px 0 0",
            display: "flex",
            justifyContent: "space-around"
        }; 

        return (
            <div style={rowStyle}>
                {this.displayNodesInCurrentRow()}
            </div>
        );
    }
}