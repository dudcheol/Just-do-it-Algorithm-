<template>
  <div>
    <ul>
      <!--  class="shadow"   :class="{done: todoItem.done=='y'}"-->
      <li
        v-for="todoItem in getTodoItems"
        v-bind:key="todoItem.no"
        @click="viewTodo(todoItem.no)"
      >
        <span class="yet">
          <button
            icon="democrat"
            @click.stop="completeTodo(todoItem.no)"
            :class="{ already: todoItem.done == 'y' }"
          >확인</button>
        </span>

        {{ todoItem.no }}. {{ todoItem.content }} : [{{
          todoItem.sdate | short
        }}
        ~ {{ todoItem.edate | short }}]
        <span
          class="removeBtn"
          type="button"
          @click.stop="removeTodo(todoItem.no)"
        >
          <button icon="trash-alt">삭제</button>
        </span>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
// import axios from "../axios-common.js";
// import bus from '../eventbus.js';

export default {
  data() {
    return {
      // todoItems: [],
      // sdate:'',
      // edate:''
    };
  },

  computed: {
    ...mapGetters(['getTodoItems']),
  },

  created() {
    // this.getTodoList();
    // bus.$on('getTodoList',this.getTodoList);   //이벤트 등록하는  부분.삭제나 등록 후 화면 갱신 요청처리해야해서
    console.log('todolist.vue - created');
    this.getTodoList();
  },

  methods: {
    ...mapActions(['getTodoList', 'removeTodoItem', 'completeTodoItem']),

    viewTodo(no) {
      this.$router.push('/todos/detail/' + no);
    },
    removeTodo(no){
      this.removeTodoItem(no).then(()=>{
        console.log('삭제 성공!');
      }).catch((err)=>{
        alert('삭제 실패! ' + err)
      });
    },
    completeTodo(no){
      this.completeTodoItem(no);
    },
    // removeTodo(no) {
    //   axios.delete('/delete/'+no)
    //      .then(()=>this.getTodoList())
    //      .catch(exp => alert('removeTodo처리에 실패하였습니다. : '+exp));
    // },
    // completeTodo(no){
    //   axios.put('/updateDone/'+no)
    //      .then(()=>this.getTodoList())
    //      .catch(exp => alert('completeTodo처리에 실패하였습니다. : '+exp));
    // },
    // itemAlert(response){
    //               this.todo = response.data;
    //               var msg = this.todo.no +" \n"+
    //               this.todo.content +"\n "+
    //               this.todo.sdate +" \n "+
    //               this.todo.edate +" \n";
    //               alert(msg);
    // }
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

.already {
  margin-right: 10px;
  color: #de4343;
}

.yet {
  margin-right: 10px;
  color: blue;
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
