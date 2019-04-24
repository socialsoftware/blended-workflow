import React from 'react';
import EntityInstanceLink from '../dataview/EntityInstanceLink';
import EntityInstanceHover from '../dataview/EntityInstanceHover';

export class SelectMulEntityInstances extends React.Component {
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
        this.onMouseEnter = this.onMouseEnter.bind(this);
        this.onMouseLeave = this.onMouseLeave.bind(this);
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
        return (
            <span>
                <span>{!this.state.select && this.state.selected.map(ei => <EntityInstanceLink key={ei.id} entityInstance={ei}/>)}</span>

                <span>{!this.state.select && this.state.selected.length === 0 && '[undef]'} </span>

                <span>{!this.state.select
                    ? <button onClick={this.handleStartSelection}>Start Selection</button>
                    : <button onClick={this.handleFinishSelection}>Finish Selection</button>
                }</span>
            
                <ul>{this.state.select
                    &&
                    this.props.entityInstances.map(ei => {
                        const eiIsSelected = this.state.selected.includes( ei );

                        return <li key={ei.id}>
                            <div onMouseEnter={this.onMouseEnter} onMouseLeave={this.onMouseLeave} key={`${ei.id}--hover`} style={{display:'inline-block'}}>
                                <label>
                                    <input type="checkbox"
                                        value={ei.id}
                                        checked={eiIsSelected}
                                        onChange={eiIsSelected ? this.handleUnselect : this.handleSelect}
                                    />
                                    <span style={{"font-weight":"normal","text-decoration":"underline dotted"}}>{`${ei.entity.name}[${ei.id}]`}</span>
                                </label><EntityInstanceHover show={this.state.showToolTip && ei === this.state.selectedToolTip} entityInstance={ei} />
                            </div> <EntityInstanceLink key={ei.id} isOnSelection={this.state.select} entityInstance={ei} />
                        </li>
                    })
                }</ul>
            </span>
        )
    }
} 