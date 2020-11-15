<template>
  <div id="app">
    <todo-header></todo-header>
    <todo-input v-on:sendTodoItem="addTodo"></todo-input>
    <todo-list
      v-bind:propsTodo="todoItems"
      v-on:removeTodo="removeTodo"
    ></todo-list>
  </div>
</template>

<script>
import TodoHeader from './components/TodoHeader.vue';
import TodoInput from './components/TodoInput.vue';
import TodoList from './components/TodoList.vue';

export default {
  name: 'App',
  data() {
    return {
      todoItems: [],
      key: 0,
    };
  },
  created() {
    // todoItems 초기화 : localStorage에 저장된 item들 미리 가져오기
    for (var i = 0; i < localStorage.length; i++) {
      if (localStorage.getItem(i) != null) {
        this.todoItems.push({
          key: this.key,
          value: localStorage.getItem(i),
        });
        this.key++;
      }
    }
  },
  components: {
    TodoHeader,
    TodoInput,
    TodoList,
  },
  methods: {
    addTodo(todo) {
      console.log('잘 도착함! - ' + todo);
      localStorage.setItem(this.key++, todo);
      this.todoItems.push({
        key: this.key,
        value: todo,
      });
    },
    removeTodo(todo) {
      console.log(todo.key + '아이템 삭제');
      this.todoItems.splice(todo.index, 1); // index번째에 있는 아이템부터 1개 삭제
      localStorage.removeItem(todo.key);
    },
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
