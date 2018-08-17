import React from 'react';
import { Tab } from '../../util/Tab';
import EntityInstanceLink from './EntityInstanceLink';

export class DependenceInstance extends React.Component {
    render() {
    const dependenceEntityInstances = this.props.dependence.entityInstances.length === 0 ? '[none]' : this.props.dependence.entityInstances.map(ei => <EntityInstanceLink key={ei.id} entityInstance={ei} />);
        return (
            <div>
                <Tab /><Tab />{this.props.dependence.dependence.path}: {dependenceEntityInstances}
            </div>
        )
    }
}