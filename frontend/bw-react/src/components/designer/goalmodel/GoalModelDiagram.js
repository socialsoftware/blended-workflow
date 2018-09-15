import React from 'react';
import { RepositoryService } from '../../../services/RepositoryService';
import { OperationsMenu, operations } from './OperationsMenu';
import { VisNetwork } from '../../util/VisNetwork';
import { ModalMessage } from '../../util/ModalMessage';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

const tooltip = (
    <Tooltip id="tooltip">
      Select node for conditions and<br /> double click them apply an operation
    </Tooltip>
);

const options = {
    height: 700,
    layout: {
        hierarchical: false
    },
    edges: {
        smooth: false,
        color: '#000000',
        width: 0.5,
        arrows: {
          from: {
            enabled: true,
            scaleFactor: 0.5
          }
        }
    },
    nodes: {
        shape: 'ellipse'
    },
    interaction: {
        hover: true
    }
};

export class GoalModelDiagram extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            graph: {},
            goalModel: [],
            showMenu: false,
            selectedGoal: {},
            mergeWithGoal: {},
            error: false,
            errorMessage: '',
            operation: operations.NONE
        };

        this.loadModel = this.loadModel.bind(this);
        this.handleSelectOperation = this.handleSelectOperation.bind(this);
        this.handleSelectGoal = this.handleSelectGoal.bind(this);
        this.handleOperationSubmit = this.handleOperationSubmit.bind(this);
        this.handleOperationCancel = this.handleOperationCancel.bind(this);
        this.closeErrorMessageModal = this.closeErrorMessageModal.bind(this);
    }

    loadModel() {
        const service = new RepositoryService();
        service.getGoalModelGraphVis(this.props.spec.specId).then(response => {
            const graph = response.data;
            service.getGoalModel(this.props.spec.specId).then(response => {
                this.setState({
                    graph: graph,
                    goalModel: response.data,
                    showMenu: false,
                    selectedGoal: {},
                    mergeWithGoal: {},
                    operation: operations.NONE
                });
            });
        });
    }

    componentDidMount() {
        this.loadModel();
    }

    handleSelectOperation(operation) {
        this.setState({ 
            mergeWithGoal: {},
            operation: operation 
        });
    }

    handleSelectGoal(externalId) {
        if (this.state.operation === operations.NONE ||
            this.state.operation === operations.RENAME ||
            this.state.operation === operations.SPLIT) {
            this.setState({
                showMenu: true,
                selectedGoal: this.state.goalModel.find(goal => goal.extId === externalId)
            });
        }

        if (this.state.operation === operations.MERGE) {
            const mergeWithGoal = this.state.goalModel.find(goal => goal.extId === externalId);
            if (this.state.selectedGoal.type !== mergeWithGoal.type ||
                this.state.selectedGoal === mergeWithGoal) {
                this.setState({
                    error: true,
                    errorMessage: 'The goals are not of the same type or are equal'
                });
            } else {
                this.setState({
                    mergeWithGoal: mergeWithGoal
                });
            }
        }
    }

    handleOperationSubmit(operation, inputValue) {
        const service = new RepositoryService();
        switch (operation) {
            case operations.RENAME:
                service.renameGoal(this.props.spec.specId, this.state.selectedGoal.name, inputValue)
                .then(() => {
                    this.loadModel();        
                }).catch((err) => {
                    this.setState({
                        error: true,
                        errorMessage: 'ERROR: '+ err.response.data.type + ' - ' + err.response.data.value
                    });
                });
                break;
            case operations.MERGE:
                alert(this.state.selectedGoal.name + 
                    this.state.mergeWithGoal.name + inputValue);
                service.mergeGoals(this.props.spec.specId, this.state.selectedGoal, 
                    this.state.mergeWithGoal, inputValue)
                .then(() => {
                    this.loadModel();        
                }).catch((err) => {
                    this.setState({
                        error: true,
                        errorMessage: 'ERROR: '+ err.response.data.type + ' - ' + err.response.data.value
                    });
                });
                break;
            default:
        }
    }

    handleOperationCancel() {
        this.setState({ 
            showMenu: false,
            selectedGoal: {},
            mergeWithGoal: {},
            operation: operations.NONE 
        });
    }

    closeErrorMessageModal() {
        this.setState({
            error: false,
            errrorMessage: ''
        });
    }

    render() {
        return (
            <div>
                <OverlayTrigger placement="bottom" overlay={tooltip}>
                    <h3>{this.props.spec.name}: Goal Model Diagram</h3>
                </OverlayTrigger><br /><br />
                
                {this.state.error && 
                <ModalMessage 
                    title='Error Message' 
                    message={this.state.errorMessage} 
                    onClose={this.closeErrorMessageModal} />}

                {this.state.showMenu &&
                <OperationsMenu 
                    selectedGoal={this.state.selectedGoal} 
                    mergeWithGoal={this.state.mergeWithGoal}
                    handleSelectOperation={this.handleSelectOperation}
                    handleSubmit={this.handleOperationSubmit}
                    handleCancel={this.handleOperationCancel}
                    goalModel={this.state.goalModel}/>}

                <div style={{width:'1000px' , height: '700px'}}>
                    <VisNetwork 
                        graph={this.state.graph} 
                        options={options} 
                        onSelection={this.handleSelectGoal} />
                </div>
            </div>
        );
    }
}