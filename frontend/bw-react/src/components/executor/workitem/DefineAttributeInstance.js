import React from 'react';
import { Tab } from '../../util/Tab';
import { AttributeInstance } from '../dataview/AttributeInstance';

export class DefineAttributeInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.renderAttribute = this.renderAttribute.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value}, function() {
            // change unit of work associated with this entity instance to be defined
        });
    }

    renderAttribute() {
        if (this.props.attributeInstance.toDefine === true) {
            return <div> <Tab />{this.props.attributeInstance.attribute.name}: <input type="text" value={this.state.value} onChange={this.handleChange} /> </div>;
        } else {
            return <AttributeInstance attributeInstance={this.props.attributeInstance}/>;
        }
    }

    render() {
        return ( 
            <div>
                {this.renderAttribute()}
            </div>
        )
    }
}