import React from 'react';
import Header from './Header';
import { Main } from './Main';
import { RepositoryService } from '../services/RepositoryService';
import { Jumbotron } from 'react-bootstrap';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      specifications: [],
    };
  }

  componentDidMount() {
    const service = new RepositoryService();
    service.getSpecifications().then(response => {
      this.setState({ specifications: response.data }
      );
    });
  }

  render() {
    return (
       <div>
        <Header specifications={this.state.specifications} />
        <Jumbotron>
          <Main />
        </Jumbotron>
      </div>
    )
  }
}

export default App;
