import React, { Component } from 'react';
import './App.css';
import Navbar from './components/navbar.js'
import {getPassengerByCriteria} from './service/index'
import {Sidepanel} from './components/sidebar.js';
import ForceGraph from './components/forceGraph.js';
import './../node_modules/cbp-theme/dist/cbp-theme.css';
class App extends Component {
  constructor(props){
    super(props);
    this.onFormSubmit = this.onFormSubmit.bind(this);
    this._formatCriteria = this._formatCriteria.bind(this);
    this.state = {
      search: {
        "Address": {},
        "Agency": {},
        "Document": {},
        "Flight": {},
        "Passenger":{}
      }, data:{}};
  }
  onFormSubmit(search){
    getPassengerByCriteria(this._formatCriteria(search))
      .then(res => {
        return this.setState({data: res.data});
      })
      .catch(err=> console.log("getAllPassengers", err));
  }
  //Transform because state format different than required by services
  _formatCriteria(search){
    console.log("search", search);
    let panelList = ["Address", "Agency","Document","Flight", "Passenger"];
    let panel;
    if(search.openAccordion===undefined 
      || search.openAccordion===""){
      panel = {};
    }
    else {
      panel = search[search.openAccordion] || {};
    }    
    panelList.forEach(value=>{
      if(value!==search.openAccordion) {
        panel[value.toLowerCase()]=search[value]!==undefined;
      }
    });
    return panel;
  }
  componentDidMount(){
    getPassengerByCriteria(this._formatCriteria(this.state.search))
      .then(res => {
        return this.setState({data: res.data});
      })
      .catch(err=> console.log("getAllPassengers", err));
  }
  render() {
    return (
      <span>
        <Navbar />
        <span style={{height: "87vh"}} className="flex">
          <Sidepanel handleSubmit={this.onFormSubmit}/>
          <ForceGraph search={this.state.search} data={this.state.data}/>
        </span>
      </span>
    );
  }
}

export default App;
