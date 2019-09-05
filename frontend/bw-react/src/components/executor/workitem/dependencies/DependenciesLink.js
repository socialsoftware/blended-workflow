import React from 'react';
import { connect } from 'react-redux';
import { setGlobalEntityInstanceState } from '../../../../actions/setGlobalEntityInstanceState';
import { setGlobalAttributeInstanceState } from '../../../../actions/setGlobalAttributeInstanceState';
import { setGlobalAttributeInstanceValue } from '../../../../actions/setGlobalAttributeInstanceValue';
import { Modal, Button } from 'react-bootstrap';
import { ModalMessage } from '../../../util/ModalMessage';
import { DependencyTree } from './DependencyTree';
import { RepositoryService } from '../../../../services/RepositoryService';
import "./DependencyTreeModalStyle.css";

const mapStateToProps = state => {
    return { 
        spec: state.spec,
        name: state.name,
        user: state.user,
        entityInstances: state.entityInstances
    };
};

const mapDispatchToProps = dispatch => {
    return {
        setGlobalEntityInstanceState: (entityInstance, newState) => dispatch(setGlobalEntityInstanceState(entityInstance, newState)),
        setGlobalAttributeInstanceState: (attributeInstance, entityInstanceId, newState) => dispatch(setGlobalAttributeInstanceState(attributeInstance, entityInstanceId, newState)),
        setGlobalAttributeInstanceValue: (attributeInstance, entityInstanceId, newValue) => dispatch(setGlobalAttributeInstanceValue(attributeInstance, entityInstanceId, newValue))
    };
};

