import './App.css';
import TOC from './components/TOC';
import Content from './components/Content';
import Subject from './components/Subject';
import { Component } from 'react';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Subject title="WEB" sub="world wide web!"></Subject>
        <Subject title="React"></Subject>
        <TOC></TOC>
        <Content
          title="HTML"
          desc="HTML is HyperText Markup Language."
        ></Content>
      </div>
    );
  }
}

export default App;
