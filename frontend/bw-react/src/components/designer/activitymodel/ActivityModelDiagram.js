import React from 'react';
import { RepositoryService } from '../../../services/RepositoryService';
import { OperationsMenu, OPERATION } from './OperationsMenu';
import { VisNetwork } from '../../util/VisNetwork';
import { ModalMessage } from '../../util/ModalMessage';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

const tooltip = (
	<Tooltip id="tooltip">
		Select node for conditions and<br /> double click them apply an operation
	</Tooltip>
);

const options = {
	height: "700",
	layout: {
		hierarchical: false,
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
		shape: 'ellipse',
	},
	interaction: {
		hover: true
	},
	physics: {
		enabled: false,
	}
};

export class ActivityModelDiagram extends React.Component {

	/*constructor( props ) {
		super( props );

		this.state = {
			activityModel: [],
			activityGraph: {},
		};

		this.loadModel = this.loadModel.bind( this );
	}*/

	constructor( props ) {
		super(props);

		this.state = {
			graph: {},
			activityModel: [],
			showMenu: false,
			selectedActivity: {},
			mergeWithActivity: {},
			activityConditions: [],
			error: false,
			errorMessage: '',
			operation: OPERATION.NONE,
		};

		this.networkRef = React.createRef();

		this.loadModel = this.loadModel.bind( this );
		//this.setActivityConditions = this.setActivityConditions.bind( this );
		this.handleSelectOperation = this.handleSelectOperation.bind( this );
		this.handleSelectActivity = this.handleSelectActivity.bind( this );
		//this.handleSelectCondition = this.handleSelectCondition.bind( this );
		//this.handleOperationSubmit = this.handleOperationSubmit.bind( this );
		this.handleOperationCancel = this.handleOperationCancel.bind( this );
		this.closeErrorMessageModal = this.closeErrorMessageModal.bind( this );
		this.storeGraph = this.storeGraph.bind( this );
	}


	async loadModel() {
		const service = new RepositoryService();

		/*service.getActivities( this.props.spec.specId ).then( response => {
			this.setState( {
				activityModel: response.data,
			} );
		} );*/

		/*try {
			this.setState( {
				activityModel: await service.getActivities( this.props.spec.specId ).then( r => r.data ),
				activityGraph: await service.getActivityGraph( this.props.spec.specId ).then( r => r.data ),
			} );
		} catch( e ) {
			console.warn( e );
		}*/

		/*service.getActivityGraph( this.props.spec.specId ).then( response => {
			this.setState( {
				activityGraph: response.data,
			} );
		} );*/

		try {
			const graph = await service.getActivityModelGraphVis( this.props.spec.specId ).then( r => r.data );

			const model = await service.getActivityModel(this.props.spec.specId).then( r => r.data );

			/*model.forEach( activity => { TODO: after support is added.
				const node = graph.nodes.find( node => node.id === activity.extId );
				const activityPosition = activity.position;

				if ( activityPosition !== null ) {
					node.x = activityPosition.x;
					node.y = activityPosition.y;
				}
			} );*/

			this.setState( {
				graph: graph,
				activityModel: model,
				showMenu: false,
				selectedActivity: {},
				mergeWithActivity: {},
				activityConditions: [],
				operation: OPERATION.NONE,
			} );
		} catch( e ) {
			console.error( '@ActivityModelDiagram::loadModel', e ); // TODO
			this.setState( {
				error: true,
				errorMessage: e.message,
			} );
		}
	}

	componentDidMount() {
		this.loadModel();
	}

	componentWillUnmount() {
		//this.storeGraph(); TODO
	}



	handleSelectActivity( externalId ) {
		if (
			this.state.operation === OPERATION.NONE
			|| this.state.operation === OPERATION.RENAME
		) {
			console.log( '@handleSelectActivity' )
			this.setState( {
				showMenu: true,
				selectedActivity: this.state.activityModel.find( activity => activity.extId === externalId ),
			} );
		}

		if ( this.state.operation === OPERATION.MERGE ) {
			const mergeWithActivity = this.state.activityModel.find( activity => activity.extId === externalId );
			
			if ( this.state.selectedActivity === mergeWithActivity ) {
				this.setState( {
					error: true,
					errorMessage: 'The activities are equal!',
				} );
			} else if ( this.state.selectedActivity.type !== mergeWithActivity.type ) {
				this.setState( {
					error: true,
					errorMessage: `The activities are not of the same type: ${this.state.selectedActivity.type} <> ${mergeWithActivity.type}`,
				} );
			} else {
				this.setState( {
					mergeWithActivity: mergeWithActivity,
				} );
			}
		}

		if ( this.state.operation === OPERATION.SPLIT ) {
			//this.setGoalConditions(this.state.goalModel.find(goal => goal.extId === externalId));
		}
	}

