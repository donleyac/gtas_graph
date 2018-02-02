import React, {Component} from 'react';
import {Panel} from 'react-bootstrap'
import './accordionPanel.css';
export class AccordionPanel extends Component {
  constructor(props, context) {
    super(props, context);
  }
  render() {
    return (
      <div id ="accordionPanel">
        <Panel eventKey="1">
          <Panel.Heading>
            <span className="check-container">
              {this.props.check
                ?(<input type="checkbox"
                  name="titlecheck"
                  checked={this.props.value.titlecheck}
                  onChange={this.props.handleChange}
                  />)
                :null}
              <Panel.Title toggle={this.props.value.titlecheck}><h2 aria-expanded="true">
                {this.props.title}
                <i class="fa fa-angle-right pull-right"></i>
              </h2></Panel.Title>
            </span>
          </Panel.Heading>
          <Panel.Body collapsible>
            {React.Children.map(this.props.children, child=>{
              return React.cloneElement(child, {handleChange: this.props.handleChange,
                                                value: this.props.value[child.name]})
            })}
          </Panel.Body>
        </Panel>
      </div>
    );
  }
}
