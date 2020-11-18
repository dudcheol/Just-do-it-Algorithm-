import Vue from 'vue';
import Vuex from 'vuex';
import axios from '../axios/axios-common.js';

Vue.use(Vuex); // app 전역에서 vuex 사용 가능하도록 Vue 객체에 등록

// Store 생성
const store = new Vuex.Store({
  state: {
    todoItems: [],
    sdate: '',
    edate: '',
    todoItem: {},
  },
  // 단순히 값을 가져 가는 것이라서 component안의 computed안에서 사용함.
  getters: {
    // 작업이 된 state값을 돌려줌. param 사용 불가.
    getTodoItems(state) {
      return state.todoItems;
    },

    getTodoItem(state) {
      return state.todoItem;
    },
  },
  actions: {
    // 메소드 형식. 비동기 통신 내용(백엔드 서버와 통신)
    getTodoList: (store) => {
      console.log('store.js - actions - getTodoList');
      axios
        .get('/')
        .then((response) => {
          store.commit('selectAll', { todoItems: response.data });
        })
        .catch((exp) => alert('getTodoList 처리에 실패하였습니다.' + exp));
    },

    getTodoItem: (store, payload) => {
      console.log('store.js=' + payload);
      axios
        .get('/' + payload)
        .then((res) => {
          store.commit('selectOne', { todoItem: res.data });
        })
        .catch((exp) => alert('viewTodo처리에 실패하였습니다.' + exp));
    },

    addTodoItem: (store, payload) => {
      console.log(payload.content);
      return axios
        .post('/insert', {
          content: payload.content,
          id: payload.id,
        })
        .then(() => {
          console.log('추가하였습니다.');
          store.dispatch('getTodoList');
        })
        .catch((err) => {
          console.log('추가 실패하였습니다.' + err);
        });
    },

    removeTodoItem: (store, payload) => {
      return axios
        .delete('/delete/' + payload)
        .then(()=>{
          store.dispatch('getTodoList');
        })
        .catch((exp) => {exp});
    },

    completeTodoItem: (store, payload)=>{
      axios.put('/updateDone/'+payload)
         .then(()=>{
           store.dispatch('getTodoList');
         })
         .catch(exp => alert('completeTodo처리에 실패하였습니다. : '+exp));
    },
  },

  mutations: {
    // state를 변경하는 부분. 메소드 형식. 동기 작업(순차적인 작업들). commit으로 호출함
    // 지정된 파라미터를 사용할 수 있음
    selectAll(state, payload) {
      console.log('getTodoList-mutations: ' + payload);
      state.todoItems = payload.todoItems;
    },

    selectOne(state, payload) {
      state.todoItem = payload.todoItem;
    },
  },
});

export default store;
