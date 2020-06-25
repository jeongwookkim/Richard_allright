<template>
  <div id="nav">
    <v-container>
      <v-row no-gutters align="center">
        <v-col cols="auto" xm="8" md="8">
          <img alt="Vue logo" src="../assets/logo.png"/>
        </v-col>
        <v-col cols="auto" xm="4" md="4" class="text-center">
          <router-link to="/">Home</router-link> |
          <router-link to="/parkingLot">ParkingLot</router-link> |
          <router-link to="/contact">Contact</router-link> |
          <router-link to="/profile">Mypage</router-link>
        </v-col>
      </v-row>
    </v-container>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "Header"
};
</script>

<style></style>
