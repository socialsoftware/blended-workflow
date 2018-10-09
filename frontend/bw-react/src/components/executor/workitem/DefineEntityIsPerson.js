import React from 'react';
import { connect } from 'react-redux';
import { setSelectedPerson } from '../../../actions/setSelectedPerson';
import { SelectEntityIsPerson } from './SelectEntityIsPerson';

const mapStateToProps = state => {
    return {
        entityIsPersonDtos: state.entityIsPersonDtos,
    };
};

const mapDispatchToProps = dispatch => {
    return {
        setSelectedPerson: (entID, person) => dispatch(setSelectedPerson(entID, person))
    };
};

class ConnectedDefineEntityIsPerson extends React.Component {
    constructor(props) {
        super(props);

        this.handleSelection = this.handleSelection.bind(this);
    }

    handleSelection(person) {
        this.props.setSelectedPerson(this.props.entityIsPersonDto.entity.extId, person);
    }

    render() {
        return (
            <div>
                <span>{this.props.entityIsPersonDto.entity.name}: </span>
                <SelectEntityIsPerson onSelection={this.handleSelection} personContext={this.props.entityIsPersonDto.personContext} />
            </div>
        )
    }
}

const DefineEntityIsPerson = connect(mapStateToProps, mapDispatchToProps)(ConnectedDefineEntityIsPerson);

export default DefineEntityIsPerson;