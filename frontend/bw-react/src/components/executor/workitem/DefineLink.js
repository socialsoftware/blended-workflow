import React from 'react';
import { connect } from 'react-redux';
import { setLinkEntityInstances } from '../../../actions/setLinkEntityInstances';
import { Tab } from '../../util/Tab';
import { MulLink } from '../dataview/MulLink';
import ManageInnerEntityInstances from './ManageInnerEntityInstances';
import { SelectMulEntityInstances } from './SelectMulEntityInstances';

const mapStateToProps = state => {
    return {
        unitOfWork: state.unitOfWork
    };
};

const mapDispatchToProps = dispatch => {
    return {
      setLinkEntityInstances: (entityInstance, mulCondition, entityInstances) => dispatch(setLinkEntityInstances(entityInstance, mulCondition, entityInstances))
    };
};

class ConnectedDefineLink extends React.Component {
    constructor(props) {
        super(props);

        this.getEntityInstancesForInnerRelation = this.getEntityInstancesForInnerRelation.bind(this);
        this.renderLink = this.renderLink.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    getEntityInstancesForInnerRelation() {
        if (this.props.link.innerCreate) {
            return this.props.unitOfWork.filter(ei => ei.entity.name === this.props.link.mulCondition.targetEntity.name);
        } else {
            return this.props.unitOfWork.filter(ei => 
                ei.entity.name === this.props.link.mulCondition.targetEntity.name &&
                ei.id >= 0
            );
        }
    }

    renderLink() {
        if (!this.props.link.toDefine) {
            return <MulLink key={this.props.link.mulCondition.externalId} link={this.props.link} />;
        } else if (this.props.link.candidateEntityInstances.length === 0) {
            return (
                <div>
                    <Tab /><span>{this.props.link.mulCondition.rolename}({this.props.link.mulCondition.cardinality}): </span> 
                    <ManageInnerEntityInstances entityName={this.props.link.mulCondition.targetEntity.name} 
                        entityInstances={this.getEntityInstancesForInnerRelation()} 
                        max={this.props.link.mulCondition.max}
                        innerCreate={this.props.link.innerCreate}/>
                </div>
            );
        } else {
            return (
                <div>
                    <Tab /><span>{this.props.link.mulCondition.rolename}({this.props.link.mulCondition.cardinality}): </span>
                    <SelectMulEntityInstances entityInstances={this.props.link.candidateEntityInstances} mulCondition={this.props.link.mulCondition} onSubmit={this.handleSubmit}/>
            </div> );
        }
    }

    handleSubmit(selected) {
        this.props.setLinkEntityInstances(this.props.entityInstance, this.props.link.mulCondition, selected);
    }

    render() {
        return (
            <div>
                {this.renderLink()}
            </div>
        )
    }
}

const DefineLink = connect(mapStateToProps, mapDispatchToProps)(ConnectedDefineLink);

export default DefineLink;