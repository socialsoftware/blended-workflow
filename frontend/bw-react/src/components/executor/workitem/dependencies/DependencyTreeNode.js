import React from 'react';
import { connect } from 'react-redux';
import { ArcherElement } from 'react-archer';
import EntityInstanceLink from '../../dataview/EntityInstanceLink';
import { RepositoryService } from '../../../../services/RepositoryService';

const mapStateToProps = state => {
    return {
        spec: state.spec,
        name: state.name,
        user: state.user,
        entityInstances: state.entityInstances
    };
};

class ConnectedDependencyTreeNode extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            dependentAttributeInstances: []
        };

        this.getEntityInstance = this.getEntityInstance.bind(this);
        this.getEntityInstanceId = this.getEntityInstanceId.bind(this);
        this.getAttributeInstanceName = this.getAttributeInstanceName.bind(this);
        this.displayEntityInstance = this.displayEntityInstance.bind(this);
        this.getEntityName = this.getEntityName.bind(this);
        this.displayAttributeInstance = this.displayAttributeInstance.bind(this);
        this.getNodeId = this.getNodeId.bind(this);
        this.getTargetNodesIds = this.getTargetNodesIds.bind(this);
        this.getRelation = this.getRelation.bind(this);
        this.getAllRelations = this.getAllRelations.bind(this);
        this.getAttributeInstanceType = this.getAttributeInstanceType.bind(this);
        this.getAttributeInstanceValue = this.getAttributeInstanceValue.bind(this);
        this.attributeInstanceIsSkipped = this.attributeInstanceIsSkipped.bind(this);
        this.displayDefinedAttributeInstance = this.displayDefinedAttributeInstance.bind(this);
        this.attributeInstanceIsDependencyRoot = this.attributeInstanceIsDependencyRoot.bind(this);
        this.displayRootAttributeInstance = this.displayRootAttributeInstance.bind(this);
        this.displaySkippedAttributeInstance = this.displaySkippedAttributeInstance.bind(this);
        this.getInputId = this.getInputId.bind(this);
        this.getDependentAttributeInstances = this.getDependentAttributeInstances.bind(this);
    }

    getEntityInstance(attributeInstance) {
        if (this.attributeInstanceIsDependencyRoot(attributeInstance))
            return this.props.entityInstance;

        return this.props.entityInstances.find(ei => {
            return ei.attributeInstances.find(ai => {
                return ai.externalId === attributeInstance.externalId;
            })
        });
    }

    getEntityInstanceId(attributeInstance) {
        return this.getEntityInstance(attributeInstance).id;
    }

    getAttributeInstanceName(attributeInstance) {
        return attributeInstance.attribute.name;
    }

    getEntityName(attributeInstance) {
        return attributeInstance.attribute.entityName;
    }

    getEntityInstanceState(attributeInstance) {
        return this.getEntityInstance(attributeInstance).state;
    }

    displayEntityInstance(attributeInstance) {
        var textColor = "";
        var titleText = "";

        if (this.getEntityInstanceState(attributeInstance) === "SKIPPED") {
            textColor = "#eb8318";
            titleText = "Skipped Entity Instance";
        }

        return (
            <div>
                <span style={{color: textColor}} title={titleText}>
                    {this.getEntityName(attributeInstance)}[{this.getEntityInstanceId(attributeInstance)}] &nbsp;
                </span>
                <EntityInstanceLink entityInstance={this.getEntityInstance(attributeInstance)} isOnSelection={true}/>
            </div>
        );
    }

    getAttributeInstanceType(attributeInstance) {
        return attributeInstance.attribute.type;
    }

    getAttributeInstanceValue(attributeInstance) {
        return attributeInstance.value;
    }

    attributeInstanceIsSkipped(attributeInstance) {
        return attributeInstance.state === "SKIPPED";
    }

    attributeInstanceIsDependencyRoot(attributeInstance) {
        return attributeInstance.state === "UNDEFINED";
    }

    displayRootAttributeInstance(attributeInstance) {
        return (
            <div>
                {this.getAttributeInstanceName(attributeInstance)}: <span style={{color: "#A9A9A9"}}><b>Dependency Root</b></span>
            </div>
        );
    }

    getInputId(attributeInstance) {
        return this.getNodeId(attributeInstance) + "_input";
    }

    displaySkippedAttributeInstance(attributeInstance) {
        return (
            <div> 
                {this.getAttributeInstanceName(attributeInstance)}: <input 
                                                                        id={this.getInputId(attributeInstance)} 
                                                                        type="text" 
                                                                        placeholder="Skipped"/>  
            </div>
        );
    }

    displayDefinedAttributeInstance(attributeInstance) {
        return this.getAttributeInstanceName(attributeInstance) + ": " + this.getAttributeInstanceType(attributeInstance) + 
            "[" + this.getAttributeInstanceValue(attributeInstance) + "]";
    }

    displayAttributeInstance(attributeInstance) { 
        if (this.attributeInstanceIsDependencyRoot(attributeInstance)) 
            return this.displayRootAttributeInstance(attributeInstance);

        if (this.attributeInstanceIsSkipped(attributeInstance)) 
            return this.displaySkippedAttributeInstance(attributeInstance);

        return this.displayDefinedAttributeInstance(attributeInstance);
    }

    getNodeId(attributeInstance) {
        return this.getEntityInstanceId(attributeInstance) + this.getAttributeInstanceName(attributeInstance);
    }

    getTargetNodesIds() {
        return this.state.dependentAttributeInstances.map(ai => {
            return this.getNodeId(ai);
        });
    }

    getRelation(id) {
        return {
            targetId: id,
            targetAnchor: "top",
            sourceAnchor: "bottom"
        };
    }

    getAllRelations() {
        return this.getTargetNodesIds().map(id => {
            return this.getRelation(id);
        });
    }

    getDependentAttributeInstances(attributeInstance) {
        const service = new RepositoryService(this.props.user);
        return service.getDependentAttributeInstances(this.props.spec.specId, this.props.name, attributeInstance, 
            this.getEntityInstanceId(attributeInstance));
    }

    componentDidMount() {
        this.getDependentAttributeInstances(this.props.attributeInstance).then(response => {
            this.setState({
                dependentAttributeInstances: response.data
            });
        });
    }

    render() {
        const nodeStyle = {
            padding: "10px",
            border: "1px solid black"
        };

        const attributeInstanceDivStyle = {
            marginTop: "5px",
            maxWidth: "290px"
        };

        return (
            <ArcherElement id={this.getNodeId(this.props.attributeInstance)} relations={this.getAllRelations()}>
                <div style={nodeStyle}>
                    <div>
                        {this.displayEntityInstance(this.props.attributeInstance)}
                    </div>
                    <div style={attributeInstanceDivStyle}>
                        {this.displayAttributeInstance(this.props.attributeInstance)}
                    </div>
                </div>
            </ArcherElement>
        );
    }
}

const DependencyTreeNode = connect(mapStateToProps)(ConnectedDependencyTreeNode);

export default DependencyTreeNode;