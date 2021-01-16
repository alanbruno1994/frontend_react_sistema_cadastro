import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

import Header from './Components/Header';
import Routes from './Routes';
import Login from './Components/Login';

ReactDOM.render(
  <React.Fragment>
   <Routes/>
  </React.Fragment>,
  document.getElementById('root')
);


