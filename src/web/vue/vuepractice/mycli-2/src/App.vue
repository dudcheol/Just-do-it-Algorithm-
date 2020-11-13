<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <img alt="Vue logo" src="./assets/logo.png">
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  
    <!-- compo 사용 -->
    <test-world></test-world>
    <TestWorld></TestWorld>
    <other></other>
  </div>
</template>

<script>
//1.import(IMPORT 하면 반드시 사용해야 함)
// import HelloWorld from './components/HelloWorld.vue'
import TestWorld from './components/TestWorld.vue'
import Other from './components/Other.vue'

export default {
  name: 'App',
  components: {
    // HelloWorld,  
    TestWorld,
    Other
  },
  data:function(){
    return{
      msg:'승수는 어디에...'
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
