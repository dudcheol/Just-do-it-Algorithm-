<template>
    <div>
        <todo-header></todo-header>        
        <todo-list></todo-list>
        <todo-footer></todo-footer>
    </div>
</template>

<script>

import TodoList from './TodoList.vue'
import TodoHeader from './TodoHeader.vue'
import TodoFooter from './TodoFooter.vue'

export default {
 
  components: {
    TodoHeader,    
    TodoList,
    TodoFooter,

    
  }
}

</script>

<style>

</style>