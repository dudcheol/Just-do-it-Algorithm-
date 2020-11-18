import Vue from 'vue';
import VueRouter from 'vue-router'
import TodoTop from '../components/TodoTop.vue'
import TodoDetail from '../components/TodoDetail.vue'
import TodoInput from '../components/TodoInput.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'TodoTop',
    component: TodoTop
  },
  {
    path: '/todos/detail/:no',
    name: 'TodoDetail',    
    component: TodoDetail
    
  },
  {
    path: '/todos/input',
    name: 'TodoInput',
    component: TodoInput
  }
]

const router = new VueRouter({
  mode: 'history',
  //base: process.env.BASE_URL,
  routes
})

export default router
