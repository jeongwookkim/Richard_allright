<template>
  <div>
    <h1>헤더 컴포넌트</h1>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "Header",
};
</script>

<style></style>
