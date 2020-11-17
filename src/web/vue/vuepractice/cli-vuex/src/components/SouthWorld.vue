<template>
  <div>
    <h1>South World</h1>
    <h3>{{ $store.state.harry.num }}</h3>
    <h3>{{ this.$store.state.harry.name }}</h3>
    <h3>{{ this.$store.state.harry.address }}</h3>
    <h3>{{ $store.state.counter }}</h3>
    <li v-for="item in $store.state.arr" :key="item">{{ item }}</li>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
