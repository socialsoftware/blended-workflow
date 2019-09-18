import React from 'react';
import { connect } from 'react-redux';
import { setAttributeInstanceValue } from '../../../actions/setAttributeInstanceValue';
import { Tab } from '../../util/Tab';
import { AttributeInstance } from '../dataview/AttributeInstance';
import DependenciesLink from './dependencies/DependenciesLink';
import { RepositoryService } from '../../../services/RepositoryService';

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
        setAttributeInstanceValue: (entityInstance, attributeInstance, value) => dispatch(setAttributeInstanceValue(entityInstance, attributeInstance, value))
    };
};

class ConnectedDefineAttributeInstance extends React.Component {
    componentIsMounted = false;

    constructor(props) {
        super(props);

        this.state = {
            inputIsDisabled: false,
            title: "",
            buttonColor: "success",
            dependencyTree: []
        };

        this.setInputToDisabled = this.setInputToDisabled.bind(this);
        this.setInputToEnabled = this.setInputToEnabled.bind(this);
        this.setInputStatus = this.setInputStatus.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.renderAttribute = this.renderAttribute.bind(this);
        this.getEntityInstance = this.getEntityInstance.bind(this);
        this.getAttributeInstance = this.getAttributeInstance.bind(this);
        this.getDependenceInstance = this.getDependenceInstance.bind(this);
        this.attributeInstanceIsSkipped = this.attributeInstanceIsSkipped.bind(this);
        this.dependencyTreeContainsSkippedAttributeInstances = this.dependencyTreeContainsSkippedAttributeInstances.bind(this);
        this.skippedAttributeInstanceIsPresent = this.skippedAttributeInstanceIsPresent.bind(this);
        this.getDependencyTree = this.getDependencyTree.bind(this);
        this.attributeInstanceIsSelected = this.attributeInstanceIsSelected.bind(this);
        this.updateDependencyTree = this.updateDependencyTree.bind(this);
    }

    setInputToDisabled() {
        if (!this.state.inputIsDisabled)
            this.setState({
                inputIsDisabled: true,
                title: "There are dependency definitions missing!",
                buttonColor: "warning"
            });
    }

    setInputToEnabled() {
        if (this.state.inputIsDisabled)
            this.setState({
                inputIsDisabled: false,
                title: "",
                buttonColor: "success"
            });
    }

    handleChange(event) {
        this.props.setAttributeInstanceValue(this.props.entityInstance, this.props.attributeInstance, event.target.value);
    }

    getEntityInstance(id) {
        return this.props.entityInstances.find(ei => {
            return ei.id === id;
        });
    }

    getAttributeInstance(entityInstance, name) {
        return entityInstance.attributeInstances.find(ai => {
            return ai.attribute.name === name;
        });
    }

    getDependenceInstance(attributeInstance) {
        return attributeInstance.dependenceInstances.find(di => {
            return di.dependence.product.includes(attributeInstance.attribute.name);
        });
    }

    attributeInstanceIsSkipped(attributeInstance) {
        return attributeInstance.state === "SKIPPED";
    }

    skippedAttributeInstanceIsPresent(attributeInstances) {
        return attributeInstances.reduce((result, ai) => result || this.attributeInstanceIsSkipped(ai), false);
    }

    getDependencyTree(attributeInstance) {
        const service = new RepositoryService(this.props.user);
        return service.getDependencyTree(this.props.spec.specId, this.props.name, attributeInstance, this.props.entityInstance.id);
    }

    dependencyTreeContainsSkippedAttributeInstances() {
        return this.state.dependencyTree
            .reduce((result, attributeInstances) => result || this.skippedAttributeInstanceIsPresent(attributeInstances), false);
    }

    setInputStatus() {
        this.dependencyTreeContainsSkippedAttributeInstances() ? this.setInputToDisabled() : this.setInputToEnabled();
    }

    attributeInstanceIsSelected() {
        return this.props.entityInstance.id > 0 && this.props.attributeInstance.toDefine === true;
    }

    updateDependencyTree(dependencyTree) {
        this.setState({
            dependencyTree: dependencyTree
        });
        this.setInputStatus();
        this.props.setDependencyTree(dependencyTree);
    }

    renderAttribute() {
        if (this.props.attributeInstance.toDefine === true) {
            if (this.props.entityInstance.id > 0) {
                var entityInstance = this.getEntityInstance(this.props.entityInstance.id);
                var attributeInstance = this.getAttributeInstance(entityInstance, this.props.attributeInstance.attribute.name);

                if (attributeInstance.dependenceInstances.length > 0)
                    var dependenceInstance = this.getDependenceInstance(attributeInstance);
            }

            var inputValue = this.props.attributeInstance.value;

            if (this.state.inputIsDisabled) {
                if (inputValue.length !== 0)
                    this.props.setAttributeInstanceValue(this.props.entityInstance, this.props.attributeInstance, "");
                inputValue = "";
            }

            return (
                <div>
                    <Tab/>{this.props.attributeInstance.attribute.name}: <input 
                                                                            type="text" 
                                                                            value={inputValue} 
                                                                            onChange={this.handleChange} 
                                                                            disabled={this.state.inputIsDisabled}
                                                                            title={this.state.title}/> 
                    {dependenceInstance != null && <DependenciesLink 
                                                        buttonColor={this.state.buttonColor} 
                                                        buttonTitle={this.state.title}
                                                        dependencyTreeRoot={attributeInstance}
                                                        dependencyTree={this.state.dependencyTree}
                                                        rootEntityInstance={entityInstance}
                                                        updateDependencyTree={this.updateDependencyTree}/>
                    }
                   </div>
            );
        } else {
            return <AttributeInstance attributeInstance={this.props.attributeInstance}/>;
        }
    }

    componentDidMount() {
        this.componentIsMounted = true;

        if (this.attributeInstanceIsSelected()) {
            var attributeInstance = this.getAttributeInstance(this.getEntityInstance(this.props.entityInstance.id), 
                this.props.attributeInstance.attribute.name);

            this.getDependencyTree(attributeInstance).then(response => {
                if (this.componentIsMounted) {
                    this.setState({
                        dependencyTree: response.data
                    });

                    this.setInputStatus();
                }
            });
        }
    }

    componentWillUnmount() {
        this.componentIsMounted = false;
    }

    render() {
        return ( 
            <div>
                {this.renderAttribute()}
            </div>
        )
    }
}

const DefineAttributeInstance = connect(mapStateToProps, mapDispatchToProps)(ConnectedDefineAttributeInstance);

export default DefineAttributeInstance;