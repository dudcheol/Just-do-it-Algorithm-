<template>
  <div>
      <table>
          <tr>
              <td>{{no}}</td>
              <td>{{id}}</td>
              <td>{{content}}</td>
              <td>{{sdate}}</td>
              <td>{{edate}}</td>
              <td>{{done}}</td>
          </tr>
      </table>
    <a href="/">돌아가기</a>
  </div>
</template>

<script>

import axios from "../axios-common.js";//axios


export default {
    data:function(){
       return{
        no:'',
        id:'',
        content:'',
        sdate:'',
        edate:'',
        done:''
       }

    },
    created:function(){
        var no = this.$route.params.no; 
        axios.get('/todos/'+no)
                .then(response => {
                     //this.itemAlert(response);
                    this.no = response.data.no;
                    this.id = response.data.id;
                    this.content = response.data.content;
                    this.sdate = response.data.sdate;
                    this.edate = response.data.edate;
                    this.done = response.data.done;
                })
                .catch(exp => alert('viewTodo처리에 실패하였습니다.'+exp));
    },  

}
</script>

<style>
table {
    border: 1px solid blue; 
}
</style>