import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import Promise from 'promise-polyfill';
import {App} from './components';
import registerServiceWorker from './registerServiceWorker';

if (!window.Promise) {
  window.Promise = Promise;
}

axios.defaults.baseURL = window.stuff_public_url;

ReactDOM.render(<App baseUrl={window.stuff_public_url}/>, document.getElementById('root'));
registerServiceWorker();
