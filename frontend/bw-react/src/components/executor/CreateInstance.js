import React from 'react';
import { RepositoryService } from '../../services/RepositoryService';
import ExecuteWorkItem from './workitem/ExecuteWorkItem';

export class CreateInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            value: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit() {
        this.props.onSubmit(this.state.value);
        this.setState({
            value: ''
        });
    }

    render() {
        return (
            <div>
                    <label> Name:
                        <input type="text" value={this.state.value} onChange={this.handleChange} />
                    </label> <button onClick={this.handleSubmit}>Create</button>
            </div>
       )
    }
}
