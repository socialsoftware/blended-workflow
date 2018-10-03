import React from 'react';
import { connect } from 'react-redux';
import { RepositoryService } from '../../services/RepositoryService';
import { DashboardSpecView } from './DashboardSpecView';

const mapStateToProps = state => {
    return {
        user: state.user,
    };
};

class ConnectedDashboard extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            dashboard: {}
        };

        this.getDashboard = this.getDashboard.bind(this);
    }

    componentDidMount() {
        this.getDashboard();
    }

    getDashboard() {
        const service = new RepositoryService(this.props.user);
        service.getDashboard().then(response => { 
            const dashboardDto = response.data;

            var sortedDashboard = {};

            dashboardDto.activityWorkItems.forEach(element => {
                if (!sortedDashboard[element.specId]) {
                    sortedDashboard[element.specId] = {};
                }

                if (!sortedDashboard[element.specId][element.workflowInstanceName]) {
                    sortedDashboard[element.specId][element.workflowInstanceName] = {
                        'activityWorkItems' : [],
                        'goalWorkItems' : []
                    };
                }

                sortedDashboard[element.specId][element.workflowInstanceName]["activityWorkItems"].push(element);
            });

            dashboardDto.goalWorkItems.forEach(element => {
                if (!sortedDashboard[element.specId]) {
                    sortedDashboard[element.specId] = {};
                }

                if (!sortedDashboard[element.specId][element.workflowInstanceName]) {
                    sortedDashboard[element.specId][element.workflowInstanceName] = {
                        'activityWorkItems' : [],
                        'goalWorkItems' : []
                    };
                }
                
                sortedDashboard[element.specId][element.workflowInstanceName]["goalWorkItems"].push(element);
            });

            console.log(sortedDashboard);

            this.setState({
                dashboard : sortedDashboard
            });
        });
    }
    render() {
        const specs = Object.keys(this.state.dashboard).map(spec => 
            <DashboardSpecView specName={spec} instances={this.state.dashboard[spec]} />
        );

        return (
            <div> 
                <h2>Dashboard</h2>
                <hr></hr>
                {specs}
            </div>
        )
    }
} 

const Dashboard = connect(mapStateToProps)(ConnectedDashboard);

export default Dashboard;