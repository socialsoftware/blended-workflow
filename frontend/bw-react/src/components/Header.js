import React from 'react';
import { Link } from 'react-router-dom';

export class Header extends React.Component {
    render() {
        const specs = this.props.specifications.map(spec => <li key={spec.specId}><Link to={`/specifications/${spec.specId}`}>{spec.name}</Link></li>);

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