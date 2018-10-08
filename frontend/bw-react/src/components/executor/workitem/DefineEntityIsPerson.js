import React from 'react';
import { connect } from 'react-redux';
import { setSelectedEntityInstance } from '../../../actions/setSelectedEntityInstance';
import { deleteEntityInstance } from '../../../actions/deleteEntityInstance';
import DefineAttributeInstance from './DefineAttributeInstance';
import DefineLink from './DefineLink';
import { SelectEntityInstance } from './SelectEntityInstance';

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

class ConnectedDefineEntityIsPerson extends React.Component {
    constructor(props) {
        super(props);

        console.log(this.props.entityIsPersonDto);
    }

    render() {
        return (
            <div>
                <span>Entity</span>
            </div>
        )
    }
}

const DefineEntityIsPerson = connect(mapStateToProps, mapDispatchToProps)(ConnectedDefineEntityIsPerson);

export default DefineEntityIsPerson;