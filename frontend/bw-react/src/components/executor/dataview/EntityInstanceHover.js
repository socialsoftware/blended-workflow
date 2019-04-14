import React from 'react';
import { connect } from 'react-redux';
import { EntityInstance } from './EntityInstance';

import "./EntityInstanceHoverStyles.css";

const mapStateToProps = state => {
    return {
        specId: state.specId,
        name: state.name,
        entityInstances: state.entityInstances,
    };
};

class ConnectedEntityInstanceHover extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };

        this.getEntityInstanceById = this.getEntityInstanceById.bind(this)
    }

    getEntityInstanceById(id) {
        return this.props.entityInstances.find(function(element) {
           return element.id === id;
        });
    }

    render() {
        const styles = {
            display: this.props.show ? 'inline-block' : 'none',
        };
        
        return (
            <div className="entity-instance-tooltip" style={styles}>
                <EntityInstance entityInstance={this.getEntityInstanceById(this.props.entityInstance.id)} />
            </div>
        )
    }
}

const EntityInstanceHover = connect(mapStateToProps)(ConnectedEntityInstanceHover);

export default EntityInstanceHover;