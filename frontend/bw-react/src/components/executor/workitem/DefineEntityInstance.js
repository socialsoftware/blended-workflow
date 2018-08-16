import React from 'react';
import { connect } from 'react-redux';
import { setSelectedEntityInstanceAction } from '../../../actions/setSelectedEntityInstanceAction';
import { DefineAttributeInstance } from './DefineAttributeInstance';
import { MulLink } from '../dataview/MulLink';
import { SelectEntityInstance } from './SelectEntityInstance';

const mapStateToProps = state => {
    return {
        entityInstances: state.entityInstances
    };
};

const mapDispatchToProps = dispatch => {
    return {
      setSelectedEntityInstanceAction: (oldId, newId) => dispatch(setSelectedEntityInstanceAction(oldId, newId))
    };
};

class ConnectedDefineEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.getAttributeInstances = this.getAttributeInstances.bind(this);
        this.handleSelection = this.handleSelection.bind(this);
    }

    getAttributeInstances() {
        if (this.props.entityInstance.id < 0) {
            return this.props.entityInstance.attributeInstances;
        } else {
            const entityInstance = this.props.entityInstances.find(ei => {
                return ei.id === this.props.entityInstance.id;
            });
            return this.props.entityInstance.attributeInstances.
                map(ai => {
                    if (ai.toDefine) {
                        return ai;
                    } else {
                        return entityInstance.attributeInstances.find(aix => {
                            return aix.attribute.name === ai.attribute.name;
                        });
                    }
                });
        }
    }

    handleSelection(entityInstance) {
        this.props.setSelectedEntityInstanceAction(this.props.entityInstance.id, entityInstance.id);
    }

    render() {
        return (
            <div>
                {this.props.entityInstance.entity.name}[{this.props.entityInstance.id < 0 && this.props.entityInstance.exists ?  'undef' : this.props.entityInstance.id}] {this.props.entityInstance.exists && <SelectEntityInstance entityInstances={this.props.entityInstance.entityInstancesContext} onSelection={this.handleSelection}/>} <br/>
                {this.getAttributeInstances().map(att => <DefineAttributeInstance key={att.attribute.name} attributeInstance={att} />)}
                {this.props.entityInstance.links.map(link => <MulLink key={link.mulCondition.externalId} link={link} />)}
            </div>
        )
    }
}

const DefineEntityInstance = connect(mapStateToProps, mapDispatchToProps)(ConnectedDefineEntityInstance);

export default DefineEntityInstance;