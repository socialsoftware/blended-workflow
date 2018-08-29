import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import DataModel from './datamodel/DataModel';

export class Designer extends React.Component {
    render() {
        return (
            <div>
                <ul>
                    <li><Link to={`/specifications/designer/datamodel`}>Data Model</Link></li>
                    <li>State Model</li>
                    <li>Goal Model</li>
                    <li>Activity Model</li>
                </ul>
                <Switch>
                    <Route path='/specifications/designer/datamodel' component={DataModel} />
                </Switch>
            </div>
        )
    }
} 