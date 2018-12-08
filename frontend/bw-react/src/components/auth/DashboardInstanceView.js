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
    updateRedux(specId, instanceName) {
        const service = new RepositoryService(this.props.user);
        service.getSpecification(specId).then(response => {
            this.props.selectSpecification(response.data);
        }).then(response => {
            this.props.selectInstance(instanceName);
        }).then(response => {
            service.getWorkflowInstances(specId).then(response => {
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
                    <div className="col-md-12" >
                        <Link onClick={() => {this.updateRedux(this.props.specName, this.props.instanceName)}}
                            to={`/specifications/instances/instance/${this.props.instanceName}`}>
                            <Button bsStyle="success" block>Load Instance</Button>
                        </Link>
                    </div>    
                </div>
            </div>
        )
    }
}

const DashboardInstanceView = connect(mapStateToProps, mapDispatchToProps)(ConnectedDashboardInstanceView);

export default DashboardInstanceView;