import React from 'react';
import { ArcherContainer } from 'react-archer';
import DependencyTreeNode from './DependencyTreeNode';
import { DependencyTreeRow } from './DependencyTreeRow';

export class DependencyTree extends React.Component {
    constructor(props) {
        super(props);

        this.displayRows = this.displayRows.bind(this);
        this.displayRootNode = this.displayRootNode.bind(this);
        this.hasMoreThanSevenNodesInARow = this.hasMoreThanSevenNodesInARow.bind(this);
    }

    displayRows() {
      return this.props.attributeInstances.map((depthLevelElements, rowIndex) => {
        return <DependencyTreeRow
                  key={rowIndex}
                  attributeInstances={depthLevelElements}/>
      });
    }

    displayRootNode() {
      const rootNodeStyle = {
        display: "flex",
        justifyContent: "center" 
      };

      return (
        <div style={rootNodeStyle}>
          <DependencyTreeNode
            isRoot={true}
            attributeInstance={this.props.rootAttributeInstance} 
            entityInstance={this.props.rootEntityInstance}/>
        </div>
      );
    }

    hasMoreThanSevenNodesInARow() {
      return this.props.attributeInstances.reduce((result, row) => result || row.length > 7, false);
    }

    render() {
      var widthValue = "";

      if (this.hasMoreThanSevenNodesInARow())
        widthValue = "fit-content";

      return (
        <div style={{width: widthValue, margin: "0 auto"}}>
          <ArcherContainer strokeColor="black" arrowLength="6" arrowThickness="5">
              {this.displayRootNode()}
              {this.displayRows()}
          </ArcherContainer>
        </div>
      );
    }
}