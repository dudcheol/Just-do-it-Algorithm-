import React, { Component } from 'react';

class Subject extends Component {
  // render 함수가 반드시 있어야 함
  // 자바스크립트와 "유사"한 것이지, 같은것이 아님
  render() {
    return (
      <header>
        <h1>{this.props.title}</h1>
        {this.props.sub}
      </header>
    );
  }
}

export default Subject;
