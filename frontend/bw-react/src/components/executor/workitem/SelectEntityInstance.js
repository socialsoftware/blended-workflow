import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';
import { Button } from 'react-bootstrap';

export class SelectEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            select: false,
            selected: props.currInstance || this.props.entityInstances[ 0 ],
        };

        this.handleSelectOption = this.handleSelectOption.bind(this);
    }

    handleSelectOption( e ) {
        const index = e.nativeEvent.target.selectedIndex;

        if ( index === 0 ) {
        	// Default
        	return;
        }

        this.setState( {
            select: true,
            selected: this.props.entityInstances.find(
                ei => ei.id === e.nativeEvent.target[ index ].value
            ),
        }, function() {
            this.props.onSelection( this.state.selected );
        } );
    }

    render() {
    	const options = [];

    	if ( this.props.entityInstances.length ) {
    		options.push( <option key={'--'} value={'--'}>{'--'}</option> );
    	}

    	[].push.apply( options, this.props.entityInstances
			.map( (ei, i) => <option key={ei.id} value={ei.id}>{ei.id}</option> )
        );

        return (
            <span>
                <select value={( this.state.selected || {} ).id} onChange={this.handleSelectOption}>{
                    options
                }</select>
                {
                    this.state.selected && <EntityInstanceLink key={this.state.selected.id} entityInstance={this.state.selected} />
                }
            </span>
        );
    }
} 