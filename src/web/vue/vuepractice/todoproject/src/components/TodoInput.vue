<template>
  <div id="app">
      할 일 입력: <input type="text" v-model="todoItem" @keyup.enter="addTodo"><input type="button" @click="addTodo" value="추가">
  </div>
</template>

<script>
export default {
    data() {
        return {
            todoItem:'', // 사용자가 입력한 값이 들어옴
            num: 0, // 로컬 스토리지에 저장할 때, key로 사용할 값
        }
    },
    methods: {
        addTodo(){
            // 사용자가 입력한 값을 받아와서 로컬 스토리지에 저장
            if(this.todoItem != ''){
                localStorage.setItem(++this.num, this.todoItem);
                this.todoItem = '';
            }
        }
    },
}
</script>

<style>

</style>