class ConnectedDependenciesLink extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
            error: false,
            errorMessage: ''
        };

        this.openCloseLink = this.openCloseLink.bind(this);
        this.closeErrorMessageModal = this.closeErrorMessageModal.bind(this);
        this.handleDefine = this.handleDefine.bind(this);
        this.thereAreDependenciesToDefine = this.thereAreDependenciesToDefine.bind(this);
        this.getInputId = this.getInputId.bind(this);
        this.getNodeId = this.getNodeId.bind(this);
        this.getEntityInstance = this.getEntityInstance.bind(this);
        this.getAttributeInstance = this.getAttributeInstance.bind(this);
        this.getEntityInstanceId = this.getEntityInstanceId.bind(this);
        this.getAttributeInstanceName = this.getAttributeInstanceName.bind(this);
        this.attributeInstanceIsSkipped = this.attributeInstanceIsSkipped.bind(this);
        this.getUpdatedAttributeInstanceValue = this.getUpdatedAttributeInstanceValue.bind(this);
        this.getUpdatedAttributeInstances = this.getUpdatedAttributeInstances.bind(this);
        this.displayDependencyTree = this.displayDependencyTree.bind(this);
        this.checkSkippedAttributeInstancesInDependencyTree = this.checkSkippedAttributeInstancesInDependencyTree.bind(this);
        this.updateGlobalStateInstances = this.updateGlobalStateInstances.bind(this);
        this.updateGlobalStateInstance = this.updateGlobalStateInstance.bind(this);
    }

    openCloseLink() {
        this.setState({
            open: !this.state.open
        });
    }

    closeErrorMessageModal() {
        this.setState({
            error: false,
            errorMessage: ''
        });
    }

    getEntityInstance(attributeInstance) {
        return this.props.entityInstances.find(ei => {
            return ei.attributeInstances.find(ai => {
                return ai.externalId === attributeInstance.externalId;
            })
        });
    }

    getEntityInstanceId(attributeInstance) {
        return this.getEntityInstance(attributeInstance).id;
    }

    getAttributeInstance(attributeInstance) {
        return this.getEntityInstance(attributeInstance).attributeInstances.find(ai => {
            return ai.externalId === attributeInstance.externalId;
        });
    }

    getAttributeInstanceName(attributeInstance) {
        return attributeInstance.attribute.name;
    }

    getNodeId(attributeInstance) {
        return this.getEntityInstanceId(attributeInstance) + this.getAttributeInstanceName(attributeInstance);
    }

    getInputId(attributeInstance) {
        return this.getNodeId(attributeInstance) + "_input";
    }

    attributeInstanceIsSkipped(attributeInstance) {
        return attributeInstance.state === "SKIPPED";
    }

    getUpdatedAttributeInstanceValue(attributeInstance) {
        return document.getElementById(this.getInputId(attributeInstance)).value;
    }

    updateGlobalStateInstance(attributeInstance) {
        this.props.setGlobalAttributeInstanceValue(attributeInstance, this.getEntityInstanceId(attributeInstance), attributeInstance.value);
        this.props.setGlobalAttributeInstanceState(attributeInstance, this.getEntityInstanceId(attributeInstance), "DEFINED");
        this.props.setGlobalEntityInstanceState(this.getEntityInstance(attributeInstance), "DEFINED");
    }

    updateGlobalStateInstances(dependencyTree) {
        return dependencyTree.map(treeDepthLevel => {
            return treeDepthLevel.map(attributeInstance => {
                if (this.attributeInstanceIsSkipped(attributeInstance)) {
                    attributeInstance.state = "DEFINED";
                    this.updateGlobalStateInstance(attributeInstance);
                }

                return attributeInstance;
            })
        });
    }

    getUpdatedAttributeInstances(dependencyTree) {
        return dependencyTree.map(treeDepthLevel => {
            return treeDepthLevel.map(attributeInstance => {
                if (this.attributeInstanceIsSkipped(attributeInstance)) 
                    attributeInstance.value = this.getUpdatedAttributeInstanceValue(attributeInstance);

                return attributeInstance;
            })
        });
    }

    checkSkippedAttributeInstancesInDependencyTree(dependencyTree) {
        const service = new RepositoryService(this.props.user);
        return service.checkDependentAttributeInstances(this.props.spec.specId, this.props.name, dependencyTree);
    }

    handleDefine() {
        var updatedDependencyTree = this.getUpdatedAttributeInstances(this.props.dependencyTree);

        this.checkSkippedAttributeInstancesInDependencyTree(updatedDependencyTree)
        .then(() => {
            this.updateGlobalStateInstances(updatedDependencyTree);
            this.openCloseLink();
            this.props.updateDependencyTree(updatedDependencyTree);
        }).catch((err) => {
            this.setState({
                error: true,
                errorMessage: 'ERROR: '+ err.response.data.type + ' - ' + err.response.data.value
            });
        });
    }

    thereAreDependenciesToDefine() {
        return this.props.buttonColor === "warning";
    }

    displayDependencyTree() {
        return (
            <Modal dialogClassName="dependency-tree-modal" show={this.state.open} onHide={this.openCloseLink}>   
                <Modal.Body>
                    <div style={{overflowX: "auto"}}>
                        <DependencyTree 
                            rootAttributeInstance={this.props.dependencyTreeRoot} 
                            rootEntityInstance={this.props.rootEntityInstance}
                            attributeInstances={this.props.dependencyTree}/>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    {this.thereAreDependenciesToDefine() && <Button bsStyle="primary" onClick={this.handleDefine}>Define</Button>}
                    <Button onClick={this.openCloseLink}>Close</Button>
                </Modal.Footer>
            </Modal>
        );  
    }

    render() {
        return (
            <div style={{display: "inline-block"}}>
                <Button bsStyle={this.props.buttonColor} style={{margin: 2.5}} title={this.props.buttonTitle} onClick={this.openCloseLink}>
                    Dependencies
                </Button>

                {this.state.error && <ModalMessage title='Error Message' message={this.state.errorMessage} onClose={this.closeErrorMessageModal}/>}

                {this.displayDependencyTree()}
            </div>
        )
    }
}

const DependenciesLink = connect(mapStateToProps, mapDispatchToProps)(ConnectedDependenciesLink);

export default DependenciesLink;