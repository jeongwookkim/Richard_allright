<template>
  <v-app>
    <Header />

    <v-spacer></v-spacer>

    <v-content> </v-content>
  </v-app>
</template>

<script>
import Header from "./components/Header.vue";

export default {
  name: "App",
  components: {
    Header
  },
  data: () => ({
    //
  })
};
</script>
