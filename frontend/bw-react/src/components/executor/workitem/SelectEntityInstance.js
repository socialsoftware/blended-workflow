import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';
import EntityInstanceHover from '../dataview/EntityInstanceHover';
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
        this.onMouseEnter = this.onMouseEnter.bind(this);
        this.onMouseLeave = this.onMouseLeave.bind(this);
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

    onMouseEnter( e ) {
        this.setState( {
            showToolTip: true,
            selectedToolTip: this.props.entityInstances.find(ei => ei.id === e.target.value),
        } );
    }

    onMouseLeave( e ) {
        this.setState( {
            showToolTip: false,
            selectedToolTip: null,
        } );
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
                    <div onMouseEnter={this.onMouseEnter} onMouseLeave={this.onMouseLeave} key={`${ei.id}--hover`} style={{display:'inline-block'}}>
                        <Button bsStyle="primary" value={ei.id} onClick={this.handleSelect}>{
                            `${ei.entity.name}[${ei.id}]`
                        }</Button><EntityInstanceHover show={this.state.showToolTip && ei === this.state.selectedToolTip} entityInstance={ei} />
                    </div> <EntityInstanceLink isOnSelection={this.state.select} key={ei.id} entityInstance={ei} />
                </li>)}</ul>
            </span>
        )
    }
} 