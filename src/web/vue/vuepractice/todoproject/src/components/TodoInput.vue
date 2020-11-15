<template>
  <div id="app">
    할 일 입력:
    <input type="text" v-model="todoItem" @keyup.enter="addTodo" /><input
      type="button"
      @click="addTodo"
      value="추가"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      todoItem: '', // 사용자가 입력한 값이 들어옴
    };
  },
  methods: {
    addTodo() {
      // 사용자가 입력한 값을 받아와서 로컬 스토리지에 저장
      if (this.todoItem != '') {
        console.log('-------------방출시작-------------');
        this.$emit('sendTodoItem', this.todoItem);
        this.todoItem = '';
        console.log('-------------방출완료-------------');
      }
    },
  },
};
</script>

<style></style>
