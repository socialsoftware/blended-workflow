import React from 'react';
import ActivityExecutor from '../executor/ActivityExecutor';
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

class ConnectedDashboardSpecView extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.updateRedux(this.props.match.params.specId, this.props.match.params.instanceName);
    }

    updateRedux(specId, instanceName) {
        const service = new RepositoryService(this.props.user);
        service.getSpecification(specId).then(response => {
            const spec = response.data;
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
        return ( 
            <ActivityExecutor />
            
            
        )
    }
}

const DashboardSpecView = connect(mapStateToProps, mapDispatchToProps)(ConnectedDashboardSpecView);

export default DashboardSpecView;