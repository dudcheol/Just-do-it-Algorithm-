<template>
  <div class="inputBox shadow">
    <!--@keyup.enter="addTodo" -->
    할일입력 :
    <input
      type="text"
      v-model="todo.content"
      @keyup.enter="addTodo"
      placeholder="할일 입력"
    />
    <!-- 기한 : <input type="date" v-model="todo.endDate" placeholder="기한"> -->
    <button @click="addTodo">추가</button>
    <span class="addContainer" @click="addTodo">
      <div class="addBtn fas fa-plus" aria-hidden="true"></div>
    </span>
  </div>
</template>

<script>
import axios from '../axios-common.js';
import bus from '../eventbus.js'; //bus

export default {
  data() {
    return {
      todo: {
        content: '',
        // endDate:'',
        no: 0,
      },
    };
  },
  methods: {
    addTodo() {
      if (this.todo.content.trim() != '') {
        console.log('할일 추가 :: ' + this.todo.content.trim());
        axios
          .post('/todos', {
            content: this.todo.content,
            id: 'dada',
          })
          .then(() => {
            console.log('추가하였습니다.');
            bus.$emit('get-todo-list'); //추가 후 화면에 반영해야 해서
          })
          .catch(() => alert('추가에 실패하였습니다.'));
        this.clear();
      } else {
        console.log('공백입력.');
        this.clear();
      }
    },
    clear() {
      this.todo.content = '';
      // this.todo.endDate = '';
    },
  },
};
</script>

<style scoped>
input:focus {
  outline: none;
}
.inputBox {
  background: white;
  height: 50px;
  line-height: 50px;
  border-radius: 5px;
}
.inputBox input {
  border-style: none;
  font-size: 0.9rem;
}
/* .addContainer {
  float: right;
  background: linear-gradient(to right, #6478FB, #8763FB);
  display: inline-block;
  width: 3rem;
  border-radius: 0 5px 5px 0;
}
.addContainer2 {
  float: right;
  background: linear-gradient(to right, #647811, #527810);
  display: inline-block;
  width: 3rem;
  border-radius: 0 5px 5px 0;
} */
.modifyBtn,
.addBtn {
  color: white;
  vertical-align: middle;
}
</style>

<!--
        modifyTodo(){ ///미완성...
           
            if(this.todo.content.trim() != ''){
                console.log('할일 수정 :: '+this.todo.content.trim());
                http.put('/todolist/todo/'+this.todo.no,{
                        no : this.todo.no,
                        content : this.todo.content,
                        endDate : this.todo.endDate,
                        userId: 'java'
                    })
                    .then(()=> {
                        console.log('수정하였습니다.');
                        bus.$emit('getTodoList');
                    })
                    .catch(()=> alert('수정에 실패하였습니다.'));
                    this.clear();
            }else{
                console.log('공백입력.');
                this.clear();
            }    
        }, -->
