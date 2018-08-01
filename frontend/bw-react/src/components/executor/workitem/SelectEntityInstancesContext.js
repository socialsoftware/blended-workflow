import React from 'react'
import { EntityInstanceLink } from '../dataview/EntityInstanceLink'

export class SelectEntityInstancesContext extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            selected: this.props.selectedEntityInstances
        };

        this.handleSelect = this.handleSelect.bind(this);
        this.handleUnselect = this.handleUnselect.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSelect(event) {
        if (this.props.entityContext.mulCondition.max > this.state.selected.length) {
            const element = this.props.entityContext.entityInstanceContextSet.find(eic => eic.index == event.target.value );
            const selected = this.state.selected;
            selected.push(element);
            this.setState({
                selected: selected
            })
        } 
    }

    handleUnselect(event) {
        const selected = this.state.selected.filter(eic => eic.index != event.target.value);
        this.setState( {
            selected: selected
        });
    }

    handleSubmit() {
        this.props.onSubmit(this.state.selected);
    }

    render() {
        const notSelected = this.props.entityContext.entityInstanceContextSet.filter(ei => !this.state.selected.includes(ei));
        return (
            <span>
                {this.state.selected.map(eic => <span><EntityInstanceLink entityInstance={eic.entityInstance} /> <button value={eic.index} onClick={this.handleUnselect} >Unselect</button> </span>)}
                {notSelected.map(eic => <span><EntityInstanceLink entityInstance={eic.entityInstance} /> <button value={eic.index} onClick={this.handleSelect} >Select</button> </span>)}
                <button onClick={this.handleSubmit}>Finish Selection</button>
            </span>
        )
    }
} 