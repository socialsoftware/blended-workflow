import React from 'react';
import EntityInstanceLink from './EntityInstanceLink';

export class SelectEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            select: false,
            selected: {}
        };

        this.handleStartSelection = this.handleStartSelection.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
    }

    handleStartSelection() {
        this.setState({
            select: true,
            selected: {}
        });
    }

    handleSelect(event) {
        const element = this.props.entityInstances.find(ei => ei.id === event.target.value);
        this.setState({
            select: false,
            selected: element
        }, function() {
            this.props.onSelection(this.state.selected);
        });
    }

    render() {
        const notSelected = this.props.entityInstances.filter(ei => ei.id !== this.state.selected.id);
        return (
            <span>
                <span>{this.state.selected.id && <EntityInstanceLink entityInstance={this.state.selected}/>} </span>
                <span>{!this.state.select && notSelected.length > 0 && <button onClick={this.handleStartSelection}>Start Selection</button>}</span>
                <span>{this.state.select && notSelected.map(ei => <span key={ei.id} ><EntityInstanceLink key={ei.id} entityInstance={ei} /> <button value={ei.id} onClick={this.handleSelect} >Select</button> </span>)}</span>
            </span>
        )
    }
} 