import React from 'react';
import { connect } from 'react-redux';
import { setSelectedEntityInstance } from '../../../actions/setSelectedEntityInstance';
import DefineAttributeInstance from './DefineAttributeInstance';
import DefineLink from './DefineLink';
import { SelectEntityInstance } from './SelectEntityInstance';

const mapStateToProps = state => {
    return {
        entityInstances: state.entityInstances
    };
};

const mapDispatchToProps = dispatch => {
    return {
      setSelectedEntityInstance: (oldId, newId) => dispatch(setSelectedEntityInstance(oldId, newId))
    };
};

class ConnectedDefineEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.getAttributeInstances = this.getAttributeInstances.bind(this);
        this.getLinks = this.getLinks.bind(this);
        this.handleSelection = this.handleSelection.bind(this);
    }

    getAttributeInstances() {
        if (this.props.entityInstance.id < 0) {
            return this.props.entityInstance.attributeInstances;
        } else {
            const entityInstance = this.props.entityInstances.find(ei => {
                return ei.id === this.props.entityInstance.id;
            });
            return this.props.entityInstance.attributeInstances.map(ai => {
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

    getLinks() {
        if (this.props.entityInstance.id < 0) {
            return this.props.entityInstance.links;
        } else {
            const entityInstance = this.props.entityInstances.find(ei => {
                return ei.id === this.props.entityInstance.id;
            });
            return this.props.entityInstance.links.map(l => {
                if (l.toDefine) {
                    return l;
                } else {
                    return entityInstance.links.find(lx => {
                        return lx.mulCondition.rolename === l.mulCondition.rolename;
                    });
                }
            });
        }
    }

    handleSelection(entityInstance) {
        this.props.setSelectedEntityInstance(this.props.entityInstance.id, entityInstance.id);
    }

    render() {
        return (
            <div>
                {this.props.entityInstance.entity.name}[{this.props.entityInstance.id < 0 && this.props.entityInstance.exists ?  'undef' : this.props.entityInstance.id}] {this.props.entityInstance.exists && <SelectEntityInstance entityInstances={this.props.entityInstance.entityInstancesContext} onSelection={this.handleSelection}/>} <br/>
                {this.getAttributeInstances().map(att => <DefineAttributeInstance key={att.attribute.name} entityInstance={this.props.entityInstance} attributeInstance={att} />)}
                {this.getLinks().map(link => <DefineLink key={link.mulCondition.externalId} entityInstance={this.props.entityInstance} link={link} />)}
                <br />
            </div>
        )
    }
}

const DefineEntityInstance = connect(mapStateToProps, mapDispatchToProps)(ConnectedDefineEntityInstance);

export default DefineEntityInstance;