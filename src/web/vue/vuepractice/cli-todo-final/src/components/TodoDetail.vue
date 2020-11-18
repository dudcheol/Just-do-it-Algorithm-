<template>
  <div>
    <header>
        <img alt="Vue logo" src="../assets/logo.png" />    
    </header>
      <h1>Todo Detail</h1>
        <div id="tdiv">
        <table>
        <thead>
            <tr>
            <th scope="col">#</th>
            <th scope="col">ID</th>
            <th scope="col">내 용</th>
            <th scope="col">시작일</th>
            <th scope="col">종료일</th>
            <th scope="col">완료여부</th>
            </tr>
        </thead>
          <tbody>
            <tr>      
                <th>{{todoItem.no}}</th>
                <td>{{todoItem.id}}</td>
                <td>{{todoItem.content}}</td>
                <td>{{todoItem.sdate | short }}</td>
                <td>{{todoItem.edate  | short}}</td>
                <td>{{todoItem.done}}</td>
            </tr>   
         </tbody>
    </table>
    </div>
    <hr>
    <router-link to="/">돌아가기</router-link>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
// import axios from "../axios-common.js";//axios

export default {
  data(){
    return{

    }
  },

  created() {
    this.getTodoItem(this.$route.params.no);
    console.log(this.todoItem.done);
  },

  computed:{
    ...mapGetters({todoItem : 'getTodoItem'})
  },

  methods: {
    ...mapActions(['getTodoItem']),
  },
     
    // data:function(){
    //    return{
    //     no:'',
    //     id:'',
    //     content:'',
    //     sdate:'',
    //     edate:'',
    //     done:''
    //    }

    // },
    // created:function(){
    //     var no = this.$route.params.no; 
    //     axios.get('/'+no)
    //             .then(response => {
    //                  //this.itemAlert(response);
    //                 this.no = response.data.no;
    //                 this.id = response.data.id;
    //                 this.content = response.data.content;
    //                 this.sdate = response.data.sdate;
    //                 this.edate = response.data.edate;
    //                 this.done = response.data.done;
    //             })
    //             .catch(exp => alert('viewTodo처리에 실패하였습니다.'+exp));
    // },  
    // filters:{
    //     short(val){
    //     return val.substring(0,10);
    //     }
    // }
}
</script>

<style scoped>
table{
    width: 1000px;
    margin: 10px auto;
    border:1px solid red;
}
table,th, tr, td {
    border: 1px solid blue; 
    border-collapse: separate;
}
</style>