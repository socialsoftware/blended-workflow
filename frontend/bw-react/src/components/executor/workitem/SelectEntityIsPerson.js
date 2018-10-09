import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';
import { Button } from 'react-bootstrap';

export class SelectEntityIsPerson extends React.Component {
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
        const element = this.props.personContext.find(p => p.name === event.target.value);
        this.setState({
            select: false,
            selected: element
        }, function() {
            this.props.onSelection(this.state.selected);
        });
    }

    render() {
        const notSelected = this.props.personContext.filter(p => p.name !== this.state.selected.name);
        return (
            <span>
                <span>{!this.state.select && notSelected.length > 0 && <button onClick={this.handleStartSelection}>Start Selection</button>}</span>
                <span>{this.state.select && notSelected.map(p => 
                    <span key={p.name} > {p.name}
                        <Button bsStyle="primary" value={p.name} onClick={this.handleSelect} >Select</Button>
                        </span>)}
                </span>
            </span>
        )
    }
} 