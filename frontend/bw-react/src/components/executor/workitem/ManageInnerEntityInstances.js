import React from 'react';
import { connect } from 'react-redux';
import { createEntityInstance } from '../../../actions/createEntityInstance';
import EntityInstanceLink from '../dataview/EntityInstanceLink';

const mapStateToProps = state => {
    return {
        entityInstancesToDefine: state.entityInstancesToDefine,
        unitOfWork: state.unitOfWork
    };
};

const mapDispatchToProps = dispatch => {
    return {
        createEntityInstance: entityName => dispatch(createEntityInstance(entityName))
    };
};

class ConnectedManageInnerEntityInstances extends React.Component {
    constructor(props) {
        super(props);

        this.handleCreate = this.handleCreate.bind(this);
        this.renderCreate = this.renderCreate.bind(this);
    }

    handleCreate() {
        const entityInstanceToDefine = JSON.parse(JSON.stringify(
            this.props.entityInstancesToDefine.find(ei => ei.entity.name === this.props.entityName)));
        const min = this.props.unitOfWork.map(ei => parseInt(ei.id)).reduce((id1, id2) => Math.min(id1, id2), 0) - 1;
        const newEntityInstance = { ...entityInstanceToDefine, id: min.toString()};
        this.props.createEntityInstance(newEntityInstance);
    }

    renderCreate() {
        return <span> <button onClick={this.handleCreate}>Create</button></span>;
    }

    render() {
        return (
            <span>
                {this.props.entityInstances.map(ei => <span><EntityInstanceLink key={ei.id} entityInstance={ei} /> </span>)}
                {this.props.innerCreate && this.props.entityInstances.length < this.props.max && this.renderCreate()}
            </span>
        )
    }
}

const ManageInnerEntityInstances = connect(mapStateToProps, mapDispatchToProps)(ConnectedManageInnerEntityInstances);

export default ManageInnerEntityInstances;