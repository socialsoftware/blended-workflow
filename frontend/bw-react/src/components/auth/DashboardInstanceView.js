import React from 'react';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { RepositoryService } from '../../services/RepositoryService';
import { selectSpecification } from '../../actions/selectSpecification';
import { selectInstance } from '../../actions/selectInstance';
import { setInstances } from '../../actions/setInstances';
import { setSpecifications } from '../../actions/setSpecifications';

const mapStateToProps = state => {
    return { 
        specifications: state.specifications,
        spec: state.spec,
        instances: state.instances,
        name: state.name,
        user: state.user,
     };
}; 

const mapDispatchToProps = dispatch => {
    return {
        setSpecifications: (specifications) => dispatch(setSpecifications(specifications)),
        selectSpecification: spec => dispatch(selectSpecification(spec)),
        setInstances: (instances) => dispatch(setInstances(instances)),
        selectInstance: name => dispatch(selectInstance(name)),
    };
  };

class ConnectedDashboardInstanceView extends React.Component {
    constructor(props) {
        super(props);
    }

    updateRedux(specName, instanceName) {
        const service = new RepositoryService(this.props.user);
        service.getSpecification(specName).then(response => {
            const spec = response.data;
            this.props.selectSpecification(response.data);
        }).then(response => {
            this.props.selectInstance(instanceName);
        }).then(response => {
            service.getWorkflowInstances(specName).then(response => {
                this.setState({
                    instances: response.data
                });

                this.props.setInstances(response.data);
            });

            
        });
    }
    
    render() {
        const activities = Object.values(this.props.activityWorkItems).map(activity => 
            <p>{activity.name}</p>
        );

        const goals = Object.values(this.props.goalWorkItems).map(goal => 
            <p>{goal.name}</p>
        );

        return ( 
            <div className="container" style={{background: "white", borderRadius: 15 + "px", marginTop: 20 + "px", padding: 25 + "px"}}>
                <h3>{this.props.instanceName}</h3>
                <div className="row">
                    <div className="col-md-6">
                        <h4>Activities</h4>
                        {activities}
                    </div>
                    <div className="col-md-6">
                        <h4>Goals</h4>
                        {goals}
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6" >
                    <Link to={`/dashboard/executor/activities/${this.props.specName}/${this.props.instanceName}`} onClick={() => {this.updateRedux(this.props.specName, this.props.instanceName)}}><Button bsStyle="primary" block> Go to Activity View</Button></Link>
                    </div>
                    <div className="col-md-6">
                        <Link to='/specifications/executor/goals' onClick={() => {this.updateRedux(this.props.specName, this.props.instanceName)}}><Button bsStyle="danger" block>Go to Goal View</Button></Link>
                    </div>
                    
                </div>
            </div>
        )
    }
}

const DashboardInstanceView = connect(mapStateToProps, mapDispatchToProps)(ConnectedDashboardInstanceView);

export default DashboardInstanceView;