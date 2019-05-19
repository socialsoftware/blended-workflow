import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import { RepositoryService } from '../services/RepositoryService';

import { setSpecifications } from '../actions/setSpecifications';
import { selectSpecification } from '../actions/selectSpecification';
import { setInstances } from '../actions/setInstances';
import { selectInstance } from '../actions/selectInstance';
import { setUsers } from '../actions/setUsers';
import { selectUser } from '../actions/selectUser';

const mapStateToProps = state => ( {
    specifications: state.specifications,
    spec: state.spec,
    instances: state.instances,
    name: state.name,
    users: state.users,
    user: state.user,
} );

const mapDispatchToProps = dispatch => ( {
    setSpecifications: specifications => dispatch(setSpecifications(specifications)),
    selectSpecification: spec => dispatch(selectSpecification(spec)),
    setInstances: instances => dispatch(setInstances(instances)),
    selectInstance: name => dispatch(selectInstance(name)),
    setUsers: users => dispatch(setUsers(users)),
    selectUser: user => dispatch(selectUser(user)),
} );

class ConnectedHeader extends React.Component {
    constructor(props) {
        super(props);

        this.selectSpecification = this.selectSpecification.bind(this);
    }

    selectSpecification(spec) {
        const service = new RepositoryService(this.props.user);
        service.getWorkflowInstances(spec.specId).then(response => {
            this.setState({
                instances: response.data
            });
            this.props.selectSpecification(spec);
            this.props.setInstances(response.data);
        });
    }

    selectUser(user) {
        const service = new RepositoryService(this.props.user);
        service.loginUser(user.username).then(response => {
            const userDto = {
                username: user.username,
                token: response.data.tokenType + " " + response.data.accessToken,
            };
            this.props.selectUser(userDto);
        });
    }

    componentDidMount() {
        const service = new RepositoryService(this.props.user);
        service.getSpecifications().then(response => {
            this.props.setSpecifications(response.data);

            service.getUsers().then(response => {
                this.props.setUsers(response.data);
            });
        });
        
    }

    render() {
        const specs = this.props.specifications.map( spec =>
            <LinkContainer key={spec.specId} to={`/specifications/spec/${spec.specId}`}>
                <MenuItem eventKey={spec.specId} onClick={() => {this.selectSpecification(spec)}}>{spec.name}</MenuItem>
            </LinkContainer>
        );

        const instances = this.props.instances.map( ( i, index ) =>
            <LinkContainer key={`${i.name}--${index}`} to={`/specifications/instances/instance/${i.name}`}>
                <MenuItem eventKey={i.name} onClick={() => {this.props.selectInstance(i.name)}}>{i.name}</MenuItem>
            </LinkContainer>
        );

        const users = this.props.users.map( user =>
            <LinkContainer key={user.username} to='/'>
                <MenuItem eventKey={user.username} onClick={() => {this.selectUser(user)}}>{user.username}</MenuItem>
            </LinkContainer>
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
                            <LinkContainer to='/specifications'>
                                <MenuItem eventKey={1.1} onClick={() => {this.props.selectSpecification({})}}>Manage Specifications</MenuItem>
                            </LinkContainer>
                            <MenuItem divider />
                            {specs}
                        </NavDropdown>
                    </Nav>
                    {this.props.spec.specId && <Nav>
                         <NavItem eventKey={2}>
                            {this.props.spec.name}
                        </NavItem>
                        <NavDropdown eventKey={3} title="Designer" id="basic-nav-dropdown">
                            <LinkContainer to='/specifications/designer/datamodel'>
                                <MenuItem eventKey={3.1}>Data Model</MenuItem>
                            </LinkContainer>
                            <MenuItem eventKey={3.2}>State Model</MenuItem>
                            <LinkContainer to='/specifications/designer/goalmodel'>
                                <MenuItem eventKey={3.3}>Goal Model</MenuItem>
                            </LinkContainer>
                            <MenuItem eventKey={3.4}>Activity Model</MenuItem>
                        </NavDropdown>
                        <NavDropdown eventKey={4} title="Instance" id="basic-nav-dropdown">
                            <LinkContainer to='/specifications/instances'>
                                <MenuItem eventKey={4.1} onClick={() => {this.props.selectInstance('')}}>Manage Instances</MenuItem>
                            </LinkContainer>
                            <MenuItem divider />
                            {instances}
                        </NavDropdown>
                    </Nav>}
                    {this.props.name && <Nav>
                        <NavItem eventKey={5}>
                            {this.props.name}
                        </NavItem>
                        <NavDropdown eventKey={6} title="Executor" id="basic-nav-dropdown">
                            <LinkContainer to='/specifications/executor/data'>
                                <MenuItem eventKey={6.1}>Data View</MenuItem>
                            </LinkContainer>
                            <LinkContainer to='/specifications/executor/goals'>
                                <MenuItem eventKey={6.2}>Goal View</MenuItem>
                            </LinkContainer>
                            <LinkContainer to='/specifications/executor/activities'>
                                <MenuItem eventKey={6.3}>Activity View</MenuItem>
                            </LinkContainer>
                        </NavDropdown>
                    </Nav>}
                    {(this.props.users || []).length ? <Nav pullRight>
                        {(this.props.user || {}).username && <LinkContainer to='/dashboard'>
                            <NavItem eventKey={8}>{this.props.user.username}</NavItem>
                        </LinkContainer>}
                        <NavDropdown eventKey={7} title="Users" id="basic-nav-dropdown">
                            {users}
                        </NavDropdown>
                    </Nav> : ''}
                </Navbar.Collapse>
            </Navbar>
        );
    }
}

const Header = connect(mapStateToProps, mapDispatchToProps)(ConnectedHeader);

export default Header;
