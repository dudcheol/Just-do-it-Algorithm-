<template>
  <div>
      <nav>       
        <todo-top></todo-top>       
      </nav>

      <router-view></router-view>
    </div>
</template>

<script>


import Vue from 'vue';
import VueRouter from 'vue-router' 
import TodoTop from './components/TodoTop.vue'
import TodoDetail from './components/TodoDetail.vue'

Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
      {
        path: '/top',
        component: TodoTop
      },
      {
        path: '/todos/:no',         
        component: TodoDetail
      },    
     
    ],
    mode:'history'
  })

export default {
  components:{  
    TodoTop,
  },
  router,  
}



</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
body {
    text-align: center;
    background-color: #F6F6F8;
  }
  input {
    border-style: groove;
    width: 200px;
  }
  button {
    border-style: groove;
  }
  .shadow {
    box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03)
  }

</style>
