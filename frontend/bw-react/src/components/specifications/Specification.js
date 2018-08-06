import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { Designer } from '../designer/Designer';
import Executor from '../executor/Executor';

class Specification extends React.Component {
    render() {
        return (
            <div>
                <Link to={`/specifications/designer`}>Designer</Link> <br />
                <Link to={`/specifications/executor`}>Executor</Link>

                <Switch>
                    <Route path='/specifications/designer' component={Designer} />
                    <Route path='/specifications/executor' component={Executor} />
                </Switch>

            </div>
        )
    }
}

export default Specification;
