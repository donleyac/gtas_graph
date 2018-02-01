import React, { Component } from 'react';
import './App.css';
import Navbar from './components/navbar.js'
import './../node_modules/cbp-theme/dist/cbp-theme.css';

class App extends Component {
  render() {
    return (
      <Navbar />
    );
  }
}

export default App;
