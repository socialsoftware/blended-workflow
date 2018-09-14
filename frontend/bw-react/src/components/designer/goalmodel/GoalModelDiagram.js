import React from 'react';
import { RepositoryService } from '../../../services/RepositoryService';
import { OperationsMenu } from './OperationsMenu';
import { VisNetwork } from '../../util/VisNetwork';
import { ModalMessage } from '../../util/ModalMessage';

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
            showMenu: false,
            goalModel: [],
            selectedGoal: {},
            error: false,
            errorMessage: ''
        };

        this.handleSelection = this.handleSelection.bind(this);
        this.handleOperationSubmit = this.handleOperationSubmit.bind(this);
        this.handleOperationCancel = this.handleOperationCancel.bind(this);
        this.closeErrorMessageModal = this.closeErrorMessageModal.bind(this);
    }

    componentDidMount() {
        const service = new RepositoryService();
        service.getGoalModelGraphVis(this.props.specId).then(response => {
            this.setState({
                graph: response.data
            });
        });
    }

    handleSelection(externalId) {
        const service = new RepositoryService();
        service.getGoalModel(this.props.specId).then(response => {
            const goals = response.data;
            this.setState({
                showMenu: true,
                goalModel: goals,
                selectedGoal: goals.find(goal => goal.extId === externalId)
            });
        });
    }

    handleOperationSubmit(operation, inputValue) {
        const service = new RepositoryService();
        service.renameGoal(this.props.specId, this.state.selectedGoal.name, inputValue)
        .then(() => {
            service.getGoalModelGraphVis(this.props.specId).then(response => {
                this.setState({ 
                    graph: response.data,
                    showMenu: false,
                    selectedGoal: {} 
                });     
            });
        }).catch((err) => {
            this.setState({
                error: true,
                errorMessage: 'ERROR: '+ err.response.data.type + ' - ' + err.response.data.value
            });
        });
    }

    handleOperationCancel() {
        this.setState({ 
            showMenu: false,
            selectedGoal: {} 
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
                {this.state.error && 
                <ModalMessage 
                    title='Error Message' 
                    message={this.state.errorMessage} 
                    onClose={this.closeErrorMessageModal} />}

                {this.state.showMenu &&
                <OperationsMenu 
                    selectedGoal={this.state.selectedGoal} 
                    handleSubmit={this.handleOperationSubmit}
                    handleCancel={this.handleOperationCancel}
                    goalModel={this.state.goalModel}/>}
                <div style={{width:'1000px' , height: '700px'}}>
                    <VisNetwork 
                        graph={this.state.graph} 
                        options={options} 
                        onSelection={this.handleSelection} />
                </div>
            </div>
        );
    }
}