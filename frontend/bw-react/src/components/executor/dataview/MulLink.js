import React from 'react';
import { Tab } from '../../util/Tab';
import EntityInstanceLink from './EntityInstanceLink';

export class MulLink extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Tab />{this.props.link.mulCondition.rolename}: {this.props.link.entityInstances.map(ei => <EntityInstanceLink key={ei.id} entityInstance={ei} />) }
            </div>
        )
    }
}
