<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png" />
    <north-world></north-world>
    <hr />
    <south-world></south-world>
    <hr />
    <board-world></board-world>
  </div>
</template>

<script>
import NorthWorld from './components/NorthWorld.vue';
import SouthWorld from './components/SouthWorld.vue';
import BoardWorld from './components/BoardWorld.vue';

export default {
  name: 'App',
  components: {
    NorthWorld,
    SouthWorld,
    BoardWorld,
  },
};
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
