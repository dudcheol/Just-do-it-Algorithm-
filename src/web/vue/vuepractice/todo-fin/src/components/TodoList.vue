<template>
  <div>
    <ul>   <!--  class="shadow"   :class="{done: todoItem.done=='y'}"-->
      <li v-for="(todoItem) in todoItems" v-bind:key="todoItem.no"  @click="viewTodo(todoItem.no)">
            <span class="yet">
              <font-awesome-icon icon="democrat" @click.stop="completeTodo(todoItem.no)" :class="{already: todoItem.done=='y'}"/>
            </span> 

            {{todoItem.no}}.  {{todoItem.content}} :  [{{todoItem.sdate | short}} ~ {{todoItem.edate | short}}]
            <span  class="removeBtn"    type="button"    @click.stop="removeTodo(todoItem.no)">
               <font-awesome-icon icon="trash-alt" />    
            </span>             
      </li> 
    </ul>


    
  </div>
</template>

<script>
import axios from "../axios-common.js";
import bus from '../eventbus.js';


export default {
  data() {
    return {
      todoItems: [],
      sdate:'',
      edate:''
    };
  },

  created() {
    this.getTodoList();
    bus.$on('getTodoList',this.getTodoList);   //이벤트 등록하는  부분.삭제나 등록 후 화면 갱신 요청처리해야해서   
  },

  methods: {
    removeTodo(no) {
      axios.delete('/todos/'+no)
         .then(()=>this.getTodoList())
         .catch(exp => alert('removeTodo처리에 실패하였습니다. : '+exp));

    },
    getTodoList() {
      axios.get('/todos')
         .then(response => this.todoItems = response.data)
         .catch(exp => alert('getTodoList처리에 실패하였습니다.'+exp));
      
    },
    completeTodo(no){
      axios.put('/todos/'+no)
         .then(()=>this.getTodoList())
         .catch(exp => alert('completeTodo처리에 실패하였습니다. : '+exp));
    },
    viewTodo(no){     
      this.$router.push('/todos/detail/'+no);     
    },
    
    itemAlert(response){
                  this.todo = response.data;
                  var msg = this.todo.no +" \n"+
                  this.todo.content +"\n "+
                  this.todo.sdate +" \n "+
                  this.todo.edate +" \n";

                  alert(msg);
    }
  },
  filters:{
    short(val){
      return val.substring(0,10);
    }
  }
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
.done{
  background-color: lightslategray;
}
</style>