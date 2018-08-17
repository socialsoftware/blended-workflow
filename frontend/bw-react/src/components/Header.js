import React from 'react';
import { connect } from 'react-redux';
import { selectSpecification } from '../actions/selectSpecification';
import { Link } from 'react-router-dom';

const mapDispatchToProps = dispatch => {
    return {
        selectSpecification: specId => dispatch(selectSpecification(specId))
    };
  };

class ConnectedHeader extends React.Component {
    render() {
        const specs = this.props.specifications
        .map(spec => <li key={spec.specId}><Link onClick={() => {this.props.selectSpecification(spec.specId)}} to={`/specifications/${spec.specId}`}>{spec.name}</Link></li>);

        return (
            <header>
                <nav>
                    <ul>
                        <li><Link to='/'>Blended Workflow</Link></li>
                        <li><Link to='/specifications'>Specifications</Link>
                            <ul>
                                <li><Link to='/specifications/manage'>Manage Specifications</Link></li>
                                {specs}
                            </ul>
                        </li>
                    </ul>
                </nav>
            </header>
        )
    }
}

const Header =  connect(null, mapDispatchToProps)(ConnectedHeader);

export default Header;
