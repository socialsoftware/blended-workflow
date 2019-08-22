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
        this.getEntityInstanceById = this.getEntityInstanceById.bind(this);
        this.entityInstanceIsSkipped = this.entityInstanceIsSkipped.bind(this);
    }

    getEntityInstanceById(id) {
        return this.props.entityInstances.find(function(element) {
           return element.id === id;
        });
    }

    openCloseLink() {
        this.setState( {
            open: !this.state.open,
        } );
    }

    entityInstanceIsSkipped(entityInstance) {
        return entityInstance.state === "SKIPPED";
    }

    render() {
        const entityNameDisplay = `${this.props.entityInstance.entity.name}[${this.props.entityInstance.id}]`;
        var textColor = "";
        var titleText = "";

        if (this.entityInstanceIsSkipped(this.props.entityInstance)) {
            textColor = "#eb8318";
            titleText = "Skipped Entity Instance";
        }

        return (
            <div style={{display: "inline-block"}}>
                {this.props.entityInstance.id > 0 && 
                    <span style={{color: textColor}} title={titleText}>
                        {this.props.isOnSelection ? "" : `${entityNameDisplay} `}
                        <OpenCloseButton open={this.state.open} onClick={this.openCloseLink}/>
                        &nbsp;
                    </span>
                }
                {/* {this.state.open && <EntityInstance entityInstance={this.getEntityInstanceById(this.props.entityInstance.id)} />}  */}

                <Modal show={this.state.open} onHide={this.openCloseLink}>
                    <Modal.Body>
                        <EntityInstance entityInstance={this.getEntityInstanceById(this.props.entityInstance.id)} />
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