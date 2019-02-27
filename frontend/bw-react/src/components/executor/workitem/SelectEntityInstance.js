import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';
import { Button } from 'react-bootstrap';

export class SelectEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            select: false,
            selected: {}
        };

        this.handleStartSelection = this.handleStartSelection.bind(this);
        this.handleCloseSelection = this.handleCloseSelection.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
    }

    handleStartSelection() {
        this.setState({
            select: true,
            selected: {}
        });
    }

    handleCloseSelection() {
        this.setState({
            select: false,
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
                <span>{
                    !this.state.select && notSelected.length > 0
                        ? <button onClick={this.handleStartSelection}>Start Selection</button>
                        : <button onClick={this.handleCloseSelection}>Close Selection</button>
                }</span>
                <ul>{this.state.select && notSelected.map(ei => <li key={ei.id}>
                    <Button bsStyle="primary" value={ei.id} onClick={this.handleSelect}>{
                        `${ei.entity.name}[${ei.id}]`
                    }</Button> <EntityInstanceLink key={ei.id} entityInstance={ei} />
                </li>)}</ul>
            </span>
        )
    }
} 