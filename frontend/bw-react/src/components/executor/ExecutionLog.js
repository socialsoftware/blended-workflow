import React from 'react';
import { Table, Button } from 'react-bootstrap';

export class ExecutionLog extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            open: false,
        };

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
                    </tr>
                </thead>
                <tbody>
                {this.props.log.map(wi => 
                    <tr> 
                        <td>{wi.timestamp}</td>
                        <td>{wi.name}</td>
                        <td>{wi.type}</td>
                        <td>{wi.preArguments}</td>
                        <td>{wi.postArguments}</td>
                    </tr>
                )}
                </tbody>          
                </Table>
                }
            </div>
        );
    }
}