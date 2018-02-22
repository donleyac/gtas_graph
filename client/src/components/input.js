import React from 'react';

const TextInput= (props)=>{
  return (<div className="mdl-textfield mdl-js-textfield">
    <input type="text" name={props.name} value={props.value} onChange={props.handleChange}
     className="mdl-textfield__input" placeholder={props.placeholder}/>
  </div>)
}
export default TextInput;
