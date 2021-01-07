import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

import Header from './Components/Header';
import Routes from './Routes';

ReactDOM.render(
  <React.Fragment>
    <Header></Header>
    <Routes/>
  </React.Fragment>,
  document.getElementById('root')
);


