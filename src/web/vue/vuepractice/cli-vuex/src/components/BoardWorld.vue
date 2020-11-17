<template>
  <div>
    <h1>Board List</h1>
    <table>
      <tr v-for="board in boardlist" :key="board.num">
        <td>{{ board.num }}</td>
        <td>{{ board.name }}</td>
        <td>{{ board.wdate }}</td>
        <td>{{ board.title }}</td>
        <td>{{ board.content }}</td>
        <td>{{ board.count }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  created() {
    // actions의 selectAll을 호출해 놔야 함. dispatch()로 호출함.
    this.$store.dispatch('selectAll');
  },

  computed: {
    // 계산형 속성. 메소드 형식. cache기능이 있음.
    boardlist() {
      return this.$store.state.boardlist;
    },
  },
};
</script>

<style>
table,
tr,
td {
  border: 1px solid dodgerblue;
}
</style>
