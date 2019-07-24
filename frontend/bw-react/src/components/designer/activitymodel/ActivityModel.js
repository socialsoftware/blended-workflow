import React from 'react';
import { connect } from 'react-redux';
import { ActivityModelDiagram } from './ActivityModelDiagram';

const mapStateToProps = state => {
    return { spec: state.spec };
};

class ConnectedActivityModel extends React.Component {
    render() {
        return (
            <ActivityModelDiagram spec={this.props.spec} />
        );
    }
}

const ActivityModel = connect(mapStateToProps)(ConnectedActivityModel);

export default ActivityModel;