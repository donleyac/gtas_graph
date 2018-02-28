import React, {Component} from 'react';
import { InteractiveForceGraph, ForceGraphNode, ForceGraphArrowLink} from 'react-vis-force';
import {Tooltip} from 'react-bootstrap';
import './forceGraph.scss';
export default class ForceGraphImpl extends Component{
  constructor(props) {
    super(props);
    this.state = {
      tooltip: ""
    };
  }
  //Needed to remove complex object clutter from node
  _cleanNode(node){
    const removeList = ["passengers", "flights", "documents", "phones", "emails"];
    for(let i=0; i<removeList.length;i++){
      delete node["data"][removeList[i]];
    }
    return node;
  }
  render(){
    const LINK_VAL = 10;
    const COLOR_MAP = {
      "flight": "blue",
      "passenger": "red",
      "agency": "green",
      "pnr": "purple",
      "address": "lime",
      "creditCard":"yellow",
      "document": "orange",
      "phone": "aqua",
      "email": "silver"
    };
    const LABEL_MAP = {
      "flight": "flightNumber",
      "passenger": "lastName",
      "agency": "name",
      "pnr": "recordLocator",
      "address": "city",
      "creditCard": "cardType",
      "document": "documentNumber",
      "phone": "number",
      "email": "domain"
    };
  return(
    <span className="flex justify-content-center full-width">
      {this.props.data["nodes"] && this.props.data["links"]
      ?<InteractiveForceGraph
        simulationOptions={{ height: this.props.dimensions[0], width: this.props.dimensions[1] }}
        labelAttr="label"
        onSelectNode={(event, node) => this.setState({tooltip:JSON.stringify(node["data"], null, 2)})}
        onDeselectNode={(event, node)=> this.setState({tooltip:""})}
        zoom
        highlightDependencies
        showLabels>
        {this.props.data["nodes"].map((node, index)=>{
          const oNode = Object.assign({}, this._cleanNode(node));
          oNode["label"] = node["data"][LABEL_MAP[oNode["type"]]];
          //If id not casted to string, links are not visible
          oNode["id"] = oNode["data"]["id"]+"";
          return (<ForceGraphNode key={index+"node"}
          node={oNode} fill={COLOR_MAP[oNode["type"]]} />)
        })}
        {this.props.data["links"].map((link, index)=>{
          let oLink = {};
          //If source/target not casted to string, highlightDependencies doesn't work
          oLink["source"] = link["source"]+"";
          oLink["target"] = link["target"]+"";
          //Required for highlightDependencies to work, line thickness
          oLink["value"] = LINK_VAL;
          return (<ForceGraphArrowLink
            key={index+"link"}
            link={oLink} />)
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
