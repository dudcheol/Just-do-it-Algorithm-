<template>
  <div>
    <ul>
      <li v-for="(todoItem, index) in propsTodo" :key="todoItem.key">
        {{ todoItem.value }}
        <button @click="removeTodo(todoItem.key, index)">삭제</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ['propsTodo'],
  data() {
    return {};
  },
  methods: {
    removeTodo(key, index) {
      console.log(index + '삭제 emit!');
      this.$emit('removeTodo', {
        key,
        index,
      });
    },
  },
};
</script>

<style></style>
