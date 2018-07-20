import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';

export class Instances extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            specId: props.match.params.specId
        }
    }

    componentDidMount() {
        const service = new RepositoryService();
        service.getSpecifications().then(response => {
            this.setState({ specifications: response.data }
            )
        });
    }

    render() {
        return (
            <div>
                {/* <ul>
                    <li><Link to={`/specifications/${this.state.specId}/executor/instances`}>Instances</Link></li>
                </ul>
                <Switch>
                    <Route path='/specifications/:specId/executor/instances' component={Instances} />
                </Switch> */}
            </div>
        )
    }
} 