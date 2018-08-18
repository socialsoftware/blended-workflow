import React from 'react';

export class Attribute extends React.Component {
    renderDependencies() {
        if (this.props.attribute.dependencies.length !== 0) {
            return <span>dependsOn {this.props.attribute.dependencies.map(d => <span key={d.extId}>{d.path}</span>)}</span>
        } else {
            return "";
        }
    }

    render() {
        return (
            <div>
                &nbsp; &nbsp; {this.props.attribute.name}: {this.props.attribute.type} {this.renderDependencies()}
            </div>
        )
    }
}
