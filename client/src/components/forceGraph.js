import React from 'react';
import { InteractiveForceGraph, ForceGraphNode, ForceGraphArrowLink } from 'react-vis-force';
const ForceGraphImpl = ()=> (
  <InteractiveForceGraph
    simulationOptions={{ height: 300, width: 300 }}
    labelAttr="label"
    onSelectNode={(node) => console.log(node)}
    highlightDependencies>
    <ForceGraphNode node={{ id: 'first-node', label: 'First node' }} fill="red" />
    <ForceGraphNode node={{ id: 'second-node', label: 'Second node'}} fill="blue"/>
    <ForceGraphNode node={{ id: 'third-node', label: 'Third node'}} fill="blue" />
    <ForceGraphArrowLink link={{ source: "first-node", target: "second-node", value: 10 }} />
    <ForceGraphArrowLink link={{ source: "first-node", target: "third-node", value: 10 }} />
  </InteractiveForceGraph>
);

export default ForceGraphImpl;
