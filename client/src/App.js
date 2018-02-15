import React, { Component } from 'react';
import './App.css';
import Navbar from './components/navbar.js'
import {Sidepanel} from './components/sidebar.js';
import ForceGraph from './components/forceGraph.js';
import './../node_modules/cbp-theme/dist/cbp-theme.css';
import testJson from './test.json';
import smallTestJson from './smallTest.json';
class App extends Component {
  constructor(props){
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
    this.state = {search: ''};
  }
  onFormSubmit(value){
    this.setState({search: JSON.stringify(value)});
  }
  render() {
    return (
      <span>
        <Navbar />
        <span style={{height: "87vh"}} className="flex">
          <Sidepanel handleSubmit={this.onFormSubmit}/>
          <ForceGraph data={testJson}/>
          <h2>{this.state.search}</h2>
        </span>
      </span>
    );
  }
}

export default App;
