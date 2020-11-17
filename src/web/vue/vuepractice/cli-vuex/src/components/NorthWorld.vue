<template>
  <div>
    <h1>North world</h1>
    <h3>{{ this.$store.state.message }}</h3>
    <h3>{{ this.$store.state.counter }}</h3>
    <button @click="addCounter">add counter</button>
    <button @click="subCounter">sub counter</button>
  </div>
</template>

<script>
export default {
  methods: {
    addCounter() {
      // this.$store.state.counter++;
      // mutations 호출. commit으로 호출
      // this.$store.commit('addCounter');
      // this.$store.commit('addCounter2', 999); // 파라미터 전달
      this.$store.commit('addCounter3', {
        value: 123,
        arr: ['하이', '헬로우', '안녕하세요'],
      }); // 파라미터 전달
    },
    subCounter() {
      this.$store.commit('subCounter');
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
