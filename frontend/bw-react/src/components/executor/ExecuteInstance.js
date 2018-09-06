import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import DataView from './DataView';
import GoalExecutor from './GoalExecutor';
import ActivityExecutor from './ActivityExecutor';

export const ExecuteInstance = (props) => (
    <div> 
        <Switch>
            <Route path='/specifications/executor/instances/data' component={DataView}/>
            <Route path='/specifications/executor/instances/goals' component={GoalExecutor}/>
            <Route path='/specifications/executor/instances/activities' component={ActivityExecutor}/>
        </Switch>
    </div>
)
