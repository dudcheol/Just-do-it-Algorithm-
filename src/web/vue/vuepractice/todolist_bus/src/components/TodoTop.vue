<template>
    <div>
        <todo-header></todo-header>
        <todo-input></todo-input>
        <todo-list></todo-list>
    </div>
</template>

<script>

import TodoList from './TodoList.vue'
import TodoHeader from './TodoHeader.vue'
import TodoInput from './TodoInput.vue'

export default {
   name: 'Top',
  components: {
    TodoHeader,
    TodoInput,
    TodoList
  }
}

</script>

<style>

</style>