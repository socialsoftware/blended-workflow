import React from 'react';
import { connect } from 'react-redux';
import { setUnitOfWork } from '../../../actions/setUnitOfWork';
import { setEntityIsPersonDtos } from '../../../actions/setEntityIsPersonDtos'; 
import DefineEntityInstance from './DefineEntityInstance';
import DefineEntityIsPerson from './DefineEntityIsPerson';
import { RepositoryService } from '../../../services/RepositoryService';

const mapStateToProps = state => {
    return {
        spec: state.spec,
        name: state.name,
        user: state.user,
        entityInstances: state.entityInstances,
        unitOfWork: state.unitOfWork,
        entityIsPersonDtos: state.entityIsPersonDtos,
    };
};

const mapDispatchToProps = dispatch => {
    return {
        setUnitOfWork: unitOfWork => dispatch(setUnitOfWork(unitOfWork)),
        setEntityIsPersonDtos: dtos => dispatch(setEntityIsPersonDtos(dtos))
    };
};

class ConnectedExecuteWorkItem extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            workItem: this.props.workItem,
            dependencyTree: []
        };        
        //console.log(this.props.workItem);
        this.props.setUnitOfWork(this.createUnitOfWork());
        this.props.setEntityIsPersonDtos(this.props.workItem.entityIsPersonDTOSet);
        this.createUnitOfWork = this.createUnitOfWork.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleExecute = this.handleExecute.bind(this);
        this.workItemIsAGoal = this.workItemIsAGoal.bind(this);
        this.setEntityInstanceState = this.setEntityInstanceState.bind(this);
        this.setAttributeInstanceState = this.setAttributeInstanceState.bind(this);
        this.attributeDefHasNoEntityInstanceSelected = this.attributeDefHasNoEntityInstanceSelected.bind(this);
        this.setDependencyTree = this.setDependencyTree.bind(this);
        this.defineSkippedAttributeInstancesInDependencyTree = this.defineSkippedAttributeInstancesInDependencyTree.bind(this);
    };

    createUnitOfWork() {
        let counter = 0;
        return this.state.workItem.entityInstancesToDefine.map(ei => JSON.parse(JSON.stringify(ei)))
            .map(ei => {
                if (ei.id === null) {
                    ei.id = --counter;
                } 
                return ei;
            });
    };

    handleClose() {
        this.props.onClose();
    }

    setAttributeInstanceState(attributeInstance, state) {
        if (attributeInstance.toDefine) {
            attributeInstance.state = state;

            if (state === "SKIPPED")
                attributeInstance.value = "SKIPPED";
        }

        return attributeInstance;
    }

    setEntityInstanceState(entityInstance, state) {
        if (!entityInstance.exists)
            entityInstance.state = state;
        
        entityInstance.attributeInstances.map(ai => this.setAttributeInstanceState(ai, state));

        return entityInstance;
    }

    defineSkippedAttributeInstancesInDependencyTree(dependencyTree) {
        const service = new RepositoryService(this.props.user);
        return service.defineDependentAttributeInstances(this.props.spec.specId, this.props.name, dependencyTree);
    }

    setDependencyTree(dependencyTree) {
        this.setState({
            dependencyTree: dependencyTree
        });
    }

    handleExecute(e) {
        if (e.target.value === "DEFINED")
            this.defineSkippedAttributeInstancesInDependencyTree(this.state.dependencyTree)

        const workItem = this.state.workItem;
        workItem.unitOfWork = this.props.unitOfWork;

        workItem.unitOfWork.map(ei => this.setEntityInstanceState(ei, e.target.value));

        this.props.onExecute(workItem);
    }

    workItemIsAGoal() {
        return window.location.href.includes("goals");
    }

    attributeDefHasNoEntityInstanceSelected(entityInstanceToDefine) {
        return entityInstanceToDefine.exists && entityInstanceToDefine.id === -1;
    }

    activityCanBeSkipped(entityInstanceToDefine) {
        return !(this.workItemIsAGoal() || entityInstanceToDefine.entity.mandatory || this.attributeDefHasNoEntityInstanceSelected(entityInstanceToDefine))
    }

    render() {
        console.log(this.props.unitOfWork);
        return ( 
            <div>
                {this.props.unitOfWork.map(ei => <DefineEntityInstance key={ei.id} entityInstance={ei} onSetDependencyTree={this.setDependencyTree}/>)}
                {this.props.workItem.entityIsPersonDTOSet && <div>
                    <strong>Entity is Person Relationships</strong>    
                    {this.props.workItem.entityIsPersonDTOSet.map(eip => <DefineEntityIsPerson entityIsPersonDto={eip} />)}
                </div>}
                <div>
                    <button onClick={this.handleClose}>Close</button> &nbsp;
                    <button onClick={this.handleExecute} value={"DEFINED"}>Execute</button> &nbsp;
                    {this.props.unitOfWork.reduce((result, ei) => result && this.activityCanBeSkipped(ei), true) && 
                        <button onClick={this.handleExecute} value={"SKIPPED"}>Skip</button>
                    }
                </div>
            </div>
        )
    }
}

const ExecuteWorkItem = connect(mapStateToProps, mapDispatchToProps)(ConnectedExecuteWorkItem);

export default ExecuteWorkItem;