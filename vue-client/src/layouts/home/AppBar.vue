<template>
  <div>
    <v-app-bar
      id="home-app-bar"
      app
      color="white"
      elevation="1"
      height="80"
    >
      <base-img
        :src="require('@/assets/logo.png')"
        class="mr-3 hidden-xs-only"
        contain
        max-width="130"
        width="100%"
        @click="$router.push({ name : 'Home' })"
      />

      <!-- <base-img
        :src="require('@/assets/zero-logo-light.svg')"
        contain
        max-width="128"
        width="100%"
      /> -->

      <v-spacer />

      <div v-if="!isLoggedIn">
        <v-tabs
          class="hidden-sm-and-down"
          optional
        >
          <v-tab
            v-for="(name, i) in items1"
            :key="i"
            :to="{ name }"
            :exact="name === 'Home'"
            :ripple="false"
            active-class="text--primary"
            class="font-weight-bold"
            min-width="96"
            text
          >
            {{ name }}
          </v-tab>
        </v-tabs>
        <v-app-bar-nav-icon
          class="hidden-md-and-up"
          @click="drawer = !drawer"
        />
      </div>

      <div v-else>
        <v-tabs
          class="hidden-sm-and-down"
          optional
        >
          <v-tab
            v-for="(name, i) in items2"
            :key="i"
            :to="{ name }"
            :exact="name === 'Home'"
            :ripple="false"
            active-class="text--primary"
            class="font-weight-bold"
            min-width="96"
            text
          >
            {{ name }}
          </v-tab>
        </v-tabs>
        <v-app-bar-nav-icon
          class="hidden-md-and-up"
          @click="drawer = !drawer"
        />
      </div>
    </v-app-bar>
    <div v-if="!isLoggedIn">
      <home-drawer
        v-model="drawer"
        :items="items1"
        />
    </div>
    <div v-else>
      <home-drawer
        v-model="drawer"
        :items="items2"
        />
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    name: 'HomeAppBar',

    components: {
      HomeDrawer: () => import('./Drawer'),
    },

    data: () => ({
      drawer: null,
      items1: [
        'Home',
        'ParkingLot',
        'Contact',
        'Login',
      ],
      items2: [
        'Home',
        'ParkingLot',
        'Contact',
        'Mypage',
        'Logout',
      ],
    }),

    computed: {
      ...mapGetters(['isLoggedIn']),
    },
  }
</script>

<style lang="sass">
  #home-app-bar
    .v-tabs-slider
      max-width: 24px
      margin: 0 auto

    .v-tab
      &::before
        display: none
</style>
