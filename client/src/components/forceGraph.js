import React, {Component} from 'react';
import { InteractiveForceGraph, ForceGraphNode, ForceGraphArrowLink} from 'react-vis-force';
import {Tooltip} from 'react-bootstrap';
export default class ForceGraphImpl extends Component {
  constructor(props) {
    super(props);
    this.state = {tooltip: ""}
  }

render(){
  const LINK_VAL = 10;
  const COLOR_MAP = {
    "flight": "blue",
    "passenger": "red",
  }
  return(
    <span className="flex justify-content-center full-width">
      <InteractiveForceGraph
        simulationOptions={{ height: 500, width: 700 }}
        labelAttr="label"
        onSelectNode={(event, node) => this.setState({tooltip:JSON.stringify(node)})}
        onDeselectNode={(event, node)=> this.setState({tooltip:""})}
        zoom
        highlightDependencies
        showLabels>
        {this.props.data["nodes"].map((node,index)=>{
          return (<ForceGraphNode node={{ id: index+"", label: node["title"], name:node["name"], gender: node["gender"]}} fill={COLOR_MAP[node["label"]]} />)
        })}
        {this.props.data["links"].map(link=>{
          return (<ForceGraphArrowLink
            link={{ source: link["source"], target: link["target"], value: LINK_VAL }} />)
        })}
      </InteractiveForceGraph>
      <Tooltip placement="top" className="in" id="tooltip-bottom">
        {this.state.tooltip}
      </Tooltip>
    </span>
    )
  }
}
