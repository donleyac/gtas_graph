import React, {Component} from 'react';
import {AccordionPanel} from './accordionPanel.js';
import TextInput from './input.js';
export class Sidepanel extends Component {
  constructor(props){
    super(props);
    this.state ={
      "Address": {titlecheck:true},
      "Agency": {titlecheck:true}
    };
    this.handleChange = this.handleChange.bind(this);
  }
  handleChange(key, event){
    const target = event.target;
    const value = target.type==='checkbox'?target.checked:target.value;
    const name = target.name;
    let panel = this.state[key];
    panel[name] = value;
    this.setState({
      [key]: panel
    });
  }
  render() {
    return (
      <div className="cbp-sidebar sidebar after-header">
        <form className="filters">
          <div className="filter-list" role="tablist">
            <h1 className="filter-heading">Filters
              <button onClick={()=>this.props.handleSubmit(this.state)} type="button" className="btn btn-icon-only btn-primary">
                <i className="fa fa-inverse fa-search"></i>
                <span className="sr-only">Search</span>
              </button>
              <a href="" className="filters-clear">Clear Filters</a>
            </h1>
            <AccordionPanel check title="Address" value={this.state["Address"]}
              handleChange={(e)=>this.handleChange("Address",e)}>
              <TextInput name="line1" placeholder="line 1" />
              <TextInput name="city" placeholder="city" />
              <TextInput name="state" placeholder="state" />
              <TextInput name="postalCode" placeholder="postal code" />
              <TextInput name="country" placeholder="country" />
            </AccordionPanel>
            <AccordionPanel check title="Agency" value={this.state["Agency"]}
              handleChange={(e)=>this.handleChange("Agency",e)}>
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

    )
  }
}
