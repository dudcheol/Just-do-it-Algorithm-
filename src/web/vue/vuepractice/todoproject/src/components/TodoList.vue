<template>
  <div>
      <ul>
          <li v-for="(todoItem, index) in todoItems" :key="index">
              {{todoItem}}
              <button></button> <!-- 버튼을 클릭하면 삭제되도록 -->
          </li>
      </ul>
  </div>
</template>

<script>
export default {
    data() {
        return {
            todoItems:[],
        }
    },
    created(){ // todoList.vue 컴포넌트가 생성된 직후에 호출
        this.getTodoList();
    },
    methods: {
        getTodoList(){
            // 로컬 스토리지에서 내용을 가져와서 어딘가에 저장해놓기
            if(localStorage.length > 0){ // 데이터가 한개라도 있으면
                for (let i = 1; i < localStorage.length; i++) {
                    var value = localStorage.getItem(i); // ()안에 key를 주면 value가 나옴
                    this.todoItems.push(value); // 배열에 넣는 작업
                }
            }
        }
    },
}
</script>

<style>

</style>