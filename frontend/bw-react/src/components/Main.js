import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { Home } from './Home';
import { ManageSpecifications } from './specifications/ManageSpecifications';
import { Specification } from './specifications/Specification';
import DataModel from './designer/datamodel/DataModel';
import GoalModel from './designer/goalmodel/GoalModel';
import ActivityModel from './designer/activitymodel/ActivityModel';
import ManageInstances from './executor/ManageInstances';
import { Instance } from './executor/Instance';
import DataView from './executor/DataView';
import GoalExecutor from './executor/GoalExecutor';
import ActivityExecutor from './executor/ActivityExecutor';
import Dashboard from './auth/Dashboard';

export const Main = () => (
  <main>
    <Switch>
      <Route exact path='/' component={Home}/>
      <Route exact path='/specifications' component={ManageSpecifications} />
      <Route path='/specifications/spec/:specId' component={Specification} />
    
      <Route exact path='/specifications/designer/datamodel' component={DataModel} />
      <Route exact path='/specifications/designer/goalmodel' component={GoalModel} />
      <Route exact path='/specifications/designer/activitymodel' component={ActivityModel} />
               
      <Route exact path='/specifications/instances' component={ManageInstances} />
      <Route path='/specifications/instances/instance/:name' component={Instance} />
                
      <Route exact path='/specifications/executor/data' component={DataView}/>
      <Route exact path='/specifications/executor/goals' component={GoalExecutor}/>
      <Route exact path='/specifications/executor/activities' component={ActivityExecutor}/>

      <Route exact path='/dashboard' component={Dashboard} />
    </Switch>
  </main>
)
