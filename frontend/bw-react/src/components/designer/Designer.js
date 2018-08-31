import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import DataModel from './datamodel/DataModel';
import GoalModel from './goalmodel/GoalModel';

export class Designer extends React.Component {
    render() {
        return (
            <div>
                <ul>
                    <li><Link to={`/specifications/designer/datamodel`}>Data Model</Link></li>
                    <li>State Model</li>
                    <li><Link to={`/specifications/designer/goalmodel`}>Goal Model</Link></li>
                    <li>Activity Model</li>
                </ul>
                <Switch>
                    <Route path='/specifications/designer/datamodel' component={DataModel} />
                    <Route path='/specifications/designer/goalmodel' component={GoalModel} />
                </Switch>
            </div>
        )
    }
} 