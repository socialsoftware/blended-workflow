import React from 'react';
import { connect } from 'react-redux';
import { setLinkEntityInstances } from '../../../actions/setLinkEntityInstances';
import { Tab } from '../../util/Tab';
import { MulLink } from '../dataview/MulLink';
import { SelectMulEntityInstances } from './SelectMulEntityInstances';

const mapDispatchToProps = dispatch => {
    return {
      setLinkEntityInstances: (entityInstance, mulCondition, entityInstances) => dispatch(setLinkEntityInstances(entityInstance, mulCondition, entityInstances))
    };
};

class ConnectedDefineLink extends React.Component {
    constructor(props) {
        super(props);

        this.renderLink = this.renderLink.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    renderLink() {
        if (!this.props.link.toDefine) {
            return <MulLink key={this.props.link.mulCondition.externalId} link={this.props.link} />;
        } else if (this.props.link.candidateEntityInstances.length === 0) {
            return (
                <div>
                    <Tab /><span>{this.props.link.mulCondition.rolename}({this.props.link.mulCondition.cardinality}): [to associate...]</span>
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

const DefineLink = connect(null, mapDispatchToProps)(ConnectedDefineLink);

export default DefineLink;