// @flow
import React, {Component} from 'react';
import axios from 'axios';
import { InteractiveForceGraph, ForceGraphNode, ForceGraphArrowLink} from 'react-vis-force';
import {Tooltip} from 'react-bootstrap';
import './forceGraph.scss';

type Props = {data:any};
type State = {tooltip: string, data: any}
export default class ForceGraphImpl extends Component<Props, State>{
  constructor(props: any) {
    super(props);
    this.state = {
      tooltip: "",
      // data: this.props.data
      data:{}
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

  //Need to remove complex object clutter from node
  _cleanNode(node:any){
    const removeList = ["passengers", "flights"];
    for(let i=0; i<removeList.length;i++){
      delete node["data"][removeList[i]];
    }
    return node;
  }
  render(){
    const LINK_VAL:number = 10;
    const COLOR_MAP = {
      "flight": "blue",
      "passenger": "red",
      "agency": "green",
      "pnr": "purple",
      "address": "black",
      "creditCard":"yellow"
    };
    const LABEL_MAP = {
      "flight": "flightNumber",
      "passenger": "lastName",
      "agency": "name",
      "pnr": "recordLocator",
      "address": "city",
      "creditCard": "cardType"
    }
  return(
    <span className="flex justify-content-center full-width">
      {this.state.data["nodes"] && this.state.data["links"]
      ?<InteractiveForceGraph
        simulationOptions={{ height: 500, width: 700 }}
        labelAttr="label"
        onSelectNode={(event, node) => this.setState({tooltip:JSON.stringify(node["data"], null, 2)})}
        onDeselectNode={(event, node)=> this.setState({tooltip:""})}
        zoom
        highlightDependencies
        showLabels>
        {this.state.data["nodes"].map((node:any, index: number)=>{
          const oNode = Object.assign({}, this._cleanNode(node));
          oNode["label"] = node["data"][LABEL_MAP[oNode["type"]]];
          //If id not casted to string, links are not visible
          oNode["id"] = oNode["data"]["id"]+"";
          return (<ForceGraphNode key={index+oNode["type"]}
          node={oNode} fill={COLOR_MAP[oNode["type"]]} />)
        })}
        {this.state.data["links"].map((link, index)=>{
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
