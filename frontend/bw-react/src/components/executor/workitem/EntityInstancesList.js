import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';
import EntityInstanceHover from '../dataview/EntityInstanceHover';
import { Button } from 'react-bootstrap';

export class EntityInstancesList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showToolTip: false,
            selectedToolTip: null
        };

        this.onMouseEnter = this.onMouseEnter.bind(this);
        this.onMouseLeave = this.onMouseLeave.bind(this);
    }

    onMouseEnter(e) {
        this.setState({
            showToolTip: true,
            selectedToolTip: this.props.allEntityInstances.find(ei => ei.id === e.target.value),
        });
    }

    onMouseLeave(e) {
        this.setState({
            showToolTip: false,
            selectedToolTip: null,
        });
    }

    render() {
        var buttonColor = "primary";

        if (this.props.entityInstancesState === "SKIPPED") 
            buttonColor = "warning"; 

        return this.props.entityInstances.map(ei => 
                <li key={ei.id}>
                    <div onMouseEnter={this.onMouseEnter} onMouseLeave={this.onMouseLeave} key={`${ei.id}--hover`} 
                        style={{display:'inline-block'}}>
                        <Button bsStyle={buttonColor} style={{margin: 2.5}} value={ei.id} onClick={this.props.handleClick}>
                            {`${ei.entity.name}[${ei.id}]`}
                        </Button>
                        <EntityInstanceHover show={this.state.showToolTip && ei === this.state.selectedToolTip} entityInstance={ei}/>
                    </div> 
                    <EntityInstanceLink isOnSelection={this.props.select} key={ei.id} entityInstance={ei}/>
                </li>
        ) 
    }
}