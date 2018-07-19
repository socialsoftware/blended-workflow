import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { Home } from './Home';
import { Specifications } from './specifications/Specifications'

export const Main = () => (
  <main>
    <Switch>
      <Route exact path='/' component={Home}/>
      <Route path='/specifications' component={Specifications}/>
    </Switch>
  </main>
)
