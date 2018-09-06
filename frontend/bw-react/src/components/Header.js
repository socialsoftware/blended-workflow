import React from 'react';
import { connect } from 'react-redux';
import { selectSpecification } from '../actions/selectSpecification';
import { Link } from 'react-router-dom';
import ManageInstancesLinks from './executor/ManageInstancesLinks';
import { Navbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';

const mapStateToProps = state => {
    return { spec: state.spec,
             name: state.name
     };
};

const mapDispatchToProps = dispatch => {
    return {
        selectSpecification: spec => dispatch(selectSpecification(spec))
    };
  };

class ConnectedHeader extends React.Component {
    render() {
        const specs = this.props.specifications
            .map(spec => 
                <MenuItem eventKey={spec.specId}> 
                    <Link onClick={() => {this.props.selectSpecification(spec)}} 
                        to={`/specifications/${spec.specId}`}>{spec.name}
                    </Link>
                </MenuItem>);

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
                            <MenuItem eventKey={1.1}><Link to='/specifications/manage'>Manage Specifications</Link></MenuItem>
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
                            <ManageInstancesLinks />
                        </NavDropdown>
                    </Nav>}
                    {this.props.name && <Nav>
                        <NavItem eventKey={5}>
                            {this.props.name}
                        </NavItem>
                        <NavDropdown eventKey={6} title="Executor" id="basic-nav-dropdown">
                            <MenuItem eventKey={6.1}><Link to={`/specifications/executor/instances/data`}>Data View</Link></MenuItem>
                            <MenuItem eventKey={6.2}><Link to={`/specifications/executor/instances/goals`}>Goal View</Link></MenuItem>
                            <MenuItem eventKey={6.3}><Link to={`/specifications/executor/instances/activities`}>Activity View</Link></MenuItem>
                        </NavDropdown>
                    </Nav>}
                </Navbar.Collapse>
            </Navbar>
        )
    }
}

const Header =  connect(mapStateToProps, mapDispatchToProps)(ConnectedHeader);

export default Header;
