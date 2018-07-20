import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { ManageSpecifications } from './ManageSpecifications';
import { Specification } from './Specification';

export const Specifications = () => (
  <div>
    <Switch>
      <Route path='/specifications/manage' component={ManageSpecifications} />
      <Route path='/specifications/:specId' component={Specification} />
    </Switch>
  </div>
)
