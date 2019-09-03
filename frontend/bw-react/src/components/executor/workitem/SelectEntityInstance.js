import React from 'react';
import { EntityInstancesList } from './EntityInstancesList';

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
        const notSelectedDefined = this.props.entityInstances.filter(ei => (ei.id !== this.state.selected.id) && (ei.state === "DEFINED"));
        const notSelectedSkipped = this.props.entityInstances.filter(ei => (ei.id !== this.state.selected.id) && (ei.state === "SKIPPED"));

        return (
            <span>
                <span>
                    {!this.state.select && (notSelectedDefined.length > 0 || notSelectedSkipped.length > 0)
                            ? <button onClick={this.handleStartSelection}>Start Selection</button>
                            : <button onClick={this.handleCloseSelection}>Close Selection</button>
                    }
                </span>
                {this.state.select && 
                    <ul>
                        <EntityInstancesList allEntityInstances={this.props.entityInstances} entityInstances={notSelectedDefined} 
                            entityInstancesState="DEFINED" select={this.state.select} handleClick={this.handleSelect}/>
                        <EntityInstancesList allEntityInstances={this.props.entityInstances} entityInstances={notSelectedSkipped} 
                            entityInstancesState="SKIPPED" select={this.state.select} handleClick={this.handleSelect}/>
                    </ul>
                }
            </span>
        )
    }
} 