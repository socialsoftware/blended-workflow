import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { Designer } from '../designer/Designer';
import { Executor } from '../executor/Executor';

export class Specification extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            specId: props.match.params.specId,
        };
    }

    render() {
        return (
            <div>
                <Link to={`/specifications/${this.state.specId}/designer`}>Designer</Link> <br />
                <Link to={`/specifications/${this.state.specId}/executor`}>Executor</Link>

                <Switch>
                    <Route path='/specifications/:specId/designer' component={Designer} />
                    <Route path='/specifications/:specId/executor' component={Executor} />
                </Switch>

            </div>
        )
    }
}
