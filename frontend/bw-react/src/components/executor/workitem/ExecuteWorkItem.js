import React from 'react';
import { connect } from 'react-redux';
import { setUnitOfWork } from '../../../actions/setUnitOfWork';
import DefineEntityInstance from './DefineEntityInstance';
import DefineEntityIsPerson from './DefineEntityIsPerson';

const mapStateToProps = state => {
    return {
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

        this.state = {
            workItem: this.props.workItem,
        };        
        
        this.props.setUnitOfWork(this.createUnitOfWork());

        this.createUnitOfWork = this.createUnitOfWork.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleExecute = this.handleExecute.bind(this);
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

    handleExecute() {
        const workItem = this.state.workItem;
        workItem.unitOfWork = this.props.unitOfWork;

        this.props.onExecute(workItem);
    }

    render() {
        return ( 
            <div>
                {this.props.unitOfWork.map(ei => <DefineEntityInstance key={ei.id} entityInstance={ei} />)}
                {this.props.workItem.entityIsPersonDTOSet && <div>
                    <strong>Entity is Person Relationships</strong>    
                    {this.props.workItem.entityIsPersonDTOSet.map(eip => <DefineEntityIsPerson entityIsPersonDto={eip} />)}
                </div>}
                <div><button onClick={this.handleClose}>Close</button> <button onClick={this.handleExecute}>Execute</button></div>
            </div>
        )
    }
}

const ExecuteWorkItem = connect(mapStateToProps, mapDispatchToProps)(ConnectedExecuteWorkItem);

export default ExecuteWorkItem;