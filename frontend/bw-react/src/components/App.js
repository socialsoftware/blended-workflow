import React from 'react';
import Header from './Header';
import { Main } from './Main';
import { RepositoryService } from '../services/RepositoryService';
import { Jumbotron } from 'react-bootstrap';

class App extends React.Component {
  render() {
    return (
       <div>
          <Header />
          <Jumbotron>
            <Main />
          </Jumbotron>
      </div>
    )
  }
}

export default App;
