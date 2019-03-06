import React from 'react';
import { connect } from 'react-redux';
import { EntityInstance } from './EntityInstance';
import { Modal } from 'react-bootstrap';

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
            position: 'absolute',
            display: this.props.show ? 'inline-block' : 'none',
        };
        
        return (
            <div style={styles}>
                {/*<Modal show={this.props.show}>
                    <Modal.Body>
                        <EntityInstance entityInstance={this.getEntityInstanceById(this.props.entityInstance.id)} />
                    </Modal.Body>
                </Modal>*/}
                <EntityInstance entityInstance={this.getEntityInstanceById(this.props.entityInstance.id)} />
            </div>
        )
    }
}

const EntityInstanceHover = connect(mapStateToProps)(ConnectedEntityInstanceHover);

export default EntityInstanceHover;