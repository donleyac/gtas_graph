import React, {Component} from 'react';
import TextInput from './input.js';
export class Searchbar extends Component {
  constructor(props, context) {
    super(props, context);
    this.handleChange = this.handleChange.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.state = {
      value: ''
    };
  }
  handleClick(e) {

  }
  handleChange(e) {
    this.setState({ value: e.target.value });
  }
  render() {
    return (
      <div className="form-group">
        <div className="input-group">
          <TextInput value={this.state.value} handleChange={this.handleChange}
            placeholder="Search"/>
          <span className="input-group-btn">
            <button onClick={this.handleClick} className="btn btn-primary">
              <i className="fa fa-search"></i>
            </button>
          </span>
        </div>
      </div>
    )
  }
}
