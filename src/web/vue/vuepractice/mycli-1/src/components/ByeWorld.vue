<!-- 화면 template 태그는 vue파일 내에서 1번만 나옴 -->
<template>
    <h1>안녕? 오늘 참 힘들다....</h1>
</template>

<!-- js script 태그는 vue파일에서 1번만 나옴 -->
<script>
export default {
    data() {
        return {
            num:12345,
        }
    },
}
</script>

<!-- css. style태그는 여러 번 나올 수 있음 -->
<style scoped>
h1{
    color:crimson;
    font-size: 30pt;
}
</style>