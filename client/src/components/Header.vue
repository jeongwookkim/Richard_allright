<template>
  <div>
    <div id="nav">
      <img alt="Vue logo" src="../assets/logo.png" />
      <router-link to="/">Home</router-link> |
      <router-link to="/parkingLot">ParkingLot</router-link> |
      <router-link to="/contact">Contact</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "Header"
};
</script>

<style></style>
