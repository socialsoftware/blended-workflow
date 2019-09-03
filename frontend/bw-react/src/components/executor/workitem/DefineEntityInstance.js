import React from 'react';
import { connect } from 'react-redux';
import { setSelectedEntityInstance } from '../../../actions/setSelectedEntityInstance';
import { deleteEntityInstance } from '../../../actions/deleteEntityInstance';
import DefineAttributeInstance from './DefineAttributeInstance';
import DefineLink from './DefineLink';
import { SelectEntityInstance } from './SelectEntityInstance';
import EntityInstanceLink from '../dataview/EntityInstanceLink';

const mapStateToProps = state => {
    return {
        entityInstances: state.entityInstances,
        unitOfWork: state.unitOfWork
    };
};

const mapDispatchToProps = dispatch => {
    return {
        setSelectedEntityInstance: (oldId, newId) => dispatch(setSelectedEntityInstance(oldId, newId)),
        deleteEntityInstance: id => dispatch(deleteEntityInstance(id))
    };
};

class ConnectedDefineEntityInstance extends React.Component {
    constructor(props) {
        super(props);

        this.canDelete = this.canDelete.bind(this);
        this.getAttributeInstances = this.getAttributeInstances.bind(this);
        this.getLinks = this.getLinks.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
        this.handleSelection = this.handleSelection.bind(this);
    }

    canDelete() {
        const number = this.props.unitOfWork.filter(ei => ei.entity.name === this.props.entityInstance.entity.name).length;
        return number > 1 && this.props.entityInstance.id < 0 && !this.props.entityInstance.exists;
    }

    getAttributeInstances() {
        if (this.props.entityInstance.id < 0) {
            return this.props.entityInstance.attributeInstances;
        } else {
            const entityInstance = this.props.entityInstances.find(ei => {
                return ei.id === this.props.entityInstance.id;
            });
            return entityInstance ? this.props.entityInstance.attributeInstances.map(ai => {
                if (ai.toDefine) {
                    return ai;
                } else {
                    return entityInstance.attributeInstances.find(aix => {
                        return aix.attribute.name === ai.attribute.name;
                    });
                }
            }) : [];
        }
    }

    getLinks() {
        if (this.props.entityInstance.id < 0) {
            return this.props.entityInstance.links;
        } else {
            const entityInstance = this.props.entityInstances.find(ei => {
                return ei.id === this.props.entityInstance.id;
            });
            return entityInstance ? this.props.entityInstance.links.map(l => {
                if (l.toDefine) {
                    return l;
                } else {
                    return entityInstance.links.find(lx => {
                        return lx.mulCondition.rolename === l.mulCondition.rolename;
                    });
                }
            }) : [];
        }
    }

    handleDelete() {
        this.props.deleteEntityInstance(this.props.entityInstance.id);
    }

    handleSelection(entityInstance) {
        this.props.setSelectedEntityInstance(this.props.entityInstance.id, entityInstance.id);
    }

    render() {
        const entityInstance = this.props.entityInstances.find(ei => {
            return ei.id === this.props.entityInstance.id;
        });
        var textColor = "";
        var titleText = "";

        if (this.props.entityInstance.id > 0 && entityInstance != null) 
            if (entityInstance.state === "SKIPPED") {
                titleText = "Skipped Entity Instance";
                textColor = "#eb8318";
            }

        return (
            <div>
                <span>{this.props.entityInstance.id < 0 && this.props.entityInstance.exists
                    ? `${this.props.entityInstance.entity.name}[undef]`
                    : <span>
                        <span style={{color: textColor}} title={titleText}>
                            {`${this.props.entityInstance.entity.name}[${this.props.entityInstance.id}] `}
                        </span>
                        <EntityInstanceLink key={this.props.entityInstance.id} entityInstance={this.props.entityInstance} isOnSelection={true}/>
                    </span>
                }</span>
                <span> {this.props.entityInstance.exists && <SelectEntityInstance currInstance={this.props.entityInstance} entityInstances={this.props.entityInstance.entityInstancesContext} onSelection={this.handleSelection}/>}</span>
                <span> {this.canDelete() &&  <button onClick={this.handleDelete}>Delete</button>}</span>
                {this.getAttributeInstances().map(att => <DefineAttributeInstance key={att.attribute.name} entityInstance={this.props.entityInstance} attributeInstance={att} setDependencyTree={this.props.onSetDependencyTree}/>)}
                {this.getLinks().map(link => <DefineLink key={link.mulCondition.externalId} entityInstance={this.props.entityInstance} link={link} />)}
                <br />
            </div>
        )
    }
}

const DefineEntityInstance = connect(mapStateToProps, mapDispatchToProps)(ConnectedDefineEntityInstance);

export default DefineEntityInstance;