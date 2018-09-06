import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import DataModel from '../designer/datamodel/DataModel';
import GoalModel from '../designer/goalmodel/GoalModel';
import ManageInstances from '../executor/ManageInstances';
import { ExecuteInstance } from '../executor/ExecuteInstance';
import DataView from '../executor/DataView';
import GoalExecutor from '../executor/GoalExecutor';
import ActivityExecutor from '../executor/ActivityExecutor';

class Specification extends React.Component {
    render() {
        return (
            <div>
                <Switch>
                    <Route path='/specifications/designer/datamodel' component={DataModel} />
                    <Route path='/specifications/designer/goalmodel' component={GoalModel} />
                </Switch>
                <Switch>
                    <Route path='/specifications/executor/instances' component={ManageInstances} />
                    <Route path='/specifications/executor/instances/:name' component={ExecuteInstance} />
                </Switch>
                <Switch>
                    <Route path='/specifications/executor/instances/data' component={DataView}/>
                    <Route path='/specifications/executor/instances/goals' component={GoalExecutor}/>
                    <Route path='/specifications/executor/instances/activities' component={ActivityExecutor}/>
                </Switch>
            </div>
        )
    }
}

export default Specification;
