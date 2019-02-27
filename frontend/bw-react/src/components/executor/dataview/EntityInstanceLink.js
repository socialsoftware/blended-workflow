import React from 'react';
import { connect } from 'react-redux';
import { OpenCloseButton } from './OpenCloseButton';
import { EntityInstance } from './EntityInstance';
import { Modal, Button } from 'react-bootstrap';

const mapStateToProps = state => {
    return {
        specId: state.specId,
        name: state.name,
        entityInstances: state.entityInstances,
    };
};

class ConnectedEntityInstanceLink extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };

        this.openCloseLink = this.openCloseLink.bind(this);
        this.getEntityInstanceById = this.getEntityInstanceById.bind(this)
    }

    getEntityInstanceById(id) {
        return this.props.entityInstances.find(function(element) {
           return element.id === id;
        });
    }

    openCloseLink() {
        if (this.state.open) {
            this.setState({open: false});
        } else {
            this.setState({open: true});
        }
    }

    render() {
        const entityNameDisplay = `${this.props.entityInstance.entity.name}[${this.props.entityInstance.id}]`;

        return (
            <div style={{display: "inline-block"}}>
                {this.props.entityInstance.id > 0 && <span>{this.props.isOnModal ? `${entityNameDisplay} ` : ""}<OpenCloseButton
                    open={this.state.open}
                    onClick={this.openCloseLink}
                /></span>}
                {/* {this.state.open && <EntityInstance entityInstance={this.getEntityInstanceById(this.props.entityInstance.id)} />}  */}

                <Modal show={this.state.open} onHide={this.openCloseLink}>
                    <Modal.Body>
                        <EntityInstance isOnModal={true} entityInstance={this.getEntityInstanceById(this.props.entityInstance.id)} />
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={this.openCloseLink}>Close</Button>
                    </Modal.Footer>
                </Modal>
            </div>
        )
    }
}

const EntityInstanceLink = connect(mapStateToProps)(ConnectedEntityInstanceLink);

export default EntityInstanceLink;