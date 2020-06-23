<template>
  <div id="nav">
    <v-container>
      <v-row no-gutters align="center">
        <v-col cols="12" sm="8" lg="8">
          <img alt="Vue logo" src="../assets/logo.png"/>
        </v-col>
        <v-col cols="4" sm="4" lg="4" class="text-center">
          <router-link to="/">Home</router-link> |
          <router-link to="/parkingLot">ParkingLot</router-link> |
          <router-link to="/contact">Contact</router-link>
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
