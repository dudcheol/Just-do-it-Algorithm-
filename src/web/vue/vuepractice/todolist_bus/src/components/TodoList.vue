<template>
  <div>
    <ul>
      <!-- <li v-for="todoItem in todoItems">{{todoItem}}</li> -->
      <!-- error v-bind:key='todoItem'추가해야함. -->
      <li
        v-for="todoItem in todoItems"
        v-bind:key="todoItem.no"
        class="shadow"
        :class="{ done: todoItem.done == 'y' }"
        @click="viewTodo(todoItem.no)"
      >
        <i
          class="checkBtn fas fa-check"
          @click.stop="completeTodo(todoItem.no)"
          v-show="todoItem.done == 'n'"
        ></i>
        {{ todoItem.content }} : [{{ todoItem.sdate | short }}] ~ [{{
          todoItem.edate | short
        }}]
        <span
          class="removeBtn"
          type="button"
          @click.stop="removeTodo(todoItem.no)"
        >
          <i class="far fa-trash-alt"></i>
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from '../axios-common.js';
import bus from '../eventbus.js';

export default {
  data() {
    return {
      todoItems: [],
      sdate: '',
      edate: '',
    };
  },

  created() {
    this.getTodoList(); // 서버로부터 모든 데이터 받아오는 메소드
    bus.$on('get-todo-list', this.getTodoList); //이벤트 등록.삭제나 등록 후 화면 갱신 요청처리해야해서
  },

  methods: {
    removeTodo(no) {
      axios
        .delete('/todos/' + no)
        .then(() => this.getTodoList())
        .catch((exp) => alert('removeTodo처리에 실패하였습니다. : ' + exp));
    },
    getTodoList() {
      // selectAll
      axios
        .get('/todos')
        .then((response) => (this.todoItems = response.data))
        .catch((exp) => alert('getTodoList처리에 실패하였습니다.' + exp));
    },
    completeTodo(no) {
      // 완료했을 때 처리
      axios
        .put('/todos/' + no)
        .then(() => this.getTodoList())
        .catch((exp) => alert('completeTodo처리에 실패하였습니다. : ' + exp));
    },
    viewTodo(no) {
      axios
        .get('/todos/' + no)
        .then((response) => {
          this.itemAlert(response);
        })
        .catch((exp) => alert('viewTodo처리에 실패하였습니다.' + exp));
    },
    itemAlert(response) {
      this.todo = response.data;
      var msg =
        this.todo.no +
        ' \n' +
        this.todo.content +
        '\n ' +
        this.todo.sdate +
        ' \n ' +
        this.todo.edate +
        ' \n';

      alert(msg);
    },
  },
  filters: {
    short(val) {
      return val.substring(0, 10);
    },
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0;
  text-align: left;
}
li {
  display: flex;
  min-height: 50px;
  height: 50px;
  line-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background: white;
  border-radius: 5px;
}
.checkBtn {
  line-height: 45px;
  color: #62acde;
  margin-right: 5px;
}
.removeBtn {
  margin-left: auto;
  color: #de4343;
}

.list-item {
  display: inline-block;
  margin-right: 10px;
}
.list-move {
  transition: transform 1s;
}
.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter,
.list-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
.done {
  background-color: lightslategray;
}
</style>
