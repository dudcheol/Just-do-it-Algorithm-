import Vue from 'vue';
import Vuex from 'vuex';
import axios from '../axios/axios-common.js';

Vue.use(Vuex); // app 전역에서 vuex 사용 가능하도록 Vue 객체에 등록

// Store 생성
const store = new Vuex.Store({
  state: {
    // data, 여러 컴포넌트에서 공유해서 사용하는 값들
    message: 'hello, Vue!',
    counter: 0,
    harry: {
      num: 1234,
      name: 'harry potter',
      address: 'london',
    },
    arr: [],
    boardlist: [],
  },
  actions: {
    // 메소드 형식. 비동기 통신 내용(백엔드 서버와 통신)
    selectAll: (store) => {
      axios
        .get('/boards')
        .then((res) => {
          store.commit('selectAllM', { boardlist: res.data });
        })
        .catch((err) => {
          alert('selectAll 실패! ' + err);
        });
    },
  },
  mutations: {
    // state를 변경하는 부분. 메소드 형식. 동기 작업(순차적인 작업들). commit으로 호출함
    addCounter(state, payload) {
      // 지정된 파라미터를 사용할 수 있음
      console.log(payload);
      state.counter++;
    },
    addCounter2(state, payload) {
      state.counter = payload;
    },
    addCounter3(state, payload) {
      state.counter = payload.value;
      state.arr = payload.arr;
    },

    selectAllM(state, payload) {
      console.log(payload);
      state.boardlist = payload.boardlist;
    },

    subCounter(state, payload) {
      console.log(payload);
      state.counter--;
    },
  },
});

export default store;
