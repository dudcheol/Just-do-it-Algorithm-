<template>
  <div>
    <h1>Board List</h1>
    <table>
      <tr v-for="board in getBoardList" :key="board.num">
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
// getters, Mutations, Actions에 등록해 놓은 메소드 사용하기 위해 import
// mapGetters -> computed 에서 사용
// mapActions -> methods 에서 사용
import {mapGetters} from 'vuex';

export default {
  created() {
    // actions의 selectAll을 호출해 놔야 함. dispatch()로 호출함.
    this.$store.dispatch('selectAll');
  },

  computed: {
    ...mapGetters([
      'getBoardList'
    ]),

    // ...mapGetters({// get:component에서 사용할 이름. 'getBoardList'는 getters안의 이름
    //   get: 'getBoardList',
    // }),

    // 계산형 속성. 메소드 형식. cache기능이 있음.
    boardlist() {
      // return this.$store.state.boardlist;
      return this.$store.getters.getTodoList;
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
