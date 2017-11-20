import React from 'react';
import {Divider} from 'semantic-ui-react';
import './PageFooter.css';

class PageFooter extends React.Component {
  render() {
    const ghLink = 'https://pages.github.com/';
    const reactLink = 'https://github.com/facebookincubator/create-react-app';
    const chartsLink = 'https://developers.google.com/chart/';
    const semanticLink = 'https://semantic-ui.com';
    return (
      <div>
        <Divider className="stuff-footer"/>

        <footer>
          Powered by{' '}
          <a href={ghLink}>GitHub Pages</a>,{' '}
          <a href={semanticLink}>Semantic UI</a>,{' '}
          <a href={reactLink}>React</a> and{' '}
          <a href={chartsLink}>Google Charts</a>{' '}
          &copy; Dmitry Komanov
        </footer>
      </div>
    );
  }
}

export default PageFooter;