	handleSelectOperation( operation ) { // TODO
		/*if (operation === OPERATION.SPLIT) {
			this.setGoalConditions(this.state.selectedGoal);
		} else {*/
			this.setState( {
				mergeWithActivity: {},
				//goalConditions: [],
				operation: operation,
			} );
		//}
	}

	async handleOperationSubmit( operation, inputValue ) {
		//const service = new RepositoryService();
		
		//await this.storeGraph();

		try {
			switch (operation) {
				case OPERATION.RENAME:
					/*await service.renameGoal(
						this.props.spec.specId,
						this.state.selectedGoal.name,
						inputValue,
					);*/
					console.log(OPERATION.RENAME);

					break;
				case OPERATION.MERGE:
					/*await service.mergeGoals(
						this.props.spec.specId,
						this.state.selectedGoal,
						this.state.mergeWithGoal,
						inputValue,
					);*/
					console.log(OPERATION.MERGE);

					break;
				case OPERATION.SPLIT:
					/*const goalConditions = this.state.goalConditions.filter( c => c.active );
					const sucConditions = this.state.selectedGoal.type === 'ProductGoal' ? goalConditions : null;
					const relations = this.state.selectedGoal.type === 'AssociationGoal' ? goalConditions : null;
					
					await service.splitGoal(
						this.props.spec.specId,
						this.state.selectedGoal,
						sucConditions,
						relations,
						inputValue,
					);*/
					console.log(OPERATION.SPLIT);

					break;
				case OPERATION.ADD_SEQ:
					console.log(OPERATION.ADD_SEQ);
					break;
				case OPERATION.REM_SEQ:
					console.log(OPERATION.REM_SEQ);
					break;
				default:
					return;
			}
			
			this.loadModel();
		} catch ( err ) {
			this.setState( {
				error: true,
				errorMessage: `ERROR: ${err.response.data.type} - ${err.response.data.value}`,
			} );
		}
	}

	handleOperationCancel() { // TODO
		this.setState( {
			showMenu: false,
			selectedActivity: {},
			mergeWithActivity: {},
			//goalConditions: [],
			operation: OPERATION.NONE,
		} );
	}

	closeErrorMessageModal() {
		this.setState( {
			error: false,
			errrorMessage: '',
		} );
	}

	storeGraph() {
		const graph = this.state.graph;
		const network = this.networkRef.current.network;

		const activities = graph.nodes.map( node => {
			const position = network.getPositions( node.id );

			const activity = this.state.activityModel.find( activity => activity.extId === node.id );

			activity.position = {
				x: position[ node.id ].x,
				y: position[ node.id ].y,
			};

			return activity;
		} );

		const service = new RepositoryService();

		return service.storeView( this.props.spec.specId, activities );
	}

	render() {
		//const service = new RepositoryService();
		console.log( 'PROPS', this.props );
		console.log( 'STATE', this.state );
		//console.log( 'service', this.state.activityModel[0] && service.getActivityPreConditions( this.props.spec.specId, this.state.activityModel[0].name ) );

		/*(async _=> console.log( 'OTHER', Promise.all( this.state.activityModel.map( async a => ({
			activity: a,
			pre: await service.getActivityPreConditions( this.props.spec.specId, a.name ).then( r => r.data ),
			seq: await service.getActivitySeqConditions( this.props.spec.specId, a.name ).then( r => r.data ),
			post: await service.getActivityPostConditions( this.props.spec.specId, a.name ).then( r => r.data ),
			mul: await service.getActivityMulConditions( this.props.spec.specId, a.name ).then( r => r.data ),
			rule: await service.getActivityRuleConditions( this.props.spec.specId, a.name ).then( r => r.data ),
		}) ) ) ))();*/

		console.log( 'ERROR?:', this.state.error && this.state.errorMessage );

		return (
			<div>
				<OverlayTrigger placement="bottom" overlay={ tooltip }>
					<h3>{ this.props.spec.name }: Activity Model Diagram</h3>
				</OverlayTrigger><br /><br />

				{ this.state.error && <ModalMessage
					title='Error Message'
					message={ this.state.errorMessage }
					onClose={ this.closeErrorMessageModal }
				/> }

				{ this.state.showMenu && <OperationsMenu
					selectedGoal={ this.state.selectedGoal }
					mergeWithGoal={ this.state.mergeWithGoal }
					goalConditions={ this.state.goalConditions }
					goalConditionKeys={ this.state.goalConditionKeys }
					handleSelectOperation={ this.handleSelectOperation }
					handleSelectCondition={ this.handleSelectCondition }
					handleSubmit={ this.handleOperationSubmit }
					handleCancel={ this.handleOperationCancel }
					activityModel={ this.state.activityModel }
				/> }

				<div style={{width:'1000px' , height: '700px'}}>
					<VisNetwork
						ref={ this.networkRef }
						graph={ this.state.graph }
						options={ options }
						onSelection={ this.handleSelectActivity }
					/>
				</div>
			</div>
		);
	}
}
