import React from 'react';
import { Table, Button } from 'react-bootstrap';
import { connect } from 'react-redux';

const mapStateToProps = state => {
    return { 
        user: state.user,
    };
};  

export class ConnectedExecutionLog extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };

        console.log(this.props.log);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        this.setState({
            open: !this.state.open
        });
    }

    render() { 
        return (
            <div>
                <Button onClick={this.handleClick}>{this.state.open ? 'Close Log' : 'Open Log'}</Button>
                {this.state.open &&
                <Table responsive striped hover>
                <thead>
                    <tr>
                        <th>Order</th>
                        <th>Operation Name</th>
                        <th>Operation Type</th>
                        <th>Preconditions</th>
                        <th>Products</th>
                        {this.props.user && <th>Execution User</th>}
                    </tr>
                </thead>
                <tbody>
                {this.props.log.map(wi => {
                    console.log(wi);
                    return <tr key={`${wi.type}-${wi.timestamp}`}>
                        <td>{wi.timestamp}</td>
                        <td>{wi.name}</td>
                        <td>{wi.type}</td>
                        <td>{wi.preArguments}</td>
                        <td>{wi.postArguments}</td>
                        {this.props.user && wi.executionUser && <td>{wi.executionUser.username}</td>}
                        {!wi.executionUser && <td></td>}    {/*Fill 'Execution User' column with blank <td>*/}
                    </tr>
                })}
                </tbody>          
                </Table>
                }
            </div>
        );
    }
}

const ExecutionLog = connect(mapStateToProps)(ConnectedExecutionLog);

export default ExecutionLog;