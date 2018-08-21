import React from 'react';
import { connect } from 'react-redux';
import { setUnitOfWork } from '../../../actions/setUnitOfWork';
import DefineEntityInstance from './DefineEntityInstance';

const mapStateToProps = state => {
    return {
        entityInstancesToDefine: state.entityInstancesToDefine,
        unitOfWork: state.unitOfWork
    };
};

const mapDispatchToProps = dispatch => {
    return {
        setUnitOfWork: unitOfWork => dispatch(setUnitOfWork(unitOfWork))
    };
};

class ConnectedExecuteWorkItem extends React.Component {
    constructor(props) {
        super(props);

        this.props.setUnitOfWork(this.createUnitOfWork());

        const defGroups = this.props.workItem.definitionGroupSet.map((dg,index) => { return { key: -index, value: dg} });
        const defGroupMap = new Map(defGroups.map(dg => [dg.key, dg.value]));

        this.state = {
            workItem: this.props.workItem,
            defGroupMap: defGroupMap,
        }
        
        this.createUnitOfWork = this.createUnitOfWork.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleExecute = this.handleExecute.bind(this);
        this.updateInstance = this.updateInstance.bind(this);
    };

    createUnitOfWork() {
        let counter = 0;
        return this.props.entityInstancesToDefine.map(ei => JSON.parse(JSON.stringify(ei)))
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

    handleExecute() {
        const workItem = this.state.workItem;
        workItem.definitionGroupSet = Array.from(this.state.defGroupMap.values());
        workItem.unitOfWork = this.props.unitOfWork;

        this.props.onExecute(workItem);
    }

    updateInstance(key, definitionGroupInstance) {
        const definitionGroup = this.state.defGroupMap.get(key);
        definitionGroup.definitionGroupInstanceSet = definitionGroupInstance;
        this.setState({
            defGroupMap: this.state.defGroupMap.set(key, definitionGroup)
        })
    }

    render() {
        return ( 
            <div>
                <h5>Execute Workitem {this.props.workItem.name}</h5>
                {this.props.unitOfWork.map(ei => <DefineEntityInstance key={ei.id} entityInstance={ei} />)}
                <div><button onClick={this.handleClose}>Close</button> <button onClick={this.handleExecute}>Execute</button></div>
            </div>
        )
    }
}

const ExecuteWorkItem = connect(mapStateToProps, mapDispatchToProps)(ConnectedExecuteWorkItem);

export default ExecuteWorkItem;