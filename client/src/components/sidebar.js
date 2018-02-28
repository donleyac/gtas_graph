import React, {Component} from 'react';
import {AccordionPanel} from './accordionPanel.js';
import TextInput from './input.js';
import intialState from './sidebar.json';
import deepCopy from 'deepcopy';
import './sidebar.scss';
export class Sidepanel extends Component {
  constructor(props){
    super(props);
    //Required to copy due to this.state assign being a reference
    this.state = deepCopy(intialState);
    this.handleChange = this.handleChange.bind(this);
    this.handleSidebarBtn = this.handleSidebarBtn.bind(this);
    this._resetState = this._resetState.bind(this);
    this._handleOpen = this._handleOpen.bind(this);
  }
  handleChange(panelNm, event){
    const target = event.target;
    if(target.type==='checkbox'){
      let include = target.checked;
      if(!include) {
        //Remove key from filter criteria
        delete this.state[panelNm];
        this.setState(this.state);
        //Reset open when active unchecked
        if(this.state.openAccordion===panelNm){
          this.setState({freezePanels: false}, ()=> this._handleOpen(panelNm));
        }
      }
      else {
        this.setState({
          [panelNm]: deepCopy(intialState[panelNm])
        });
      }
    }
    else {
      let name = target.name;
      let value = target.value;

      let panelVal = this.state[panelNm];
      panelVal[name] = value;

      //Set Val and freeze, only filter one at a time
      this.setState({[panelNm]: panelVal});
      this.setState({freezePanels: true});
    }
  }
  handleSidebarBtn(){
    this.state.sideBarClosed
    ?this.setState({sideBarClosed:false})
    :this.setState({sideBarClosed:true});
  }
  _resetState(){
    this.setState(deepCopy(intialState));
  }
  _handleOpen(title){
    if(!this.state.freezePanels) {
      if(this.state.openAccordion===title){
        this.setState({"openAccordion": ""});
      }
      else {
        this.setState({"openAccordion": title});
      }
    }
  }
  render() {
    let sideBarClass = "sidebar-tray";
    sideBarClass=this.state.sideBarClosed?sideBarClass+" sidebar-closed":sideBarClass;
    return (
      <div className={sideBarClass}>
        <div className="cbp-sidebar sidebar after-header">
          <form className="filters">
            <div className="filter-list" role="tablist">
              <h1 className="filter-heading">Filters
                <button onClick={()=>this.props.handleSubmit(this.state)} 
                  type="button" className="margin-side-sm btn btn-icon-only btn-primary">
                  <i className="fa fa-inverse fa-search"></i>
                  <span className="sr-only">Search</span>
                </button>
                <a onClick={this._resetState} className="filters-clear">Clear Filters</a>
              </h1>
              <AccordionPanel check title="Passenger" value={this.state["Passenger"]}
                openAccordion={this.state.openAccordion}
                handleChange={(e)=>this.handleChange("Passenger",e)}
                handleOpen={this._handleOpen}>
                <TextInput name="firstName" placeholder="firstName" />
                <TextInput name="middleName" placeholder="middleName" />
                <TextInput name="lastName" placeholder="lastName" />
                <TextInput name="citizenshipCountry" placeholder="citizenshipCountry" />
                <TextInput name="residencyCountry" placeholder="residencyCountry" />
                <TextInput name="dob" placeholder="dob" />
                <TextInput name="mariaId" placeholder="paxId" />
              </AccordionPanel>
              <AccordionPanel check title="Flight" value={this.state["Flight"]}
                handleChange={(e)=>this.handleChange("Flight",e)}
                openAccordion={this.state.openAccordion}
                handleOpen={this._handleOpen}>
                <TextInput name="flightNumber" placeholder="flightNumber" />
                <TextInput name="flightDate" placeholder="flightDate" />
                <TextInput name="embarkation" placeholder="embarkation" />
                <TextInput name="debarkation" placeholder="debarkation" />
                <TextInput name="mariaId" placeholder="flightId" />
              </AccordionPanel>
              <AccordionPanel check title="Document" value={this.state["Document"]}
                handleChange={(e)=>this.handleChange("Document",e)}
                openAccordion={this.state.openAccordion}
                handleOpen={this._handleOpen}>
                <TextInput name="documentType" placeholder="documentType" />
                <TextInput name="documentNumber" placeholder="documentNumber" />
                <TextInput name="expirationDate" placeholder="expirationDate" />
                <TextInput name="issuanceCountry" placeholder="issuanceCountry" />
              </AccordionPanel>
              <AccordionPanel check title="Address" value={this.state["Address"]}
                handleChange={(e)=>this.handleChange("Address",e)}
                openAccordion={this.state.openAccordion}
                handleOpen={this._handleOpen}>
                <TextInput name="line1" placeholder="line 1" />
                <TextInput name="city" placeholder="city" />
                <TextInput name="state" placeholder="state" />
                <TextInput name="postalCode" placeholder="postal code" />
                <TextInput name="country" placeholder="country" />
              </AccordionPanel>
              <AccordionPanel check title="Agency" value={this.state["Agency"]}
                handleChange={(e)=>this.handleChange("Agency",e)}
                openAccordion={this.state.openAccordion}
                handleOpen={this._handleOpen}>
                <TextInput name="name" placeholder="name" />
                <TextInput name="country" placeholder="country" />
                <TextInput name="identifier" placeholder="identifier" />
                <TextInput name="location" placeholder="location" />
                <TextInput name="type" placeholder="type" />
                <TextInput name="city" placeholder="city" />
              </AccordionPanel>
            </div>
          </form>
        </div>
        <button onClick={this.handleSidebarBtn} type="button" className="btn btn-flex-fix  btn-icon-only btn-primary">
          <i className="fa fa-inverse fa-arrow-left"></i>
          <span className="sr-only">Search</span>
        </button>
      </div>
    )
  }
}
