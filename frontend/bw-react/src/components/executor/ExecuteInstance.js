import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import DataView from './DataView';
import GoalExecutor from './GoalExecutor';
import ActivityExecutor from './ActivityExecutor';

export const ExecuteInstance = (props) => (
    <div> 
        <h5>Executor of instance {props.match.params.name}</h5>
        <ul>
            <li><Link to={`/specifications/executor/instances/data`}>Data View</Link></li>
            <li><Link to={`/specifications/executor/instances/goals`}>Goal View</Link></li>
            <li><Link to={`/specifications/executor/instances/activities`}>Activity View</Link></li>
        </ul>

        <Switch>
            <Route path='/specifications/executor/instances/data' component={DataView}/>
            <Route path='/specifications/executor/instances/goals' component={GoalExecutor}/>
            <Route path='/specifications/executor/instances/activities' component={ActivityExecutor}/>
        </Switch>
    </div>
)
