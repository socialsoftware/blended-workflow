import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { DataModel } from './DataModel';

export class Designer extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            specId: props.match.params.specId.substring(1)
        }
    }
    
    render() {
        return (
            <div>
                <ul>
                    <li><Link to={`/specifications/:${this.state.specId}/designer/datamodel`}>Data Model</Link></li>
                    <li>State Model</li>
                    <li>Goal Model</li>
                    <li>Activity Model</li>
                </ul>
                <Switch>
                    <Route path='/specifications/:specId/designer/datamodel' component={DataModel} />
                </Switch>
            </div>
        )
    }
} 