// @flow
import React, {Component} from 'react';
import axios from 'axios';
import { InteractiveForceGraph, ForceGraphNode, ForceGraphArrowLink} from 'react-vis-force';
import {Tooltip} from 'react-bootstrap';

type Props = {};
type State = {tooltip: string, data: any}
export default class ForceGraphImpl extends Component<Props, State>{
  constructor(props: any) {
    super(props);
    this.state = {
      tooltip: "",
      data: {}
    };
  }
  componentWillMount(){
    axios.get('http://localhost:8080/gtas-graph/graph')
    .then(res => {
      console.log(res.data);
      this.setState({data: res.data});
    })
    .catch(err=>console.log(err));
  }
  render(){
    const LINK_VAL = 10;
    const COLOR_MAP = {
      "flight": "blue",
      "passenger": "red",
    }
  console.log("render",this.state.data);
  return(
    <span className="flex justify-content-center full-width">
      {this.state.data["nodes"] && this.state.data["links"]
      ?<InteractiveForceGraph
        simulationOptions={{ height: 500, width: 700 }}
        labelAttr="label"
        onSelectNode={(event, node) => this.setState({tooltip:JSON.stringify(node)})}
        onDeselectNode={(event, node)=> this.setState({tooltip:""})}
        zoom
        highlightDependencies
        showLabels>
        {this.state.data["nodes"].map((node,index)=>{
          return (<ForceGraphNode node={{ id: index+"", label: node["title"], name:node["name"], gender: node["gender"]}} fill={COLOR_MAP[node["label"]]} />)
        })}
        {this.state.data["links"].map(link=>{
          return (<ForceGraphArrowLink
            link={{ source: link["source"], target: link["target"], value: LINK_VAL }} />)
        })}
      </InteractiveForceGraph>
      :<p>No Data</p>}
      <Tooltip placement="top" className="in" id="tooltip-bottom">
        {this.state.tooltip}
      </Tooltip>
    </span>
    )
  }
}
