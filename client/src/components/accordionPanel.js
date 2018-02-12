import React, {Component} from 'react';
import {Panel} from 'react-bootstrap'
import './accordionPanel.scss';
export class AccordionPanel extends Component {
  render() {
    return (
      <div className ="accordionPanel">
        <Panel eventKey="1">
          <Panel.Heading>
            {this.props.check
              ?(<input type="checkbox"
                name="titlecheck"
                className="checkbox"
                checked={this.props.value.titlecheck}
                onChange={this.props.handleChange}
                />)
              :null}
            <Panel.Title toggle={this.props.value.titlecheck}><h2 aria-expanded="true">
              {this.props.title}
              <i className="fa fa-angle-right pull-right"></i>
            </h2></Panel.Title>
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
