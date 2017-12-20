import React from 'react';
import {Route, Switch} from 'react-router';
import {BrowserRouter} from 'react-router-dom';
import {PageHeader, PageFooter} from './..';
import * as page from './../../pages';
import PropTypes from 'prop-types';
import './App.css';
import {Container} from 'semantic-ui-react';

class App extends React.Component {
  render() {
    const {baseUrl} = this.props;
    return (
      <BrowserRouter basename={baseUrl}>
        <Container className="stuff-app" fluid>
          <PageHeader/>

          <div className="stuff-content">
            <Switch>
              <Route exact path="/" component={page.Home}/>
              <Route exact path="/read-lines" component={page.ReadLines}/>
              <Route exact path="/mysql-streaming" component={page.MysqlStreaming}/>
              <Route exact path="/scala-serialization" component={page.ScalaSerialization}/>
              <Route exact path="/scala-string-format" component={page.ScalaStringFormat}/>
              <Route path="*" component={page.NotFound}/>
            </Switch>
          </div>

          <PageFooter/>
        </Container>
      </BrowserRouter>
    );
  }
}

App.propTypes = {
  baseUrl: PropTypes.string
};

App.defaultProps = {
  baseUrl: '/'
};

export default App;
