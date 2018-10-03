import React from 'react';
import { Button } from 'react-bootstrap';

export class DashboardInstanceView extends React.Component {
    constructor(props) {
        super(props);
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
                        <Button bsStyle="primary" block>Go to Activity View</Button>
                    </div>
                    <div className="col-md-6">
                        <Button bsStyle="danger" block>Go to Goal View</Button>
                    </div>
                </div>
            </div>
        )
    }
}