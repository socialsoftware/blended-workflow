import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { Instances } from './Instances'

export class Executor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            specId: props.match.params.specId
        }
    }

    render() {
        return (
            <div> 
                <ul>
                    <li><Link to={`/specifications/${this.state.specId}/executor/instances`}>Instances</Link></li>
                </ul>
                <Switch>
                    <Route path='/specifications/:specId/executor/instances' component={Instances} />
                </Switch>
            </div>
        )
    }
} 