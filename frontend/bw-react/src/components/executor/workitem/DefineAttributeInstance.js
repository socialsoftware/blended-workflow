import React from 'react';
import { connect } from 'react-redux';
import { setAttributeInstanceValue } from '../../../actions/setAttributeInstanceValue';
import { Tab } from '../../util/Tab';
import { AttributeInstance } from '../dataview/AttributeInstance';

const mapDispatchToProps = dispatch => {
    return {
        setAttributeInstanceValue: (entityInstance, attributeInstance, value) => dispatch(setAttributeInstanceValue(entityInstance, attributeInstance, value))
    };
};

class ConnectedDefineAttributeInstance extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.renderAttribute = this.renderAttribute.bind(this);
    }

    handleChange(event) {
        this.props.setAttributeInstanceValue(this.props.entityInstance, this.props.attributeInstance, event.target.value);
    }

    renderAttribute() {
        if (this.props.attributeInstance.toDefine === true) {
            return <div> <Tab />{this.props.attributeInstance.attribute.name}: <input type="text" value={this.props.attributeInstance.value} onChange={this.handleChange} /> </div>;
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

const DefineAttributeInstance = connect(null, mapDispatchToProps)(ConnectedDefineAttributeInstance);

export default DefineAttributeInstance;