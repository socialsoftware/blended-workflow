import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';

export class SelectEntityInstances extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            select: false,
            selected: []
        };

        this.handleStartSelection = this.handleStartSelection.bind(this);
        this.handleFinishSelection = this.handleFinishSelection.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
        this.handleUnselect = this.handleUnselect.bind(this);
    }

    handleStartSelection() {
        this.setState({
            select: true
        });
    }

    handleFinishSelection() {        
        this.setState({
            select: false
        }, function() {
            this.props.onSubmit(this.state.selected);
        });
    }

    handleSelect(event) {
        if (this.props.mulCondition.max > this.state.selected.length) {
            const element = this.props.entityInstances.find(ei => ei.id === event.target.value );
            const selected = this.state.selected;
            selected.push(element);
            this.setState({
                selected: selected
            })
        } 
    }

    handleUnselect(event) {
        const selected = this.state.selected.filter(ei => ei.id !== event.target.value);
        this.setState( {
            selected: selected
        });
    }

    render() {
        const notSelected = this.props.entityInstances.filter(ei => !this.state.selected.includes(ei));
        return (
            <span>
                <span>{!this.state.select && this.state.selected.map(ei => <EntityInstanceLink key={ei.id} entityInstance={ei}/>)} </span>
                <span>{!this.state.select && this.state.selected.length === 0 && '[undef]'} </span>
                <span>{!this.state.select && <button onClick={this.handleStartSelection} >Start Selection</button>}</span>

                <span>{this.state.select && this.state.selected.map(ei => <span key={ei.id} ><EntityInstanceLink key={ei.id} entityInstance={ei} /> <button value={ei.id} onClick={this.handleUnselect} >Unselect</button> </span>)}</span>
                <span>{this.state.select && notSelected.map(ei => <span key={ei.id} ><EntityInstanceLink key={ei.id} entityInstance={ei} /> <button value={ei.id} onClick={this.handleSelect} >Select</button> </span>)}</span>
                <span>{this.state.select && <button onClick={this.handleFinishSelection}>Finish Selection</button>}</span>
            </span>
        )
    }
} 