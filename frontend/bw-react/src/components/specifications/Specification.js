import React from 'react';
import { Switch, Route, Link } from 'react-router-dom';
import { RepositoryService } from '../../services/RepositoryService';
import { Designer } from '../designer/Designer';

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
                <Link to={`/specifications/${this.state.specId}/designer`}>Designer</Link>

                <Switch>
                    <Route path='/specifications/:specId/designer' component={Designer} />
                </Switch>

            </div>
        )
    }
}
