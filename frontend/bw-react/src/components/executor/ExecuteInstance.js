import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { GoalExecutor } from './GoalExecutor';
import { ActivityExecutor } from './ActivityExecutor';

export const ExecuteInstance = (props) => (
    <div> 
        <h5>Executor of instance {props.match.params.name}</h5>
        <ul>
            <li>Data View</li>
            <li><Link to={`/specifications/${props.match.params.specId}/executor/instances/${props.match.params.name}/goals`}>Goal View</Link></li>
            <li><Link to={`/specifications/${props.match.params.specId}/executor/instances/${props.match.params.name}/activities`}>Activity View</Link></li>
        </ul>

        <Switch>
            <Route path='/specifications/:specId/executor/instances/:name/goals' component={GoalExecutor}/>
            <Route path='/specifications/:specId/executor/instances/:name/activities' component={ActivityExecutor}/>
        </Switch>
    </div>
)
