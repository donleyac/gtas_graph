import React, {Component} from 'react';
import {Panel} from 'react-bootstrap'
import './accordionPanel.scss';
export class AccordionPanel extends Component {
  render() {
     return (
      <div className ="accordionPanel">
        <Panel expanded={this.props.openAccordion===this.props.title && this.props.value.include}>
          <Panel.Heading>
            {this.props.check
              ?(<input type="checkbox"
                name="include"
                className="checkbox"
                checked={this.props.value.include}
                onChange={this.props.handleChange}
                />)
              :null}
            <Panel.Title><h2 aria-expanded="true" 
                onClick={()=>this.props.handleOpen(this.props.title)}>
              {this.props.title}
              <i className="fa fa-angle-right pull-right"></i>
            </h2></Panel.Title>
          </Panel.Heading>
          <Panel.Body collapsible>
            {React.Children.map(this.props.children, child=>{
              return React.cloneElement(child, 
              {handleChange: this.props.handleChange,
                value: this.props.value[child.props.name]})
            })}
          </Panel.Body>
        </Panel>
      </div>
    );
  }
}
