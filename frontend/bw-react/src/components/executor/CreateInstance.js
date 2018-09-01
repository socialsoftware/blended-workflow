import React from 'react';
import { connect } from 'react-redux';
import { setUnitOfWork } from '../../actions/setUnitOfWork';
import { RepositoryService } from '../../services/RepositoryService';
import DefineEntityInstance from './workitem/DefineEntityInstance';

const mapStateToProps = state => {
    return {
        unitOfWork: state.unitOfWork
    };
};

const mapDispatchToProps = dispatch => {
    return {
        setUnitOfWork: unitOfWork => dispatch(setUnitOfWork(unitOfWork))
    };
};

class ConnectedCreateInstance extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            create: false,
            value: '',
            workItem: {}
        };

        this.createUnitOfWork = this.createUnitOfWork.bind(this);
        this.handleCreate = this.handleCreate.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        const service = new RepositoryService();
        service.getInitWorkItem(this.props.specId).then(response => {
            this.props.setUnitOfWork(this.createUnitOfWork(response.data.entityInstancesToDefine));
            this.setState({
                workItem: response.data
            });
        });
    }

    createUnitOfWork(entityInstancesToDefine) {
        let counter = 0;
        return entityInstancesToDefine.map(ei => JSON.parse(JSON.stringify(ei)))
            .map(ei => {
                ei.id = --counter;
                return ei;
            });
    }

    handleCreate() {
        this.setState({
            create: !this.state.create
        });
    }

    handleChange(event) {
        this.setState({
            value: event.target.value
        });
    }

    handleSubmit() {
        this.props.onSubmit(this.state.value, this.props.unitOfWork);
        this.setState({
            create: false,
            value: '',
            workItem: {}
        });
   }

    render() {
        return (
            <div>
                {this.state.create &&
                    <span>
                        <label> Instance Name:
                            <input type="text" value={this.state.value} onChange={this.handleChange} />
                        </label> <br />
                    </span>
                }
                {this.state.create && this.props.unitOfWork && 
                    this.props.unitOfWork.map(ei => <DefineEntityInstance key={ei.id} entityInstance={ei} />)}
                <button onClick={this.handleCreate}>{this.state.create ? 'Close' : 'Create'}</button>
                {this.state.create &&
                    <span> <button onClick={this.handleSubmit}>Submit</button></span>
                }
            </div>
       )
    }
}

const CreateInstance = connect(mapStateToProps, mapDispatchToProps)(ConnectedCreateInstance);

export default CreateInstance;