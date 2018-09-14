import React from 'react';
import { RepositoryService } from '../../../services/RepositoryService';
import { Button, DropdownButton, MenuItem, Form, FormControl } from 'react-bootstrap';

const operations = {
    NONE: 'operation',
    RENAME: 'rename by',
    MERGE: 'merge with',
    SPLIT: 'split by'
};

export class OperationsMenu extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            operation: operations.NONE,
            inputValue: ''
        };

        this.setOperation = this.setOperation.bind(this);
        this.handleInputValueChange = this.handleInputValueChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleClose= this.handleClose.bind(this);
    }

    setOperation(value) {
        this.setState({ operation: value });
    }

    handleInputValueChange(event) {
        this.setState({ inputValue: event.target.value });
    }

    handleSubmit() {
        this.props.handleSubmit(operations.RENAME, this.state.inputValue);
        // const service = new RepositoryService();
        // service.renameGoal(this.props.selectedGoal.specId, this.props.selectedGoal.name,)
        // .then(() => {
        //     this.handleClose();
        // }).catch((err) => {
        //     this.setState({
        //         error: true,
        //         errorMessage: 'ERROR: '+ err.response.data.type + ' - ' + err.response.data.value
        //     });
        // });
    }

    handleClose() {
        this.props.handleCancel();
        this.setState({
            inputValue: '',
            operation: operations.NONE,
        });
    }

    render() {
       return (
           <Form inline>
                {this.props.selectedGoal.name && <span>
                <Button id='1'>{this.props.selectedGoal.name}</Button>
                <span> </span>
                <DropdownButton 
                    bsStyle='primary'
                    title={this.state.operation}
                    id='2'>
                    <MenuItem eventKey="1" onClick={() => this.setOperation(operations.RENAME)}>{operations.RENAME}</MenuItem>
                    <MenuItem eventKey="2" onClick={() => this.setOperation(operations.MERGE)}>{operations.MERGE}</MenuItem>
                    <MenuItem eventKey="3" onClick={() => this.setOperation(operations.SPLIT)}>{operations.SPLIT}</MenuItem>
                </DropdownButton></span>}{' '}

                {(this.state.operation === operations.MERGE || this.state.operation === operations.SPLIT) && 
                <DropdownButton 
                    bsStyle='primary'
                    title='Goal'
                    id='3'>
                    <MenuItem eventKey="1">Rename</MenuItem>
                    <MenuItem eventKey="2">Merge</MenuItem>
                    <MenuItem eventKey="3" active>Split</MenuItem>
                </DropdownButton>}
                
                {this.state.operation === operations.RENAME && 
                <FormControl
                    id="4"
                    type="text"
                    label="Text"
                    placeholder="name"
                    value={this.state.inputValue}
                    onChange={this.handleInputValueChange}
                />}{' '}
                
                {this.state.inputValue.length > 0 && <span>
                <Button id='5' onClick={this.handleSubmit}>Submit</Button> 
                <span> </span>
                <Button id='6' onClick={this.handleClose}>Cancel</Button> </span>}

            </Form>
       );
    }
}