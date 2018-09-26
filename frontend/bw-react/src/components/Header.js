import React from 'react';
import { connect } from 'react-redux';
import { setSpecifications } from '../actions/setSpecifications';
import { selectSpecification } from '../actions/selectSpecification';
import { setInstances } from '../actions/setInstances';
import { selectInstance } from '../actions/selectInstance';
import { setUsers } from '../actions/setUsers';
import { RepositoryService } from '../services/RepositoryService';
import { Link } from 'react-router-dom';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';

const mapStateToProps = state => {
    return { 
        specifications: state.specifications,
        spec: state.spec,
        instances: state.instances,
        name: state.name,
        users: state.users,
     };
};

const mapDispatchToProps = dispatch => {
    return {
        setSpecifications: (specifications) => dispatch(setSpecifications(specifications)),
        selectSpecification: spec => dispatch(selectSpecification(spec)),
        setInstances: (instances) => dispatch(setInstances(instances)),
        selectInstance: name => dispatch(selectInstance(name)),
        setUsers: (users) => dispatch(setUsers(users)),
    };
  };

class ConnectedHeader extends React.Component {
    constructor(props) {
        super(props);

        this.selectSpecification = this.selectSpecification.bind(this);
    }

    selectSpecification(spec) {
        const service = new RepositoryService();
        service.getWorkflowInstances(spec.specId).then(response => {
            this.setState({
                instances: response.data
            });
            this.props.selectSpecification(spec);
            this.props.setInstances(response.data);
        });
    }

    selectUser(user) {
        const service = new RepositoryService();
        console.log(user.username);
        service.loginUser(user.username).then(response => {
            console.log(response);
        });
    }

    componentDidMount() {
        const service = new RepositoryService();
        service.getSpecifications().then(response => {
            this.props.setSpecifications(response.data);
        });
        service.getUsers().then(response => {
            this.props.setUsers(response.data);
        });
        
      }

    render() {
        const specs = this.props.specifications.map(spec => 
            <MenuItem eventKey={spec.specId}> 
                <Link onClick={() => {this.selectSpecification(spec)}}
                    to={`/specifications/spec/${spec.specId}`}>
                    {spec.name}
                </Link>
            </MenuItem>
        );

        const instances = this.props.instances.map(i =>
            <MenuItem eventKey={i.name}>
                <Link onClick={() => {this.props.selectInstance(i.name)}}
                    to={`/specifications/instances/instance/${i.name}`}>
                    {i.name}
                </Link>
            </MenuItem>
        );

        const users = this.props.users.map(user => 
            <MenuItem eventKey={user.username}> 
                <Link onClick={() => {this.selectUser(user)}}
                    to={`/`}>
                    {user.username}
                </Link>
            </MenuItem>
        );

        return (
            <Navbar inverse collapseOnSelect>
                <Navbar.Header>
                    <Navbar.Brand>
                        <Link to='/'>Blended Workflow</Link>
                    </Navbar.Brand>
                    <Navbar.Toggle />
                </Navbar.Header>
                <Navbar.Collapse>
                    <Nav>
                        <NavDropdown eventKey={1} title="Specification" id="basic-nav-dropdown">
                            <MenuItem eventKey={1.1}><Link onClick={() => {this.props.selectSpecification({})}} to='/specifications'>Manage Specifications</Link></MenuItem>
                            <MenuItem divider />
                            {specs}
                        </NavDropdown>
                    </Nav>
                    {this.props.spec.specId && <Nav>
                         <NavItem eventKey={2}>
                            {this.props.spec.name}
                        </NavItem>
                        <NavDropdown eventKey={3} title="Designer" id="basic-nav-dropdown">
                            <MenuItem eventKey={3.1}><Link to={`/specifications/designer/datamodel`}>Data Model</Link></MenuItem>
                            <MenuItem eventKey={3.2}>State Model</MenuItem>
                            <MenuItem eventKey={3.3}><Link to={`/specifications/designer/goalmodel`}>Goal Model</Link></MenuItem>
                            <MenuItem eventKey={3.4}>Activity Model</MenuItem>
                        </NavDropdown>
                        <NavDropdown eventKey={4} title="Instance" id="basic-nav-dropdown">
                            <MenuItem eventKey={4.1}><Link onClick={() => {this.props.selectInstance('')}} to='/specifications/instances'>Manage Instances</Link></MenuItem>
                            <MenuItem divider />
                            {instances}
                        </NavDropdown>
                    </Nav>}
                    {this.props.name && <Nav>
                        <NavItem eventKey={5}>
                            {this.props.name}
                        </NavItem>
                        <NavDropdown eventKey={6} title="Executor" id="basic-nav-dropdown">
                            <MenuItem eventKey={6.1}><Link to={`/specifications/executor/data`}>Data View</Link></MenuItem>
                            <MenuItem eventKey={6.2}><Link to={`/specifications/executor/goals`}>Goal View</Link></MenuItem>
                            <MenuItem eventKey={6.3}><Link to={`/specifications/executor/activities`}>Activity View</Link></MenuItem>
                        </NavDropdown>
                    </Nav>}
                    {this.props.users && <Nav pullRight>
                        <NavDropdown eventKey={7} title="Users" id="basic-nav-dropdown">
                            {users || <MenuItem eventKey={7.1}>No users</MenuItem>}
                        </NavDropdown>
                    </Nav>}
                </Navbar.Collapse>
            </Navbar>
        )
    }
}

const Header =  connect(mapStateToProps, mapDispatchToProps)(ConnectedHeader);

export default Header;
