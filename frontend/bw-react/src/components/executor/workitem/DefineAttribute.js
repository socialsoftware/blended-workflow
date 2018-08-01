import React from 'react'
import { Tab } from '../../util/Tab'

export class DefineAttribute extends React.Component {
    constructor(props) {
        super(props);

        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value}, function() {
            this.props.onChange(this.props.defAttribute.path, this.state.value);
        });
    }

    render() {
        return ( 
            <div>
                <Tab />{this.props.defAttribute.name}: <input type="text" value={this.state.value} onChange={this.handleChange} />
            </div>
        )
    }
}