import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';
import { Button } from 'react-bootstrap';

export class SelectEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            select: false,
            selected: (this.state || {}).selected || this.props.entityInstances[ 0 ],
        };

        this.handleStartSelection = this.handleStartSelection.bind(this);
        this.handleSelect = this.handleSelect.bind(this);

        this.handleSelectOption = this.handleSelectOption.bind(this);
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

    /*render() {
        const notSelected = this.props.entityInstances.filter(ei => ei.id !== this.state.selected.id);
        return (
            <span>
                <span>{!this.state.select && notSelected.length > 0 && <button onClick={this.handleStartSelection}>Start Selection</button>}</span>
                <span>{this.state.select && notSelected.map(ei => <span key={ei.id} ><EntityInstanceLink key={ei.id} entityInstance={ei} /> <Button bsStyle="primary" value={ei.id} onClick={this.handleSelect} >Select</Button> </span>)}</span>
            </span>
        )
    }*/

    handleSelectOption( e ) {
        const index = e.nativeEvent.target.selectedIndex;

        this.setState( {
            select: true,
            selected: this.props.entityInstances.find(
                ei => ei.id === e.nativeEvent.target[ index ].value
            ),
        }/*, function() {
            this.props.onSelection( this.state.selected );
        }*/ );
    }

    render() {
        return (
            <span>
                <select value={( this.state.selected || {}).id} onChange={this.handleSelectOption}>{
                    this.props.entityInstances
                        //.filter( ei => ei.id !== this.state.selected.id )
                        .map( (ei, i) => <option key={ei.id} value={ei.id}>{ei.id}</option> )
                }</select>
                {
                    this.state.selected && <EntityInstanceLink key={this.state.selected.id} entityInstance={this.state.selected} />
                }
            </span>
        );
    }
} 