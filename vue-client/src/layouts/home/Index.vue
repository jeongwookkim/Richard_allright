<template>
  <v-app>
    <home-app-bar />

    <home-view />

    <home-footer />
  </v-app>
</template>

<script>
  export default {
    name: 'HomeLayout',

    components: {
      HomeAppBar: () => import('@/layouts/home/AppBar'),
      HomeFooter: () => import('@/layouts/home/Footer'),
      HomeView: () => import('@/layouts/home/View'),
    },
  }
</script>
